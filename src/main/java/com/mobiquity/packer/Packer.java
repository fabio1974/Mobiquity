package com.mobiquity.packer;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.mobiquity.combinations.AlgApache;
import com.mobiquity.combinations.AlgIterative;
import com.mobiquity.combinations.CombinationAlgorithm;
import com.mobiquity.combinations.AlgGuava;
import com.mobiquity.exception.APIException;
import com.mobiquity.exception.APIExceptionConsumer;
import com.mobiquity.model.Item;
import com.mobiquity.model.Pack;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Packer {

  private Packer() {
  }

  /**
   * Exposed lib method to mount the ideal package based on the rules of weight and max cost.
   * I'm using a strategy pattern to instantiate a different algorithm to calculate combinations.
   * So, any combination algorithm can be injected into the PackMounter
   * @param filePath the absolute path to the input file
   * @return an string as specified at the output file
   * @throws APIException
   */
  public static String pack(String filePath) throws APIException  {

    var file = new File(filePath);
    StringBuilder sb = new StringBuilder();
    CombinationAlgorithm combinationAlgorithm = new AlgIterative();
    //CombinationAlgorithm combinationAlgorithm = new AlgApache();
    //CombinationAlgorithm combinationAlgorithm = new AlgGuava();
    PackMounter mounter = new PackMounter(combinationAlgorithm);

    try (Stream<String> fileLines = Files.lines(file.toPath(), Charset.forName(StandardCharsets.UTF_8.name()))) {

      //iterates over each line of the input file to generate the StringBuilder  output
      fileLines.forEach(throwsAPIExceptionWrapper(packLine -> {
        Pack pack = buildPack(packLine);
        int[] mountedPackIndexes = mounter.mount(pack);
        sb.append(printIndexes(mountedPackIndexes)).append("\n");
      }));

      return sb.toString();

    } catch (Exception ex) {
      throw new APIException("API error:",ex);
    }
  }



  /**
   * This method builds a representation object from each line from input file.
   * Each pack has its weight limit and all available items as in the file.
   * @param packLine -> each string line from input file.
   * @return a object instance of Pack
   * @throws APIException
   */
  private static Pack buildPack(String packLine) throws APIException {
    var packChuncks = packLine.split(":");
    var pack = new Pack();

    //setting weight limit
    pack.setWeighLimit(Double.parseDouble(packChuncks[0]));

    //adding all available items from file
    for (String it : packChuncks[1].trim().split("\\s+")) {
      var itemChuncks = it.replaceAll("[()]", "").split(",");
      Item item = buildItem(itemChuncks);
      pack.addAvailableItem(item);
    }
    return pack;
  }


  /**
   * builds a instance of a Item from a string chunk from the file
   * @param itemChunks
   * @return an instance of Item
   * @throws APIException as a barrier for input file constraints
   */
  private static Item buildItem(String[] itemChunks) throws APIException {
    var item = new Item(
            Integer.parseInt(itemChunks[0]),
            Double.parseDouble(itemChunks[1]),
            Integer.parseInt(itemChunks[2].replace("€", "")));
    if(item.getCost()>100 || item.getWeigh()>100)
      throw new APIException("Cost and weigh must be < 100!");
    return item;
  }


  /**
   * String representation of a line in the output
   * */
  private static String printIndexes(int[] indexes){
    return indexes.length==0? "-" : Arrays.stream(indexes).mapToObj(String::valueOf).collect(Collectors.joining(","));
  }



  /**
   * This method is a workaround to rethrow an APIException through a lambda function. It receives
   * a customized consumer as a parameter. This consumer throws an APIException in its accept method.
   * */
  private static <T> Consumer<T> throwsAPIExceptionWrapper(APIExceptionConsumer<T, APIException> APIExceptionConsumer) {
    return i -> {
      try {
        APIExceptionConsumer.accept(i);
      } catch (Exception ex) {
        throw new RuntimeException(ex);
      }
    };
  }

}

package com.mobiquity.packer;

import com.mobiquity.model.PackBuilder;
import com.mobiquity.observables.*;
import com.mobiquity.exception.APIException;
import com.mobiquity.exception.APIExceptionConsumer;
import com.mobiquity.model.Pack;
import com.mobiquity.observer.ConcreteObserver;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Packer {

  private Packer() {
  }

  /**
   * The application uses a Observable Pattern approach .
   * The observable calculates the items indexes combinations - only indexes.
   * The indexes' combination is passed to observer.
   * The observer checks if the combination is the ideal based on rules of weight and cost
   * After processing all combinations, the observer prints to the output
   * The API is scalable fo any combination algorithm implementation
   * -  To check it, comment CombinationRecursive observable and uncomment the
   * -  CombinationCommonApache and run tests */
  public static String pack(String filePath) throws APIException  {
    var file = new File(filePath);
    var packBuilder = new PackBuilder();

    Observable observable = new CombinationRecursive();
    //Observable observable = new CombinationCommonApache();

    try (Stream<String> fileLines = Files.lines(file.toPath(),Charset.forName(StandardCharsets.UTF_8.name()))) {

      //iterates over each line of the input file
      fileLines.forEach(throwsAPIExceptionWrapper(packLine -> {
        Pack pack = packBuilder.buildPackFromString(packLine);
        observable.setPack(pack);
        createObserver(observable,pack);
      }));

      return SingletonOutput.getInstance().toString();

    } catch (Exception ex) {
      throw new APIException("API error:",ex);
    }
  }

  /**
   * Creates the concrete observer instance, passing the pack to it.
   * This observer is responsible for two things:
   * 1. mount the ideal package based on the rules of weight and max cost
   * 2. print the answer to the output singleton StringBuilder
   * A new concrete observer instance is necessary to clean its state
   * after process each pack.
   * @param observable the observable that sends the notifications for each combination
   * @param pack represents each line of the input file
   */
  private static void createObserver(Observable observable, Pack pack) {
    var concreteObserver = new ConcreteObserver(pack);
    observable.setObserver(concreteObserver);
    observable.runCombinations();
    concreteObserver.printLineToOutput();
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

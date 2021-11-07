package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.Pack;

/**
 * class responsible for parsing a line from the input file to a Pack object with its
 * all available items
 */
public class PackParser {
    /**
     * This method builds a representation object from each line from input file.
     * Each pack has its weight limit and all available items as in the file.
     * @param packLine -> each string line from input file.
     * @return a object instance of Pack
     * @throws APIException as a barrier for input file constraints
     */
    public Pack buildPackFromString(String packLine) throws APIException {
        var packChuncks = packLine.split(":");
        var pack = new Pack();

        //setting weight limit
        pack.setWeighLimit(Double.parseDouble(packChuncks[0]));
        if(pack.getWeighLimit()>=100)
            throw new APIException("Weight limit should be < 100!");

        //adding all available items from the file line
        for (String it : packChuncks[1].trim().split("\\s+")) {
            var itemChuncks = it.replaceAll("[()]", "").split(",");
            Item item = buildItemFromStrings(itemChuncks);
            pack.addAvailableItem(item);
        }
        return pack;
    }


    /**
     * builds a instance of a Item from a string chunk like (1,53.38,€45)
     * @param itemChunks split string chunk
     * @return an instance of Item
     * @throws APIException as a barrier for input file constraints
     */
    Item buildItemFromStrings(String[] itemChunks) throws APIException {
        var item = new Item(
                Integer.parseInt(itemChunks[0]),
                Double.parseDouble(itemChunks[1]),
                Integer.parseInt(itemChunks[2].replace("€", "")));
        if(item.getCost()>100 || item.getWeigh()>100)
            throw new APIException("Cost and weigh must be < 100!");
        return item;
    }
}

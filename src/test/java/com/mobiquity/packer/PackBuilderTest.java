package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackBuilderTest {

    @Test
    void weightLimitMustBeShorterThan100() {
        var packBuilder = new PackParser();
        String line = "181 : (1,53.38,€45) (2,88.62,€18) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        APIException ex = assertThrows(APIException.class, () -> {
            packBuilder.buildPackFromString(line);
        });
        assertEquals("Weight limit should be < 100!", ex.getMessage());
    }

    @Test
    void buildPackOk() {
        var packBuilder = new PackParser();
        String line = "181 : (1,53.38,€45) (2,88.62,€18) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        APIException ex = assertThrows(APIException.class, () -> {
            var pack = packBuilder.buildPackFromString(line);
            assertEquals(pack.getAvailableItems().size(),9);
        });
    }

    @Test
    void costAndWeightMustBeShorterThan100() {
        var packBuilder = new PackParser();
        var chuncks = "(1,53.38,€145)".replaceAll("[()]", "").split(",");
        APIException ex = assertThrows(APIException.class, () -> {
            packBuilder.buildItemFromStrings(chuncks);
        });
        assertEquals("Cost and weigh must be < 100!", ex.getMessage());
    }
}

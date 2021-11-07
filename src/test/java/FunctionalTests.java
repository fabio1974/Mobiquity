import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FunctionalTests {

    @Test
    public void CostAndWeightMustBeUnder100Ok()  {
        var ex = assertThrows(APIException.class, () -> {
            var filePath =  getFilePath("example_input_2");
            Packer.pack(filePath);
        });
        assertEquals("com.mobiquity.exception.APIException: Cost and weigh must be < 100!",
                ex.getMessage());
    }

    @Test
    public void WeightLimitMustBeUnder100Ok()  {
        var ex = assertThrows(APIException.class, () -> {
            var filePath =  getFilePath("example_input_3");
            Packer.pack(filePath);
        });
        assertEquals("com.mobiquity.exception.APIException: Weight limit should be < 100!",
                ex.getMessage());
    }

    @Test
    public void ItemNumberMustBeUnderOrEqual15()  {
        var ex = assertThrows(APIException.class, () -> {
            var filePath =  getFilePath("example_input_4");
            Packer.pack(filePath);
        });
        assertEquals("com.mobiquity.exception.APIException: Pack with more than 15 items!",
                ex.getMessage());
    }


    @Test
    public void functionalExempleTestIsOk() throws APIException {
        String r = "4\n" +
                "-\n" +
                "2,7\n" +
                "8,9\n";
        var filePath = getFilePath("example_input");
        assertEquals(r,Packer.pack(filePath));
    }

    private String getFilePath(String fileName) {
        URL resource = this.getClass().getResource(fileName);
        try {
            assert resource != null;
            return Paths.get(resource.toURI()).toAbsolutePath().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}

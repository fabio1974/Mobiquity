import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class BasicTests {



/*
    @Test
    public void checkingIterativeCombinationAlgorithm() {
        CombinationAlgorithm generator = new AlgIterative();
        var selection = generator.calculateCombinations(5, 2);
        assertEquals(10, selection.size());
    }
*/

/*
    @Test
    public void checkingGuavaCombinationAlgorithm() {
        CombinationAlgorithm generator = new AlgGuava();
        var selection = generator.calculateCombinations(5, 2);
        assertEquals(10, selection.size());
    }
*/

/*
    @Test
    public void checkingApacheCombinationAlgorithm() {
        var generator = new CommonApacheCombinationSubject();
        var selection = generator.calculateCombinations(5, 2);
        assertEquals(10, selection.size());
    }
*/


    @Test
    public void basicReturn() throws APIException {
        String r = "4\n" +
                "-\n" +
                "2,7\n" +
                "8,9\n";
        var filePath =  getFilePath("example_input");
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

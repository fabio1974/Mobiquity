import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        var filePath = "/home/fabiobarros/git/Mobiquity/src/test/resources/example_input";
        assertEquals(r,Packer.pack(filePath));
    }

}

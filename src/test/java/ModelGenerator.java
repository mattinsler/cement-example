import com.mattinsler.guiceymongo.data.generator.GuiceyDataGenerator;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Dec 6, 2010
 * Time: 2:10:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModelGenerator {
    @Test
    public void generate() {
        final GuiceyDataGenerator generator = new GuiceyDataGenerator();
        generator.setSourceDirectory("src/main/java");
        generator.setOutputPackage("com.mattinsler.cement.example.model");

        try {
            generator.generate("src/main/resources/model.data");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}

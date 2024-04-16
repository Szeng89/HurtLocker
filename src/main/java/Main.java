import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Arrays;

public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }


    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();

        String[] items = output.split("##");
//        for (String item : items) {

        try (FileWriter writer = new FileWriter("output2.txt")) {
            writer.write("[\n"); // Start of JSON array

            for (int i = 0; i < items.length; i++) {
                String value = items[i];

                // Add quotes around strings
                if (value instanceof String) {
                    value = "\"" + value + "\"";
                }

                writer.write("  " + value);

                //nextline
                if (i != items.length - 1) {
                    writer.write("\n");
                }
            }

            writer.write("\n]"); // End of JSON array
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

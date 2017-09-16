import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Testing {
    public static void main(String[] args) {

        try {
            File afile = new File("thisisfortesting.txt");

            FileWriter afileWriter = new FileWriter(afile);

            afileWriter.write("Hello World!\n"); //Very simple!

            afileWriter.write("File IO is cool and not scary!");

            afileWriter.close();
        } //If Java doesn't find the file it will create it for us
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter VIN number: ");
        String vinEntry = scanner.nextLine();
        int vinNum = Integer.parseInt(vinEntry);

        System.out.println("Enter current odometer reading: ");
        String odoEntry = scanner.nextLine();
        double odoNum = Double.parseDouble(odoEntry);

        System.out.println("Enter gallons of gas used: ");
        String gasEntry = scanner.nextLine();
        double gasNum = Double.parseDouble(gasEntry);

        System.out.println("Enter odometer reading at last oil change: ");
        String oilOdoEntry = scanner.nextLine();
        double oilOdoNum = Double.parseDouble(oilOdoEntry);

        System.out.println("Enter engine size in L: ");
        String engineEntry = scanner.nextLine();
        double engineNum = Double.parseDouble(engineEntry);

        VehicleInfo newVehicle = new VehicleInfo();




        String filename = "test.json";
        String[] fileContents = getFileContents(filename);



    }


    public static String[] getFileContents (String fileName) {
        File file = new File (fileName);
        try {
            Scanner fileScanner = new Scanner(file);
            List<String> fileContents = new ArrayList<>();
            while (fileScanner.hasNext()) {
                fileContents.add(fileScanner.nextLine());
            }
            return fileContents.toArray(new String[0]); //Converts the list to an array
        } //Since this time we are asking for data back from the file
        //We have to specify what to do if it isn't found
        catch (FileNotFoundException ex) {
            System.out.println("Could not find file *" + fileName + "*");
            ex.printStackTrace();
            return null;
        }
    }
}

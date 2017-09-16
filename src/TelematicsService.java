import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TelematicsService {

    public void report(VehicleInfo newVehicleInfo){
        createdJsonFile(newVehicleInfo);
        findJsonFile(newVehicleInfo);

    }

    public void createdJsonFile(VehicleInfo newVehicleInfo) {
        try {
            File file = new File(newVehicleInfo.getVIN() + ".json");
            FileWriter fileWriter = new FileWriter(file);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(newVehicleInfo);

            fileWriter.write(json);

            fileWriter.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void findJsonFile(VehicleInfo newVehicleInfo) {
        try{
            File file = new File(".");
            for (File f : file.listFiles()) {
                if (f.getName().endsWith(".json")) {
                    System.out.println(f.getName());
                    // Now you have a File object named "f".
                    // You can use this to create a new instance of Scanner

//                    String[] fileContents = ("list.txt");

                    File file = new File (fileName);

                    Scanner fileScanner = new Scanner(f.getName());
                    List<String> fileContents = new ArrayList<>();
                    while (fileScanner.hasNext()) {
                        fileContents.add(fileScanner.nextLine());
                    }
                    return fileContents.toArray(new String[0]);

                    ObjectMapper mapper = new ObjectMapper();
                    VehicleInfo vi = mapper.readValue("1234.json", VehicleInfo.class);
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }



}

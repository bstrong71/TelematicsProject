import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TelematicsService {

    public void report(VehicleInfo newVehicleInfo){
        createdJsonFile(newVehicleInfo);
        findJsonFile();

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

    public void findJsonFile() {
        try{
            File file = new File(".");
            for (File f : file.listFiles()) {
                if (f.getName().endsWith(".json")) {
//                    System.out.println(f.getName());

                    File newFile = new File(f.getName());
                    try {
                        Scanner fileScanner = new Scanner(newFile);
                        List<String> bazzContents = new ArrayList<>();
                        while (fileScanner.hasNext()) {
                            bazzContents.add(fileScanner.nextLine());
                        }
                        ObjectMapper newMapper = new ObjectMapper();
                        VehicleInfo vInfo = newMapper.readValue(newFile, VehicleInfo.class);
                    }
                    catch (FileNotFoundException ex) {
                        System.out.println("Could not find file *" + f.getName() + "*");
                        ex.printStackTrace();
                    }
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }



}

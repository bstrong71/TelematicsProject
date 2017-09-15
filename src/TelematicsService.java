import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TelematicsService {

    public void report(VehicleInfo newVehicleInfo){
        createdJsonFile(newVehicleInfo);


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



}

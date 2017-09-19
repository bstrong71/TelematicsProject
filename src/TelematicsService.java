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
//        findAllJson();
        dashboardHtml(newVehicleInfo);

    }
    // *** create json file with vehicle info ***
    public void createdJsonFile(VehicleInfo newVehicleInfo) {
        try {
            File fileForJson = new File(newVehicleInfo.getVIN() + ".json");
            FileWriter fileWriter = new FileWriter(fileForJson);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(newVehicleInfo);

            fileWriter.write(json);

            fileWriter.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static List<String> getFileContents (String fileName) {
        File file = new File (fileName);
        try {
            Scanner fileScanner = new Scanner(file);
            List<String> fileContents = new ArrayList<>();
            while (fileScanner.hasNext()) {
                fileContents.add(fileScanner.nextLine());
            }
            return fileContents;
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find file *" + fileName + "*");
            ex.printStackTrace();
            return null;
        }
    }

    // *** find all json files and convert back into vehicle info objects
    public List<VehicleInfo> findAllJson() {
        File file = new File(".");
        ArrayList<VehicleInfo> allVehicles = new ArrayList<>();

        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".json")) {
                String vehicleJSON = getFileContents(f.getName()).get(0);
                ObjectMapper mapper = new ObjectMapper();
                try {
                    VehicleInfo vi = mapper.readValue(vehicleJSON, VehicleInfo.class);
                    allVehicles.add(vi);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return allVehicles;
    }

    // *** update dashboard.html file
    public void dashboardHtml(VehicleInfo newVehicleInfo) {
        try {
            String fileName = "dashboard.html";
            File fileForHtml = new File(fileName);
            FileWriter fileWriter = new FileWriter(fileForHtml);

            int totalCars = 0;
            double totalOdo = 0;
            double totalGas = 0;
            double totalOil = 0;
            double totalEng = 0;

            for(VehicleInfo car: findAllJson()) {
                totalCars += 1;
                totalOdo += car.getOdometer();
                totalGas += car.getGasConsumed();
                totalOil += car.getOdoLastOilChg();
                totalEng += car.getEngSizeL();
            }

            double avgOdo = totalOdo/totalCars;
            double avgGas = totalGas/totalCars;
            double avgOil = totalOil/totalCars;
            double avgEng = totalEng/totalCars;

            String preamble = String.format("<html>\n" +
                    "  <title>Vehicle Telematics Dashboard</title>\n" +
                    "  <body>\n" +
                    "    <h1 align=\"center\">Averages for # vehicles</h1>\n" +
                    "    <table align=\"center\">\n" +
                    "        <tr>\n" +
                    "            <th>Odometer (miles) |</th><th>Consumption (gallons) |</th><th>Last Oil Change |</th><th>Engine Size (liters)</th>\n" +
                    "        </tr>\n" +
                    "        <tr>\n" +
                    "            <td align=\"center\">" + "%.1f" + "</td><td align=\"center\">" + "%.1f" + "</td><td align=\"center\">" + "%.1f" + "</td align=\"center\"><td align=\"center\">" + "%.1f" + "</td>\n" +
                    "        </tr>\n" +
                    "    </table>\n" +
                    "    <h1 align=\"center\">History</h1>\n" +
                    "    <table align=\"center\" border=\"1\">\n" +
                    "        <tr>\n" +
                    "            <th>VIN</th><th>Odometer (miles)</th><th>Consumption (gallons)</th><th>Last Oil Change</th><th>Engine Size (liters)</th>\n" +
                    "        </tr>\n", avgOdo, avgGas, avgOil, avgEng);


            String carData="";
            for(VehicleInfo car: findAllJson()) {
                carData += ("        <tr>\n" +
                        "            <td align=\"center\">" + car.getVIN() + "</td><td align=\"center\">" + car.getOdometer() + "</td><td align=\"center\">" + car.getGasConsumed() + "</td><td align=\"center\">" + car.getOdoLastOilChg() + "</td align=\"center\"><td align=\"center\">" + car.getEngSizeL() + "</td>\n" +
                        "        </tr>\n");

            }

            String closing = ("</table>\n" +
                    "  </body>\n" +
                    "</html>\n");

            String htmlString = preamble + carData + closing;

            fileWriter.write(htmlString);

            fileWriter.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
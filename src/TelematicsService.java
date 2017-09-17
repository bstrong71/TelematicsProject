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
    // *** find all json files and convert back into vehicle info objects
    public void findJsonFile() {
        try{
            File file = new File(".");
            for (File f : file.listFiles()) {
                if (f.getName().endsWith(".json")) {


                System.out.println("f.getName: " + f.getName());

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
    // *** update dashboard.html file
    public void dashboardHtml(VehicleInfo newVehicleInfo) {
        try {
            File file = new File(".");
            for (File h : file.listFiles()) {

                if (h.getName().endsWith(".json")){


                    String fileName = newVehicleInfo.getVIN() + ".html";
                    File fileForHtml = new File(fileName);
                    FileWriter fileWriter = new FileWriter(fileForHtml);

                    String htmlString = String.format("<html>\n" +
                            "  <title>Vehicle Telematics Dashboard</title>\n" +
                            "  <body>\n" +
                            "    <h1 align=\"center\">Averages for # vehicles</h1>\n" +
                            "    <table align=\"center\">\n" +
                            "        <tr>\n" +
                            "            <th>Odometer (miles) |</th><th>Consumption (gallons) |</th><th>Last Oil Change |</th><th>Engine Size (liters)</th>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "            <td align=\"center\">#</td><td align=\"center\">#</td><td align=\"center\">#</td align=\"center\"><td align=\"center\">#</td>\n" +
                            "        </tr>\n" +
                            "    </table>\n" +
                            "    <h1 align=\"center\">History</h1>\n" +
                            "    <table align=\"center\" border=\"1\">\n" +
                            "        <tr>\n" +
                            "            <th>VIN</th><th>Odometer (miles)</th><th>Consumption (gallons)</th><th>Last Oil Change</th><th>Engine Size (liters)</th>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "            <td align=\"center\">%d</td><td align=\"center\">%.1f</td><td align=\"center\">%.1f</td><td align=\"center\">%.1f</td align=\"center\"><td align=\"center\">%.1f</td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "            <td align=\"center\">45435</td><td align=\"center\">123</td><td align=\"center\">234</td><td align=\"center\">345</td align=\"center\"><td align=\"center\">4.5</td>\n" +
                            "        </tr>\n" +
                            "    </table>\n" +
                            "  </body>\n" +
                            "</html>\n", newVehicleInfo.getVIN(), newVehicleInfo.getOdometer(), newVehicleInfo.getGasConsumed(), newVehicleInfo.getOdoLastOilChg(), newVehicleInfo.getEngSizeL());

                    fileWriter.write(htmlString);

                    fileWriter.close();
                }
            }

//            String fileName = newVehicleInfo.getVIN() + ".html";
//            File fileForHtml = new File(fileName);
//            FileWriter fileWriter = new FileWriter(fileForHtml);
//
//            String htmlString = String.format("<html>\n" +
//                    "  <title>Vehicle Telematics Dashboard</title>\n" +
//                    "  <body>\n" +
//                    "    <h1 align=\"center\">Averages for # vehicles</h1>\n" +
//                    "    <table align=\"center\">\n" +
//                    "        <tr>\n" +
//                    "            <th>Odometer (miles) |</th><th>Consumption (gallons) |</th><th>Last Oil Change |</th><th>Engine Size (liters)</th>\n" +
//                    "        </tr>\n" +
//                    "        <tr>\n" +
//                    "            <td align=\"center\">#</td><td align=\"center\">#</td><td align=\"center\">#</td align=\"center\"><td align=\"center\">#</td>\n" +
//                    "        </tr>\n" +
//                    "    </table>\n" +
//                    "    <h1 align=\"center\">History</h1>\n" +
//                    "    <table align=\"center\" border=\"1\">\n" +
//                    "        <tr>\n" +
//                    "            <th>VIN</th><th>Odometer (miles)</th><th>Consumption (gallons)</th><th>Last Oil Change</th><th>Engine Size (liters)</th>\n" +
//                    "        </tr>\n" +
//                    "        <tr>\n" +
//                    "            <td align=\"center\">%d</td><td align=\"center\">%.1f</td><td align=\"center\">%.1f</td><td align=\"center\">%.1f</td align=\"center\"><td align=\"center\">%.1f</td>\n" +
//                    "        </tr>\n" +
//                    "        <tr>\n" +
//                    "            <td align=\"center\">45435</td><td align=\"center\">123</td><td align=\"center\">234</td><td align=\"center\">345</td align=\"center\"><td align=\"center\">4.5</td>\n" +
//                    "        </tr>\n" +
//                    "    </table>\n" +
//                    "  </body>\n" +
//                    "</html>\n", newVehicleInfo.getVIN(), newVehicleInfo.getOdometer(), newVehicleInfo.getGasConsumed(), newVehicleInfo.getOdoLastOilChg(), newVehicleInfo.getEngSizeL());
//
//            fileWriter.write(htmlString);
//
//            fileWriter.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}

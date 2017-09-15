import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // *** take inputs from CLI ***
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

        // *** new vehicle info instance ***
        VehicleInfo newVehicleInfo = new VehicleInfo();
            newVehicleInfo.setVIN(vinNum);
            newVehicleInfo.setOdometer(odoNum);
            newVehicleInfo.setGasConsumed(gasNum);
            newVehicleInfo.setOdoLastOilChg(oilOdoNum);
            newVehicleInfo.setEngSizeL(engineNum);

        TelematicsService newTelematicsService = new TelematicsService();

        newTelematicsService.report(newVehicleInfo);
    }



}

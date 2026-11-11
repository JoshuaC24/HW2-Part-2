
package edu.monmouth.hw2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import edu.monmouth.vehicle.Airplane;
import edu.monmouth.vehicle.Boat;
import edu.monmouth.vehicle.BoatException;
import edu.monmouth.vehicle.Car;
import edu.monmouth.vehicle.CarException;
import edu.monmouth.vehicle.PowerTypes;
import edu.monmouth.vehicle.Vehicle;

public class HW2 {

    public static void main(String[] args) {
        initialize(args);

        List<String> strings = new ArrayList<String>();
        List<Vehicle> vehicles = new ArrayList<Vehicle>();

        readStrings(args[HW2Constants.STRINGARG], strings);
        readVehicles(args[HW2Constants.VEHICLEARG], vehicles);

        System.out.println("Strings read: " + strings.size());
        System.out.println("Valid vehicles read: " + vehicles.size());

        exerciseStringList(strings);
        exerciseVehicleList(vehicles);
    }

    
    
    
    
    private static void initialize(String[] args) {

        if (args.length != HW2Constants.REQUIREDARGS) {
            System.err.println(
                "Usage: HW2 <log file> <strings file> <vehicles file>"
            );
            System.exit(HW2Constants.BADARGS);
        }

        try {
            PrintStream redirectStream =
                new PrintStream(args[HW2Constants.LOGARG]);

            System.setOut(redirectStream);
            System.setErr(redirectStream);

        } catch (FileNotFoundException fnfe) {
            System.err.println(
                "Cannot redirect stderr and stdout "
                + fnfe.getMessage()
            );
            fnfe.printStackTrace();
            System.exit(HW2Constants.BADREDIRECT);
        }
    }

    
    
    
    private static void readStrings(
            String fileName, List<String> strings) {

        try (BufferedReader reader =
                new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }

        } catch (FileNotFoundException fnfe) {
            System.err.println(
                "Cannot open " + fileName + ": "
                + fnfe.getMessage()
            );
            System.exit(HW2Constants.BADFILE);

        } catch (IOException ioe) {
            System.err.println(
                "Error reading " + fileName + ": "
                + ioe.getMessage()
            );
            System.exit(HW2Constants.BADFILE);
        }
    }

    
    
    
    private static void readVehicles(
            String fileName, List<Vehicle> vehicles) {

        try (BufferedReader reader =
                new BufferedReader(new FileReader(fileName))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                try {
                    vehicles.add(createVehicle(line));

                } catch (BoatException | CarException ve) {
                    reportBadLine(
                        lineNumber, line, ve.getMessage()
                    );

                } catch (NumberFormatException nfe) {
                    reportBadLine(
                        lineNumber, line,
                        "Size is not an integer: "
                        + nfe.getMessage()
                    );

                } catch (IllegalArgumentException iae) {
                    reportBadLine(
                        lineNumber, line, iae.getMessage()
                    );
                }
            }

        } catch (FileNotFoundException fnfe) {
            System.err.println(
                "Cannot open " + fileName + ": "
                + fnfe.getMessage()
            );
            System.exit(HW2Constants.BADFILE);

        } catch (IOException ioe) {
            System.err.println(
                "Error reading " + fileName + ": "
                + ioe.getMessage()
            );
            System.exit(HW2Constants.BADFILE);
        }
    }

    private static void reportBadLine(
            int lineNumber, String line, String reason) {

        System.err.println(
            "Invalid line " + lineNumber
            + " [" + line + "] discarded: " + reason
        );
    }

    private static Vehicle createVehicle(String line)
            throws BoatException, CarException {

        String[] fields = line.split(
            HW2Constants.SEPARATOR, -1
        );

        if (fields.length != HW2Constants.NUMFIELDS) {
            throw new IllegalArgumentException(
                "Expected " + HW2Constants.NUMFIELDS
                + " fields but found " + fields.length
            );
        }

        PowerTypes power;

        try {
            power = PowerTypes.valueOf(
                fields[HW2Constants.POWERFIELD]
            );

        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(
                "Unknown power type '"
                + fields[HW2Constants.POWERFIELD] + "'"
            );
        }

        int size = Integer.parseInt(
            fields[HW2Constants.SIZEFIELD]
        );

        if (fields[HW2Constants.TYPEFIELD].equals(
                HW2Constants.CARCODE)) {

            return new Car(power, size);

        } else if (fields[HW2Constants.TYPEFIELD].equals(
                HW2Constants.BOATCODE)) {

            return new Boat(power, size);
        }

        throw new IllegalArgumentException(
            "Unknown vehicle type '"
            + fields[HW2Constants.TYPEFIELD] + "'"
        );
    }

    
    private static void exerciseStringList(List<String> strings) {

        System.out.println("\n String list ");

        System.out.println("isEmpty: " + strings.isEmpty());

        int removeIndex = HW2Constants.STRING_REMOVE_INDEX;

        
        
        
        if (strings.size() > removeIndex) {
            System.out.println(
                "remove(" + removeIndex + "): "
                + strings.remove(removeIndex)
            );
        }

        System.out.println("List after remove:");

        for (String s : strings) {
            System.out.println("  " + s);
        }

        strings.add("Hawks");

        System.out.println(
            "After add(\"Hawks\"), size: "
            + strings.size()
        );

        System.out.println("Iterating with an Iterator:");

        Iterator<String> it = strings.iterator();

        while (it.hasNext()) {
            System.out.println("  " + it.next());
        }

        System.out.println("Number of strings: " + strings.size());

        ListIterator<String> listIt = strings.listIterator();

        System.out.println("Forward ListIterator:");

        while (listIt.hasNext()) {
            System.out.println("  " + listIt.next());
        }

        System.out.println("Reverse ListIterator:");

        while (listIt.hasPrevious()) {
            System.out.println("  " + listIt.previous());
        }
    }

    
    
    
    
    private static void exerciseVehicleList(
            List<Vehicle> vehicles) {

        System.out.println("\n Vehicle list ");

        System.out.println("isEmpty: " + vehicles.isEmpty());

        int removeIndex = HW2Constants.VEHICLE_REMOVE_INDEX;

        if (vehicles.size() > removeIndex) {
            System.out.println(
                "remove(" + removeIndex + "): "
                + vehicles.remove(removeIndex)
            );
        }

        System.out.println("List after remove:");

        for (Vehicle v : vehicles) {
            System.out.println("  " + v);
        }

        vehicles.add(new Airplane(PowerTypes.ELECTRICITY));

        System.out.println(
            "After add(Airplane), size: "
            + vehicles.size()
        );

        System.out.println("Iterating:");

        Iterator<Vehicle> it = vehicles.iterator();

        while (it.hasNext()) {
            System.out.println("  " + it.next());
        }

        try {
            Vehicle inList =
                new Car(PowerTypes.GASOLINE, 4);

            Vehicle notInList =
                new Boat(PowerTypes.GASOLINE, 1);

            System.out.println(
                "contains(" + inList + "): "
                + vehicles.contains(inList)
            );

            System.out.println(
                "contains(" + notInList + "): "
                + vehicles.contains(notInList)
            );

        } catch (CarException | BoatException ve) {
            System.err.println(ve.getMessage());
            ve.printStackTrace();
        }

        System.out.println("Number of vehicles: " + vehicles.size());

        ListIterator<Vehicle> listIt = vehicles.listIterator();

        System.out.println("Forward ListIterator:");

        while (listIt.hasNext()) {
            System.out.println("  " + listIt.next());
        }

        System.out.println("Reverse ListIterator:");

        while (listIt.hasPrevious()) {
            System.out.println("  " + listIt.previous());
        }

        System.out.println("Testing remove(Object):");

        try {
            Vehicle vehicleToRemove = null;

            for (Vehicle v : vehicles) {

                if (v instanceof Car) {
                    Car car = (Car) v;

                    vehicleToRemove = new Car(
                        car.getPowerSource(),
                        car.getEngineSize()
                    );
                    break;

                } else if (v instanceof Boat) {
                    Boat boat = (Boat) v;

                    vehicleToRemove = new Boat(
                        boat.getPowerSource(),
                        boat.getLength()
                    );
                    break;
                }
            }

            if (vehicleToRemove != null) {
                System.out.println(
                    "Vehicle to remove: " + vehicleToRemove
                );

                boolean removed =
                    vehicles.remove(vehicleToRemove);

                System.out.println("Vehicle removed: " + removed);
            } else {
                System.out.println("No Car or Boat to remove.");
            }

        } catch (CarException | BoatException ve) {
            System.err.println(ve.getMessage());
            ve.printStackTrace();
        }

        System.out.println("List after remove(Object):");

        
        
        
        for (Vehicle v : vehicles) {
            System.out.println("  " + v);
        }

        System.out.println("Final size: " + vehicles.size());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

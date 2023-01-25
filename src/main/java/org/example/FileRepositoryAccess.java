package org.example;

import org.example.entity.Vehicle;
import org.example.repository.RepositoryAccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileRepositoryAccess implements RepositoryAccess {
    private File fileStorage;

    public FileRepositoryAccess() {
        this.fileStorage = createFile();
    }

    @Override
    public Vehicle registerVehicle(Vehicle vehicle) {
        String stringFromObject = convertVehicleObjectToString(vehicle);
        writeToFile(fileStorage,stringFromObject);
        vehicle.setMessage("Sikeres mentés");
        return vehicle;
    }

    @Override
    public Vehicle getVehicleByRegistrationNumber(String registrationNumber) {
        String objectString = readSpecificLineFromFile(fileStorage, registrationNumber);
        return createVehicleFromString(objectString);
    }

    private File createFile() {
        fileStorage = new File("storage.txt");
        return fileStorage;
    }

    private void writeToFile(File fileStorage, String objectString) {
        try {
            FileWriter writer = new FileWriter(fileStorage);
            writer.write(objectString);
            writer.close();
        } catch (IOException e) {
           throw new RuntimeException();
        }
    }

    private String convertVehicleObjectToString(Vehicle vehicle) {
        StringBuilder builder = new StringBuilder();
        return builder.append(vehicle.getRegistrationNumber())
                .append(";")
                .append(vehicle.getMake())
                .append(";")
                .append(vehicle.getModel())
                .append(";")
                .append(vehicle.getNumberOfSeats())
                .append(";")
                .append(vehicle.getVehicleType())
                .toString();
    }

    private String readSpecificLineFromFile(File fileStorage, String lineStarter) {
        String line;
        String[] stringsInLine;
        try {
            Scanner scanner = new Scanner(fileStorage);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                    stringsInLine = data.split(";");
                    if(stringsInLine[0].equals(lineStarter)) {
                        line = data;
                        return line;
                    }

                else return null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    return null;
    }

    private Vehicle createVehicleFromString(String objectString) {
        Vehicle vehicle = new Vehicle();
        if(objectString != null) {
            String[] objectAttr = objectString.split(";");
            vehicle.setRegistrationNumber(objectAttr[0]);
            vehicle.setMake(objectAttr[1]);
            vehicle.setModel(objectAttr[2]);
            vehicle.setNumberOfSeats(objectAttr[3]);
            vehicle.setVehicleType(objectAttr[4]);
            vehicle.setMessage("Keresett jármű: " + vehicle.getMake() + ", " + vehicle.getModel());
        } else {
            vehicle.setRegistrationNumber(null);
            vehicle.setMake(null);
            vehicle.setModel(null);
            vehicle.setNumberOfSeats(null);
            vehicle.setVehicleType(null);
            vehicle.setMessage("Keresett jármű nincs az adatbázisban");
        }
        return vehicle;
    }
}
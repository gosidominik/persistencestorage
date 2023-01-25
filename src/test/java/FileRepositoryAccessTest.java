import org.example.FileRepositoryAccess;
import org.example.entity.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileRepositoryAccessTest {
    FileRepositoryAccess fileRepositoryAccess = new FileRepositoryAccess();
    Vehicle testVehicle = new Vehicle();



    @Test
    public void registerOneVehicle() {
        setTestVehicle(testVehicle);
        fileRepositoryAccess.registerVehicle(testVehicle);
        Assertions.assertEquals("Renault", fileRepositoryAccess.getVehicleByRegistrationNumber("AA:BC-123").getMake());
    }

    public void setTestVehicle(Vehicle vehicle) {
        this.testVehicle.setRegistrationNumber("AA:BC-123");
        this.testVehicle.setMake("Renault");
        this.testVehicle.setModel("Megane");
        this.testVehicle.setNumberOfSeats("5");
        this.testVehicle.setVehicleType("m1");
    }
}

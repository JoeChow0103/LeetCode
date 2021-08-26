import java.util.HashMap;
import java.util.List;

public class ParkingLotNew {
}

// Enums and Constants
enum VehicleType {
    CAR, TRUCK, ELECTRIC, VAN, MOTORBIKE
}

enum ParkingSpotType {
    HANDICAPPED, COMPACT, LARGE, MOTORBIKE, ELECTRIC
}

enum AccountStatus {
    ACTIVE, BLOCKED, BANNED, COMPROMISED, ARCHIVED, UNKNOWN
}

class Address {
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}

class Person {
    private String name;
    private Address address;
    private String email;
    private String phone;
}

// Account, Admin, and ParkingAttendant

abstract class Account {
    private String userName;
    private String password;
    private AccountStatus status;
    private Person person;

    public boolean resetPassword(String newPassword) throws Exception {
        if (password.equals(newPassword)) throw new Exception("need a different password");
        this.password = newPassword;
        return true;
    };
}

class Admin extends Account {
    public boolean addParkingFloor(ParkingFloor floor) { // TODO
        return false;
    }
    public boolean addParkingSpot(String floorName, ParkingSpot spot) { // TODO
        return false;
    }
    public boolean addParkingDisplayBoard(String floorName, ParkingDisplayBoard displayBoard) {
        return false;
    }
    public boolean addCustomerInfoPanel(String floorName, CustomerInfoPanel infoPanel) {
        return false;
    }
    public boolean addEntrancePanel(EntrancePanel entrancePanel) {
        return false;
    }
    public boolean addExitPanel(ExitPanel exitPanel) {
        return false;
    }
}

class ParkingAttendant extends Account {
    public boolean processTicket(String TicketNumber) {
        return false;
    }
}

// Vehicle:
abstract class Vehicle {
    private String licenseNumber;
    private final VehicleType type;
    private ParkingTicket ticket;

    public Vehicle(VehicleType type) {
        this.type = type;
    }

    public void assignTicket(ParkingTicket ticket) {
        this.ticket = ticket;
    }

    public VehicleType getType() {
        return type;
    };
}

class Car extends Vehicle {
    public Car() {
        super(VehicleType.CAR);
    }
}

class Van extends Vehicle {
    public Van() {
        super(VehicleType.VAN);
    }
}

class Truck extends Vehicle {
    public Truck() {
        super(VehicleType.TRUCK);
    }
}

class Electric extends Vehicle {
    public Electric() {
        super(VehicleType.ELECTRIC);
    }
}

class Motorbike extends Vehicle {
    public Motorbike() {
        super(VehicleType.MOTORBIKE);
    }
}

//ParkingSpot:
abstract class ParkingSpot {
    private String number;
    private boolean free;
    private Vehicle vehicle;
    private final ParkingSpotType type;

    public boolean isFree() {
        return free;
    };

    public ParkingSpot(ParkingSpotType type) {
        this.type = type;
    }

    public boolean assignVehicle(Vehicle vehicle) throws Exception {
        if (this.vehicle != null) throw new Exception("has been occupied");
        this.vehicle = vehicle;
        free = false;
        return true;
    }

    public boolean removeVehicle() {
        this.vehicle = null;
        free = true;
        return true;
    }

    public ParkingSpotType getType() {
        return type;
    };

    public boolean setNumber(String number) throws Exception {
        if (this.number != null) throw new Exception("has been set");
        this.number = number;
        return true;
    }

    public String getNumber() {
        return number;
    };
}

class HandicappedSpot extends ParkingSpot {
    public HandicappedSpot() {
        super(ParkingSpotType.HANDICAPPED);
    }
}

class CompactSpot extends ParkingSpot {
    public CompactSpot() {
        super(ParkingSpotType.COMPACT);
    }
}

class LargeSpot extends ParkingSpot {
    public LargeSpot() {
        super(ParkingSpotType.LARGE);
    }
}

class MotorbikeSpot extends ParkingSpot {
    public MotorbikeSpot() {
        super(ParkingSpotType.MOTORBIKE);
    }
}

class ElectricSpot extends ParkingSpot {
    public ElectricSpot() {
        super(ParkingSpotType.ELECTRIC);
    }
}

// ParkingFloor:
class ParkingFloor {
    private String name;
    private HashMap<String, HandicappedSpot> handicappedSpots;
    private HashMap<String, CompactSpot> compactSpots;
    private HashMap<String, LargeSpot> largeSpots;
    private HashMap<String, MotorbikeSpot> motorbikeSpots;
    private HashMap<String, ElectricSpot> electricSpots;
    private HashMap<String, CustomerInfoPortal> infoPortals;
    private ParkingDisplayBoard displayBoard;

    public ParkingFloor(String name) {
        this.name = name;
    }

    public void addParkingSpot(ParkingSpot spot) {
        switch (spot.getType()) {
            case HANDICAPPED:
                handicappedSpots.put(spot.getNumber(), (HandicappedSpot) spot);
                break;
            case COMPACT:
                compactSpots.put(spot.getNumber(), (CompactSpot) spot);
                break;
            case LARGE:
                largeSpots.put(spot.getNumber(), (LargeSpot) spot);
                break;
            case MOTORBIKE:
                motorbikeSpots.put(spot.getNumber(), (MotorbikeSpot) spot);
                break;
            case ELECTRIC:
                electricSpots.put(spot.getNumber(), (ElectricSpot) spot);
                break;
            default:
                System.out.println("Wrong parking spot type!");
        }
    }

    public void assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot) throws Exception {
        spot.assignVehicle(vehicle);
        switch (spot.getType()) {
            case HANDICAPPED:
                updateDisplayBoardForHandicapped(spot);
                break;
            case COMPACT:
                updateDisplayBoardForCompact(spot);
                break;
            case LARGE:
                updateDisplayBoardForLarge(spot);
                break;
            case MOTORBIKE:
                updateDisplayBoardForMotorbike(spot);
                break;
            case ELECTRIC:
                updateDisplayBoardForElectric(spot);
                break;
            default:
                System.out.println("Wrong parking spot type!");
        }
    }

    private void updateDisplayBoardForHandicapped(ParkingSpot spot) {
        if (this.displayBoard.getHandicappedFreeSpot().getNumber() == spot.getNumber()) {
            // find another free handicapped parking and assign to displayBoard
            for (String key : handicappedSpots.keySet()) {
                if (handicappedSpots.get(key).isFree()) {
                    this.displayBoard.setHandicappedFreeSpot(handicappedSpots.get(key));
                }
            }
            this.displayBoard.showEmptySpotNumber();
        }
    }

    private void updateDisplayBoardForLarge(ParkingSpot spot) {
        if (this.displayBoard.getLargeFreeSpot().getNumber() == spot.getNumber()) {
            // find another free compact parking and assign to displayBoard
            for (String key : largeSpots.keySet()) {
                if (largeSpots.get(key).isFree()) {
                    this.displayBoard.setLargeFreeSpot(largeSpots.get(key));
                }
            }
            this.displayBoard.showEmptySpotNumber();
        }
    }

    private void updateDisplayBoardForMotorbike(ParkingSpot spot) {
        if (this.displayBoard.getMotorbikeFreeSpot().getNumber() == spot.getNumber()) {
            // find another free Motorbike parking and assign to displayBoard
            for (String key : motorbikeSpots.keySet()) {
                if (motorbikeSpots.get(key).isFree()) {
                    this.displayBoard.setMotorbikeFreeSpot(motorbikeSpots.get(key));
                }
            }
            this.displayBoard.showEmptySpotNumber();
        }
    }

    private void updateDisplayBoardForElectric(ParkingSpot spot) {
        if (this.displayBoard.getElectricFreeSpot().getNumber() == spot.getNumber()) {
            // find another free Electric parking and assign to displayBoard
            for (String key : compactSpots.keySet()) {
                if (compactSpots.get(key).isFree()) {
                    this.displayBoard.setElectricFreeSpot(compactSpots.get(key));
                }
            }
            this.displayBoard.showEmptySpotNumber();
        }
    }

    private void updateDisplayBoardForCompact(ParkingSpot spot) {
        if (this.displayBoard.getCompactFreeSpot().getNumber() == spot.getNumber()) {
            // find another free compact parking and assign to displayBoard
            for (String key : compactSpots.keySet()) {
                if (compactSpots.get(key).isFree()) {
                    this.displayBoard.setCompactFreeSpot(compactSpots.get(key));
                }
            }
            this.displayBoard.showEmptySpotNumber();
        }
    }

    public void freeSpot(ParkingSpot spot) {
        int freeHandicappedSpotCount = 0;
        int freeCompactSpotCount = 0;
        int freeLargeSpotCount = 0;
        int freeMotorbikeSpotCount = 0;
        int freeElectricSpotCount = 0;
        spot.removeVehicle();
        switch (spot.getType()) {
            case HANDICAPPED:
                freeHandicappedSpotCount++;
                break;
            case COMPACT:
                freeCompactSpotCount++;
                break;
            case LARGE:
                freeLargeSpotCount++;
                break;
            case MOTORBIKE:
                freeMotorbikeSpotCount++;
                break;
            case ELECTRIC:
                freeElectricSpotCount++;
                break;
            default:
                System.out.println("Wrong parking spot type!");
        }
    }

    public boolean isFull() {
        return false;
    }
}

// ParkingDisplayBoard
class ParkingDisplayBoard {
    private String id;
    private ParkingFloor floor;
    private HandicappedSpot handicappedFreeSpot;
    private CompactSpot compactFreeSpot;
    private LargeSpot largeFreeSpot;
    private MotorbikeSpot motorbikeFreeSpot;
    private ElectricSpot electricFreeSpot;
    
    public ParkingDisplayBoard(ParkingFloor floor) {
        this.floor = floor;
    }

    public void showEmptySpotNumber() {
        String message = "";
        if(handicappedFreeSpot.isFree()){
            message += "Free Handicapped: " + handicappedFreeSpot.getNumber();
        } else {
            message += "Handicapped is full";
        }
        message += System.lineSeparator();

        if(compactFreeSpot.isFree()){
            message += "Free Compact: " + compactFreeSpot.getNumber();
        } else {
            message += "Compact is full";
        }
        message += System.lineSeparator();

        if(largeFreeSpot.isFree()){
            message += "Free Large: " + largeFreeSpot.getNumber();
        } else {
            message += "Large is full";
        }
        message += System.lineSeparator();

        if(motorbikeFreeSpot.isFree()){
            message += "Free Motorbike: " + motorbikeFreeSpot.getNumber();
        } else {
            message += "Motorbike is full";
        }
        message += System.lineSeparator();

        if(electricFreeSpot.isFree()){
            message += "Free Electric: " + electricFreeSpot.getNumber();
        } else {
            message += "Electric is full";
        }

        show(message);
    }

    public void show(String message) {};

    public ParkingSpot getCompactFreeSpot() {
        return compactFreeSpot;
    }

    public void setCompactFreeSpot(CompactSpot compactSpot) {
    }

    public ParkingSpot getElectricFreeSpot() {
        return null;
    }

    public void setElectricFreeSpot(CompactSpot compactSpot) {
    }

    public ParkingSpot getMotorbikeFreeSpot() {
        return null;
    }

    public void setMotorbikeFreeSpot(MotorbikeSpot motorbikeSpot) {
    }

    public ParkingSpot getLargeFreeSpot() {
        return null;
    }

    public void setLargeFreeSpot(LargeSpot largeSpot) {
    }

    public ParkingSpot getHandicappedFreeSpot() {
        return null;
    }

    public void setHandicappedFreeSpot(HandicappedSpot handicappedSpot) {
    }
}

class ParkingLot {
    private String name;
    private List<String> address;
    private double parkingRate;

    private int compactSpotCount;
    private int largeSpotCount;
    private int motorbikeSpotCount;
    private int electricSpotCount;
    private final int maxCompactCount;
    private final int maxLargeCount;
    private final int maxMotorbikeCount;
    private final int maxElectricCount;

    public ParkingLot(int maxCompactCount, int maxLargeCount, int maxMotorbikeCount, int maxElectricCount) {
        this.maxCompactCount = maxCompactCount;
        this.maxLargeCount = maxLargeCount;
        this.maxMotorbikeCount = maxMotorbikeCount;
        this.maxElectricCount = maxElectricCount;
    }

    private HashMap<String, EntrancePanel> entrancePanels;
    private HashMap<String, ExitPanel> exitPanels;
    private HashMap<String, ParkingFloor> parkingFloors;

    // all active parking tickets, identified by their ticketNumber
    private HashMap<String, ParkingTicket> activeTickets;

    // singleton ParkingLot to ensure only one object of ParkingLot in the system,
    // all entrance panels will use this object to create new parking ticket: getNewParkingTicket(),
    // similarly exit panels will also use this object to close parking tickets
    private static ParkingLot parkingLot = null;

    // private constructor to restrict for singleton
//    private ParkingLot() {
//        // 1. initialize variables: read name, address and parkingRate from database
//        // 2. initialize parking floors: read the parking floor map from database,
//        //  this map should tell how many parking spots are there on each floor. This
//        //  should also initialize max spot counts too.
//        // 3. initialize parking spot counts by reading all active tickets from database
//        // 4. initialize entrance and exit panels: read from database
//    }

    // static method to get the singleton instance of StockExchange
    public static ParkingLot getInstance() {
//        if (parkingLot == null) {
//            parkingLot = new ParkingLot();
//        }
        return parkingLot;
    }

    // note that the following method is 'synchronized' to allow multiple entrances
    // panels to issue a new parking ticket without interfering with each other
    public synchronized ParkingTicket getNewParkingTicket(Vehicle vehicle) throws ParkingFullException {
        if (this.isFull(vehicle.getType())) {
            throw new ParkingFullException();
        }
        ParkingTicket ticket = new ParkingTicket();
        vehicle.assignTicket(ticket);
        ticket.saveInDB();
        // if the ticket is successfully saved in the database, we can increment the parking spot count
        this.incrementSpotCount(vehicle.getType());
        this.activeTickets.put(ticket.getTicketNumber(), ticket);
        return ticket;
    }

    public boolean isFull(VehicleType type) {
        // trucks and vans can only be parked in LargeSpot
        if (type == VehicleType.TRUCK || type == VehicleType.VAN) {
            return largeSpotCount >= maxLargeCount;
        }

        // motorbikes can only be parked at motorbike spots
        if (type == VehicleType.MOTORBIKE) {
            return motorbikeSpotCount >= maxMotorbikeCount;
        }

        // cars can be parked at compact or large spots
        if (type == VehicleType.CAR) {
            return (compactSpotCount + largeSpotCount) >= (maxCompactCount + maxLargeCount);
        }

        // electric car can be parked at compact, large or electric spots
        return (compactSpotCount + largeSpotCount + electricSpotCount) >= (maxCompactCount + maxLargeCount
                + maxElectricCount);
    }

    // increment the parking spot count based on the vehicle type
    private boolean incrementSpotCount(VehicleType type) {
        if (type == VehicleType.TRUCK || type == VehicleType.VAN) {
            largeSpotCount++;
        } else if (type == VehicleType.MOTORBIKE) {
            motorbikeSpotCount++;
        } else if (type == VehicleType.CAR) {
            if (compactSpotCount < maxCompactCount) {
                compactSpotCount++;
            } else {
                largeSpotCount++;
            }
        } else { // electric car
            if (electricSpotCount < maxElectricCount) {
                electricSpotCount++;
            } else if (compactSpotCount < maxCompactCount) {
                compactSpotCount++;
            } else {
                largeSpotCount++;
            }
        }
        return true;
    }

    public boolean isFull() {
        for (String key : parkingFloors.keySet()) {
            if (!parkingFloors.get(key).isFull()) {
                return false;
            }
        }
        return true;
    }

    public void addParkingFloor(ParkingFloor floor) {
        /* store in database */ }

    public void addEntrancePanel(EntrancePanel entrancePanel) {
        /* store in database */ }

    public void addExitPanel(ExitPanel exitPanel) {
        /* store in database */ }
}

class ParkingTicket {
    public String getTicketNumber() {
        return "";
    }

    public void saveInDB() {
    }
}
class EntrancePanel {

}
class CustomerInfoPanel{};
class ExitPanel{};
class CustomerInfoPortal{};
class ParkingFullException extends Exception {
}

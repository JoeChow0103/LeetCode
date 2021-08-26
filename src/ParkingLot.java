//public class ParkingLot {
//    /**
//     * step0: clarify your assumption
//     * step1: find nouns and verbs
//     * step2: classes
//     * step3: relationship
//     * step4: for Class, fields & methods
//     * step5: public private → outside
//     * step6: ?
//     *
//     * fields → access modifier, final, static, type, name(self-explain)
//     * methods → constructor(overloading), getter setter(lombok), specific (reuse)
//     * relationship → override vs overloading + abstract class vs interface
//     *
//     * step0:
//     * level? many levels
//     * fee? No
//     * type? Bus, Car, Moto
//     * handicap: No; the relation of type is independent but intersection
//     *
//     * step1:
//     * ParkingLot, Level, ParkingSpot
//     * Bus, Car, Moto
//     *
//     * step2 & step3
//     * ParkingLot, Level, ParkingSpot 包含
//     * Bus, Car, Moto  → Vehicle 继承
//     *
//     * option1: interface
//     * option2: abtract class
//     * option3: class
//     */
//}

/*
BEHAVIOR:
1. Ticket getTicket
2. getParkedVehicleInfo
3. DepartureAndPay

ABSTRACT:
Ticket: id, plate_number, arrival_at , parking location
Vehicle: hourParkingRate, park; List<ParkingSlot>
--Sedan, SUV, Truck
Level: id, total_capacity, available_capacity; List<ParkingSlot>
ParkSlot: id, SlotType(compact, full, super_full)
ParkingLot: id, address, List<Level>
 */

//enum Type {
//    BUS,
//    CAR,
//    MOTO
//}
//
//abstract class Vehicle {
//    protected Type type; // final
//    public Type getType() {
//        return type;
//    }
//}
//
//class Bus extends Vehicle {
//    // fields
//
//    // methods
//    public Bus() {
//        this.type = Type.BUS;
//    }
//}
//
//class Car extends Vehicle {
//    // fields
//
//    // methods
//    public Car() {
//        this.type = Type.CAR;
//    }
//}
//
//class Moto extends Vehicle {
//    // fields
//
//    // methods
//    public Moto() {
//        this.type = Type.MOTO;
//    }
//}
//
//class ParkingSpot {
//    // fields
//    private Vehicle cur; // it's ok for OOD, but for DAO use id
//    private final Type type; // if get value won't change
//
//    // methods
//    public ParkingSpot(Type type) {
//        this.type = type;
//        cur = null;
//    }
//
//    public boolean canPark(Vehicle v) { // read operation
//        // corner case
//        if (v == null) {
//            throw new IllegalArgumentException("Invalid input parameter: canPark()");
//        }
//        return cur == null && this.type == v.getType();
//    }
//    public synchronized boolean goPark(final Vehicle v) { // don't predict users' behavior: valid input | in order
//        // parking war; if see a spot is obsessed then lock it
//        if (canPark(v)) {
//            cur = v;
//            return true;
//        }
//        return false;
//    }
//    public Vehicle leavePark() {
//        // corner case
////        if (cur == null) {
////            throw new Exception("");
////        }
//        Vehicle temp = cur;
//        cur = null;
//        return temp;
//    }
//}
//
//class Level {
//    // fields
//    private final int floor;
//    private final ParkingSpot[] spots; // how to differentiate type and level: the only different is type
//
//    // methods
//    public Level(int floor, int BusCap, int CarCap, int MotoCap) { // involvement relationship need to detail to the end
//        this.floor = floor;
//        spots = new ParkingSpot[BusCap + CarCap + MotoCap];
//        for(int i = 0; i < spots.length; i++) {
//            if (i < BusCap) {
//                spots[i] = new ParkingSpot(Type.BUS);
//            }
//            else if (i >= BusCap && i < BusCap + CarCap) {
//                spots[i] = new ParkingSpot(Type.CAR);
//            }
//            else {
//                spots[i] = new ParkingSpot(Type.MOTO);
//            }
//        }
//    }
//
//    public boolean canPark(Vehicle v) {
//        for (ParkingSpot spot : spots) {
//            if (spot.canPark(v)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean goPark(Vehicle v) {
//        if (!canPark(v)) {
//            return false;
//        }
//        for (ParkingSpot spot: spots) {
//            if (spot.canPark(v)) { // not good way → return spot.goPark(v), no need add singleton, spot.goPark() already has. Atom
//                spot.goPark(v);
//                return true;
//            }
//        }
//        return false; // concurrency or compiler: two cars the same time goPark, one has parked, to the second one,
//        // in the for loop, all spot.canPark(v) is false, then it needs return false in the end.
//    }
//
//    public int numOfAvailableSpot(Vehicle v) { // trade off of readable, time complexity, realization complexity
//        // easy to maintain, reusable, readable
//        // in real, the parking lots have only k thousands, it's a small number for computer
//        // the qps is low since in one second it can be no more than one car to park
//        // then no need to upgrade on level level
//        int count = 0;
//        for (ParkingSpot spot : spots) { // numbers are no more than thousand, is a constant. Can handle quickly.
//            if (spot.canPark(v)) {
//                count++;
//            }
//        }
//        return count;
//    }
//}
//
//class ParkingLot { // reuse all the class above, keep code drive in module
//    // fields
//    private final Level[] levels; // reference cannot point to others, but the element inside can change
//
//    // methods
//    public ParkingLot(int[][] design) { // configuration driven, how to transport the data to inside
//        // wrapper relation, initial the current and the next, no need all
//        int num = design.length;
//        levels = new Level[num];
//        for (int i = 0; i < num; i++) {
//            levels[i] = new Level(i, design[i][0], design[i][1], design[i][2]); // design[][] → BQ
//        }
//    }
//    public boolean canPark(Vehicle v) {
//        for (Level lev : levels) {
//            if (lev.canPark(v)) {
//                return true;
//            }
//        }
//        return false;
//    }
//    public boolean goPark(Vehicle v) {
//        if (!canPark(v)) return false;
//        for (Level lev : levels) {
//            if (lev.canPark(v)) { // bad → return lev.goPark(v);
//                lev.goPark(v);
//                return true;
//            }
//        }
//        return false;
//    }
//}
///*
//Extension: what if you can park car in bus spot,   change the type
//	what if you can park multiple cars in bus spot;    change the type to size
//	if change merge; in level change, because surpass the parking lot
//	fee; add to car, want to all car has method fee, enforce the inheritance to concrete
// */

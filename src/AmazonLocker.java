//public class AmazonLocker {
    /*
    From User Flow to analyze
    BEHAVIOR
    Customer:
    confirm-pickup
    Carrier
    1. scan Pkg
    2. Assign Locker
    3. Sent Notification
    4. CompleteDropOff
    Backend Service:
    1. Send Notification
    2. Generate Code
    3. Bind Code to Locker.

    ABSTRACT:
    User: id, name, contact; List<Package>
    Locker: size(open interface maybe), id, location(zone, i, j), open, Package
      -- FingerPrintLocker, FaceScanLocker, LowLocker
    Drop: PackageId, LockerId, Code, drop-date, pick-date, status
    Package: id; user, Locker, Carrier
    Carrier: id, name, contact; List<Package>
    Notification: To user, Package

    RELATION:
    User -> Package -> Locker
    FingerPrintLocker, FaceScanLocker, LowLocker extend Locker
    Carrier -> Locker
     */

//    abstract class Locker {
//        private int id;
//        private Location loc;
//        priavet Package pkg;
//
//        abstract OpenResponse open(OpenRequest);
//        public boolean isAvailable() {
//            return this.pkg != null;
//        }
//    }
//
//    class FingerFrontLocker extends Locker {
//        ...
//        @Override
//        public OpenResponse open(OpenRequest) {
//
//        }
//    }
//}
import java.util.*;

class AmazonLocker {

    public enum PackageSize {
        SMALL(0),
        MEDIUM(1),
        LARGE(2);

        private int val;

        private PackageSize(int val) {
            this.val = val;
        }

        public int getValue() {
            return this.val;
        }
    }

    public class Package {
        // Size of lockers that can fit the package
        private PackageSize pkgSize;

        // Constructor
        public Package(PackageSize pkgSize) {
            this.pkgSize = pkgSize;
        }

        public PackageSize getPkgSize() {
            return pkgSize;
        }
    }

    public abstract class Locker {
        private final String id;
        private Package currentPkg;
        protected PackageSize size;

        // Constructor
        public Locker(String id) {
            this.id = id;
        }

        // check if the locker is empty
        public boolean isEmpty() {
            return currentPkg == null;
        }

        // put the pkg inside the locker
        public void receivePkg(Package pkg) {
            if (isEmpty()) {
                currentPkg = pkg;
            }
        }

        // remove the pkg from the locker
        public Package removePkg() {
            Package pkg = null;
            if (currentPkg != null) {
                // free the locker
                pkg = currentPkg;
                currentPkg = null;
            }
            return pkg;
        }
    }

    public class SmallLocker extends Locker {
        public SmallLocker(String id) {
            super(id);
            size = PackageSize.SMALL;
        }
    }

    public class MediumLocker extends Locker {
        public MediumLocker(String id) {
            super(id);
            size = PackageSize.MEDIUM;
        }
    }

    public class LargeLocker extends Locker {
        public LargeLocker(String id) {
            super(id);
            size = PackageSize.LARGE;
        }
    }

    public class LockerManager {
        private final int SIZE = 3;
        /**
         * List of lockers, by size
         * lockerSize[0] = small
         * lockerSize[1] = medium
         * lockerSize[2] = large
         */
        List<List<Locker>> lockerSize;

        // Queues for each pkg's size
//        List<List<Package>> pkgQueues;

        // Locker manager machine is given how many small, medium and large lockers it can have
        public LockerManager(int smallLockerCount, int mediumLockerCount, int largeLockerCount) {
            lockerSize = new ArrayList<>(SIZE);
//            pkgQueues = new ArrayList<>(SIZE);

            // Create small lockers
            List<Locker> smallLockers = new ArrayList<>(smallLockerCount);
            for (int i = 0; i < smallLockerCount; i++) {
                smallLockers.add(new SmallLocker("S " + i));
            }
            lockerSize.add(smallLockers);

            // Create medium lockers
            List<Locker> mediumLockers = new ArrayList<>(mediumLockerCount);
            for (int i = 0; i < mediumLockerCount; i++) {
                mediumLockers.add(new MediumLocker("M " + i));
            }
            lockerSize.add(mediumLockers);

            // Create large lockers
            List<Locker> largeLockers = new ArrayList<>(largeLockerCount);
            for (int i = 0; i < largeLockerCount; i++) {
                largeLockers.add(new LargeLocker("L " + i));
            }
            lockerSize.add(largeLockers);
        }

        // Delivery person is using this method to get where to put the package
        public String getPickUpLockerLocation(Package pkg) {
            for (int size = pkg.getPkgSize().getValue(); size < SIZE - 1; size++) {
                List<Locker> lockers = lockerSize.get(size);
                for (Locker locker : lockers) {
                    if (locker.isEmpty()) {
                        return locker.id;
                    }
                }
            }
            return null;
        }

        // Delivery person now sees the locker is open and put the package into the designated location
        public boolean deliverPackage(String pickUpLockerLocation, Package pack) {
            int size = pack.getPkgSize().getValue();
            List<Locker> lockers = lockerSize.get(size);
            for (Locker locker : lockers) {
                if (locker.id.equals(pickUpLockerLocation)) {
                    locker.receivePkg(pack);
                    return true;
                }
            }
            return false;
        }

        // Customer is given a location to pick up the package
        public Package getPackage(String pickUpLockerLocation) {
            String[] ids = pickUpLockerLocation.split(" ");
            int val = -1;
            if (ids[0].equals("S")) val = 0;
            else if (ids[0].equals("M")) val = 1;
            else if (ids[0].equals("L")) val = 2;
            else throw new RuntimeException("Invalid pickup locker location!");

            List<Locker> lockers = lockerSize.get(val);
            for (Locker locker : lockers) {
                if (locker.id.equals(pickUpLockerLocation)) {
                    return locker.removePkg();
                }
            }
            return null;
        }
    }
}

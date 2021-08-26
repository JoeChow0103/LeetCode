import java.util.*;

/*
Producer-consumer pattern: users produce request, elevator consume request
queue store requests, how it works: if up, responds to the level higher than current level,
if down, responds o the level lower than current level. So two queue or two boolean array, one for up, one for down;
every open, check the next step.

If multiple elevators, use the same queue.
Multithreading concurrency

Scheduler Design
Queue: time in order, pros: easy, time average, get O(N); cons: space big
BST: place closed, pros: distance cost small, get O(logN); cons: complex
Array: same direction, pros: average, cons: average
 */

public class Elevator{
    private int currentFloor;
    private int targetFloor;
    private int status;
    private static volatile Elevator instance = null;


    private Elevator() {
        this.currentFloor = 0;
        this.targetFloor = 0;
        this.status = 0;
    }

    public static Elevator getInstance() {
        if (instance == null) {
            synchronized (Elevator.class) {
                if (instance == null) {
                    instance = new Elevator();
                }
            }
        }

        return instance;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }


    public int getStatus() {
        return status;
    }

    public void moveToFloor(int targetFloor) {
        while (currentFloor < targetFloor) {
            moveUp();
        }
        while (currentFloor > targetFloor) {
            moveDown();
        }

        status = 0;
    }

    private void moveUp() {
        status = 1;
        currentFloor += 1;
    }

    private void moveDown() {
        status = -1;
        currentFloor -= 1;
    }
}


//public class RequestHandler {
//    List<Request> requests;
//    private static volatile RequestHandler instance = null;
//
//    public static RequestHandler getInstance() {
//        if (instance == null) {
//            synchronized (RequestHandler.class) {
//                if (instance == null) {
//                    instance = new RequestHandler();
//                }
//            }
//            return instance;
//        }
//
////    private RequestHandler() {
////        requests = new ArrayList<>();
////    }
//
//    public void addRequest(Request req) {
//        synchronized(req) {
//            requests.add(req);
//        }
//    }
//
//    private Requst getNextRequest() {
//        int curentFloor = Elevator.getInstance().getCurrentFloor();
//        int shortestdistance = Integer.MAX_VALUE;
//        Request next = null;
//
//        for (Request req: requests) {
//            if (Math.abs(req.floor - currentFLoor) < shortestdistance) {
//                next = req;
//            }
//        }
//        return next;
//    }
//
//    public void processRequest() {
//        while (true) {
//            Request req = getNextRequest();
//            if (req != null) {
//                while (Elevator.getInstance().getStatus() != 0) {}
//                Elevator.getInstance().moveToFloor(req.getTargetFloor());
//                request.remove(req);
//            }
//        }
//
//    }
//}

//public class Request {
//
//    private int targetFloor;
//
//    Request (int targetFloor) {
//        this.targetFloor = targetFloor;
//    }
//
//    public int getTargetFloor() {
//        return targetFloor;
//    }
//}
//
//
//public class User{
//    public void generateRequset(int targetFloor) {
//        RequestHandler.getInstance().addRequest(new Request(targetFloor));
//    }
//}


/****************************/
//enum Direction {
//    UP,
//    DOWN;
//}

//public class Request {
//    // fields
//    public int curFloor;
//    public Direction dir;
//    public int targetFloor;
//
//    // methods
//    public Request(Direction dir, int targetFloor, int curFloor) {
//
//    }
//}

//public class Car { // if single car, you can do it with singleton
//    // fields
//    public int curFloor;
//    public Direction dir;
//
//    // methods
//    public Car() {
//
//    }
//
//    public void moveUP() {
//
//    }
//
//    public void moveDown() {
//
//    }
//}

//public class Scheduler {
//    // fields
//    Car[] cars;
//    List<Request> upQueue;
//    List<Request[]> downQueue;
//
//    // methods
//    public Scheduler(int carSize) {
//        cars = new Car[carSize];
//        upQueue = new LinkedList<Request>();
//        downQueue = new LinkedList<Request>();
//        for () car = new
//    }
//
//
//    req1: 内部按钮 currentFloor, DIR, targetFloor
//    req2: 外部按钮 currentFloor, DIR, null
//
//    public void accept(Request req) {
//        int floor = req.targetFloor == null ? req.currentFloor : req.targetFloor;
//        if (req.dir == UP) {
//            int index = binarySearch(upQueue, floor);
//            upQueue.add(index, req);
//        } else {
//            int index = binarySearch(downQueue, floor);
//            downQueue.add(index, req);
//        }
//    }
//    init: car at floor 1, going up
//
//    public void handle() {
//
//    }
//}


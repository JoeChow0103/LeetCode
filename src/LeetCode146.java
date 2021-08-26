import java.util.HashMap;

public class LeetCode146 {
}

class DLL { // double linked list
    public int key;
    public int val;
    public DLL prev;
    public DLL next;

    public DLL(int key, int v) {
        this.key = key;
        val = v;
    }

    public void set(int v) {
        val = v;
    }
}

class LRUCache {
    /*
    LRUCache. There is frequency and time order in LRU. When putting a file, the last one has priority than others.
    When getting a file, this file has priority than others too. This means I need to remove a file and
    update the priority. So this reminds of the ddl. I can efficiently insert and remove a node from head or tail.
    I can also efficiently to remove any node from the body of a DDL.
    double linked list. ADV:
    1. remove node without other reference;
    2. constant time to add and remove from head or tail
    3. efficiently to find the prev and next
     */
    private int capacity;
    private int curSize;
    private HashMap<Integer, DLL> map = new HashMap<Integer, DLL>();
    private DLL head;
    private DLL tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        curSize = 0;
        // initial DLL
        head = new DLL(0,0);
        tail = new DLL(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        // once use then put to head
        if (!map.containsKey(key)) return -1;
        DLL node = map.get(key);
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        // once bigger than the capacity, remove the least frequently used one
        if (!map.containsKey(key)) {
            DLL node = new DLL(key, value);
            map.put(key, node);
            if (curSize < capacity) {
                curSize++;
            } else {
                // remove
                DLL lastOne = tail.prev;
                lastOne.prev.next = tail;
                tail.prev = lastOne.prev;
                map.remove(lastOne.key);
            }
            moveToHead(node);
        } else {
            DLL node = map.get(key);
            node.set(value);
            moveToHead(node);
        }
    }

    private void moveToHead(DLL node) {
        // remove
        DLL prev = node.prev;
        DLL next = node.next;
        if (prev != null) prev.next = next;
        if (next != null) next.prev = prev;

        // move to head
        node.next = head.next;
        head.next = node;
        node.prev = head;
        node.next.prev = node;
    }
}
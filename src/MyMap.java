import java.util.ArrayList;
import java.util.List;

public class MyMap<K, V>{
    private static class Cell<K, V>{
        private K key;
        private V val;
        public Cell(K key, V val){
            this.key = key;
            this.val = val;
        }
        //getter setter

        public K getKey() { return this.key; }

        public void setKey(K key) { this.key = key; }

        public V getVal() { return this.val; }

        public void setVal(V val) { this.val = val; }

        @Override
        public int hashCode() {
            return this.key == null ? 0 : this.key.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            //check instance
            if(!(obj instanceof Cell<?, ?>)) return false;

            Cell<K, V> that = (Cell<K, V>) obj;
            //check that null && that.key == null
            return  that  == null
                    ? false
                    : (this.getKey() == null
                    ?
                    that.getKey() == null
                    : this.getKey().equals(that.getKey()));
        }
    }

    private static final double LOAD_FACTOR = 0.75d;

    private int capacity;
    private List<Cell<K, V>>[] buckets;
    private int size = 0;//the number of radish

    //map的两种constructor
    public MyMap(){
        this(256);
    }

    public MyMap(int capacity){
        this.capacity = capacity;
        this.buckets = (List<Cell<K, V>>[])new ArrayList[capacity];
        this.size = 0; //the number of radish
    }

    public int size() {
        return size;
    }

    //put method
    public void put(K key, V val){
        int idx = getBucketIdx(key);
        //小心这个方式，代言人没有权利new: List<Cell> bucket = this.buckets[idx];
        if(this.buckets[idx] == null){
            this.buckets[idx] = new ArrayList<>();
        }
        List<Cell<K, V>> bucket = buckets[idx];
        Cell<K, V> insertMe = new Cell<K, V>(key, val);
        for(Cell cell: bucket){
            if(cell.equals(insertMe)){
                cell.setVal(val);
            }
            return;
        }
        bucket.add(insertMe);
        this.size++;
        //check load balancer
        if(this.size >= this.capacity * LOAD_FACTOR){
            rehashing();
        }
    }
    private int getBucketIdx(K key){
        return key == null ? 0 :
                Math.abs(key.hashCode() % this.capacity);
    }

    //get method:为什么cast V???
    public V get(K key){
        //get bucket
        List<Cell<K, V>> bucket = buckets[getBucketIdx(key)];
        if(bucket == null || bucket.size() == 0) return null;
        Cell<K, V> getMe = new Cell<>(key, null);
        for(Cell cell: bucket){
            if(cell.equals(getMe)) return (V) cell.getVal();
        }
        return null;
    }
    //delete method
    public boolean remove(K key){
        List<Cell<K, V>> bucket = buckets[getBucketIdx(key)];
        if(bucket == null || bucket.size() == 0) return false;
        Cell<K, V> removeMe = new Cell<>(key, null);
        for(int i = 0; i < bucket.size(); i++){
            if(bucket.get(i).equals(removeMe)){
                //swap to the end and delete
                swap(bucket, i, bucket.size() - 1);
                bucket.remove(bucket.size() - 1);
                this.size--;
                return true;
            }
        }
        return false;
    }

    private void swap(List<Cell<K, V>> bucket, int target, int last){
        Cell<K, V> temp = bucket.get(last);
        bucket.set(target, bucket.get(target));
        bucket.set(last, temp);
    }

    //rehashing
    private void rehashing(){
        this.capacity *= 2;
        List<Cell<K, V>>[] newBuckets = new ArrayList[this.capacity];
        for(List<Cell<K, V>> bucket :this.buckets){
            if(bucket == null || bucket.size() == 0) continue;
            for(Cell cell: bucket){
                int idx = getBucketIdx((K) cell.getKey());
                if(newBuckets[idx] == null){
                    newBuckets[idx] = new ArrayList<>();
                }
                newBuckets[idx].add(cell);
            }
        }
        this.buckets = newBuckets;
    }

    public static void main(String[] args){
        System.out.println("Expected OutPut");
        System.out.println("================");
        System.out.println("3, 3, M, 6");
        System.out.println();
        System.out.println("OutPut");
        System.out.println("================");
        MyMap<Integer, Character> map = new MyMap<>(3);
        map.put(1, 'Y');
        System.out.print(map.capacity + ", ");
        map.put(2, 'X');
        System.out.print(map.capacity + ", ");
        //System.out.println(String.valueOf(map.get(2)));
        //System.out.println(1);
        map.put(3, 'M');
        System.out.print(map.get(3) + ", ");
        System.out.print(map.capacity + ", ");
    }

}

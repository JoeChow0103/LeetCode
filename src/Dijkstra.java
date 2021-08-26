public class Dijkstra {
    /*
    one node to any other node; shortest path
    Best First Search; or BFS with various weight
    process:
    从1开始，更新1的邻居的距离，在node上面更新
    从1的最近的邻居2，如果1到邻居的距离比从1到2再到此距离短，更新2的邻居的距离
…
    重复性：已经遍历过的不再遍历，不会往回遍历
    data structure:
    min heap更新最小的，出heap的就是已经visit，进heap的点是边
    heap里面出现了1，2，3，1出去了，不会再对1更新，但是3还在heap中，4，5，6会加入heap，会对heap中和外的点更新，所以 customized heap → myheap(), 有update功能 O(n)
    对于b，也可以用PriorityQueue中的remove() → O(n)
    Start Nodes → Expand Generate: 防止重复遍历HashSet, flag
    注意：有没有可能weight是负数，环，没有联通，有没有direction
     */
     /*
    template:

    public void dijkstra(GraphNode root) {
        // assume connected, undirected graph
        PriorityQueue<GraphNode> pq = new PriorityQueue<>(); 假设带更新
        Set<GraphNode> set = new HashSet<>(); //去重
        pq.offer();
        while (!pq.isEmpty()) {
            // expand poll()
            // find neis generate put into heap
        }

    }

      */

    /*
    class GraphNode {
        int value;
        boolean visited; // false
        int distance; // +inf,不能代替visited，因为会不停的变

        List<Pair> neis; Pair(GraphNode, weight) Hashmap(prefer)

        GraphNode() {

        }
    }

    public void dijkstra(GraphNode start) {
        PriorityQueue<GraphNdoe> minHeap = new PriorityQueue<GraphNode>(k, new Comparator<CraphNode>(){ // k is size; Comparator is interface: 匿名类，通过comparator interface实现compare函数，实际上create一个class
            @Override// lambda expression (c1, c2) -> {c1.distance - c2.distance} java8
            public int compare(TreeNode c1, TreeNode c2) {
                if (c1.value == c2.value) {
                    return 0;
                }
                return c1.value < c2.value ? -1 : 1; // maxHeap: 1: -1
                return c1.distance - c2.distance; //maxHeap: c2 - c; 不能保证int
            }
        })
        // HashSet
    }

    Heap: offer(), poll(), peek()

     */

}

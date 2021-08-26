public class LeetCode785 {
    /*
    color a node with red, color its all neighbors to green.
    if its neighbor already colored and the colors are the same, then not bipartite
    Time: O(V+E), Space: O(V)
     */
    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        for(int i = 0; i < graph.length; i++){
            int color = 0;
            if(colors[i] == 0){
                color = 1;
            }else{
                color = colors[i];
            }
            if(hasCycle(i, color, graph, colors)){
                return false;
            }

        }
        return true;
    }

    private boolean hasCycle(int index, int color, int[][] graph, int[] colors){
        if(colors[index] == -color){
            return true;
        }

        if(colors[index] == color){
            return false;
        }

        colors[index] = color;

        for(int next : graph[index]){
            if(hasCycle(next, -color, graph, colors)){
                return true;
            }
        }
        return false;
    }
}

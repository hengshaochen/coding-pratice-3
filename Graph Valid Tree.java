class Solution {
    public boolean validTree(int n, int[][] edges) {
        // 要是tree的條件有兩個
        
        // 1. 有N個node必須有n - 1個邊
        if (edges.length != n - 1) {
            return false;
        }
        
        // 2. 必須是連通的: 建圖，用HashMap來建立圖，和Adjancey List的概念相同
        // 建完圖後，隨便從一點開始走，如果有連通就代表是樹
        HashMap<Integer, HashSet<Integer>> graph = initGraph(n, edges);
        
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> connect_node = new HashSet<>();
        q.add(0);
        connect_node.add(0);
        
        while (!q.isEmpty()) {
            int cur = q.remove();
            for (Integer neighbor : graph.get(cur)) {
                if (connect_node.contains(neighbor)) {
                    continue;
                }
                
                // 若沒走訪, 標記為走訪過
                connect_node.add(neighbor);
                q.add(neighbor);
            }
        }
        return connect_node.size() == n;
        
    }
    HashMap<Integer, HashSet<Integer>> initGraph(int n, int[][] edges) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<Integer, HashSet<Integer>>();

        // 將點放入graph
        for (int i = 0; i < n ; i++) {
            graph.put(i, new HashSet<Integer>());
        }

        // 將邊放入graph
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v); // ex: node 0 的邊集合加入1
            graph.get(v).add(u); // ex: node 1 的邊集合加入0
        }
        return graph;
    }
}
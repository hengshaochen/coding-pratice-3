// BFS
class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        Queue<Integer> q = new LinkedList<>();
        
        // color[] == 0 代表沒塗色過, 顏色有-1和1
        for (int i = 0; i < graph.length; i++) {
            if (color[i] != 0) {
                // 代表已經塗色過
                continue;
            }
            
            q.add(i);
            int cur_color = -1;
            while (!q.isEmpty()) {
                // assign 1 / -1 level by level
                cur_color *= -1;
                int qsize = q.size();
                for (int j = 0; j < qsize; j++) {
                    int curVertex = q.remove();
                    // 如果當前點已經塗色，檢查是否和鄰居相同，如果相同就return false。
                    // 用trick，如果這層應該塗的顏色和之前塗色的不同，就代表是false
                    if (color[curVertex] != 0 && color[curVertex] != cur_color) {
                        return false;
                    }
                    // 沒塗色的話，就塗上顏色
                    color[curVertex] = cur_color;
                    
                    // 如果鄰居沒塗色，已經塗色的就不用加入，加入鄰居
                    for (int neighbor : graph[curVertex]) {
                        if (color[neighbor] == 0) {
                            q.add(neighbor);
                        }
                    }
                }
            }
        }
        return true;
    }
}

// DFS

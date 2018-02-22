class Solution {
    class Coord {
        int x, y;
        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0) {
            return;
        }
        
        int row = rooms.length;
        int col = rooms[0].length;
        
        Queue<Coord> q = new LinkedList<>();
        // 把所有起點加入到queue
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) {
                    q.add(new Coord(i, j));
                }
            }
        }
        
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        while (!q.isEmpty()) {
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                Coord cur = q.remove();
                for (int j = 0; j < 4; j++) {
                    Coord neighbor = new Coord(cur.x + dx[j], cur.y + dy[j]);
                    if (outOfBound(rooms, neighbor.x, neighbor.y)) {
                        continue;
                    }
                    
                    if (rooms[neighbor.x][neighbor.y] == Integer.MAX_VALUE) {
                        q.add(neighbor);
                        rooms[neighbor.x][neighbor.y] = rooms[cur.x][cur.y] + 1;
                    }
                }
            }
        }
    }
    
    public boolean outOfBound(int[][] rooms, int x, int y) {
        if (x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length) {
            return true;
        }
        return false;
    }
}
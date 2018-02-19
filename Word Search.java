// 在這題DFS比BFS好，因為空間複雜度較小，空間複雜度為word的長度。而BFS會一直膨脹空間（queue空間較大）
// Time, Space參考花花
class Solution {
    public boolean exist(char[][] board, String word) {
        if (board.length == 0) {
            return false;
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(i, j, board, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean dfs(int i, int j, char[][] board, String word, int cur_length) {
        // 三種base case: 1.超出邊界 2.和要找的char不匹配 --> return false  /  3.找到答案 --> return true
        if (outOfBoundary(i, j, board) || word.charAt(cur_length) != board[i][j]) {
            return false;
        }
        if (cur_length == word.length() - 1) {
            return true;
        }
        
        // 走過的點不能重複走，用小技巧把它變成0，之後backtrack在回朔回原本的char
        char origin_char = board[i][j];
        board[i][j] = '0';
        boolean found = dfs(i, j + 1, board, word, cur_length + 1)
                        || dfs(i - 1, j, board, word, cur_length + 1)
                        || dfs(i, j - 1, board, word, cur_length + 1)
                        || dfs(i + 1, j, board, word, cur_length + 1);
        board[i][j] = origin_char;  // 回朔
        
        return found;
    }
    
    public boolean outOfBoundary(int x, int y, char[][] board) {
        return x < 0 || x >= board.length || y < 0 || y >= board[0].length;
    }
}



// BFS BUGGGGGGclass Solution {
    class Coord {
        int x, y;
        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public boolean exist(char[][] board, String word) {
        // 思路：很像number of island。就是找到該段的起頭字母，然後做BFS。BFS走相鄰的上下左右鄰居，
        // 然後如果鄰居的char和word的下個char相同，則走那條路，其他條路不需要走。（終止條件：當cur的string長度跟word相同即可）
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (word.charAt(0) == board[i][j]) {
                    Coord startPoint = new Coord(i, j);
                    if (bfs(board, word, new StringBuilder(), startPoint)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean bfs(char[][] board, String word, StringBuilder sb, Coord startPoint) {
        Queue<Coord> q = new LinkedList<>();
        
        q.add(startPoint);
        int cur_length = 1;
        // corner case: 若word長度剛好為1，直接return true，不然後面會超過string的長度
        if (cur_length == word.length()) {
            return true;
        }
        
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        
        // 判斷是否走訪過
        boolean[][] visited = new boolean[board.length][board[0].length];
        visited[startPoint.x][startPoint.y] = true;
        
        while (!q.isEmpty()) {
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                Coord cur = q.remove();
                boolean found = false;
                for (int j = 0; j < 4; j++) {
                    // 走訪四個鄰居: 出界 或 走訪過 就不能再走訪
                    Coord neighbor = new Coord(cur.x + dx[j], cur.y + dy[j]);
                    if (outOfBoundary(neighbor.x, neighbor.y, board) || 
                        visited[neighbor.x][neighbor.y] == true)  {
                        continue;
                    }
                    
                    // 終止條件
                    if (cur_length == word.length()) {
                        return true;
                    }
                    
                    if (board[neighbor.x][neighbor.y] == word.charAt(cur_length)) {
                        /*System.out.println("------" );
                        System.out.println(cur.x + " " + cur.y);
                        System.out.println(board[neighbor.x][neighbor.y]);*/
                        q.add(neighbor);
                        found = true;
                        visited[neighbor.x][neighbor.y] = true;
                    }
                    
                }
                if (found == true) {
                    cur_length++;
                }
            }
        }
        // 如果都沒有辦法達到終止條件，代表找不到。return false
        return false;
    }
    
    public boolean outOfBoundary(int x, int y, char[][] board) {
        return x < 0 || x >= board.length || y < 0 || y >= board[0].length;
    }
}
class Solution {
    public boolean validTicTacToe(String[] board) {
        // X和O的數量要對
        // diagonal不能有贏的條件
        int countX = 0, countY = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == 'X') {
                    countX++;
                } else if (board[i].charAt(j) == 'O') {
                    countY++;
                }
            }
        }
        
        // X可能比O多1, 也可能和O一樣
        if (countX == countY || countX == countY + 1) {
            // 驗證是否有贏
            if (checkWin(board, 'X')) {
                // X贏的話，O要輸，而且O會比X少一個
                if (checkWin(board, 'O')) {
                    return false;
                }
                return countX == countY + 1;
            } else if (checkWin(board, 'O')) {
                // O贏的話，X和O數量必須一樣，且X要輸
                if (checkWin(board, 'X')) {
                    return false;
                }
                return countX == countY;
            }
        } else {
            return false;
        }
        return true;
    }
    
    boolean checkWin(String[] board, char player) {
        // 水平，垂直各三條
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == player && board[i].charAt(1) == player && board[i].charAt(2) == player) {
                return true;
            }
            if (board[0].charAt(i) == player && board[1].charAt(i) == player && board[2].charAt(i) == player) {
                return true;
            }
        }
        
        // diagonal兩條
        if (board[0].charAt(0) == player && board[1].charAt(1) == player && board[2].charAt(2) == player) {
            return true;
        }
        if (board[0].charAt(2) == player && board[1].charAt(1) == player && board[2].charAt(0) == player) {
            return true;
        }
        return false;
    }
}
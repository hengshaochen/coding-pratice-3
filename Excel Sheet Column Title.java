class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            // 要-1是因為A不是從0開始，是從1開始。例如1 % 26 = 1, 1 + 41 = 42的ascii是B, 42 - 1 = 41 ASCII才是對應到A才正確！
            // 如果不-1，例如input = 1 答案是A，會變成1 % 26 = 1, 1 + 41 = 42, 42的ascii是B就錯了，所以要-1
            n--;
            sb.append( (char)(n % 26 + 'A') ); 
            n /= 26;
        }
        // 反轉，我們計算的時候是先算最低位。
        return sb.reverse().toString();
    }
}
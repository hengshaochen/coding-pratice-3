// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        // cutting a rod
        /*
        length   | 1   2   3   4   5   6   7   8  
        --------------------------------------------
        price    | 1   5   8   9  10  17  17  20
        */
        int[] price = {0, 1, 5, 8, 9, 10, 17, 17, 20};
        System.out.println(cut_a_rod(price, 8, new int[price.length]));
    }
    
    public int cut_a_rod(int[] price, int cur_len, int[] dp) {
        // base case
        if (cur_len == 1) {
            return price[1];
        }
        if (dp[cur_len] != 0) {
            return dp[cur_len];
        }
        
        int ans = 0;
        for (int i = 1; i <= cur_len; i++) {
            ans = Math.max(cut_a_rod(price, cur_len - i, dp) + price[i], ans);
        }
        dp[cur_len] = ans;
        return dp[cur_len];
    }
}
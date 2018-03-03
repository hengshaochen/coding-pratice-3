// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        System.out.println(arrangeMaxNumber(1234));
    }
    
    public int arrangeMaxNumber(int num) {
        if (num < 0 || num > Integer.MAX_VALUE) {
            // Corner case, not integer or have negative.
            return -1;
        }
        
        // use a bucket to stored the repeat time of each digit
        int[] bucket = new int[10];
        // input integer number to char, help us to divide the digit
        for (char cur : (num + "").toCharArray()) {
            bucket[cur - '0'] += 1;  // convert char to integer
        }
        
        // Greedy algorithm: from large to small, continue build the MAXIMUM number
        // StringBuilder sb = 
        int ans = 0;
        for (int i = 9; i >= 1; i--) {
            if (bucket[i] != 0) {
                ans = (ans * 10) + i;
                bucket[i] -= 1;
            }
        }
        return ans;
    }
}
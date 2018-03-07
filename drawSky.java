// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    // Thought: two adjacent skyline, if next is higher than previous one, that means we need paint next one for (next - previous) times. If next is shorter than previous, that means the next one already been painted.
    // Algo: Compare the previous skyline and current skyline, if the current skyline is higher than the previous one, then we need paint (current_height - previous_height) times.
    // Time Complexity: O(N) , Space Complexity: O(1)
    public int solution(int[] A) {
        final int MAX_STROKES_TIME = 1000000000;
        // Conrer case
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int count = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                count += (A[i] - A[i - 1]);
            }
        }
        
        if (count > MAX_STROKES_TIME) {
            return -1;
        }
        
        return count;
    }
}
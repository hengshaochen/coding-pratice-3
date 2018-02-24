// O(n^2)
class Solution {
    public boolean increasingTriplet(int[] nums) {
        /*
        1, 3, 2, 4, 8
        1
        1  2
        1  2  2 <-- 這個2是從dp[0]那個1+1來的
        
        
        思路，把dp[i]定義為在i之前有幾個連續上漲的數字, 譬如dp[3], 就是在index 0 ~ 2有幾個數字比nums[3]小
        遍歷數組，然後只要dp[i] >= 3就是有答案
        
            時間O(n^2)
        */
        
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[i] >= 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

// 最優解：時間O(n), 空間O(1)
class Solution {
    public boolean increasingTriplet(int[] nums) {
        // 設一個min, second_min
        // 如果當前數字可以更新min或second_min代表一定不是答案, 若不能更新代表當前數字一定和min和second_min可以組成答案了！
        // Time: O(n), Space: O(1)
        int min = Integer.MAX_VALUE;
        int second_min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= min) {
                min = nums[i];
            } else if (nums[i] <= second_min) {
                
                second_min = nums[i];
            } else {
                // 這種case代表不能更新min及second_min, 代表這個數字一定是比min, second_min都大
                return true;
            }
            // System.out.println(min + " " + second_min);
        }
        return false;
        /*
        5 4 3 2 1
        5 4
        1,5,2,8,4
        */
    }
}

// 時間O(n), 空間O(n)
class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length < 3 ) {
            return false;
        }
        
        // 紀錄最小值
        int n = nums.length;
        int[] forward = new int[n];
        int[] backward = new int[n];
        
        // forward從前面往後紀錄當前最小值
        forward[0] = nums[0];
        for (int i = 1; i < n; i++) {
            forward[i] = Math.min(forward[i - 1], nums[i]);
        }
        
        // backward從後面往前紀錄當前最大值
        backward[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            backward[i] = Math.max(backward[i + 1], nums[i]);
        }
        
        for (int i = 1; i <= n - 2; i++) {
            if (forward[i] < nums[i] && nums[i] < backward[i]) {
                return true;
            }
        }
        return false;
    }
}
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            // cur_last_index 紀錄當前最後一個數的index
            ans = Math.max(ans, LIS(nums, i, dp));
        }
        return ans;
    }
    
    public int LIS(int[] nums, int cur_last_index, int[] dp) {
        // 代表已經沒有元素剩下自己，回傳自己就是1
        if (cur_last_index == 0) {
            return 1;
        }
        
        // 代表這個子問題已經解過
        if (dp[cur_last_index] != 0) {
            return dp[cur_last_index];
        }
        
        int ans = 1;
        for (int i = 0; i < cur_last_index; i++) {
            // 只需要解比當前最尾端 小的元素的子問題即可
            if (nums[cur_last_index] > nums[i]) {
                ans = Math.max(ans, LIS(nums, i, dp) + 1);
            }
        }
        dp[cur_last_index] = ans;
        return dp[cur_last_index];
    }
}
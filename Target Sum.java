// dfs
class Solution {
    int ans = 0;
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        dfs(nums, S, 0, 0);
        return ans;
    }
    
    void dfs(int[] nums, int target, int curSum, int idx) {
        // base case
        if (idx == nums.length) {
            if (target == curSum) {
                ans += 1;
            }
            return;
        }
        
        dfs(nums, target, curSum + nums[idx], idx + 1);
        dfs(nums, target, curSum - nums[idx], idx + 1);
    }
}


// dfs + mem
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        // dp[i][j]代表使用前面i個數字組成sum為j的最多有幾種可能
        int[][] dp = new int[nums.length][2001];
        
        // 初始化為最小值
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        return dfs(nums, 0, 0, S, dp);
    }
    
    public int dfs(int[] nums, int index, int curSum, int target, int[][] dp) {
        // base case
        if (index == nums.length) {
            if (curSum == target) {
                return 1;
            } else {
                return 0;
            }
        }
        
        // 1000是offset, 不行出現dp[-1000][]這種，要變成dp[0][]。而dp[0][]要變成dp[1000][]
        if (dp[index][curSum + 1000] != Integer.MIN_VALUE) {
            return dp[index][curSum + 1000];
        }
        
        int add = dfs(nums, index + 1, curSum + nums[index], target, dp);
        int sub = dfs(nums, index + 1, curSum - nums[index], target, dp);
        dp[index][curSum + 1000] = add + sub;
        return dp[index][curSum + 1000];
    }
}
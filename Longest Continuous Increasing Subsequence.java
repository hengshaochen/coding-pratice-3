// 暴力法 O(n^2)
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = 1;
            max = Math.max(max, cur);
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[j - 1]) {
                    cur++;
                    max = Math.max(max, cur);
                } else {
                    break;
                }
            }
        }
        return max;
    }
}

// DP的思想，但不需要額外空間，用變數儲存即可。Time: O(n) Space:O(1)
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        // 定義dp[i]等於在i這個下標的長度下，的LCIS。後來發現不用用空間，直接用一個變數代替即可。
        // 用一個變數記錄當前最大值, 另一個變數累積目前的連續長度，若遇到不連續就歸1重新計算。
        if (nums.length == 0) {
            return 0;
        }
        int max = 1;
        int cur_count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                cur_count++;
                max = Math.max(max, cur_count);
            } else {
                cur_count = 1;
            }
        }
        return max;
    }
}
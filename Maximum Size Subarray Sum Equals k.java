// Brute Force 窮舉所有subarray O(n^2)
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        // brute-force 窮舉所有的subarray取最長的
        
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = nums[i];
            if (count == k) { 
                ans = Math.max(ans, 1);
            }
            for (int j = i + 1; j < nums.length; j++) {
                count += nums[j];
                if (count == k) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
}

// O(n)
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        // 思路2: O(n)
        // 用空間換取時間，自己想出來的。用Prefix Sum的思路，用一個變數紀錄當前的prefix sum，然後把歷史的prefix sum存到map<prefix, index>
        // 如果prefix已經存在，就不需要put到map，因為要取盡量長的subarray
        // 用數學原理：cur_prefix - (先前的某個下標的prefix) = k --> 移項：cur_prefix - (k) = 先前某下標prefix (查map) 就嘗試更新max_ans. 
        int cur_prefix = 0;
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            cur_prefix += nums[i];
            
            // Case1: prefix本身就等於k
            if (cur_prefix == k) {
                ans = Math.max(ans, i + 1);
            }
            // Case2: prefix要和前面的互相計算求所有可能的subarray才等於k
            if (map.containsKey(cur_prefix - k)) {
                ans = Math.max(ans, i - map.get(cur_prefix - k));
            }
            
            // 如果不存在map中，put進去，如果存在，不需要put，因為我們是要取map_subarray，越遠越好。
            if (!map.containsKey(cur_prefix)) {
                map.put(cur_prefix, i);
            }
        }
        return ans;
    }
}
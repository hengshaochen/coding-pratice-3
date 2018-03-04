// 暴力法O(n^2)
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        // 這題主要要注意corner case, 當k = 0時，[0]是false, [0,0]是true, 因為題目要求是subarray至少2
        // 然後如果k是0會divide by zero exception, 要特判
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                count += nums[j];
                // 若k是0, 防止divide by zero
                if (k == 0 && count == 0) {
                    return true;
                }
                if (k != 0 && count % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}

// HashSet, prefix的思路，跟先前MAXIMUM size subarray sum equal k差不多思路，但注意set是存先前prefix % k的結果
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return false;
        }
        // 思路，也是用prefix的思路，配合HashMap儲存先前的結果
        // hashset儲存prefix % k的結果, 看筆記上的數學公式移項
        HashSet<Integer> set = new HashSet<>();
        int prefix = nums[0];
        if (k != 0) {
            set.add(prefix % k);
        }
        
        for (int i = 1; i < nums.length; i++) {
            prefix += nums[i];
            if (k == 0 && prefix == 0) {
                return true;
            }
            if ( (k != 0 && set.contains(prefix % k)) || prefix == k) {
                return true;
            }
            
            if (k != 0) {
                set.add(prefix % k);
            }
        }
        return false;
    }
}
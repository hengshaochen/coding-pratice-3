// 超時，要自己加
class Solution {
    int ans = 0;
    public int combinationSum4(int[] nums, int target) {
        // Arrays.sort(nums);
        dfs(0, nums, target);
        return ans;
    }
    
    public void dfs(int cur, int[] nums, int target) {
        if (cur > target) {
            return;
        }
        if (cur == target) {
            ans++;
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (cur + nums[i] > target) {
                break;
            }
            dfs(cur + nums[i], nums, target);
        }
    }
}

// DP
public class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    public int combinationSum4(int[] nums, int target) {
        int count = 0;
        if (nums == null || nums.length ==0 || target < 0 ) return 0;
        if ( target ==0 ) return 1;
        if (map.containsKey(target)) return map.get(target);
        for (int num: nums){
            count += combinationSum4(nums, target-num);
        }
        map.put(target, count);
        return count;
    }
}
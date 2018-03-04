class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        // 和Maximum Size subarray equal k一樣, 用Prefix + hashmap的思想
        // 但後來發現hashmap是找剛好存在，現在是要找>=的，想到用treemap, 可以用floorKey找treemap中是否存在prefix - s的key, 
        // 若存在就代表有subarray是 >= s的, 然後取最短的.
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int min = Integer.MAX_VALUE;
        int prefix = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            if (prefix >= s) {
                min = Math.min(min, i + 1);
            }
            
            if (map.floorKey(prefix - s) != null) {
                //System.out.println(i + " " + map.floorKey(prefix - s));
                min = Math.min(min, i - map.get(map.floorKey(prefix - s)));
            }
            
            // 如果已經存在這個prefix, 還是無腦put進去，因為想求min, 會取得更短的subarray, 和max equal k那題相反
            map.put(prefix, i);
        }
        
        if (min == Integer.MAX_VALUE) {
            // Corner case: 代表找不到就都回傳0
            // 例如s = 3, nums = [1,1] 
            return 0;
        }
        return min;
    }
}
class Solution {
    public int totalHammingDistance(int[] nums) {
        // 用mask計算1出現次數
        int mask = 1;
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int count_of_num_1 = 0;
            for (int j = 0; j < nums.length; j++) {
                // 計算該位在nums中有幾個1，例如題目0100, 1110, 0010, 假設第二低位，共有1個0, 2個1。
                // num:    1110
                // mask:   0010
                // result: 0010 --> 不等於0
                if ((nums[j] & mask) != 0) {
                    count_of_num_1++;
                }
            }
            int count_of_num_0 = nums.length - count_of_num_1;
            ans = ans + count_of_num_0 * count_of_num_1;
            
            // mask從0000 .... 0001  變成 0000 .... 0010 , 每次往左移動1 bit
            mask = mask << 1;
        }
        
        return ans;
    }
}
class Solution {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < A.length; i++) {
            int max = A[i];
            if (L <= max && max <= R) {
                ans++;
            }
            for (int j = i + 1; j < A.length; j++) {
                max = Math.max(max, A[j]);
                if (L <= max && max <= R) {
                    ans++;
                }
                // 如果超過R就不用在往下組subarray, 一定不符合條件，如果是< R還可以繼續嘗試，因為後面還有更大的可能。
                if (max > R) {
                    break;
                }
            }
        }
        return ans;
    }
}
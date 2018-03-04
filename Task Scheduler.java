class Solution {
    public int leastInterval(char[] tasks, int n) {
        // Step1: 統計出現次數
        int[] cnt = new int[26];
        for (char cur : tasks) {
            cnt[cur - 'A']++;
        }
        
        // Step2: 排序
        Arrays.sort(cnt);
        
        // 統計p (計算和最高頻出現次數一樣多的字母有幾個(要包含最高頻)）
        int i = 25;
        int p = 0;
        while (cnt[25] == cnt[i]) {
            p++;
            i--;
        }
        
        return Math.max(tasks.length, (cnt[25] -1) * (n + 1) + p);
    }
}
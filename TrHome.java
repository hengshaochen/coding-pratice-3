// Task1
// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int[] A) {
        // Conrer case
        if (A == null || A.length == 0) {
            return 0;
        }
        // 求花最少幾次，可以把每一面都變成一樣的數字
        // Algo: 窮舉所有可能性，維護一個min來儲存minmum number of moves。例如input array = [1,1,6]代表有2種可能，把它全部變成1，把它全部變成6。把兩種需要變換的次數都算出來，[1,1,6]變成[1,1,1]需要兩次操作，[1,1,6]變成[6,6,6]需要兩次操作。因此答案是2
        // Time Complexity: O(6n) = O(n) , 6種不同可能,每次需要跑n次
        // Space Complexity: O(constant)
        
        // 儲存最終答案(minmum number of moves)
        int min = Integer.MAX_VALUE;
        
        // 建表來判斷a數->b數需要幾次操作
        int[][] rotateTime = new int[][]{
                              { 0, 0, 0, 0, 0, 0, 0 },
                              { 0, 0, 1, 1, 1, 1, 2 },
                              { 0, 1, 0, 1, 1, 2, 1 },
                              { 0, 1, 1, 0, 2, 1, 1 },
                              { 0, 1, 1, 2, 0, 1, 1 },
                              { 0, 1, 2, 1, 1, 0, 1 },
                              { 0, 2, 1, 1, 1, 1, 0 } };
                              
        
        // 統計有幾種可以變換的可能, 用HashSet去重複數字
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
        }
        
        // 窮舉所有可能，curPips是當前要轉成的數字
        for (Integer curPips : set) {
            int count = 0;
            for (int i = 0; i < A.length; i ++) {
                if (A[i] != curPips) {
                    count += rotateTime[A[i]][curPips];
                }
            }
            min = Math.min(min, count);
        }
        
        return min;
    }
}

// Task2: 給起始跟終點時間HH:MM:SS，求這個時間區間中有多少個interestingTime
// interestingTime: HH:MM:SS只包含 <= 2 種數字 例如11:11:11可以，11:13:31可以，12:00:00不行，因為有1,2,0就三種了
// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(String S, String T) {
        // Algo: 把input string的起始結束時間轉成絕對時間（秒數），接著遍歷每一秒，把每一秒轉回String，接著判斷該時間是否為Interesting並統計。
        // 把起始跟結束時間先轉成second(絕對時間)
        int start = str2Int(S);
        int end = str2Int(T);
        int ans = 0;
        for (int i = start; i <= end; i++) {
            String curTime = int2Str(i);
            if (isInteresting(curTime)) {
                ans++;
            }
        }
        
        return ans;
    }
    
    // HH:MM:SS --> Second
    int str2Int(String str) {
        String[] strs = str.split(":");
        int ans = 0;
        String hour = strs[0];
        String minute = strs[1];
        String second = strs[2];
        
        ans += (hour.charAt(0) - '0') * 36000 + (hour.charAt(1) - '0') * 3600;
        ans += (minute.charAt(0) - '0') * 600 + (minute.charAt(1) - '0') * 60;
        ans += (second.charAt(0) - '0') * 10 + (second.charAt(1) - '0') * 1;
        return ans;
    }
    
    // Second --> HH:MM:SS
    String int2Str(int time) {
        int hour = time / 3600;
        time %= 3600;
        int minute = time % 60;
        time %= 60;
        int second = time;
        String h = String.format("%02d", hour);
        String m = String.format("%02d", minute);
        String s = String.format("%02d", second);
        return h + m + s;
    }
    
    // 判斷是否為interesting
    boolean isInteresting(String str) {
        int[] map = new int[10];
        // 統計每個數字出現次數
        for (char cur : str.toCharArray()) {
            map[cur - '0'] += 1;
        }
        
        // 計算HH:MM:SS中多少個不同的數字
        int distinct = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] >= 1) {
                distinct += 1;
            }
        }
        
        // 如果不同數字出現次數<= 2就是interesting, 反之就不是
        return distinct <= 2;
    }
}
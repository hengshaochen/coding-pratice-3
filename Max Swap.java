// O(n^2)
class Solution {
    public int maximumSwap(int num) {
        char[] str = Integer.toString(num).toCharArray();
        int max = num;
        
        for (int i = 0; i < str.length; i++) {
            for (int j = i + 1; j < str.length; j++) {
                swap(str, i, j);
                max = Math.max(max, Integer.valueOf(String.valueOf(str)));
                swap(str, i, j);
            }
        }
        return max;
    }
    
    public void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
    
}

// O(n)
class Solution {
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        
        int[] buckets = new int[10];
        for (int i = 0; i < digits.length; i++) {
            buckets[digits[i] - '0'] = i;
        }
        
        for (int i = 0; i < digits.length; i++) {
            for (int k = 9; k > digits[i] - '0'; k--) {
                if (buckets[k] > i) {
                    char tmp = digits[i];
                    digits[i] = digits[buckets[k]];
                    digits[buckets[k]] = tmp;
                    return Integer.valueOf(new String(digits));
                }
            }
        }
        
        return num;
    }
}
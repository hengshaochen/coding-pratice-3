// Live Home 
// 1 Prefix Sum
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        int[] arr = {2, 1, 3, 5, 4};
        
        int index_sum = 0;
        int num_sum = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            index_sum += (i + 1);
            num_sum += arr[i];
            if (index_sum == num_sum) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}

// Travel (Leetcode 76 Sliding Window)
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        int[] arr = {7, 3, 7, 3, 1, 3, 4, 1};
        System.out.println(shortest_travel_day(arr));
    }
    
    public int shortest_travel_day(int[] arr) {
        
        // algorithm: use Target hashSET to store all the distinct location, 
        // and we need to find the shortest day that ensure we visit all location
        // every time use Source hashmap, and ensure the Source hashmap contains
        // all the location in Target hashSET and try to reduce the day.
        // Time Complexity: O(n), Space Complexity: O(n)
        HashSet<Integer> target_set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            target_set.add(arr[i]);
        }
        

        HashMap<Integer, Integer> source_map = new HashMap<>();
        int i = 0, j = 0;
        int min_travel_day = arr.length;
        while (i < arr.length) {
            // put the sourceMap to valid
            while (!valid(target_set, source_map) && j < arr.length) {
                
                //System.out.println(i + " " + j);
                if (!source_map.containsKey(arr[j])) {
                    source_map.put(arr[j], 1);
                } else {
                    source_map.put(arr[j], source_map.get(arr[j]) + 1);
                }
                j++;
            }
            
            /*
            System.out.println("----------");
            for (Integer cur : source_map.keySet()) {
                System.out.println(cur);
            }
            //System.out.println(i + " " + j);
            */
            
            
            // if we find the shorter travel day, then we update it
            if (valid(target_set, source_map)) {
                if (min_travel_day > j - i) {
                    min_travel_day = j - i;
                    // System.out.println(i + " " + j);
                }
            }
            
            // try to reduce the travel day
            source_map.put(arr[i], source_map.get(arr[i]) - 1);
            if (source_map.get(arr[i]) == 0) {
                source_map.remove(arr[i]);
            }
            i++;
        }
        
        return min_travel_day;
    }
    
    // vertify whether source_set contains all the element in target_set
    public boolean valid(HashSet<Integer> target_set, HashMap<Integer, Integer> source_map) {
        
        Iterator<Integer> it = target_set.iterator();
         while(it.hasNext()){
            if (!source_map.containsKey(it.next())) {
                return false;
            }
         }
        return true;
    }
}


// 腳踏車問題：求間隔離左右兩邊腳踏車 的最大值，例如下面例子-1 0 1 9 10, 可以停在5, 5離左右兩邊1和9都是距離4, ans = 4
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        //int[] arr = {-1, 0, 2, 3, 8, 10, 11, 12};
        int[] arr = {-1, 9, 0, 1, 10};
        Arrays.sort(arr);
        
        int max_distance = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int cur_distance = Math.abs(arr[i] - arr[i + 1]) / 2;
            
            max_distance = Math.max(max_distance, cur_distance);
        }
        
        System.out.println(max_distance);
    }
}

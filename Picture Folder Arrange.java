// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

// Algo: 1. use a object to store the attribute of each photo 2. use hashmap<City, List<Object>> to sort the reference of each city 3. because we have many list from different city, we can sort the photo by time. 4. assign the consecutive natural number 5. put all data in list, and sort by origin input string order. 6. build the answer string
// Time Complexity: O(MlogM), M is the number of photo in string. Space Complexity: O(M)

class Solution {
    class photoInformation {
        String photoName;
        String extension;
        String cityName;
        String time;
        
        // stored the final name of photo
        String finalName;
        
        // record the origin order of the input string
        int originOrder;
        
        // constractor
        public photoInformation(String photoName, String extension, String cityName, String time, int originOrder) {
            this.photoName = photoName;
            this.extension = extension;
            this.cityName= cityName;
            this.time = time;
            this.originOrder = originOrder;
        }
        
        public String toString() {
            return photoName + " " + extension + " " + cityName + " " + time + " " + originOrder + " " + finalName;
        }
        
        public void setFinalName(String finalName) {
            this.finalName = finalName;
        }
    }
    public String solution(String S) {
        // Conrer case
        if (S == null || S.length() == 0) {
            return S;
        }
        
        int counter = 0;
        // 1. parse the input and create the object of each input
        // 2. and put the object to HashMap according to CITY_NAME
        HashMap<String, List<photoInformation>> map = new HashMap<>();
        for (String cur : S.split("\n")) {
            // [0]: photoName + extension, [1]: city, [2]: time
            String[] curAttribute = cur.split(", ");
            // [0]: photoName, [1]: Extension
            String[] photoAndExtension = curAttribute[0].split("\\.");
            
            photoInformation curPhoto = new photoInformation(photoAndExtension[0], photoAndExtension[1], curAttribute[1], curAttribute[2], counter++);
            
            if (!map.containsKey(curAttribute[1])) {
                map.put(curAttribute[1], new ArrayList<photoInformation>());
            }
            map.get(curAttribute[1]).add(curPhoto);
        }
        
        // 3. for each list sort the photo by time
        Comparator<photoInformation> cmp = new Comparator<photoInformation>() {
            public int compare(photoInformation e1, photoInformation e2) {
                return e1.time.compareTo(e2.time);
            }
        };
        for (String cur : map.keySet()) {
            List<photoInformation> curCityList = map.get(cur);
            Collections.sort(curCityList, cmp);
            
            int photoNumberOfCity = curCityList.size();
            for (int i = 0; i < photoNumberOfCity; i++) {
                int len = String.valueOf(photoNumberOfCity).length();
                
                // 4. assign the consecutive natural number
                String curAssignNumber = String.format("%0" + len + "d", i + 1);
                curCityList.get(i).setFinalName(curCityList.get(i).cityName + curAssignNumber + "." + curCityList.get(i).extension);
            }
        }
        
        // 5. put all data in list, and sort by origin input string order.
        List<photoInformation> answerList = new ArrayList<>();
        for (String cur : map.keySet()) {
            List<photoInformation> curCityList = map.get(cur);
            for (photoInformation curPhoto : curCityList) {
                answerList.add(curPhoto);
            }
        }
        Comparator<photoInformation> cmp2 = new Comparator<photoInformation>() {
            public int compare(photoInformation e1, photoInformation e2) {
                return e1.originOrder - e2.originOrder;
            }
        };
        Collections.sort(answerList, cmp2);
        
        // 6. build the answer string
        StringBuilder sb = new StringBuilder();
        for (photoInformation cur : answerList) {
            sb.append(cur.finalName +  "\n");
        }
        
        return sb.toString();
    }
}
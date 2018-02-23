// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        double[] arr = {10, 2, 9};
        double avg = 0;
        for (int i = 0; i < arr.length; i++) {
            avg += arr[i];
        }
        avg /= arr.length;
        
        double min = Double.MAX_VALUE;
        int min_idx = -1;
        for (int i = 0; i < arr.length; i++) {
            double cur_distance = Math.abs(arr[i] - avg);
            if (min > cur_distance) {
                min = cur_distance;
                min_idx = i;
            }
        }
        System.out.println(min_idx);
    }
}
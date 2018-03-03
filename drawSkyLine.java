// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        int[] A = new int[6];
        A[0] = 1;
        A[1] = 3;
        A[2] = 1;
        A[3] = 3;
        A[4] = 2;
        A[5] = 1;
        System.out.println(skylineDraw(A));
    }
    
    public int skylineDraw(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int count = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                count += (A[i] - A[i - 1]);
            }
        }
        return count;
    }
    

    
}
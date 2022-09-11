import java.util.Arrays;

public class Certification {
    public static boolean check(Comparable[] a){
    //TODO: call sort(), if given array a is sorted, return true; other wise return false
        ShellSort.sort(a);
        printArray(a);
        for (int i = 0; i < a.length-1; i++) {
            if (!ShellSort.less(a[i],a[i+1])) return false;
        }
        return true;
    }

    // ************** helper func ********************
    public static void printArray(Comparable[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static void main(String[] args) {
        // TODO: generate random int array, test check method
        Comparable[] a = new Comparable[5];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int)(Math.random() * (6)); // Min + (int)(Math.random() * ((Max - Min) + 1))
        }
        printArray(a);
        System.out.println(check(a));
    }
}

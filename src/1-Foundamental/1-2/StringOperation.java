import edu.princeton.cs.algs4.StdOut;

public class StringOperation {
    public static boolean ifCircularRotate(String s, String t){
        if((s.substring(s.indexOf(t.charAt(0)), s.length())+s.substring(0,s.indexOf(t.charAt(0)))).equals(t)){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println("*** 1.2.4 ***");
        String string1 = "hello";
        String string2 = string1; // -> object "hello"
        string1 = "world"; // -> object "world"
        StdOut.println(string1);
        StdOut.println(string2);

        System.out.println("*** 1.2.5 ***");
        String s = "Hello world";
        s.toUpperCase();
        s.substring(6,11);
        StdOut.println(s);
        StdOut.println(s.substring(6,11).toUpperCase()); //ending index is exclusive
        String t = "o worldHell";
    }
}

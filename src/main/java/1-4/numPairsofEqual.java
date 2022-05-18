import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.GenericDeclaration;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class numPairsofEqual {
    // Constructor do nothing
    public numPairsofEqual(){}

    public static int countPairs(int[] a){
        int n = a.length;
        int numPairs = 0;
        Arrays.sort(a);
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (a[j]>a[i]){break;}
                if (a[j]==a[i]){
                    numPairs++;
                    StdOut.println(a[i] + " " + a[j]);
                }
            }
        }
        return numPairs;
    }

    public static int[] readFile(String fileName) throws IOException {
        String root = System.getProperty("user.dir")+"\\"+fileName;
        Path filePath = new File(root).toPath();
        List<String> stringList = Files.readAllLines(filePath);
        List<Integer> intList = stringList.stream().map(Integer::parseInt).collect(Collectors.toList());
        int n = intList.size();
        int[] result = new int[n];
        int i = 0;
        while(i<n){
            result[i]=intList.get(i);
            i++;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        String file = "numPair.txt";
        System.out.println("In file "+"\""+ file +" \", there are "+countPairs(readFile(file))+" pairs of number of the same value.");
    }
}

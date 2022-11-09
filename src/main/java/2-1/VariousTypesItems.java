import com.sun.javafx.collections.MappingChange;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.*;

/**
 * The code{@VariousTypeItems} aims at generating List of items of various types with random key values, including the following:
 *
 * 1. String key (at least 10 characters), one double value
 * 2. double key, ten String values(all at least ten characters)
 * 3. int key, one int[20] value
 *
 * 看了很多遍，还是看不懂题目意思，但可以肯定的是不应该用map
 *
 * Map -> List
 * List<String> list = new ArrayList<String>(m.keySet());
 * List<String> list = new ArrayList<String>(m.values());
 */
public class VariousTypesItems {
    public VariousTypesItems(){} // Constructor, do nothing

//    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
//        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
//        list.sort(Map.Entry.comparingByValue());
//        Map<K, V> result = new LinkedHashMap<>();
//        for (Map.Entry<K, V> entry : list) {
//            result.put(entry.getKey(), entry.getValue());
//        }
//        return result;
//    }
    /**
     * This function aims at generating map that of randoms keys and one double value
     * @param n
     * @param <String>
     * @param <Double>
     * @return
     */
    public static <String, Double extends Comparable<? super Double>> Map<String, Double> stringKeyOneDouble(int n){
        Map<String,Double> data = null;
        String str = null;
        Comparable value  = 1.0;
        for (int i = 0; i < n; i++) {
            str = (String) generateString();
            data.put(str, (Double) value);
        }
        return data;
    }

    /**
     * This function aims at generating map that of random double keys and 10 String values
     * @param n
     * @param <String>
     * @param <Double>
     * @return
     */
    public static <Double, String extends Comparable<? super Double>> Map<Double, String> doubleKey10StringVal(int n){
        Map<java.lang.Double, java.lang.String> data = null;
        java.lang.Double d;
        Random r = new Random();
        int s;
        java.lang.String[] values = generateStringArr(10);
        for (int i = 0; i < n; i++) {
            d = generateDouble(data);
            s = r.nextInt(10);
            data.put(d, values[s]);
        }
        return (Map<Double, String>) data;
    }

    /**
     * This function aims at generating map that of random int keys, one int[20] value
     * @param n
     * @param <Integer>
     * @param <BigInteger>
     * @return
     */
    public static <Integer, BigInteger extends Comparable<? super BigInteger>> Map<Double, String> intKeyIntArrVal(int n){
        Map<Integer, BigInteger> data = null;
        Integer key;
        BigInteger val = (BigInteger) new java.math.BigInteger("12345678900987654321");
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            key = (Integer) java.lang.Integer.valueOf(r.nextInt());
            data.put(key, val);
        }
        return (Map<Double, String>) data;
    }

    //************** Helper functions ***********
    /**
     * Generate random string with length greater than 10
     */
    public static String generateString()
    {
        Random r = new Random();
        String str = new BigInteger(130, r).toString(32);
        if (str.length()>10) return str;
        else return generateString();
    }

    /**
     * Generate String array of length n
     * @param n
     * @return
     */
    public static String[] generateStringArr(int n)
    {
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            result[i] = generateString();
        }
        return result;
    }

    /**
     * Generate a Double
     * @param map
     * @return
     */
    public static Double generateDouble(Map<Double, String> map)
    {
        Random r = new Random();
        if (map.containsKey(r)) return generateDouble(map);
        else
            return r.nextDouble();
    }

    public static void main(String[] args) {
        System.out.println(generateString());
    }
}

import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * The {@Code myTransaction} class is a datatype to encapsulate a commercial myTransaction with customer name, data, and amount
 *
 * Aim: expand myTransaction class, make it comparable, so that myTransactions can keep in order by amount
 * Idea: create comparator, so that can use Array.sort(T[], comparator)
 *       inside comparator, need to override method compare, to indicate which features of class T will get compared
 */
public class myTransaction implements Comparable<myTransaction>{

    private final String  who;      // customer
    private final Date when;     // date
    private final double  amount;   // amount


    /**
     * Initializes a new myTransaction from the given arguments.
     *
     * @param  who the person involved in this myTransaction
     * @param  when the date of this myTransaction
     * @param  amount the amount of this myTransaction
     * @throws IllegalArgumentException if {@code amount}
     *         is {@code Double.NaN}, {@code Double.POSITIVE_INFINITY},
     *         or {@code Double.NEGATIVE_INFINITY}
     */
    public myTransaction(String who, Date when, double amount) {
        if (Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
        this.who    = who;
        this.when   = when;
        this.amount = amount;
    }

    /**
     * Initializes a new myTransaction by parsing a string of the form NAME DATE AMOUNT.
     *
     * @param  myTransaction the string to parse
     * @throws IllegalArgumentException if {@code amount}
     *         is {@code Double.NaN}, {@code Double.POSITIVE_INFINITY},
     *         or {@code Double.NEGATIVE_INFINITY}
     */
    public myTransaction(String myTransaction) {
        String[] a = myTransaction.split("\\s+");
        who    = a[0];
        when   = new Date(a[1]);
        amount = Double.parseDouble(a[2]);
        if (Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
    }

    public myTransaction(Transaction trans) {
        who    = trans.who();
        when   = trans.when();
        amount = trans.amount();
    }

    public String who() {
        return who;
    }

    public Date when() {
        return when;
    }

    public double amount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%-10s %10s %8.2f", who, when, amount);
    }

    public static void transPrint(myTransaction[] a){
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i].toString());
        StdOut.println();
    }

    /**
     * Compares two myTransactions by amount.
     *
     * @param  t the other myTransaction
     * @return { a negative integer, zero, a positive integer}, depending
     *         on whether the amount of this myTransaction is { less than,
     *         equal to, or greater than } the amount of that myTransaction
     */
    @Override
    public int compareTo(myTransaction t) {
        return Double.compare(this.amount, t.amount);
    }

    // Comparators are inner class
    /**
     * Compares two myTransactions by customer name.
     */
    public static class WhoOrder implements Comparator<myTransaction> {
        @Override
        public int compare(myTransaction v, myTransaction w) {
            return v.who.compareTo(w.who);
        }
    }

    /**
     * Compares two myTransactions by date.
     */
    public static class WhenOrder implements Comparator<myTransaction> {
        @Override
        public int compare(myTransaction v, myTransaction w) {
            return v.when.compareTo(w.when);
        }
    }

    /**
     * Compares two myTransactions by amount.
     */
    public static class HowMuchOrder implements Comparator<myTransaction> {
        @Override
        public int compare(myTransaction v, myTransaction w) {
            return Double.compare(v.amount, w.amount);
        }
    }


    /**
     * Unit tests the {@code myTransaction} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws ParseException {
        myTransaction[] a = new myTransaction[4];
        a[0] = new myTransaction("Turing   6/17/1990  644.08");
        a[1] = new myTransaction("Tarjan   3/26/2002 4121.85");
        a[2] = new myTransaction("Knuth    6/14/1999  288.34");
        a[3] = new myTransaction("Dijkstra 8/22/2007 2678.40");

        StdOut.println("Unsorted");
        transPrint(a);

        StdOut.println("Sort by date");
        Arrays.sort(a, new myTransaction.WhenOrder()); // new a comparator
        transPrint(a);

        StdOut.println("Sort by customer");
        Arrays.sort(a, new myTransaction.WhoOrder());
        transPrint(a);

        StdOut.println("Sort by amount");
        Arrays.sort(a, new myTransaction.HowMuchOrder());
        transPrint(a);
    }

}

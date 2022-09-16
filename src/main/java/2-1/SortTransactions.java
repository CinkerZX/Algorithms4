import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The {@Code SortTransactions} reads a sequence of transactions from standard input, sort them
 * and print them on standard output.
 */
public class SortTransactions extends myTransaction{


    public SortTransactions(String who, Date when, double amount) {
        super(who, when, amount);
    }

    public static myTransaction[] readTransactions() throws ParseException {
        //TODO: use the method readTrans() to read inputs; cast to Transaction class, as inout to construct myTransaction
        // Then can use the sort methods
        Object[] trans = TransactionQueue.readTrans();
        myTransaction[] myTrans = new myTransaction[trans.length];
        for (int i = 0; i < trans.length; i++) {
            myTrans[i] = new myTransaction((Transaction) trans[i]);
        }
        return myTrans;
    }

    public static void main(String[] args) throws ParseException {
        myTransaction[] trans = readTransactions();
        Arrays.sort(trans, new myTransaction.HowMuchOrder());
        transPrint(trans);
    }
}

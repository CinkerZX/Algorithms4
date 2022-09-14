//import edu.princeton.cs.algs4.Transaction;

import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TransactionQueue extends myDate<Transaction>{ // Utilize the class myDate realizing the function of reading transactions into a queue and return an Array
    // Class Transactions are by default using the one that from folder 1-2
    // Added an additional function in Transaction that converting Date into SmartDate to adapt to the constructor of Transaction
    public ResizeArrayQueueOfStrings myTransactions;

    public TransactionQueue() {
        myTransactions = new myDate();
    }

    public static Object[] readTrans() throws ParseException { // abstract object class
        TransactionQueue transactions = new TransactionQueue();
        Scanner input = new Scanner(System.in);
        System.out.println("Please input Name, Transaction amount, and Date in the form of  Alice; 1000; dd/mm/yyyy");
        System.out.println("Finish by entering #");
        while(input.hasNext()){
            String line = input.nextLine();
            if (line.equals("#")){
                break;
            }
            String[] info = line.split(";");
            String name = info[0];
            Double amount = Double.valueOf(info[1]);
            java.util.Date d = new SimpleDateFormat("dd/MM/yyyy").parse(info[2]);
            edu.princeton.cs.algs4.Date dd = new Date(d.getMonth(),d.getDay(),d.getYear());
            Transaction trans = new Transaction(name, dd, amount);
            transactions.add(trans); // concrete class
        }
        return transactions.toArraylist();
    }

    public static void main(String[] args) throws ParseException {
        Object[] transArray = readTrans();
        for (int i = 0; i < transArray.length; i++) {
            Transaction temp = (Transaction) transArray[i];// Concrete class
            System.out.println(temp.toString());
        }
    }
}

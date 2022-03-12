import java.text.ParseException;

public class Transaction implements Comparable<Transaction> {
    private final String who;
    private final SmartDate when;
    private final double amount;

    public Transaction(String w, SmartDate d, double a){
        this.who = w;
        this.when = d;
        this.amount = a;
    }

    public Transaction(String trans) throws ParseException {
        String[] elements = trans.split(" ");
        this.who = elements[0];
        this.amount = Double.parseDouble(elements[2]);
        this.when = new SmartDate(elements[4]);
    }

    public String toString(){
        return this.who+" transferred "+this.amount+" on "+this.when.toString();
    }

    public boolean equals(Transaction t){
        if (this.who.equals(t.getWho()) && this.when.compareTo(t.getWhen())==1 && this.amount == t.getAmount()) return true;
        return false;
    }

    public double getAmount() {
        return amount;
    }

    public SmartDate getWhen() {
        return when;
    }

    public String getWho() {
        return who;
    }

    public int hashCode(){
        return this.toString().hashCode();
    }

    @Override
    public int compareTo(Transaction o) {
        if (this.equals(o)) return 1;
        return 0;
    }

    public static void main(String[] args) throws ParseException {
        SmartDate date1 = new SmartDate(3, 12,2022);
        Transaction firstTransaction = new Transaction("Alice",date1,500);
        String record1 = firstTransaction.toString();
        System.out.println(record1);
        Transaction secondTransaction = new Transaction(record1);
        System.out.println(secondTransaction.toString());
        System.out.println(secondTransaction.compareTo(firstTransaction));
        System.out.println(firstTransaction.equals(secondTransaction));
    }
}

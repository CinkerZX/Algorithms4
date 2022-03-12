import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SmartDate implements Comparable<SmartDate> {
    private final int month;
    private final int day;
    private final int year;

    public SmartDate(int m, int d, int y) throws IllegalArgumentException{
        if (m > 12) throw new IllegalArgumentException("The month has to be less than or equal to 12.");
        if (m == 2 && d>28){
            if (d > 29 || (d==29 && y%4 != 0)){
                throw new IllegalArgumentException("The date doesn't exists!");
            }
        }
        if (m<=0 || d <= 0 || y <= 0) throw new IllegalArgumentException("The month, day and year have to be a positive natural number.");
        this.month = m;
        this.day = d;
        this.year = y;
    }

    public SmartDate(SmartDate aDate) throws IllegalArgumentException{
        this.month = aDate.getMonth();
        this.day = aDate.getDay();
        this.year = aDate.getYear();
    }

    public SmartDate(String s) throws ParseException {
        String[] elements = s.split("/");
        this.month = Integer.parseInt(elements[0]);
        this.day = Integer.parseInt(elements[1]);
        this.year = Integer.parseInt(elements[2]);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String toString(){
        return this.month+"/"+this.day+"/"+this.year;
    }

    public String dayOfWeek() throws ParseException {
        Calendar c = Calendar.getInstance();
        Date mydate = new SimpleDateFormat("M/dd/yyyy").parse(this.toString());
        c.setTime(mydate);
        int dayWeek = c.get(Calendar.DAY_OF_WEEK);
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(mydate); // EEEE => Saturday   E/EE/EEE => Sat
    }

    public static void main(String[] args) throws ParseException {
        SmartDate today = new SmartDate(3, 12,2022);
        System.out.println("The date is: "+today.toString());
        System.out.println("It is "+today.dayOfWeek() + ".");
    }

    @Override
    public int compareTo(SmartDate o) {
        if (this.day == o.getDay() && this.month == o.getMonth() && this.year == o.getYear()) return 1;
        return 0;
    }
}

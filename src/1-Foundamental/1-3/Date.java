import java.text.DateFormat;
//import java.text.ParseException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class myDate<E> extends ResizeArrayQueueOfStrings<E> { // Utilize the class ResizeArrayQueueOfStrings realizing the function of reading dates into a queue and return an Array

    public ResizeArrayQueueOfStrings mydate;

    public myDate(){
        super(10);
        mydate = new ResizeArrayQueueOfStrings(10);
    }

    public static Object[] readDates() throws ParseException { // abstract object class
        myDate dates = new myDate();
        Scanner input = new Scanner(System.in);
        System.out.println("Please input Dates in the form of dd/mm/yyyy: ");
        System.out.println("Finish by entering #");
        while(input.hasNext()){
            String line = input.nextLine();
            if (line.equals("#")){
                break;
            }
            Date d = new SimpleDateFormat("dd/MM/yyyy").parse(line);
            dates.add(d); // concrete class
        }
        return dates.toArraylist();
    }

    public static void main(String[] args) throws ParseException {
        Object[] dateArray = readDates();
        for (int i = 0; i < dateArray.length; i++) {
            Date temp = (Date) dateArray[i];// Concrete class
            DateFormat myFormat = new SimpleDateFormat("dd.MM.yyyy"); //print out in the defined format
            System.out.println(myFormat.format(temp));
        }
    }
}

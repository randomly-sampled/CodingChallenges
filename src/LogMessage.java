import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by yrafiyev on 2017-07-07.
 */
public class LogMessage {

    public static void main(String[] args){
        Scanner fileScanner = new Scanner(System.in);
        Scanner lineScanner = new Scanner(fileScanner.nextLine());
        long integer = 0;
        DateFormat dateFormat = new SimpleDateFormat("(MM/dd/yyyy-hh:mm:ss)");

        long start = 0;
        long shut = 0;
        long connected = 0;
        long disconnected = 0;
        long conncted_time = 0;
        int lastOperation = -1;
        while (lineScanner.hasNext()){
            String date = lineScanner.next();
            Date date1 = null;
            try {
                date1 = dateFormat.parse(date);
            } catch (ParseException e){System.out.println("Error");}
            lineScanner.next();
            String op = lineScanner.next();
            if(op.equals("START")){
                start = date1.getTime();
                lastOperation = 1;
            } else if(op.equals("SHUTDOWN")){
                shut = date1.getTime();
                if(lastOperation == 3)
                    conncted_time += (shut - connected);
            } else if(op.equals("CONNECTED")){
                lastOperation = 3;
                connected = date1.getTime();
            } else if(op.equals("DISCONNECTED")){
                disconnected = date1.getTime();
                conncted_time += (disconnected - connected);
                lastOperation = 4;
            }
        }

        System.out.println(conncted_time*100/(shut - start) + "%");

    }

}

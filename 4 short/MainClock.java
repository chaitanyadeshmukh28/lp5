import java.util.*;
import java.rmi.*;
import java.time.LocalTime;
import java.time.format.*;

public class MainClock {
    public static void main(String[] args) {
        try {
            
            String clock1 = "rmi://" + "127.0.0.1/" + args[0];
            String clock2 = "rmi://" + "127.0.0.1/" + args[1];
            String clock3 = "rmi://" + "127.0.0.1/" + args[2];

            ServerIntfc c1 = (ServerIntfc) Naming.lookup(clock1);
            System.out.println(c1.getTime());
            ServerIntfc c2 = (ServerIntfc) Naming.lookup(clock2);
            System.out.println(c2.getTime());
            ServerIntfc c3 = (ServerIntfc) Naming.lookup(clock3);
            System.out.println(c3.getTime());


            LocalTime serverTime = LocalTime.parse("03:00:00",DateTimeFormatter.ofPattern("HH:mm:ss"));
            System.out.println("Server time is: " + serverTime);
            long serverNano = serverTime.toNanoOfDay();
            long c1Nano = c1.getTime().toNanoOfDay() - serverNano;
            long c2Nano = c1.getTime().toNanoOfDay() - serverNano;
            long c3Nano = c1.getTime().toNanoOfDay() - serverNano;
            long avg = (c1Nano + c2Nano + c3Nano)/4;

            c1.adjustTime(serverTime, avg);
            c2.adjustTime(serverTime, avg);
            c3.adjustTime(serverTime, avg);
            serverTime = serverTime.plusNanos(avg);

            System.out.println("The adjusted time is : " + serverTime);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

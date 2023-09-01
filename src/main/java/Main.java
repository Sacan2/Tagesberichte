import java.util.Date;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        for (int hour = 6; hour <= 18; hour++) {
            for (int minute = 0; minute < 60; minute += 5) {
                if (hour == 18 && minute > 0) {
                    break; // Stop at 18:00
                }
                Date time = new Date(0, 0, 0, hour, minute);
                System.out.println(time);
            }
        }
        new MyFrame();
    }
}

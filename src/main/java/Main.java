import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        for (int i = 6,j=0; i < 18 && j < 60; i++,j +=5) {
            String stunden = String.format("%02d", i);
            String minuten = String.format("%02d", j);

            if (Objects.equals(stunden,"06")){
                System.out.println("Stunden ::"+stunden+" Minute::"+minuten);
                if (Objects.equals(minuten,"55")){
                    System.out.println("yeah");
                    i += 2;
                }
                i--;
                System.out.println("innerhalb der if funktion "+i);
            }
            if (Objects.equals(stunden,"07")){
                System.out.println("Stunden asdf ::"+stunden+" Minute::"+minuten);
                i--;
                System.out.println("die zahl halt"+i);
            }
            System.out.println("Außerhalb der if funktion "+i);
        }
        //new MyFrame();
    }
}

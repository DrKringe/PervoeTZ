import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println( "Введите выражение " );
        Scanner in = new Scanner(System.in);
        System.out.println( Calcul.calc(in.nextLine()) );
    }

}
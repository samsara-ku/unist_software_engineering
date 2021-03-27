import data.Load;
import java.io.IOException;
import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] arg) throws IOException {
        Scanner sc = new Scanner(System.in);

        Load Load = new Load(sc.nextLine(), sc.nextLine());
    }
}
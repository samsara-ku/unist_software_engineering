import data.Load;
import java.io.IOException;

public class HelloWorld {
    public static void main(String[] args) throws IOException {
        Load Load = new Load(args[0], args[1]);
    }
}
import data.Load;
import java.io.IOException;

public class HelloWorld {
    public static void main(String[] args) throws IOException {

        // Check whether the arguments are two or not
        if(args.length != 2){
            System.out.println("Inappropriate # of arguments!");
            return;
        }
        Load Load = new Load(args[0], args[1]);
    }
}
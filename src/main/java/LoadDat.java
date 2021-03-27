import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;

public class LoadDat {

    public static ArrayList<String> ReadDat(String FileName) {

        //Read FileName.Dat file and return StringList

        ArrayList<String> arrList = new ArrayList<String>();
        try{
                File file = new File(String.format("../../../data/%s", FileName));
                FileReader filereader = new FileReader(file);
                BufferedReader bufReader = new BufferedReader(filereader);
                String line = "";
                while((line = bufReader.readLine()) != null){
                arrList.add(line);
                }
                bufReader.close();
        }catch (FileNotFoundException e) {
                System.out.println(e);
        }catch (IOException e) {
                System.out.println(e);
        }
        return arrList;
    }

    public static void main(String[] args) {

        //main function

/*        ArrayList<String> StringList = LoadDat.ReadDat("users.dat");
        for (int i=0; i<StringList.size(); i++) {
                System.out.println(StringList.get(i));
        } //for test
        */
    }
}
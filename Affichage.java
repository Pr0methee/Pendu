import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class Affichage {

    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void draw(int i){
        assert (0<=i && i<=10) || i==-1;
        if (i==10){
            for (int j=1;j<=10;j++){
                System.out.println("");
            }
        }
        else{
            try{
                String filename = "cache\\"+String.valueOf(i)+".txt";
                String encoding = "UTF-8";
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), encoding));

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
                bufferedReader.close();
            }
            catch (IOException e) {
                System.out.println("An error occurred while reading the file: " + e.getMessage());
                e.printStackTrace();    
            }
        }
    }

    public static void draw_word(String ch, List<String> known){
        ch = ch.toUpperCase();
        String result="THE WORLD IS : ";
        for (char car:ch.toCharArray()){
            if (known.contains(String.valueOf(car))){
                result = result + String.valueOf(car);
            } 
            else{
                result = result + " _ ";
            }
        }
        System.out.println(result);
    }

    public static void OneRound(String word,List<String> known,List<String> tried,String message){
        Affichage.clear();
        if (message!=""){
            System.out.println(message);
        }
        Affichage.draw_word(word, known);
        System.out.println("BE CAREFUL NOT TO BE HANGED !");
        Affichage.draw(10-tried.size());
        String result = "LETTERS YOU TRIED : ";
        if (tried.size()==0){
            result = "NO LETTERS TRIED YET";
        }
        for (String car:tried){
            result = result + " "+car+" ";
        }
        System.out.println(result);
    }
}
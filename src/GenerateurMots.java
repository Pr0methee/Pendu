import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class GenerateurMots {
    private List<String> table;
    private Random random;

    public GenerateurMots() {
        this.table =new ArrayList<>();
        this.random = new Random();
        this.reset();
    }

    public void reset(){
     try{
        FileReader fileReader = new FileReader("cache/mots.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            this.table.add(line);
        }
        bufferedReader.close();
        fileReader.close();
        }
    catch (IOException e){
        System.out.println("An error occurred while reading the file: " + e.getMessage());
        e.printStackTrace();
     }   
    }

    public String give(){
        if (this.table.size()==0){
            this.reset();
        }
        int index=this.random.nextInt(this.table.size());
        String result = this.table.get(index);
        this.table.remove(index);
        return result.toUpperCase();
    }

    public boolean test(String word, List<String> letters){
        for (char car : word.toCharArray()){
            if (!letters.contains(String.valueOf(car))){
                return false;
            }
        }
        return true;
    }
}

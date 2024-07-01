import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Game {
    private List<String> tried;
    private List<String> known;
    private Scanner scan;
    private GenerateurMots gen;   
    private String word; 
    
    public Game(){
        this.scan = new Scanner(System.in);
        this.gen = new GenerateurMots();
    }

    public void play(){
        while (true){
            this.new_game();
            String result =this.ask_yes_no("Do you want to play once more  ? (Y/N) :");
            System.out.println(result);
            System.out.println(result.length());
            
            if (result.equals("N")){
                break;
            }
        }
    }

    public void new_game(){
        this.word = this.gen.give();
        this.tried = new ArrayList<>();
        this.known = new ArrayList<>();
        boolean cnd=true;
        String message="";
        while (cnd){
            Affichage.OneRound(this.word,this.known, this.tried,message);
            String letter = this.ask_letter("Try a letter :");
            letter = letter.toUpperCase();
            if (this.word.contains(letter) ){
                this.known.add(letter);
                message = "You are lucky, "+letter+" is in the word ! ";
            }       
            else{
                this.tried.add(letter);
                message = "Oh no ! "+letter+" is not in the word !";
            }    

            if (this.tried.size()==10 || this.gen.test(this.word,this.known)){
                cnd=false;
            }
        }

        if (this.tried.size()==10){
            Affichage.clear();
            System.out.println("What a sad day, you have been hanged !");
            Affichage.draw(0);
            
        }
        else {
            Affichage.clear();
            System.out.println("Youhou ! You are free !");
            Affichage.draw(-1);
        }
        System.out.println("The word was : "+this.word);
    }

    public String ask_letter(String message){
        System.out.println(message);
        String result = this.scan.nextLine();
        if (this.tried.contains(result) || this.known.contains(result)){
            return this.ask_letter("Try another letter :");
        }
        if (result.length() == 1){
            return result;
        }
        return this.ask_letter("You should consider giving a letter :");
    }

    public String ask_yes_no(String message){
        System.out.println(message);
        String result = this.scan.nextLine();
        if (result.equals("Y") || result.equals("N")){
            return result;
        }
        else if (result.equals("n")){
            return "N";
        }
        else if (result.equals("y")){
            return "Y";
        }
        return this.ask_yes_no("Do you want to play once more  ? (Y/N) :");
    }

}

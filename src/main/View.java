package main;
import main.Viewable;

public class View implements Viewable {
    public View(){}

    public void printWelcome(){
        // Here add some Ascii art!
        //
        System.out.println("👋 Welcome to the Avatar Universe !");
    }

    public void printEndGame(){

        // Add some more ascii art!!
        System.out.println("👋 Goodbye!");

    };
    public void print(String input){
        System.out.println(input);
    };

}

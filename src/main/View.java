package main;
import main.Viewable;

public class View implements Viewable {
    public View(){
        System.out.println("View class is initialized!");
    }

    public void printWelcome(){
        // Here add some Ascii art!
        //
        System.out.println("Welcome to the Avatar Universe !");
        System.out.println("👋 Welcome to the MVC Demo for Avatar Game !");
        System.out.println("Type something and hit enter and I'll echo it back. Type 1 to quit.");


    }

    public void printEndGame(){

        // Add some more ascii art!!
        System.out.println("👋 Goodbye!");

    };
    public void print(String input){
        System.out.println(input);
    };

    public void okay(){
        System.out.println("Welcome to Avatar Game the !");
    }
}

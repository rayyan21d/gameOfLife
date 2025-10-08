package main;
import main.Viewable;

/**
 * My Ideas was to extend this as a text interface from the Viewable interface and then maybe when I do a GUI
 * The GUIInterface will handle the logic to display visuals..
 * **/
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

package main;

import java.util.Scanner;
import main.View;
import main.Model;

public class Controller {

    private Model model;
    private View view;

    public Controller() {
        this.model = new Model();
        this.view = new View();
    }

    public void run(){
        System.out.println("Hello World from controller!");
        Scanner sc = new Scanner(System.in);
        view.printWelcome();

        while(true){

            String input = sc.nextLine();
            if(input.equals("1")){
                view.printEndGame();
                break;
            }

            model.setData(input);
            view.print("Data Set: "+model.getData());

        }

    }
}


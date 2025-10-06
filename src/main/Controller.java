package main;

import java.util.Scanner;
import main.View;
import main.Model;
import main.World;
import main.Story;

public class Controller {

    private Model model;
    private View view;
    private Parser parser;
    private final World world;
    private final Story story;


    public Controller(World world, View view) {
        this.model = new Model();
        this.view = new View();
        this.world = world;
        this.parser = new Parser(world.dict);
        this.story = world.story;

    }

    public void run(){

        Scanner sc = new Scanner(System.in);
        view.printWelcome();

        // Print initial scene
        Scene cur = story.getCurrentScene();
        view.print(cur.getTitle());
        view.print(cur.getDescription());

        while(true){

            // show available choices (human readable)
            view.print("\nAvailable choices: " + story.getCurrentScene().getChoices());
            view.print("\nWhat do you do?");

            String input = sc.nextLine().trim();
            if(input.equals("-1") || input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")){
                view.printEndGame();
                break;
            }

            Command command = parser.parse(input);
            if(command == null){
                view.print("Invalid Command!");
                continue;
            }

            model.setData(input);
            view.print("Data Set: "+model.getData());

            // process command through story and print result
            String result = story.processCommand(command);
            view.print(result);

            // also show new scene title/description if story transitioned
            Scene newCur = story.getCurrentScene();
            view.print("\n--- Current Scene: " + newCur.getTitle() + " ---");
            view.print(newCur.getDescription());

        }

        sc.close();

    }
}


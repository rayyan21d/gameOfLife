package main;

import java.util.Scanner;

/**
 * This class is the driver class which connects all the objects and distributes responsibility to other classes
 * It has the run method which runs the main game loop
 */
public class Controller {

    private Model model;
    private View view;
    private Parser parser;
    private final World world;
    private final Story story;

    public Controller(World world, View view) {
        this.model = new Model();
        this.view = view;
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
            // show available choices
            view.print("\nAvailable choices: " + story.getCurrentScene().getChoices());
            view.print("\nWhat do you do?");

            String input = sc.nextLine().trim();

            // Check for quit commands
            if(input.equals("-1") || input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")){
                view.printEndGame();
                view.print("\n" + model.getStatistics());
                break;
            }

            // Store input in model
            model.setData(input);

            // Parse command
            Command command = parser.parse(input);

            if(command == null){
                // Track invalid commands
                model.incrementInvalidCommands();
                view.print("Invalid Command!");

                // Suggest help after multiple failed attempts
                if (model.shouldSuggestHelp()) {
                    view.print("Hint: Try commands like 'walk forest', 'talk elder', or 'inspect torch'");
                }
                continue;
            }

            // Track valid command
            model.setCurrentCommand(command);

            // Process command through story and print result
            String result = story.processCommand(command);

            // Store result in model
            model.setLastResult(result);

            view.print(result);

            // Also show new scene title/description if story transitioned
            Scene newCur = story.getCurrentScene();
            view.print("\n--- Current Scene: " + newCur.getTitle() + " ---");
            view.print(newCur.getDescription());

            // Optional - show statistics periodically
            if (model.getCommandCount() % 10 == 0) {
                view.print("\n[" + model.getStatistics() + "]");
            }
        }

        sc.close();
    }
}
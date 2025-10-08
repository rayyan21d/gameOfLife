package main;

import main.Controller;

/**
 * It is the main manager that initializes the world class and connects the controller to the world
 * **/
public class Game {

    Controller controller;

    public Game(){}

    public void start() {
        World world = new World();
        View view = new View();

        this.controller = new Controller(world, view);
        this.controller.run();
    }
}

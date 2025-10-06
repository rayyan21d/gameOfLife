package main;

import main.Controller;

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

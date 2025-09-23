package main;

import main.Controller;

public class Game {

    Controller controller;

    public Game() {
        System.out.println("Game Constructor");
    };

    public void start() {
        this.controller = new Controller();
        this.controller.run();
    }
}

package main;

public class Model {

    String data;
    private Command command;

    public Model() {}

    public void setCommand(Command command){
        this.command = command;
    }


    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data + " fetched from Model";
    }
}

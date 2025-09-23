package main;

public class Model {

    String data;
    public Model(){
        System.out.println("Model Class is initialized!");
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data + " fetched from Model";
    }
}

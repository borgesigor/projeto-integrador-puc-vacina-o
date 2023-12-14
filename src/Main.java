import helpers.Input.*;
import service.Cli;

public class Main {
    public static void main(String[] args){
        run();
    }

    public static void run(){
        try {
            new Cli().start();
        }catch (Error e){
            new Errors(e.getMessage().split(": ")[1]);
            run();
        }
    }
}
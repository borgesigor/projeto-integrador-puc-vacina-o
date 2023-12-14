package helpers;

import java.util.ArrayList;
import java.util.Scanner;

public class Input {

    public static class InputStr{
        String msg;
        Scanner scanner;

        public InputStr(String msg){
            this.msg = msg;
            this.scanner = new Scanner(System.in);
        }

        public String getInput(){
            System.out.println("");
            System.out.println(msg);
            String input = scanner.nextLine();

            return input;
        }
    }

    public static class InputInt{
        String msg;
        Scanner scanner;

        public InputInt(String msg){
            this.msg = msg;
            this.scanner = new Scanner(System.in);
        }

        public int getInput(){
            System.out.println("");
            System.out.println(msg);
            int input = scanner.nextInt();

            return input;
        }
    }

    public static class Painel{
        String title;
        ArrayList<String> msgs;

        public Painel(){
            this.msgs = new ArrayList<String>();
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setItems(String msg) {
            msgs.add(msg);
        }

        public void show(){
            System.out.println("");
            System.out.println(title);
            for (String msg : msgs){
                System.out.println(msg);
            }
        }
    }

    public static class Title{
        public Title(String msg){
            System.out.println(" ");

            System.out.println("✅ " + msg);
        }
    }

    public static class Warning{
        public Warning(String msg){
            System.out.println(" ");

            System.out.println("\uD83D\uDD34 " + msg);
        }
    }

    public static class List{
        public List(String msg){
            System.out.println(" ");

            System.out.println("\uD83D\uDD39 " + msg);
        }
    }

    public static class Separator{
        public Separator(){
            System.out.println(" ");

            System.out.println("➖➖➖➖➖➖➖➖➖➖➖➖➖➖");
        }
    }

    public static class Errors{
        public Errors(String msg){
            System.out.println(msg);
        }
    }
}

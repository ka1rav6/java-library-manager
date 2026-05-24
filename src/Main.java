
import java.io.Console;
import java.util.Scanner;
import support.Start;

public class Main{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        Console console = System.console();
        if (console == null){
            System.out.println("No console available");
            System.exit(1);
        }

        System.out.println("Hello! Welcome to your very own library.\nWould you like to log in/sign up? (y/n)");

        while (true){
            String ans = in.nextLine();
            if (ans.equalsIgnoreCase("y")){
                Start.login(in, console);
                break;
            }
            else if (ans.equalsIgnoreCase("n")){
                System.out.println("Oh no! Hope you do log in some day");
                break;    
            }
            else
                System.out.println("That is not an option! It is y for yes and n for no. Please try again");
        }
    }
}
import java.io.Console;
import java.util.Scanner;

enum Menu{
    ISSUE_NEW_BOOK,
    RETURN_A_BOOK,
    SEARCH_BOOK,
    VIEW_ISSUED_BOOKS,
    GET_RETURN_DATES,
    GET_FINE_HISTORY,
    CHANGE_PASSWORD,
};

public class Start{
    public static void login(){
        Scanner in = new Scanner(System.in);
        // username.validate()       TODO
        Console console = System.console();
        if (console == null){
            System.out.println("No console available");
            System.exit(1);
        }
        while (true){
            System.out.printf("Please enter your username: ");
            String username = in.nextLine();
            char[] password = console.readPassword("Enter password: ");
            String pswd = new String(password);
            BaseUser user = BaseUser.findUser(username, pswd);
            if (user == null) {
                user = createAccount(in);
            }
            if (user instanceof Admin userObj) 
                System.out.println("Admin logged in");
            else if (user instanceof User userObj) 
                System.out.println("User logged in");
        }
    }
    public static BaseUser msgForCreateAccount(Scanner in){
        System.out.println("That user does not exist. Would you like to create one? (y/n)");
        while (true){
            String ans = in.next();
            if (ans.equalsIgnoreCase("y")){
                return Start.createAccount();
            }else if (ans.equalsIgnoreCase("n")){
                System.out.println("Thanks for opening the app. See you soon!");
                System.exit(0);
            }
        }
    }
    public static BaseUser createAccount(){

    }
}
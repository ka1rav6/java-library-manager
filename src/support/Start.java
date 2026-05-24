package support;

import java.io.Console;
import java.util.Scanner;
import libUsers.BaseUser;
import libUsers.Admin;
import libUsers.User;

public class Start{

    public static void login(Scanner in, Console console){

        while (true){
            System.out.print("Please enter your username: ");
            String username = in.nextLine();
            char[] password = console.readPassword("Enter password: ");
            String pswd = new String(password);
            BaseUser user = BaseUser.findUser(username, pswd);

            if (user == null){
                System.out.println("That user does not exist. Would you like to create one? (y/n)");
                while (true){
                    String ans = in.nextLine();
                    if (ans.equalsIgnoreCase("y")){
                        user = createAccount(in);
                        break;
                    } else if (ans.equalsIgnoreCase("n")){
                        System.out.println("Thanks for opening the app. See you soon!");
                        return;
                    } else {
                        System.out.println("Please enter y or n.");
                    }
                }
            }

            if (user instanceof Admin)
                System.out.println("Admin logged in. Welcome, " + user.username + "!");
            else
                System.out.println("User logged in. Welcome, " + user.username + "!");

            showMenu(in, user);
            return;
        }
    }

    public static BaseUser createAccount(Scanner in){
        System.out.print("Choose a username: ");
        String username = in.nextLine();

        while (BaseUser.usernameExists(username)){
            System.out.print("That username is taken. Try another: ");
            username = in.nextLine();
        }

        System.out.print("Choose a password: ");
        String password = in.nextLine();

        System.out.print("Enter your phone number: ");
        String phone = in.nextLine();

        System.out.print("Enter your email: ");
        String email = in.nextLine();

        System.out.print("Account type - admin or user? ");
        String type = in.nextLine();

        BaseUser user;
        if (type.equalsIgnoreCase("admin"))
            user = new Admin(username, password, phone, email);
        else
            user = new User(username, password, phone, email);

        BaseUser.addUser(user);
        System.out.println("Account created successfully!");
        return user;
    }

    public static void showMenu(Scanner in, BaseUser user){
        boolean isAdmin = (user instanceof Admin);

        while (true){
            System.out.println("\n--- Menu ---");
            System.out.println("1. Issue a book");
            System.out.println("2. Return a book");
            System.out.println("3. Search for a book");
            System.out.println("4. View issued books");
            System.out.println("5. Get return dates");
            System.out.println("6. View fine history");
            System.out.println("7. Change password");
            if (isAdmin){
                System.out.println("8. Add a book to library");
                System.out.println("9. Remove a book from library");
            }
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");

            String choice = in.nextLine();

            switch (choice){
                case "1":
                    System.out.print("Enter book name: ");
                    String bName = in.nextLine();
                    System.out.print("Enter book author: ");
                    String bAuthor = in.nextLine();
                    int idx = Book.partOfLibrary(bName, bAuthor);
                    if (idx == -1){
                        System.out.println("Book not found in library.");
                    } else {
                        Book[] books = Library.parseLibrary();
                        user.issueBook(books[idx]);
                        Library.update(books);
                    }
                    break;

                case "2":
                    System.out.print("Enter book name: ");
                    String rName = in.nextLine();
                    System.out.print("Enter book author: ");
                    String rAuthor = in.nextLine();
                    int rIdx = Book.partOfLibrary(rName, rAuthor);
                    if (rIdx == -1){
                        System.out.println("Book not found in library.");
                    } else {
                        Book[] books2 = Library.parseLibrary();
                        user.returnBook(books2[rIdx]);
                        Library.update(books2);
                    }
                    break;

                case "3":
                    System.out.print("Enter book name: ");
                    String sName = in.nextLine();
                    System.out.print("Enter book author: ");
                    String sAuthor = in.nextLine();
                    user.searchBook(sName, sAuthor);
                    break;

                case "4":
                    user.viewIssues();
                    break;

                case "5":
                    user.getReturnDates();
                    break;

                case "6":
                    var fines = user.getFineHistory();
                    if (fines.isEmpty())
                        System.out.println("No fines.");
                    else
                        for (var f : fines)
                            System.out.println("Fine: Rs" + f.amount);
                    break;

                case "7":
                    System.out.print("Enter new password: ");
                    String newP = in.nextLine();
                    user.changePassword(newP);
                    break;

                case "8":
                    if (!isAdmin){
                        System.out.println("Invalid option.");
                        break;
                    }
                    System.out.print("Enter book name: ");
                    String aName = in.nextLine();
                    System.out.print("Enter book author: ");
                    String aAuthor = in.nextLine();
                    System.out.print("Enter number of copies: ");
                    int copies = Integer.parseInt(in.nextLine());
                    ((Admin) user).addBook(aName, aAuthor, copies);
                    break;

                case "9":
                    if (!isAdmin){
                        System.out.println("Invalid option.");
                        break;
                    }
                    System.out.print("Enter book name: ");
                    String dName = in.nextLine();
                    System.out.print("Enter book author: ");
                    String dAuthor = in.nextLine();
                    ((Admin) user).removeBook(dName, dAuthor);
                    break;

                case "0":
                    System.out.println("Logged out. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }
}
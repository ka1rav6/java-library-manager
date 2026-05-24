package libUsers;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import support.Book;
import support.Email;
import support.Fine;
import support.Issues;
import support.Library;
import utils.Log;

public class BaseUser{
    public String username;
    protected String password;
    protected String phoneNumber;
    protected Email emailId;
    public ArrayList<Issues> issueHistory;
    public double currentDeposit;
    public ArrayList<Fine> fineHistory;
    private static final String USERS_FILE = "users.txt";
    public BaseUser(){
        this.issueHistory = new ArrayList<>();
        this.fineHistory = new ArrayList<>();
        this.currentDeposit = 0;
    }
    public BaseUser(String username, String password, String phoneNumber, String email){
        this();
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.emailId = new Email(email);
    }
    public void issueBook(Book book){
        if (book == null){
            System.out.println("Book not found.");
            return;
        }
        book.issueBook();
    }
    public void returnBook(Book book){
        if (book == null){
            System.out.println("Book not found.");
            return;
        }
        book.incrementAvailableCopies();
        System.out.println("Book returned successfully.");
    }
    public void changePassword(String newP){
        this.password = newP;
        saveAllUsers();
        System.out.println("Password changed.");
    }
    public String getPassword(){
        return this.password;
    }
    public void deleteAccount(){
        ArrayList<BaseUser> all = loadAllUsers();
        all.removeIf(u -> u.username.equals(this.username));
        saveUsers(all);
        System.out.println("Account deleted.");
    }
    public void searchBook(String name, String author){
        int index = Book.partOfLibrary(name, author);
        if (index == -1)
            System.out.println("Book not found in the library.");
        else{
            Book[] books = Library.parseLibrary();
            System.out.println(books[index].toString());
        }
    }
    public void viewIssues(){
        if (issueHistory.isEmpty()){
            System.out.println("No books have been issued.");
            return;
        }
        for (Issues issue : issueHistory)
            System.out.println(issue.toString());
    }
    public void getReturnDates(){
        if (issueHistory.isEmpty()){
            System.out.println("No books issued, so no return dates.");
            return;
        }
        for (Issues issue : issueHistory)
            System.out.println("Book: " + issue.book.name + " | Return by: " + issue.returnDate);
    }
    public ArrayList<Fine> getFineHistory(){
        return this.fineHistory;
    }
    public Email getEmail(){
        return this.emailId;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public String serialize(){
        String type = (this instanceof Admin) ? "admin" : "user";
        String email = (this.emailId != null) ? this.emailId.address : "none";
        return this.username + ": " + this.password + "," + this.phoneNumber + "," + email + "," + this.currentDeposit + "," + type;
    }

    public static BaseUser deserialize(String line){
        int colonIndex = line.indexOf(": ");
        if (colonIndex == -1) return null;
        String username = line.substring(0, colonIndex);
        String rest = line.substring(colonIndex + 2);
        String[] parts = rest.split(",");
        if (parts.length < 5) return null;
        String password = parts[0];
        String phone = parts[1];
        String email = parts[2];
        double deposit = Double.parseDouble(parts[3]);
        String type = parts[4];
        BaseUser user;
        if (type.equals("admin"))
            user = new Admin(username, password, phone, email);
        else
            user = new User(username, password, phone, email);
        user.currentDeposit = deposit;
        return user;
    }

    public static ArrayList<BaseUser> loadAllUsers(){
        ArrayList<BaseUser> users = new ArrayList<>();
        File file = new File(USERS_FILE);
        if (!file.exists()) return users;
        try (Scanner in = new Scanner(file)){
            while (in.hasNextLine()){
                String line = in.nextLine().strip();
                if (line.isEmpty()) continue;
                BaseUser u = deserialize(line);
                if (u != null) users.add(u);
            }
        }catch(Exception e){
            System.out.println("Error reading users file: " + e);
        }
        return users;
    }
    public static void saveUsers(ArrayList<BaseUser> users){
        try (FileWriter writer = new FileWriter(USERS_FILE)){
            for (BaseUser u : users)
                writer.write(u.serialize() + "\n");
        }catch(Exception e){
            System.out.println("Error saving users: " + e);
        }
    }
    public static void saveAllUsers(){
        //TODO
    }
    public static void addUser(BaseUser user){
        ArrayList<BaseUser> users = loadAllUsers();
        users.add(user);
        saveUsers(users);
        Log.log("New user created: " + user.username);
    }
    public static BaseUser findUser(String username, String password){
        ArrayList<BaseUser> users = loadAllUsers();
        for (BaseUser u : users){
            if (u.username.equals(username) && u.password.equals(password))
                return u;
        }
        return null;
    }
    public static boolean usernameExists(String username){
        ArrayList<BaseUser> users = loadAllUsers();
        for (BaseUser u : users){
            if (u.username.equals(username))
                return true;
        }
        return false;
    }
    @Override
    public String toString(){
        return "Username: " + this.username + " | Phone: " + this.phoneNumber;
    }
}
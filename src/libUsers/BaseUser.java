package libUsers;

import java.util.ArrayList;
import support.Book;
import support.Email;
import support.Fine;
import support.Issues;

public class BaseUser{
    /**
     * Base class for both User and Admin classes.
     */

    public String username;
    protected  String password;
    protected String phoneNumber;
    protected Email emailId;
    public ArrayList<Issues> issueHistory;
    public double currentDeposit;
    public ArrayList<Fine> fineHistory;
    public void issueBook(Book book){
    }
    public void returnBook(Book book){
    }
    public void changePassword(String newP){
        this.password = newP;
    }
    public String getPassword(){
        return this.password;
    }
    public void deleteAccount(){
    }
    public void searchBook(){
    }
    public void viewIssues(){
    }
    public void getReturnDates(){
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
    public static void saveUserMetaData(String FILE){
        
    }
    public static BaseUser findUser(String username, String password){
        return null;
    }
}

import java.util.ArrayList;

public class BaseUser{
    public String username;
    protected  String password;
    protected String phoneNumber;
    protected Email emailId;
    public ArrayList<Issues> issueHistory;
    public double currentDeposit;
    protected ArrayList<Fine> fineHistory;
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
    public void viewAvailableBooks(){
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
}
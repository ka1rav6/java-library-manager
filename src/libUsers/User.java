package libUsers;

import java.time.LocalDateTime;
import support.Book;
import support.Issues;

import utils.Log;

public class User extends BaseUser{

    public User(){
        super();
    }

    public User(String username, String password, String phoneNumber, String email){
        super(username, password, phoneNumber, email);
    }

    @Override
    public void issueBook(Book book){
        if (book == null){
            System.out.println("Book not found.");
            return;
        }
        double deposit = book.getPrice();
        this.currentDeposit += deposit;
        book.issueBook();
        var now = LocalDateTime.now();
        Issues issue = new Issues(now, now.plusDays(14), this, book);
        this.issueHistory.add(issue);
        Log.log(issue.toString());
        System.out.println(issue.toString());
    }

    @Override
    public void returnBook(Book book){
        if (book == null){
            System.out.println("Book not found.");
            return;
        }
        book.incrementAvailableCopies();
        this.currentDeposit -= book.getPrice();
        if (this.currentDeposit < 0) this.currentDeposit = 0;
        System.out.println("Book returned successfully. Deposit refunded.");
    }

    @Override
    public void deleteAccount(){
        super.deleteAccount();
    }

    @Override
    public void viewIssues(){
        super.viewIssues();
    }

    @Override
    public void getReturnDates(){
        super.getReturnDates();
    }
}
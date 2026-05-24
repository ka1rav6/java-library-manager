package libUsers;


import java.time.LocalDateTime;
import support.Book;
import support.Issues;
import utils.Log;


public class Admin extends BaseUser{

    @Override
    public String getPassword(){
        return this.password;
    }
    @Override
    public void issueBook(Book book){
        book.decrementAvailableCopies();
        var now = LocalDateTime.now();
        Issues issue = new Issues(now, now.plusDays(21), this, book);
        this.issueHistory.add(issue);
        Log.log(issue.toString());
        System.out.println(issue.toString());
    }
    @Override
    public void returnBook(Book book){

    }
    @Override
    public void deleteAccount(){

    }
    @Override
    public void searchBook(){

    }
    @Override
    public void viewIssues(){

    }
    @Override
    public void getReturnDates(){

    }
}
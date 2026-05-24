package libUsers;

import java.time.LocalDateTime;
import support.Book;
import support.Issues;
import support.Library;
import utils.Log;

public class Admin extends BaseUser{

    public Admin(){
        super();
    }

    public Admin(String username, String password, String phoneNumber, String email){
        super(username, password, phoneNumber, email);
    }

    @Override
    public void issueBook(Book book){
        if (book == null){
            System.out.println("Book not found.");
            return;
        }
        book.issueBook();
        var now = LocalDateTime.now();
        Issues issue = new Issues(now, now.plusDays(21), this, book);
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
        System.out.println("Book returned successfully.");
    }

    @Override
    public void deleteAccount(){
        super.deleteAccount();
    }

    public void addBook(String name, String author, int copies){
        Book.addBook(name, author, copies);
        System.out.println("Book added to library.");
    }

    public void removeBook(String name, String author){
        Book[] books = Library.parseLibrary();
        boolean found = false;
        for (int i = 0; i < books.length; i++){
            if (books[i] == null) break;
            if (books[i].name.equals(name) && books[i].author.equals(author)){
                books[i] = null;
                found = true;
                break;
            }
        }
        if (!found){
            System.out.println("Book not found.");
            return;
        }
        // compact the array
        Book[] updated = new Book[books.length];
        int j = 0;
        for (Book b : books){
            if (b != null) updated[j++] = b;
        }
        Library.update(updated);
        System.out.println("Book removed from library.");
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
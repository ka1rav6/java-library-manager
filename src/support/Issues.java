package support;
import java.time.LocalDateTime;
import libUsers.BaseUser;
/**
 * Class to show issuing statements of books
 */
public class Issues{
    public LocalDateTime dateIssued;
    public LocalDateTime returnDate;
    public BaseUser issuedBy;
    public Book book;

    public Issues(LocalDateTime dateIssued, LocalDateTime returnDate, BaseUser issuedBy, Book book){
        this.dateIssued = dateIssued;
        this.returnDate = returnDate;
        this.issuedBy = issuedBy;
        this.book = book;
    }
    @Override
    public String toString(){
        return "Book issued. Issue Date: " + this.dateIssued 
                + ". Return date: " + this.returnDate + ". Issued by: " + this.issuedBy
                + ". Book name: " + book.toFileString();
    }
}
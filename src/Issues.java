
import java.time.LocalDateTime;

public class Issues{
    LocalDateTime dateIssued;
    LocalDateTime returnDate;
    BaseUser issuedBy;
    Book book;

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
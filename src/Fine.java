
import java.time.LocalDateTime;


public class Fine{
    LocalDateTime time;
    LocalDateTime dateIssued;
    LocalDateTime returnDate;
    double amount;
    boolean isDeducted;
    private void calculateFine(Book book){
        this.amount = book.getPrice() * 0.1; //per day
    }
}
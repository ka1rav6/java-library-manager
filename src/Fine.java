import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Fine {
    LocalDateTime time;
    Issues issued;
    double amount;
    boolean isDeducted;
    private void calculateFine(Book book) {
        long daysLate = ChronoUnit.DAYS.between(
                issued.dateIssued,
                issued.returnDate
        );
        // 10% of book price per day
        this.amount = book.getPrice() * 0.1 * daysLate;
    }
    public Fine(Issues issue){
        this.issued = issue;
        this.calculateFine(issue.book);
    }
}
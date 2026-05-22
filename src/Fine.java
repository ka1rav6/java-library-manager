import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Fine {
    LocalDateTime time;
    Issues issued;
    double amount;
    boolean isDeducted;
    BaseUser user;
    private void calculateFine(Book book) {
        long daysLate = ChronoUnit.DAYS.between(issued.dateIssued, issued.returnDate);
        // 10% of book price per day
        this.amount = book.getPrice() * 0.1 * daysLate;
    }
    public Fine(Issues issue){
        this.issued = issue;
        this.calculateFine(issue.book);
        this.user = issue.issuedBy;
    }
    public void deductFine(){
        this.user.currentDeposit -= this.amount;
        this.time = LocalDateTime.now();
        String message = "User " + this.user.username + " fined Rs" + this.amount + " on" + this.time; 
        Log.log(message);
        this.user.fineHistory.add(this);
    }
}
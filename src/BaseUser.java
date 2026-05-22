abstract class BaseUser{
    public String username;
    private String password;
    private String phoneNumber;
    private Email emailId;
    public Issues[] issueHistory;
    private double currentDeposit;
    public abstract Fine[] fineHistory();
    public abstract void issueBook(Book book);
    public abstract void returnBook(Book book);
    public abstract void changePassword();
    public abstract void deleteAccount();
    public abstract void searchBook();
    public abstract void viewAvailableBooks();
    public abstract void getReturnDates();
    public abstract void getFineHistory();

}
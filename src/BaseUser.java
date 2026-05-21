abstract class BaseUser{
    public String username;
    private String password;
    private String phoneNumber;
    private Email emailId;
    public Book[] booksIssued;
    private double currentDeposit;
    public abstract void issueBook(Book book);
    public abstract void returnBook(Book book);
    public abstract void changePassword();
    

}
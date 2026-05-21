public class Book{
    public String name;
    public String author;
    public int copies; // default
    public boolean isIssued;

    public Book(String name, String author){
        this.name = name;
        this.author = author;
        this.copies = 1;
        this.isIssued = false;
    }


}
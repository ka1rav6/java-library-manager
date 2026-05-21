
public class Book{
    public String name;
    public String author;
    private int availableCopies; // default
    private static final int MAX_BOOKS = 1000000;

    /**
     * Note: the books are stored in books.txt in this format:
     * bookname, book-author, availablecopies
     */



    public Book(String name, String author){
        this.name = name;
        this.author = author;
        this.availableCopies = 1;
    }
    public Book(String name, String author, int availableCopies){
        this.name = name;
        this.author = author;
        this.availableCopies = availableCopies;
    }
    public void issueBook(){
        if (this.availableCopies == 0)
            System.out.println("This action is not possible. There are no more copies that exist of this book");
        else{
            this.availableCopies--;
            System.out.println("The book has been issued successfully.");
        }
    }



}
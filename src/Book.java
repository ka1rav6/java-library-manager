import java.io.FileWriter;
public class Book{
    public String name;
    public String author;
    private int availableCopies; // default

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

    public static Book partOfLibrary(String name, String author){
        Book[] books = Library.parseLibrary();
        for (var book: books){
            if (book.name.equals(name) && book.author.equals(author))
                return book;
        }
        return null;
    }
    public static void addBook(String name, String author, int availableCopies){
        var book = partOfLibrary(name, author);
        if (book != null)
            book.availableCopies += availableCopies;
            // TODO: make updater function
        else{
            try(FileWriter writer = new FileWriter(Library.getFileName(), true)){
                writer.write(name + "," + author + "," + availableCopies);
            }catch(Exception e){
                throw new RuntimeException("An exception occured: " + e);
            }
        }
    }


}
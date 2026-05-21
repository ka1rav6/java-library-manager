
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

    public static int partOfLibrary(String name, String author){
        Book[] books = Library.parseLibrary();
        int i = 0;
        for (var book: books){
            if (book.name.equals(name) && book.author.equals(author))
                return book;
            i++;
        }
        return -1;
    }
    public String toFileString(){
        return this.name + "," + this.author + "," + this.availableCopies;
    }
    @Override
    public String toString(){
        return "Book Name: " + this.name + "\n" + "Book Author: " + this.author + "\n" + "Available Copies: " + this. availableCopies + "\n";
    }

    public static void addBook(String name, String author, int availableCopies){
        var index = partOfLibrary(name, author);
        if (index != -1){
            var books = Library.parseLibrary();
            var book = books[index];
            book.availableCopies += availableCopies;
            books[index] = book;
            Library.update(books);
            }
        else{
            try(FileWriter writer = new FileWriter(Library.getFileName(), true)){
                writer.write((new Book(name, author, availableCopies)).toFileString());
            }catch(Exception e){
                throw new RuntimeException("An exception occured: " + e);
            }
        }
    }


}
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;   

public class Book{
    public String name;
    public String author;
    private int availableCopies; // default
    private static final int MAX_BOOKS = 1000000;
    private static final String FILE_NAME = "books.txt";
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
    private static Book parseLine(String line){
        int i = 0;
        String name = "";
        String author = "";
        String copies = "";
        while (i < line.length() && line.charAt(i) != ',')
            name += line.charAt(i++);
        i++;
        while (i < line.length() && line.charAt(i) != ',')
            author += line.charAt(i++);
        i++;
        while (i < line.length() && line.charAt(i) != ',')
            copies += line.charAt(i++);
        if (copies.isBlank())
            return new Book(name, author);
        return new Book(name, author, (int)Integer.parseInt(copies));
    }
    private static Book[] parseLibrary(){
        Book[] books = new Book[MAX_BOOKS];
        int i = 0;
        File file = new File(FILE_NAME);
        if (file.exists()){
            try (Scanner in = new Scanner(file)){
                while (in.hasNextLine())
                    books[i++] = parseLine(in.nextLine());
            }catch(Exception e){
                System.out.println("An exception occured: " + e);
            }
            return books;
        }else{
            throw new RuntimeException("The file: does not exist");
        }
    }
    public static Book partOfLibrary(String name, String author){
        Book[] books = parseLibrary();
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
            try(FileWriter writer = new FileWriter(FILE_NAME, true)){
                writer.write(name + "," + author + "," + availableCopies);
            }catch(Exception e){
                throw new RuntimeException("An exception occured: " + e);
            }
        }
    }


}
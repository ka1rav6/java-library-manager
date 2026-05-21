import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;   
public class Library{

    private static final int MAX_BOOKS = 1000000;
    private static final String FILE_NAME = "books.txt";

    public static String getFileName(){
        return FILE_NAME; 
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
    public static Book[] parseLibrary(){
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

    public static void update(Book[] books){
        try(FileWriter writer = new FileWriter(Library.getFileName())){
            for (var book: books){
                writer.write(book.toFileString());
            }
            }catch(Exception e){
                throw new RuntimeException("An exception occured: " + e);
            }
    }

    

}
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;   
public class Library{

    private static final int MAX_BOOKS = 1000000;
    private static final String FILE_NAME = "books.txt";

    public static String getFileName(){
        return FILE_NAME; 
    }

    private static Book parseLine(String line) {
        String[] d = line.split(",");
        if (d.length < 3)
            throw new RuntimeException("Details are incomplete.");
        return (d.length > 3) ? 
                new Book(d[0].strip(), d[1].strip(),Double.parseDouble(d[2].strip()),Integer.parseInt(d[3].strip()))
                : 
                new Book(d[0].strip(), d[1].strip(), Double.parseDouble(d[2].strip()));
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
package support;

import java.io.FileWriter;
import utils.Log;

public class Book{
    public String name;
    public String author;
    private int availableCopies; // default
    private double price;
    /**
     * Note: the books are stored in books.txt in this format:
     * bookname, book-author, availablecopies, price
     * why price is needed: price = deposit per book
     */
    public Book(String name, String author, double price){
        this.name = name;
        this.author = author;
        this.availableCopies = 1;
        this.price = price;
        Log.log("Created a new book of the name: " + this.toFileString());
    }
    public Book(String name, String author, double price, int availableCopies){
        this.name = name;
        this.author = author;
        this.availableCopies = availableCopies;
        this.price = price;
        Log.log("Created a new book of the name: " + this.toFileString());
    }
    public void decrementAvailableCopies(){
        this.availableCopies --;
    }
    public void incrementAvailableCopies(){
        this.availableCopies++;
    }
    public double getPrice(){
        return this.price;
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
            if (book == null) break;
            if (book.name.equals(name) && book.author.equals(author))
                return i;
            i++;
        }
        return -1;
    }
    public final String toFileString(){
        return this.name + "," + this.author + "," + this.availableCopies;
    }
    @Override
    public String toString(){
        return "Book Name: " + this.name + "\n" + "Book Author: " + this.author + "\n" + "Available Copies: " + this.availableCopies + "\n";
    }
    public static void addBook(String name, String author, int availableCopies){
        // for now even updating a small part of the book requires rewriting the whole library. 
        //Planning to change this by introducing indexes to books: trying to simulate "shelves"
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
                writer.write(new Book(name, author, 0, availableCopies).toFileString() + "\n");
            }catch(Exception e){
                throw new RuntimeException("An exception occured: " + e);
            }
        }
    }


}
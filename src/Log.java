
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Log{
    public static void log(String message){
        try(FileWriter myWriter = new FileWriter("filename.txt")) {
            message = createLog(message);
            myWriter.write(message);
            System.out.println("Action logged successfully");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }
    }
    private static String createLog(String message){
        message = LocalDateTime.now() + ":\r\n" + message + "\r\n\r\n";
        return message;
    }
}
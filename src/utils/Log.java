package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Log{
    private static final String LOG_FILE = "log.txt";

    public static void log(String message){
        try(FileWriter myWriter = new FileWriter(LOG_FILE, true)) {
            message = createLog(message);
            myWriter.write(message);
        } catch (IOException e) {
            System.out.println("An error occurred while logging: " + e);
        }
    }
    private static String createLog(String message){
        message = LocalDateTime.now() + ":\n" + message + "\n\n";
        return message;
    }
}
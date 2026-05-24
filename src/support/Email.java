package support;
public class Email{
    public String address;
    public Email(String string){
        if (!isValid(string))
            throw new RuntimeException("This email does not exist");
        this.address = string;
    }
    private static boolean isValid(String email){
        // Must contain exactly one '@'
        int atIndex = email.indexOf('@');
        int dotIndex = email.indexOf('.', atIndex);
        if (atIndex == -1 || atIndex != email.lastIndexOf('@')) 
            return false;
        if (atIndex == 0 || atIndex == email.length() - 1) 
            return false;
        if (dotIndex == -1 || dotIndex == email.length() - 1)
            return false;
        
        return true;
        }
}

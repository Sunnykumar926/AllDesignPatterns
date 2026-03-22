import java.util.*;

class User{
    public String name;
    public boolean isPremium;
    public User(String name, boolean isPremium){
        this.name = name;
        this.isPremium = isPremium;
    }
}
interface IDocumentReader{
    public void unlockPDF(String file, String password);
}

class RealDocumentReader implements IDocumentReader{
    @Override
    public void unlockPDF(String filePath, String password){
        System.out.println("[RealDocumentReader] Unlocking PDF at: "+ filePath);
        System.out.println("[RealDocumentReader] PDF unlocked successfully with password: "+password);
        System.out.println("[RealDocumentReader] Displaying PDF content...");
    }
}

class DocumentProxy implements IDocumentReader{
    private RealDocumentReader reader;
    private User user;
    public DocumentProxy(User user){
        this.user = user;
        this.reader = new RealDocumentReader();
    }
    @Override
    public void unlockPDF(String filePath, String password){
        if(!user.isPremium){
            System.out.println("[DocumentProxy] Access denied. Only premium members can unlock PDFs.");
            return;
        }
        reader.unlockPDF(filePath, password);
    }
}
public class ProtectionProxy{
    public static void main(String[] args) {
        User user1 = new User("Deepak", false);
        User user2 = new User("Abhishek", true);

        System.out.println("\n== Deepak (Non Premium) unlocks PDF ==");
        IDocumentReader docReader = new DocumentProxy(user1);
        docReader.unlockPDF("protected_document.pdf", "12@13S");

        System.out.println("\n== Abhishek (Premium) unlocks PDF ==");
        docReader = new DocumentProxy(user2);
        docReader.unlockPDF("protected_document.pdf", "secret123");
    }

}
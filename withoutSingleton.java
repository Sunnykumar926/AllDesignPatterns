import java.util.*;

class NoSingleton{
    public NoSingleton(){
        System.out.println("Singleton constructor called.\n each time new object created");
    }
}

public class withoutSingleton{

    public static void main(String[] args) {
        NoSingleton s1 = new NoSingleton();
        NoSingleton s2 = new NoSingleton();

        // address of each object in heap would be different
        System.out.println(s1==s2);
    }
}
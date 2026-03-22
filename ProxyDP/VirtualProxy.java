import java.util.*;

interface IImage{
    public void display();
}

class RealImage implements IImage{
    private String fileName;
    public RealImage(String fileName){
        this.fileName=fileName;
    }
    @Override
    public void display(){
        System.out.println("[Image] displaying: "+ fileName);
    }
}

class ImageProxy implements IImage{
    private String fileName;
    private RealImage realImg;
    public ImageProxy(String fileName){
        this.fileName = fileName;
        this.realImg = null;
    }

    @Override
    public void display(){
        if(realImg==null){
            realImg = new RealImage(fileName);
        }
        realImg.display();
    }
}

public class VirtualProxy{
    public static void main(String[] args){
        IImage image1 = new ImageProxy("fig.jpg");
        image1.display();
    }
}
import java.util.*;

interface ISubscriber{
    public void update();
}

interface IChannel{
    public void subscribe(ISubscriber subscriber);
    public void unsubscribe(ISubscriber subscriber);
    public void notifySubscriber();
}

class Channel implements IChannel{
    String name;
    String latestVideo;
    List<ISubscriber> subscribers;
    public Channel(String name){
        this.name = name;
        this.subscribers = new ArrayList<>();
    }
    @Override
    public void subscribe(ISubscriber subscriber){
        if(!subscribers.contains(subscriber)){
            subscribers.add(subscriber);
        }
    }
    @Override
    public void unsubscribe(ISubscriber subscriber){
        subscribers.remove(subscriber);
    }
    @Override
    public void notifySubscriber(){
        for(ISubscriber subscriber : subscribers){
            subscriber.update();
        }
    }
    public void uploadVideo(String title){
        latestVideo = title;
        System.out.println("\n["+ name + " uploaded \""+ title +"\"]");
        notifySubscriber();
    }
    public String getVideoData(){
        return "\nCheckout our new Video : "+ latestVideo + "\n";
    }
}

class Subscriber implements ISubscriber{
    private String name;
    private Channel channel;
    public Subscriber(String name, Channel channel){
        this.name = name;
        this.channel = channel;
    }
    @Override
    public void update(){
        System.out.println("Hey "+name+" ,"+ channel.getVideoData());
    }
}
public class ObserverDesignPattern{
    public static void main(String[] args){

        Channel channel = new Channel("coderArmy");
        Subscriber sub1 = new Subscriber("Sunny", channel);
        Subscriber sub2 = new Subscriber("Anup", channel);
        Subscriber sub3 = new Subscriber("Avinash", channel);

        channel.subscribe(sub1);
        channel.subscribe(sub2);
        channel.subscribe(sub3);

        channel.uploadVideo("ODP tutorial");

        channel.unsubscribe(sub1);
        channel.uploadVideo("SDP tutorial part 1");
        
    }
}

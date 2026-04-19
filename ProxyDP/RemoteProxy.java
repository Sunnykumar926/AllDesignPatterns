import java.util.*;

interface IDataService{
    public void fetchData();
}

class RealDataService implements IDataService{
    public RealDataService(){
        System.out.println("Intializing the real data server...");
    }
    @Override
    public void fetchData(){
        System.out.println("[Real Data Server]: fetching data from real data server...");
    }
}

class ProxyDataService implements IDataService{
    private RealDataService rds;
    public ProxyDataService(){
        rds = new RealDataService();
    }
    @Override
    public void fetchData(){
        System.out.println("Connecting to the server");
        rds.fetchData();
    }
}
public class RemoteProxy{
    public static void main(String[] args) {
        IDataService dataservice = new ProxyDataService();
        dataservice.fetchData();
    }
}


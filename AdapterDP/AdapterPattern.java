package AdapterDP;

interface IReports{
  public String getJSONData(String data);
}

class XMLDataProvider{
  public String getXMLData(String raw){
    int indx = raw.indexOf(":");
    String name = raw.substring(0, indx);
    String id = raw.substring(indx+1);

    return "<user>"
            +  "<name>" + name + "</name>" 
            +  "<id>" + id + "</id>"
            + "</user>";
  }
}

class XMLDataProviderAdapter implements IReports{
  private XMLDataProvider adaptee;
  public XMLDataProviderAdapter(XMLDataProvider adaptee){
    this.adaptee = adaptee;
  }
  @Override
  public String getJSONData(String data){
    String xmlData = adaptee.getXMLData(data);
    System.out.println(xmlData);

    int startName = xmlData.indexOf("<name>")+6;

    int endName = xmlData.indexOf("</name>");
    
    int startId = xmlData.indexOf("<id>")+4;
    int endId = xmlData.indexOf("</id>");
    

    String name = xmlData.substring(startName, endName);
    String id = xmlData.substring(startId, endId);
    return "{\"name\":\"" + name + "\", \"id\":" + id + "}";
  }
}

class Client{
  public void getReport(IReports report, String data){
    System.out.println("Processed JSON: "+ report.getJSONData(data));
  }
}
public class AdapterPattern{
  public static void main(String[] args){
    XMLDataProvider adaptee = new XMLDataProvider();
    XMLDataProviderAdapter adapter = new XMLDataProviderAdapter(adaptee);

    Client client = new Client();
    String rawData = "Alice:42";
    client.getReport(adapter, rawData);
  }
}

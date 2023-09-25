import javax.xml.transform.sax.SAXSource;

public class HeavyContainer extends Container{

    final public String type = "H";
    public HeavyContainer(int serialNumber, int weight, String portId){
        super(serialNumber, weight, portId);
        System.out.println("Heavy Container was successfully built");

    }
}

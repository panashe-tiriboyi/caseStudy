import javax.xml.transform.sax.SAXSource;

public class HeavyContainer extends Container{

    final public String typeC = "H";
    public HeavyContainer(int serialNumber, int weight, String portId){
        super(serialNumber, weight, portId);
        this.type = typeC;
        System.out.println(GREEN + "Heavy Container, serial number : "+ serialNumber + " at port " + portId +" was successfully built. " + RESET);

    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
        //System.out.println(GREEN + "Heavy Container, serial number : "+ serialNumber + " weight was changed to : " + weight + RESET);

    }


}

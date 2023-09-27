public class RefrigeratedContainer extends HeavyContainer{
    final public String typeC = "R";
    public RefrigeratedContainer(int serialNumber, int weight, String portId){
        super(serialNumber, weight, portId);
        this.type = typeC;
        System.out.println("Refrigerated Container, serial number : "+ serialNumber + " at port " + portId +"was changed to : " + weight);
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
        //System.out.println("Refrigerated Container, serial number : "+ serialNumber + " weight was changed to : " + weight);

    }
}

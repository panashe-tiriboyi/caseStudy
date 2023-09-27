public class LiquidContainer extends HeavyContainer{
    final public String typeC = "L";
    public LiquidContainer(int serialNumber, int weight, String portId){
        super(serialNumber, weight, portId);
        this.type = typeC;
        System.out.println(GREEN + "Liquid Container, serial number : "+ serialNumber + " at port " + portId + " was successfully built. " + weight + RESET);
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
        //System.out.println(GREEN +"Liquid Container, serial number : "+ serialNumber + " weight was changed to : " + weight + RESET);

    }
}

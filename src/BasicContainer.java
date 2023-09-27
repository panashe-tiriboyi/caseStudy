public class BasicContainer extends  Container{
    final public String typeC = "B";
    BasicContainer(int serialNumber, int weight, String portId) {
        super(serialNumber, weight, portId);
        this.type = typeC;
        //System.out.println(GREEN + "Basic Container, serial number : " + serialNumber+ " at port " + portId +" was successfully built." + RESET);
    }


    @Override
    public void setWeight(int weight) {
        super.setWeight(weight);
        System.out.println(GREEN + "Basic Container, serial number : "+ serialNumber + " weight was changed to : " + weight + RESET);
    }
}

public class BasicContainer extends  Container{
    final public String type = "B";
    BasicContainer(int serialNumber, int weight, String portId) {
        super(serialNumber, weight, portId);
        System.out.println("Basic Container was successfully built");
    }


    @Override
    public void setWeight(int weight) {
        super.setWeight(weight);
        System.out.println("Basic Container was successfully built");  System.out.println("Basic Container was successfully built");
    }
}

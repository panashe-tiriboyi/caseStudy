public abstract class Container {
    protected int serialNumber;
    protected int weight;

    protected String type;


    // Port id where container is currently at

    public Container(int serialNumber, int weight){
        this.serialNumber= serialNumber;
        this.weight=weight;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getType() {
        return type;
    }
}

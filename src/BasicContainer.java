public class BasicContainer extends  Container{
    final public String type = "B";
    public BasicContainer(int serialNumber, int weight){

        super(serialNumber, weight);
    }

    @Override
    public void setWeight(int weight) {
        if (weight > 5000 ) {
            throw new IllegalArgumentException("Weight cannot exceed 5000t");
        }
        super.setWeight(weight);
    }
}

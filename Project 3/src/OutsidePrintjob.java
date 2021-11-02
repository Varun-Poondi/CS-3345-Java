public class OutsidePrintjob extends Printjob{
    private double printCost;

    public OutsidePrintjob(String userName, int userPriority, int numberOfPages, String jobType) {
        super(userName, userPriority, numberOfPages, jobType);
    }

    public double getPrintCost() {
        return printCost;
    }

    public void setPrintCost(double printCost) {
        this.printCost = printCost;
    }
    public void calculateCost(){
        setPrintCost(0.1 * this.getNumberOfPages());
    }
}

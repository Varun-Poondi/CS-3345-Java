public class OutsidePrintjob extends Printjob{
    private double printCost;

    public OutsidePrintjob(String userName, int userPriority, int numberOfPages, String jobType) {
        super(userName, userPriority, numberOfPages, jobType); // call super since you extend from Printjob
        calculateCost(); // calculate the cost for an O Job
    }

    public double getPrintCost() {
        return printCost;
    }

    public void setPrintCost(double printCost) {
        this.printCost = printCost;
    }
    public void calculateCost(){
        setPrintCost(0.1 * this.getNumberOfPages()); // 10 cents * # of pages
    }

    @Override
    public String toString() {
        String superString = super.toString(); // get the super toString since we need to append to it
        return superString + (", Calculated Cost: $ " + getPrintCost()); // append to string
    }
}

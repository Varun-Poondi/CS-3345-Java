/*
* A job to print should be represented by a class named Printjob.  This class
   should contain the user's name, the user's priority, and number of pages.  It 
   should implement Comparable with compareTo based on job priority. 
* */
public class Printjob implements Comparable<Printjob> {
    private String userName;
    private int userPriority;
    private int numberOfPages;
    private int jobPriority;
    private String jobType;

    public Printjob(String userName, int userPriority, int numberOfPages, String jobType) {
        this.userName = userName;
        this.userPriority = userPriority;
        this.numberOfPages = numberOfPages;
        this.jobType = jobType;
        calculateJobPriority(); // do this for every object created
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserPriority() {
        return userPriority;
    }

    public void setUserPriority(int userPriority) {
        this.userPriority = userPriority;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getJobPriority() {
        return jobPriority;
    }

    public void setJobPriority(int jobPriority) {
        this.jobPriority = jobPriority;
    }

    public void calculateJobPriority(){
     setJobPriority(this.userPriority * this.numberOfPages);   // given formula
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    @Override
    public String toString() { // simple toString() method
        String printString = "";
        printString = 
                "userName: '" + userName + '\'' +
                ", userPriority: " + userPriority +
                ", numberOfPages: " + numberOfPages +
                ", jobPriority: " + jobPriority + 
                ", jobType: " + jobType;
        return printString;
    }

    @Override
    public int compareTo(Printjob o) {
        if(this.jobPriority < o.jobPriority){ // if the currentJob has higher presidency over the compared job, 
            return -1; // return -1 so we can percolate up
        }else if(this.jobPriority == o.jobPriority){ // if they are the same, priority
            return 0; // return 0 so we can percolate down, doesn't matter since they are the same
        }
        return 1; // return 1 so we can percolate down, the current job has lower presidency than the compared job.
    }
}

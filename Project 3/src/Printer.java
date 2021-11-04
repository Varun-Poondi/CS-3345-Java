import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Printer {
    public static BinaryHeap<Printjob> heap = new BinaryHeap<>();
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        String fileName;
        fileName = input.next();
        File fileObj = new File(fileName);
        
        fileName = fileChecker(fileObj, fileName, input);
        Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName))); //sc used to read the fileName
        fileReader(sc);
        
        System.out.println("Order of Job Completion: ");
        heap.printHeap();
    }
    public static String fileChecker(File fileObj, String fileName, Scanner input){
        boolean fileIsReadable = false;
        while(!fileIsReadable) {
            try {
                if (!fileObj.canRead()) {
                    throw new FileNotFoundException();
                } else {
                    fileIsReadable = true;
                }
            } catch (Exception e) {
                System.out.println("File was not found, please try again.");
                fileName = input.next();
                fileObj = new File(fileName); //ask for file input again to be tested
            }
        }
        return fileName;
    }
    public static void fileReader(Scanner sc){
        while(sc.hasNextLine()){
            Scanner sc1 = new Scanner(sc.nextLine());
            String name;
            int priority;
            int pages;
            String flag;
            
            while(sc1.hasNext()) {
                name = sc1.next();
                priority = Integer.parseInt(sc1.next());
                pages = Integer.parseInt(sc1.next());
                flag = sc1.next();
                if(flag.equals("O")){
                    OutsidePrintjob newJob = new OutsidePrintjob(name, priority, pages, flag);
                    heap.insert(newJob);
                }else{
                    Printjob newJob = new Printjob(name, priority, pages, flag);
                    heap.insert(newJob);
                }
            }
        }
    }
    
}

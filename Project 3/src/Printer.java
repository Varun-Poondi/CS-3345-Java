import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Printer {
    public static BinaryHeap<Printjob> heap = new BinaryHeap<>();
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        String fileName;
        fileName = input.next();
        Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName))); //sc used to read the fileName
        fileReader(sc);
        System.out.println("Current Heap:");
        heap.printHeap();
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

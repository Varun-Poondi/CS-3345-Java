/*
Name:   Varun Poondi
Net-ID: VMP190003
Prof:   Greg Ozbrin 
Date:   11/4/2021
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Printer {
    
    public static BinaryHeap<Printjob> heap = new BinaryHeap<>();   // declare binary heap
    public static void main(String[] args) throws FileNotFoundException {
        System.out.print("File Name: ");
        Scanner input = new Scanner(System.in); // get file name
        String fileName;        
        fileName = input.next();
        File fileObj = new File(fileName);
        fileName = fileChecker(fileObj, fileName, input);   // check if the file exists, else ask for it again
        Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName))); //sc used to read the fileName
        fileReader(sc); // read the file
        
        
        System.out.println("Current Heap Size: " + heap.currentHeapSize()); // print current heap size, should be 13 since there are 13 entries
        System.out.println("Order of Job Completion: ");    
        heap.printHeap();   // print our heap in minOrder and delete the min Val
        System.out.println("Current Heap Size: " + heap.currentHeapSize()); // should be 0, since the heap is now empty and all the jobs are complete
    }
    
    public static String fileChecker(File fileObj, String fileName, Scanner input){
        boolean fileIsReadable = false;
        while(!fileIsReadable) {
            try {
                if (!fileObj.canRead()) {   // if the file is unreadable 
                    throw new FileNotFoundException();  // throw exception
                } else {
                    fileIsReadable = true;  // else the file is readable and exit the while loop
                }
            } catch (Exception e) {
                System.out.print("File was not found, please try again.\nFile Name: "); 
                fileName = input.next(); // get the next input
                fileObj = new File(fileName); //ask for file input again to be tested
            }
        }
        return fileName; // return valid file name
    }
    public static void fileReader(Scanner sc){ 
        while(sc.hasNextLine()){ // traverse through lines
            Scanner sc1 = new Scanner(sc.nextLine()); 
            String name;
            int priority;
            int pages;
            String flag;
            
            while(sc1.hasNext()) { // gets data from individual line
                name = sc1.next(); 
                priority = Integer.parseInt(sc1.next());
                pages = Integer.parseInt(sc1.next());
                flag = sc1.next();
                if(flag.equals("O")){ // If outside job
                    OutsidePrintjob newJob = new OutsidePrintjob(name, priority, pages, flag); // create a outside job object
                    heap.insert(newJob); // add to the heap
                }else{
                    Printjob newJob = new Printjob(name, priority, pages, flag); // create a Print job object
                    heap.insert(newJob); // add to the heap
                }
            }
        }
    }
}

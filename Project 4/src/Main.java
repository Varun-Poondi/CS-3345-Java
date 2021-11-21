import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main{
    public static void main(String[] args) {
        System.out.print("File Name: ");
        Scanner input = new Scanner(System.in); // get file name
        String fileName;
        fileName = input.next();
        File fileObj = new File(fileName);
        fileName = fileChecker(fileObj, fileName, input); // check if the file exists, else ask for it again

        System.out.println(fileName + " is a valid file");
    }
    
    public static String fileChecker(File fileObj, String fileName, Scanner input) {
        boolean fileIsReadable = false;
        while (!fileIsReadable) {
            try {
                if (!fileObj.canRead()) { // if the file is unreadable
                    throw new FileNotFoundException(); // throw exception
                } else {
                    fileIsReadable = true; // else the file is readable and exit the while loop
                }
            } catch (Exception e) {
                System.out.print("File was not found, please try again.\nFile Name: ");
                fileName = input.next(); // get the next input
                fileObj = new File(fileName); // ask for file input again to be tested
            }
        }
        return fileName; // return valid file name
    }


}
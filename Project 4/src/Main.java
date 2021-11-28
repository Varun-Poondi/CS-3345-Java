//Name: Varun Poondi
//Net-ID: VMP190003
// Prof: Greg Ozbrin
//Date: 11/23/2021


import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
       int length;
       int width;
       Scanner getInfo = new Scanner(System.in);
       
        System.out.println("Kruskal's Random Maze Algorithm");
        System.out.println("-------------------------------");
        // get user input
        System.out.print("Enter Length: ");
        length = getInfo.nextInt();
        System.out.print("Enter Width: ");
        width = getInfo.nextInt();

        // create your random maze and display it
        Maze maze = new Maze(length, width);
        System.out.println();
        maze.createRandomMaze();
        maze.printMaze();
        
    }

}
    
    
    
    


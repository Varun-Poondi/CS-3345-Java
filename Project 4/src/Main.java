import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       int length;
       int width;
       Scanner getInfo = new Scanner(System.in);

        System.out.print("Enter Length: ");
        length = getInfo.nextInt();
        System.out.print("Enter Width: ");
        width = getInfo.nextInt();

        Maze maze = new Maze(length, width);
        maze.createRandomMaze();
        maze.printMaze();
        
    }

}
    
    
    
    


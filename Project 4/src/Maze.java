import java.util.*;

public class Maze {
    private final DisjSets disjSets;
    private final Cells[][] maze;
    private final int length;
    private final int width;
    
    
    public Maze(int length, int width) {
        this.length = length;
        this.width = width;
        maze = new Cells[length][width]; // Create a 2-D matrix of Cells
        disjSets = new DisjSets(length*width); // init the disjSet
        initMaze(); // init the maze
    }
    
    private void initMaze(){
        int counter = 0;
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[i].length; j++){
                Cells cell = new Cells(i, j, length, width, counter); // add a cell to each index in the maze
                counter ++;  // need to counter to give each cell a particular index
                maze[i][j] = cell;
            }
        }
    }
    public void createRandomMaze(){
        Random dRandom = new Random();
        int numberOfUnionsRequired = length*width - 1;
        int counter = 0;
        while(disjSets.find(0) != disjSets.find((length * width) - 1) && counter != numberOfUnionsRequired) { // if there isn't a path between the start and finish, keep on mining
            //get me a random x and y between 0 and 2
            
            int x = dRandom.nextInt(length);
            int y = dRandom.nextInt(width);
            
            Cells currentCell = maze[x][y];
            //System.out.println(currentCell.getIndexNumber());
            if (x != length-1 || y != width-1) { // if it is not the last cell, then mine. (there is nothing to change in the last cell)
                int root1 = disjSets.find(currentCell.getIndexNumber()); // get the root of the currentCell
                int root2 = 0;
                boolean updateMaze = false;
                int decision;
                switch (currentCell.getCaseType()) { 
                         // Can only merge right
                    case "FIRST":
                    case "R":
                    case "TR":
                        if ((x + 1) <= length - 1 && currentCell.getBottomBar().equals("__")) {
                            root2 = disjSets.find(maze[x + 1][y].getIndexNumber()); // get the root of the cell to the bottom
                            if(root1 != root2) {
                                currentCell.setBottomBar("  ");
                                updateMaze = true;
                            }
                        }
                        break;
                        // Can only merge down    
                    case "B":
                    case "BL":
                        if ((y + 1) <= width - 1 && currentCell.getRightBar().equals("|")) {
                            root2 = disjSets.find(maze[x][y + 1].getIndexNumber()); // get the root of the cell to the right
                            if(root1 != root2) {
                                currentCell.setRightBar(" ");
                                updateMaze = true;
                            }
                        }
                        break;
                        // can merge right or down
                    case "T":
                    case "L":
                    case "M":
                        decision = dRandom.nextInt(2);
                        if (decision == 1 && (x + 1) <= length - 1 && currentCell.getBottomBar().equals("__")) {
                            root2 = disjSets.find(maze[x + 1][y].getIndexNumber()); // get the root of the cell to the bottom
                            if(root1 != root2) {
                                currentCell.setBottomBar("  ");
                                updateMaze = true;
                            }
                        } else if ((y + 1) <= width - 1 && currentCell.getRightBar().equals("|")) {
                            root2 = disjSets.find(maze[x][y + 1].getIndexNumber()); // get the root of the cell to the right
                            if(root1 != root2) {
                                currentCell.setRightBar(" ");
                                updateMaze = true;
                            }
                        }
                        break;
                    default:
                        break;
                }
                // if the maze has been updated, update the maze
                if (updateMaze) {
                    disjSets.union(root1, root2); // union both roots
                    counter ++;
                }
            }
        }
    }
    public void printMaze(){
        for (Cells[] cells : maze) {
            for (Cells cell : cells) {
                System.out.print(cell.getTopBar()); // print out the top bar first
            }
            System.out.println();
            for (Cells cell : cells) {
                System.out.print(cell.getLeftBar() + cell.getBottomBar() + cell.getRightBar()); // print out the rest of the shape
            }
        }
    }
}

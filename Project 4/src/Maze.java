import java.util.*;

public class Maze {
    private final DisjSets disjSets;
    private final Cells[][] maze;
    private final int length;
    private final int width;
    
    
    public Maze(int length, int width) {
        this.length = length;
        this.width = width;
        maze = new Cells[length][width];
        disjSets = new DisjSets(length*width);
        initMaze();
    }
    
    private void initMaze(){
        int counter = 0;
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[i].length; j++){
                Cells cell = new Cells(i, j, length, width, counter);
                counter ++;
                maze[i][j] = cell;
            }
        }
    }
    public void createRandomMaze(){
        Random dRandom = new Random();
        while(disjSets.find(0) != disjSets.find((length * width) - 1)) {
            //get me a random x and y between 0 and 2
            int x = randomNumberWithRange(length - 1);
            int y = randomNumberWithRange(width - 1);

            Cells currentCell = maze[x][y];
            //System.out.println(currentCell.getIndexNumber());
            if (x != 2 || y != 2) {
                int root1 = disjSets.find(currentCell.getIndexNumber());
                int root2 = 0;
                boolean updateMaze = false;
                int decision;
                switch (currentCell.getCaseType()) {
                    // Can only merge right
                    case "FIRST":
                    case "R":
                    case "TR":
                        if ((x + 1) <= length - 1 && currentCell.getBottomBar().equals("__")) {
                            root2 = disjSets.find(maze[x + 1][y].getIndexNumber());
                            if(root1 != root2) {
                                currentCell.setBottomBar("  ");
                                updateMaze = true;
                            }
                        }
                        break;
                    case "B":
                    case "LB":
                        if ((y + 1) <= width - 1 && currentCell.getRightBar().equals("|")) {
                            root2 = disjSets.find(maze[x][y + 1].getIndexNumber());
                            if(root1 != root2) {
                                currentCell.setRightBar(" ");
                                updateMaze = true;
                            }
                        }
                        break;
                    case "T":
                    case "L":
                    case "M":
                        decision = dRandom.nextInt(2);
                        if (decision == 1 && (x + 1) <= length - 1 && currentCell.getBottomBar().equals("__")) {
                            root2 = disjSets.find(maze[x + 1][y].getIndexNumber());
                            if(root1 != root2) {
                                currentCell.setBottomBar("  ");
                                updateMaze = true;
                            }
                        } else if ((y + 1) <= width - 1 && currentCell.getRightBar().equals("|")) {
                            root2 = disjSets.find(maze[x][y + 1].getIndexNumber());
                            if(root1 != root2) {
                                currentCell.setRightBar(" ");
                                updateMaze = true;
                            }
                        }
                        break;
                    default:
                        break;
                }
                if (updateMaze) {
                    disjSets.union(root1, root2);
                }
            }
        }
    }
    public void printMaze(){
        for (Cells[] cells : maze) {
            for (Cells cell : cells) {
                System.out.print(cell.getTopBar());
            }
            System.out.println();
            for (Cells cell : cells) {
                System.out.print(cell.getLeftBar() + cell.getBottomBar() + cell.getRightBar());
            }
        }
    }
    
    public String toString(){
        return null;
    }
    private static int randomNumberWithRange(int max) {
        
        Random r = new Random();
        return r.nextInt((max) + 1);
    }
    
    
}

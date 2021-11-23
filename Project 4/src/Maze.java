import java.util.*;

public class Maze {
    private final DisjSets disjSets;
    private Cells[][] maze;
    private int length;
    private int width;
    private final ArrayList<Cells> walls = new ArrayList<>();
    
    
    public Maze(int length, int width) {
        this.length = length;
        this.width = width;
        maze = new Cells[length][width];
        disjSets = new DisjSets(length*width);
        initMaze();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Cells[][] getMaze() {
        return maze;
    }

    public void setMaze(Cells[][] maze) {
        this.maze = maze;
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
            int x = getRandomNumberInRange(0, length - 1);
            int y = getRandomNumberInRange(0, length - 1);

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
        for (int i = 0; i < maze.length; i++) {
            for (int a = 0; a < maze[i].length; a++) {
                System.out.print(maze[i][a].getTopBar());
            }
            System.out.println();
            for (int a = 0; a < maze[i].length; a++) {
                System.out.print(maze[i][a].getLeftBar() + maze[i][a].getBottomBar() + maze[i][a].getRightBar());
            }
        }
    }
    public void printCaseType(){
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[i].length; j++){
                System.out.print(maze[i][j].getCaseType() + "\t");
            }
            System.out.println();
        }
    }
    
    public String toString(){
        return null;
    }
    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    
    
}

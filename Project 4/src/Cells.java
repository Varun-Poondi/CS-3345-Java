public class Cells{
    private String topBar;
    private String leftBar;
    private String rightBar;
    private String bottomBar;
    private String caseType;
    private final int indexNumber;
    
    
    public Cells(int x, int y, int l, int w, int indexNumber) {
        setShape(x,y,l,w);
        this.indexNumber = indexNumber;
        findLocation(x,y,l,w);
    }
    
    public void setShape(int x, int y, int l, int w){
        if((x == 0 && y == 0) || (x == l-1 && y == w-1)) { // if the cell is the entrance or exit
            setTopBar("   ");
            setRightBar("");
            setLeftBar(" ");
            if((x == 0 && y == 0)) {
                setBottomBar("__");
            }else{
                setBottomBar("");
            }
        }else if(x==0 && y==1) { // if is need to keep grid aligned 
            setTopBar(" __");
            setRightBar("|");
            setLeftBar("|");
            setBottomBar("__");
        }else if(x==0 && y<=w-1) { // if the cell is located at the top and isn't the buffer cell
            setTopBar(" __");
            setRightBar("|");
            setLeftBar("");
            setBottomBar("__");
        }else if(x>=1 && y == 0){ // if the cell is located on the left side
            setTopBar(" ");
            setRightBar("|");
            setLeftBar("|");
            setBottomBar("__");
        }else{ // all the other cells have the same shape
            setTopBar(" ");
            setRightBar("|");
            setLeftBar("");
            setBottomBar("__");
        }
    }
    
    public void findLocation(int x, int y, int l, int w){
        //first 4 ifs are special cases
        if((x == 0 && y == 0)) { // if first cell
            caseType = "FIRST";
        }else if((x == l-1 && y == w-1)){ // if last cell
            caseType = "LAST";
        }else if (x == l-1 && y ==0){ // if cell is located bottom-left
            caseType = "BL";
        }else if (x == 0 && y == w-1) { // if cell is located top-right
            caseType = "TR";
        }else if(x == 0 && y > 0) { // if cell is located at the top
            caseType = "T";
        }else if(x == l-1 && y > 0){ // if cell is located at the bottom
            caseType = "B";
        }else if(x > 0 && y == 0){ // if cell is located at the left
            caseType = "L";
        }else if(x > 0 && y== w-1){ // if cell is located at the right
            caseType = "R";
        }else{
            caseType = "M"; // the cell is located in the middle
        }
    }
    
    public String getTopBar() {
        return topBar;
    }

    public void setTopBar(String topBar) {
        this.topBar = topBar;
    }

    public String getLeftBar() {
        return leftBar;
    }

    public void setLeftBar(String leftBar) {
        this.leftBar = leftBar;
    }

    public String getRightBar() {
        return rightBar;
    }

    public void setRightBar(String rightBar) {
        this.rightBar = rightBar;
    }

    public String getBottomBar() {
        return bottomBar;
    }
    

    public void setBottomBar(String bottomBar) {
        this.bottomBar = bottomBar;
    }
    
    public String getCaseType() {
        return caseType;
    }
    
    public int getIndexNumber() {
        return indexNumber;
    }
}

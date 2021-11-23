public class Cells implements Comparable<Cells>{
    private String topBar;
    private String leftBar;
    private String rightBar;
    private String bottomBar;
    private String finalShape;
    private String caseType;
    private int xPos;
    private int yPos;
    private int indexNumber;
    
    
    


    public Cells(int x, int y, int l, int w, int indexNumber) {
        setShape(x,y,l,w);
        this.xPos = x;
        this.yPos = y;
        this.indexNumber = indexNumber;
        findMerge(x,y,l,w);
    }
    public void setShape(int x, int y, int l, int w){
        if((x == 0 && y == 0) || (x == l-1 && y == w-1)) {
            setTopBar("   ");
            setRightBar("");
            setLeftBar(" ");
            if((x == 0 && y == 0)) {
                setBottomBar("__");
            }else{
                setBottomBar("");
            }
        }else if(x==0 && y==1) {
            setTopBar(" __");
            setRightBar("|");
            setLeftBar("|");
            setBottomBar("__");
        }else if(x==0 && y<=w-1) {
            setTopBar(" __");
            setRightBar("|");
            setLeftBar("");
            setBottomBar("__");
        }else if(x>=1 && y ==0){
            setTopBar(" ");
            setRightBar("|");
            setLeftBar("|");
            setBottomBar("__");
        }else{
            setTopBar(" ");
            setRightBar("|");
            setLeftBar("");
            setBottomBar("__");
        }
    }
    public void findMerge(int x, int y, int l, int w){
        if((x == 0 && y == 0)) {
            caseType = "FIRST";
        }else if((x == l-1 && y == w-1)){
            caseType = "LAST";
        }else if (x == l-1 && y ==0){
            caseType = "LB";
        }else if (x == 0 && y == w-1) {
            caseType = "TR";
        }else if(x == 0 && y > 0) {
            caseType = "T";
        }else if(x == l-1 && y > 0){
            caseType = "B";
        }else if(x > 0 && y == 0){
            caseType = "L";
        }else if(x > 0 && y== w-1){
            caseType = "R";
        }else{
            caseType = "M";
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

    public String getFinalShape() {
        return finalShape;
    }

    public void setFinalShape(String finalShape) {
        this.finalShape = finalShape;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    @Override
    public String toString() {
        finalShape = getTopBar()+"\n"+getLeftBar()+getBottomBar()+getRightBar();
        return finalShape;
    }

    @Override
    public int compareTo(Cells o) {
        return 0;
    }
    
}

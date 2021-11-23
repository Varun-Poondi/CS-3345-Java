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
    
    public String getCaseType() {
        return caseType;
    }
    
    public int getIndexNumber() {
        return indexNumber;
    }

    @Override
    public String toString() {
        return  getTopBar()+"\n"+getLeftBar()+getBottomBar()+getRightBar();
    }
    
}

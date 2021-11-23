import java.util.Arrays;

public class DisjSets {
    private final int[] s;
    public DisjSets(int numElements){
        s = new int[numElements];
        Arrays.fill(s, -1);
    }
    public void union(int root1, int root2){
        s[root2] = root1;
    }
    public int find(int x){
        if(s[x] < 0)
            return x;
        else
            return find(s[x]);
    }
    
    public void printS(){
        System.out.println();
        for(int i = 0; i < s.length; i++){
            System.out.print(s[i] + "\t");
        }
    }
}

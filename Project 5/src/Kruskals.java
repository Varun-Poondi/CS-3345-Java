import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Kruskals {
    private static class Edge implements Comparable<Edge>{
        private final int hashCode;
        private final String vertex1;
        private final String vertex2;
        private final int edgeWeight;

        private Edge(String vertex1, String vertex2, int edgeWeight) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.edgeWeight = edgeWeight;
            this.hashCode = Math.abs(vertex1.hashCode()) + Math.abs(vertex2.hashCode());
        }
        
        public String getVertex1() {
            return vertex1;
        }

        public String getVertex2() {
            return vertex2;
        }

        public int getEdgeWeight() {
            return edgeWeight;
        }

        public int getHashCode() {
            return hashCode;
        }

        @Override
        public String toString() {
            return vertex1 + "\tto\t" + vertex2 + "\tWeight : " + edgeWeight;
        }

        @Override
        public int compareTo(Edge compEdge) {
            if(this.edgeWeight < compEdge.getEdgeWeight()){
                return -1 ;
            } else if (this.edgeWeight == compEdge.getEdgeWeight()){
                return 0;
            }
            return 1;
        }
    }
    
    public static final PriorityQueue<Edge> edges = new PriorityQueue<>();
    public static final HashMap<Integer, Edge> edgeMap = new HashMap<>();
    public static final HashMap<String, Integer> vertexMap = new HashMap<>();
    public static DisjSets disjSet;
    
    
    public static void main(String[] args) throws IOException {
        System.out.print("File Name: ");
        Scanner input = new Scanner(System.in); // get file name
        String fileName;
        fileName = input.next();
        File fileObj = new File(fileName);
        fileName = fileChecker(fileObj, fileName, input);   // check if the file exists, else ask for it again
        loadEdgesIntoPriorityQueue(fileName); // read the file
        findMinimumSpanningTree();
    }
    
    public static String fileChecker(File fileObj, String fileName, Scanner input){
        boolean fileIsReadable = false;
        while(!fileIsReadable) {
            try {
                if (!fileObj.canRead()) {   // if the file is unreadable 
                    throw new FileNotFoundException();  // throw exception
                } else {
                    fileIsReadable = true;  // else the file is readable and exit the while loop
                }
            } catch (Exception e) {
                System.out.print("File was not found, please try again.\nFile Name: ");
                fileName = input.next(); // get the next input
                fileObj = new File(fileName); // ask for file input again to be tested
            }
        }
        return fileName; // return valid file name
    }
    
    public static void loadEdgesIntoPriorityQueue(String fileName) throws IOException {
        String mainVertex;
        String neighborVertex;
        int edgeWeight;
        String line;
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        int counter = 0;
        while((line = fileReader.readLine()) != null){
            String [] mainVertexData = line.split(",");
            mainVertex = mainVertexData[0];
            vertexMap.put(mainVertex, counter);
            for(int i = 1; i < mainVertexData.length; i += 2){
                neighborVertex = mainVertexData[i];
                edgeWeight = Integer.parseInt(mainVertexData[i+1]);
                Edge edge = new Edge(mainVertex, neighborVertex, edgeWeight);
                if(!edgeMap.containsKey(edge.getHashCode())) {
                    edgeMap.put(edge.getHashCode(),edge);
                    edges.add(edge);
                }
            }
            counter ++;
        }
        disjSet = new DisjSets(counter);
        fileReader.close();
    }
    
    public static void findMinimumSpanningTree() {
        int counter = 0;
        int totalEdgeWeight = 0;
        while(counter != vertexMap.size()-1){
            Edge getEdge = edges.poll();
            if(getEdge != null) {
                int vertexA = disjSet.find(vertexMap.get(getEdge.getVertex1()));
                int vertexB = disjSet.find(vertexMap.get(getEdge.getVertex2()));
                if(vertexA != vertexB){
                    disjSet.union(vertexA, vertexB);
                    System.out.println(getEdge);
                    counter ++;
                    totalEdgeWeight += getEdge.getEdgeWeight();
                }
            }
        }
        System.out.println("Total Edge Weight: " + totalEdgeWeight);
    }
}

/*
Name:   Varun Poondi
Net-ID: VMP190003
Prof:   Greg Ozbrin 
Date:   12/5/2021
*/

import java.io.*;
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
            this.hashCode = Math.abs(vertex1.hashCode()) + Math.abs(vertex2.hashCode());    //calculate unique code for checking duplicates
        }
        /*Getters*/
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
            if(this.edgeWeight < compEdge.getEdgeWeight()){ // if the current edge has higher presidency over the compared edge
                return -1 ; 
            } else if (this.edgeWeight == compEdge.getEdgeWeight()){ // if they are the same, priority
                return 0; 
            }
            return 1;
        }
    }
    //  Vital Components
    // - Hash Map and Priority Queue
    
    public static final PriorityQueue<Edge> edges = new PriorityQueue<>();      // holds all non-duplicate edges, and will be used to print out the minimum spanning tree
    public static final HashMap<Integer, Edge> edgeMap = new HashMap<>();       // holds all edges and stores them into a hashmap, will be used to detect if duplicate edges exist
    public static final HashMap<String, Integer> vertexMap = new HashMap<>();   // holds all the vertices' Name and their index number, will be used to access a vertex index in the disjSet
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
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName)); // buffer to read the csv file 
        int counter = 0;
        
        while((line = fileReader.readLine()) != null){ // assign the currentLine to string to be parsed if the current line is not null
            String [] mainVertexData = line.split(","); // create an array split by commas
            mainVertex = mainVertexData[0]; 
            vertexMap.put(mainVertex, counter); // store info into vertex map for later use
            for(int i = 1; i < mainVertexData.length; i += 2){  // traverse by 2s since we extract data 2 at a time for every traversal
                neighborVertex = mainVertexData[i]; 
                edgeWeight = Integer.parseInt(mainVertexData[i+1]);
                Edge edge = new Edge(mainVertex, neighborVertex, edgeWeight); // create and edge with the collected date
                if(!edgeMap.containsKey(edge.getHashCode())) { // if the generated hashcode for the edge doesn't already exist in the hashTable
                    edgeMap.put(edge.getHashCode(),edge); // add into the edge map
                    edges.add(edge); // add to the priority queue
                }
            }
            counter ++;
        }
        disjSet = new DisjSets(counter);    // initialize disjSet
        fileReader.close(); // close file
    }
    
    public static void findMinimumSpanningTree() {
        int counter = 0;
        int totalEdgeWeight = 0;
        while(counter != vertexMap.size()-1){   // while we have all the edges in the same equivalence class
            Edge getEdge = edges.poll(); // get the head of the queue
            if(getEdge != null) { 
                int vertexA = disjSet.find(vertexMap.get(getEdge.getVertex1()));    // get the index of the starting vertex
                int vertexB = disjSet.find(vertexMap.get(getEdge.getVertex2()));    // get the index of the ending vertex 
                if(vertexA != vertexB){ // if both vertexes are not in the same equivalence class already
                    disjSet.union(vertexA, vertexB);    // union both vertices
                    System.out.println(getEdge);    // print out the current edge
                    counter ++; 
                    totalEdgeWeight += getEdge.getEdgeWeight(); // add to the total edge weight
                }
            }
        }
        System.out.println("Total Edge Weight: " + totalEdgeWeight); // print out the total edge weight
    }
}

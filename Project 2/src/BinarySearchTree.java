//Name: Varun Poondi
//Net-ID: VMP190003
//Prof: Greg Ozbrin
//Date: 10/23/2021





// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */

/**
 * Exception class for access in empty containers
 * such as stacks, queues, and priority queues.
 * @author Mark Allen Weiss
 */

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }
    
    public int nodeCount(){
        return nodeCountHelper(root);
    }

    public boolean isFull(){
        return isFullHelper(root);
    }
    
    public boolean compareStructure(BinaryNode<AnyType> checkRoot){
        return compareStructureHelper(root,checkRoot);
    }
    
    public boolean equals(BinaryNode<AnyType> checkRoot){
        return equalsHelper(root, checkRoot);
    }
    
    public BinaryNode<AnyType> copy(){
        return copyHelper(root);
    }
    
    public BinaryNode<AnyType> mirror(){
        return mirrorHelper(root);
    }
    
    

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.print( t.element  + " ");
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );
    }

    
    private int nodeCountHelper(BinaryNode<AnyType> node){
        if(node == null){ //leaf Node + 1 detected
            return 0;
        }
        return 1 + nodeCountHelper(node.left) + nodeCountHelper(node.right); //1 + (+1 if currentNode on the left are not null) + (+1 if currentNode on the right side are not null) 
    }
    

    
    
    private boolean isFullHelper(BinaryNode<AnyType> node){
        if(node == null) { // return true if the node is currently empty or root node
            return true;
        }
        if((node.left == null && node.right != null) || (node.left != null && node.right == null)){ // if either the currentNode's left or right children are null, return false
            return false;
        }
        return isFullHelper(node.left) && isFullHelper(node.right); // move to the next node on the left and right side. (Traverse through the tree)
    }
    
    private boolean compareStructureHelper(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2){
        if(node1 == null && node2 == null){ // check if both roots are empty, base case
            return true;
        }
        if (node1 != null && node2 != null){ // if in the middle or the bottom of the tree, and either node is null, return false, else continue to traverse
            return compareStructureHelper(node1.left, node2.left) && compareStructureHelper(node1.right, node2.right);
        }
        return false; // final case when one of the nodes are null
   
    }
    private boolean equalsHelper(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2){
        if(node1 == null && node2 == null){ // check if both roots are empty, base case
            return true;
        }
        // if in the middle or the bottom of the tree, and either node is null, return false, else continue to traverse
        // must also check if both nodes at a certain spot have the same element
        if (node1 != null && node2 != null && node1.element == node2.element){
            return equalsHelper(node1.left, node2.left) && equalsHelper(node1.right, node2.right);
        }
        return false;  // final case when one of the nodes are null
    }
    
    private BinaryNode<AnyType> copyHelper(BinaryNode<AnyType> currentNode){
        if (currentNode != null) { //base case check, return null, if the tree is empty or entered null node with leaf parent
            BinaryNode<AnyType> insertNode = new BinaryNode<>(currentNode.element); //create new node
            if(currentNode.left != null) { //check if there is a left node available
                insertNode.left = copyHelper(currentNode.left); //recurse to get left child for insertNode
            }
            if(currentNode.right != null) { //check if there is a right node available
                insertNode.right = copyHelper(currentNode.right); //recurse to get right child for insertNode
            }
            return insertNode; //return new root
        }
        return null; 
    }
    private BinaryNode<AnyType> mirrorHelper(BinaryNode<AnyType> currentNode) {
        if (currentNode != null) {
            BinaryNode<AnyType> newNode = new BinaryNode<>(currentNode.element); //create a node with currentNode.element
            if (currentNode.right != null) { //if the currentNode.right side is null
                newNode.left = mirrorHelper(currentNode.right); //insert a node in the newNode.left. (Opposite)
            }
            if (currentNode.left != null) { //if the currentNode.left side is null
                newNode.right = mirrorHelper(currentNode.left); //insert a node in the newNode.right (Opposite)
            }
            return newNode; // return the node when we have have reached the lowest level possible.
        }
        return null; // the tree is empty
    }
    
    public boolean isMirror(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2){
        if(node1 == null && node2 == null){ // check if both node are null, base case to exit, or recurse back up stack 
            return true;
        }
        if(node1 != null && node2 != null && node1.element == node2.element){ 
            return isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
        }
        return false;
    }
//    
    private BinaryNode<AnyType> searchNode(AnyType element, BinaryNode<AnyType> currentNode){
        int compareResult;
        if(root != null){
            compareResult = element.compareTo( currentNode.element ); // get the compare result
            if (compareResult < 0){ // move left if element is less than the currentNode
                return searchNode(element, currentNode.left); 
            }else if (compareResult > 0){ //move right if the element is greater than the currentNode
                return searchNode(element, currentNode.right);
            }else{
                return currentNode; // you have found the searchNode, return it 
            }
        }
        return null; // the tree is empty
    }
    
    // This methods is similar to searchNode except it returns the parent of the searchNode. We have a pointer parent that 
    // keeps track of the currentNode before making a recursive call to traverse.
    private BinaryNode<AnyType> parentNode(AnyType element, BinaryNode<AnyType> currentNode, BinaryNode<AnyType> parent){
        int compareResult;
        if(root != null){
            compareResult = element.compareTo( currentNode.element ); 
            if (compareResult < 0){ // move left
                parent = currentNode;
                return parentNode(element, currentNode.left, parent);
            }else if (compareResult > 0){
                parent = currentNode;
                return parentNode(element, currentNode.right, parent);
            }else{
                return parent;
            }
        }
        return null;
    }
    
    
    public void rotateRight(AnyType element) throws Exception {
        if(root != null) { //check if the tree is empty
            System.out.println("Attempting to rotate element " + element + " right .....");
            BinaryNode<AnyType> searchNode = searchNode(element, root);  // get the searchNode
            BinaryNode<AnyType> parentNode = parentNode(element, root, root); // get the parentNode of the searchNode
            if(searchNode != null && searchNode == root && searchNode.left != null){ // if you want to rotate the root right and the searchNode is the root
                BinaryNode<AnyType> tree1 = searchNode.left;  // save the left subtree of searchNode
                searchNode.left = null; // set searchNode left subtree of searchNode to null since that side is going to change
                BinaryNode<AnyType> insertPosition = findMax(tree1); // traverse to the farthest right position of tree1
                insertPosition.right = searchNode; // at that position insert searchNode to the right of tree 1. The tree has now taken shape
                root = tree1; // update the root
                System.out.println("Successful Rotation!");
            }else if(searchNode != null && parentNode != null && searchNode.left != null){ // if you want to rotate a node in the tree that is not
                BinaryNode<AnyType> tree1 = searchNode.left; // save the left subtree of searchNode
                // We need to now check which side of the parentNode is the searchNode
                if(parentNode.left != null && element.compareTo(parentNode.left.element) == 0){ // if the searchNode is on the parent's left side
                    parentNode.left = tree1; // set the parentNode's left side to equal to tree 1. 
                    BinaryNode<AnyType> insertPosition = findMax(tree1); // move to the farthest right position of the insertPosition to insert the searchNode
                    searchNode.left = null; // set the searchNode's left side to null since we need to null since this side is now the insert position which is now the left side of the parent. 
                    insertPosition.right = searchNode; // insert the searchNode into the correct spot
                    System.out.println("Successful Rotation!");
                }else if(parentNode.right != null && element.compareTo(parentNode.right.element) == 0){ // if the searchNode is on the parent's right side
                    parentNode.right = tree1; // set the parentNode's right side to equal to tree 1.
                    BinaryNode<AnyType> insertPosition = findMin(tree1); // move to the farthest left position of the insertPosition to insert the searchNode
                    searchNode.left = null; // set the searchNode's left side to null
                    insertPosition.right = searchNode; // insert the searchNode into the correct spot
                    System.out.println("Successful Rotation!"); 
                }
            }else{
                throw new Exception("Unable to rotate right");

            }
        }else{
            throw new Exception("Tree is empty");
        }
    }
    
    
    // rotateLeft is very similar to rotateRight. They are basically the same methods with some small alterations.
    public void rotateLeft(AnyType element) throws Exception {
        if(root != null) {
            System.out.println("Attempting to rotate element " + element + " left .....");
            BinaryNode<AnyType> searchNode = searchNode(element, root);
            BinaryNode<AnyType> parentNode = parentNode(element, root, root);
            if(searchNode != null && searchNode == root && searchNode.right != null){ // if the searchNode is the root and there is a right child to the searchNode
                BinaryNode<AnyType> tree1 = searchNode.right; // save the searchNode's right tree to tree1
                searchNode.right = null; // set the right side to null since the right side is now updated
                BinaryNode<AnyType> insertPosition = findMin(tree1); // move to farthest left side since we need to need to find an open spot on left side of tree 1
                insertPosition.left = searchNode; // set the insertPosition's left side to equal the searchNode
                root = tree1; // update the root
                System.out.println("Successful Rotation!");
            }else if(searchNode != null && parentNode != null && searchNode.right != null){
                BinaryNode<AnyType> tree1 = searchNode.right;
                if(parentNode.left != null && element.compareTo(parentNode.left.element) == 0){ // if you want to rotate a node in the tree that is not
                   parentNode.left = tree1; // update the parentNode's left side to tree 1 since we need to move the searchNode to the correct spot
                   BinaryNode<AnyType> insertPosition = findMax(tree1); // find the max position so that you can insert properly
                   searchNode.right = null; // set the right side to null since the right side is now the left side of the parent
                   insertPosition.left = searchNode; // update the searchNode's position to the correct spot
                   System.out.println("Successful Rotation!");
                }else if(parentNode.right != null && element.compareTo(parentNode.right.element) == 0){ // if the searchNode is on the parent's right side
                   parentNode.right = tree1; // update the parentNode's right side to tree 1 since we need to move the searchNode to the correct spot
                   BinaryNode<AnyType> insertPosition = findMin(tree1); // find the min position so that you can insert properly
                   searchNode.right = null; // set the right side to null since the right side is now the right side of the parent
                   insertPosition.left = searchNode; // update the searchNode and insert to the correct position, completing the tree
                   System.out.println("Successful Rotation!");
                }
            }else{
                throw new Exception("Unable to rotate left");
                
            }
        }else{
            throw new Exception("Tree is empty");
        }
    }
    
    
    
    
    public void printLevels() throws Exception {
        if(root != null){ // if the tree is not empty
            Queue<BinaryNode<AnyType>> queue = new LinkedList<>(); // create a queue
            BinaryNode<AnyType> nullBreak = new BinaryNode<>(null); // a break node, if the node is found, you print a new line
            queue.add(root); // add the root to the queue to begin with
            queue.add(nullBreak); // add a break node
            while(!queue.isEmpty()) { 
                BinaryNode<AnyType> currentNode = queue.peek(); // get the first node
                if (currentNode.element != null) { // if currentNode is not a break node
                    System.out.print(currentNode.element + " "); // print currentNode's element
                    if (currentNode.left != null) { // if the left side is not null
                        queue.add(currentNode.left); // add it to the queue
                    }
                    if (currentNode.right != null) { // if the right side is not null
                        queue.add(currentNode.right); // add it to the queue
                    }
                }else{ // we have found a break node
                    System.out.println(); // print a new line
                    if(!queue.isEmpty() && queue.size() > 1) { // if there is not just a break node in the queue
                        queue.add(nullBreak); // add a break node
                    }
                }
                queue.remove(); // remove the first element from the queue 
            }
        }else{
            throw new Exception("Tree is empty\n");
        }
        System.out.println();
    }
    
    // Basic function. Just prints the left and right children of a node if found in a tree.
    // Prints null if there is no left and/or right child
    public void printCurrentNodeChildren(AnyType element){
        BinaryNode<AnyType> node = searchNode(element, root);
        
        if(node != null){ 
            String print = "";
            System.out.println("Current Children of : " + node.element);
            if (node.left != null) {
                print += "Left: " + node.left.element + "\n";
            } else {
                print += "Left: Null" + "\n";
            }
            if (node.right != null) {
                print += "Right: " + node.right.element;
            } else {
                print += "Right: Null";
            }
            System.out.println(print);
        }
    }
    
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType> {
        // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
        
    }

    /** The tree root. */
    private BinaryNode<AnyType> root;
    
    

    // Test program
    public static void main( String [ ] args ) throws Exception {
        BinarySearchTree<Integer> tree1 = new BinarySearchTree<>( );
        tree1.insert(100);
        tree1.insert(150);
        tree1.insert(50);
        tree1.insert(40);
        tree1.insert(45);

        System.out.println("________nodeCount() Test________");
        System.out.print("Tree 1: ");
        tree1.printTree();
        System.out.println("\nNumber of nodes in tree 1: " + tree1.nodeCount());

        
        System.out.println("\n__________isFull() Test__________");
        System.out.println("Tree 1 is full: " + tree1.isFull());
        tree1.insert(55);
        tree1.insert(32);
        System.out.println("Added 55 and 32, Tree 1 should be full");
        System.out.println("Tree 1 is full: " + tree1.isFull());

        
        System.out.println("\n_______compareStructures() Test_______");
        BinarySearchTree<Integer> tree2 = new BinarySearchTree<>();
        tree2.insert(60);
        tree2.insert(65);
        tree2.insert(32);
        tree2.insert(20);
        tree2.insert(25);
        System.out.print("Tree 2: ");
        tree2.printTree();
        System.out.println("\nTree 1 and Tree 2 have the same structure: " + tree2.compareStructure(tree1.root));


        System.out.println("\n________equals() and copy() Test________");
        BinarySearchTree<Integer> tree3 = new BinarySearchTree<>();
        tree3.root = tree2.copy();
        System.out.print("Tree 3: ");
        tree3.printTree();
        System.out.println("\nTree 2 and Tree 3 are equal to each other: " + tree3.equals(tree2.root));
        System.out.println("Tree 3 and Tree 1 are equal to each other: " + tree3.equals(tree1.root));
        
        
        System.out.println("\n____________Mirror and isMirror Test____________");
        BinarySearchTree<Integer> tree4 = new BinarySearchTree<>( );
        tree4.root = tree3.mirror();
        System.out.print("Tree 4: ");
        tree4.printTree();
        System.out.println("\nTree 4 is a mirror of Tree 3: " + tree4.isMirror(tree4.root, tree3.root));

        
        System.out.println("\n____________rotateRight____________");
        BinarySearchTree<Integer> tree5 = new BinarySearchTree<>();
        tree5.insert(180);
        tree5.insert(190);
        tree5.insert(166);
        tree5.insert(163);
        tree5.insert(172);
        tree5.insert(175);
        tree5.insert(200);
        /*
        Current Tree:                                                  
                                180
                               /   \
                            166     -----190
                           /   \            \
                        163     172          200
                                   \
                                    175
        
        */
        
        System.out.println("Tree 5 Root: " + tree5.root.element);
        System.out.print("Tree 5: ");
        tree5.printTree();
        System.out.println("\nAttempt to rotate 180 right. (Root Rotation Test)");

        tree5.printCurrentNodeChildren(180);
        System.out.println();

        
        tree5.rotateRight(180);
                /*
                Current Tree:
                                          166
                                         /   \
                                      163     172
                                               \
                                                175
                                                  \
                                                   180
                                                    \
                                                     190
                                                      \  
                                                       200 
                
                */
        
        
        System.out.println("\nTree 5 Root: " + tree5.root.element);
        System.out.print("Tree 5: ");
        tree5.printTree();
        
        System.out.println();
        tree5.printCurrentNodeChildren(180);

        System.out.println("\n____________rotateLeft____________");
        tree5.insert(174);
        System.out.println("Tree 5 Root: " + tree5.root.element);
        System.out.print("Tree 5: ");
        tree5.printTree();
        System.out.println("\nAttempt to rotate left 175. (Middle Node rotation)");
        tree5.printCurrentNodeChildren(175);
        System.out.println();

                
        /*
        Current Tree:
                                  166                                       
                                 /   \          
                              163     172
                                       \
                                        175   
                                       /  \
                                     174   180
                                            \
                                             190
                                              \  
                                               200 
        
        */
        tree5.rotateLeft(175);
        /*
        Current Tree:
                                  166
                                 /   \
                              163     172
                                       \
                                        180
                                       /  \
                                     175  190
                                    /       \
                                 174        200
        */
        

        System.out.println("\nTree 5 Root: " + tree5.root.element);
        System.out.print("Tree 5: ");
        tree5.printTree();
        System.out.println();
        tree5.printCurrentNodeChildren(175);


        System.out.println("\nEdge case testing. Rotate 174. (Unable to rotate statement)");
        tree5.printCurrentNodeChildren(174);
        try {
            tree5.rotateLeft(174);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
        System.out.println("\n________printLevels() Test________");
        System.out.println("Print Tree 1 Levels:");
        tree1.printLevels();
        System.out.println("Print Tree 2 Levels:");
        tree2.printLevels();
        System.out.println("Print Tree 3 Levels:");
        tree3.printLevels();
        System.out.println("Print Tree 4 Levels:");
        tree4.printLevels();
        System.out.println("Print Tree 5 Levels:");
        tree5.printLevels();
        
        
    }
}
class UnderflowException extends RuntimeException { }
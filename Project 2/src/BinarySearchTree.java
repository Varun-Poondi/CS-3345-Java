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
        BinaryNode<AnyType> mirrorRoot = new BinaryNode<>(root.element);
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
        if(node == null){
            return 0;
        }
        return 1 + nodeCountHelper(node.left) + nodeCountHelper(node.right);
    }
    

    
    
    private boolean isFullHelper(BinaryNode<AnyType> node){
        if(node == null){
            return true;
        }
        if(node.left == null && node.right != null || node.left != null && node.right == null){
            return false;
        }
        return isFullHelper(node.left) && isFullHelper(node.right);
    }
    
    private boolean compareStructureHelper(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2){
        // check if both roots are empty, base case
        if(node1 == null && node2 == null){ 
            return true;
        }
        // if in the middle or the bottom of the tree, and either node is null, return false, else continue to traverse
        if (node1 != null && node2 != null){
            return compareStructureHelper(node1.left, node2.left) && compareStructureHelper(node1.right, node2.right);
        }
        return false; // final case when one of the nodes are null
   
    }
    private boolean equalsHelper(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2){
        // check if both roots are empty, base case
        if(node1 == null && node2 == null){ 
            return true;
        }
        // if in the middle or the bottom of the tree, and either node is null, return false, else continue to traverse
        // must also check if both nodes at a certain spot have the same element
        if (node1 != null && node2 != null && node1.element == node2.element){
            return equalsHelper(node1.left, node2.left) && equalsHelper(node1.right, node2.right);
        }
        // final case when one of the nodes are null
        return false;
    }
    
    private BinaryNode<AnyType> copyHelper(BinaryNode<AnyType> currentNode){
        //base case check, return null, if the tree is empty or entered null node with leaf parent
        if (currentNode != null) { 
            //create new node
            BinaryNode<AnyType> insertNode = new BinaryNode<>(currentNode.element);
            //check if there is a left node available
            if(currentNode.left != null) {
                //recurse to get left child for insertNode
                insertNode.left = copyHelper(currentNode.left);
            }
            //check if there is a right node available
            if(currentNode.right != null) {
                //recurse to get right child for insertNode
                insertNode.right = copyHelper(currentNode.right);
            }
            //return new root
            return insertNode;
        }
        return null; 
    }
    private BinaryNode<AnyType> mirrorHelper(BinaryNode<AnyType> currentNode) {
        if (currentNode != null) {
            BinaryNode<AnyType> newNode = new BinaryNode<>(currentNode.element);
            if (currentNode.right != null) {
                newNode.left = mirrorHelper(currentNode.right);
            }
            if (currentNode.left != null) {
                newNode.right = mirrorHelper(currentNode.left);
            }

            return newNode;
        }
        return null;
    }
    
    public boolean isMirror(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2){
        // check if both node are null, base case to exit, or recurse back up stack 
        if(node1 == null && node2 == null){
            return true;
        }
        if(node1 != null && node2 != null && node1.element == node2.element){
            return isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
        }
        return false;
    }
//    
    private BinaryNode<AnyType> searchNode(AnyType element, BinaryNode<AnyType> currentNode, BinaryNode<AnyType> parent){
        int compareResult;
        if(root != null){
            compareResult = element.compareTo( currentNode.element );
            if (compareResult < 0){ // move left
                return searchNode(element, currentNode.left, parent);
            }else if (compareResult > 0){
                return searchNode(element, currentNode.right, parent);
            }else{
                return currentNode;
            }
        }
        return null;
    }
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
    
    
    public void rotateRight(AnyType element){
        //check if the tree is empty
        if(root != null) {
            System.out.println("Attempting to rotate element " + element + " right .....");
            BinaryNode<AnyType> searchNode = searchNode(element, root, root);
            BinaryNode<AnyType> parentNode = parentNode(element, root, root);
            if(searchNode != null && searchNode == root && searchNode.left != null){
                BinaryNode<AnyType> tree1 = searchNode.left;
                searchNode.left = null;
                BinaryNode<AnyType> insertPosition = findMax(tree1);
                insertPosition.right = searchNode;
                root = tree1;
                System.out.println("Successful Rotation!");
            }else if(searchNode != null && parentNode != null && searchNode.left != null){
                BinaryNode<AnyType> tree1 = searchNode.left;
                //BinaryNode<AnyType> temp = searchNode;
                if(parentNode.left != null && element.compareTo(parentNode.left.element) == 0){ // check if the parentNode has searchNode on the left side 
                    parentNode.left = tree1;
                    BinaryNode<AnyType> insertPosition = findMax(tree1);
                    searchNode.left = null;
                    insertPosition.right = searchNode;
                    System.out.println("Successful Rotation!");
                }else if(parentNode.right != null && element.compareTo(parentNode.right.element) == 0){
                    parentNode.right = tree1;
                    BinaryNode<AnyType> insertPosition = findMin(tree1);
                    searchNode.left = null;
                    insertPosition.right = searchNode;
                    System.out.println("Successful Rotation!");
                }
            }else{
                System.out.println("Unable to rotate right.");
            }
        }else{
            System.out.println("Tree is empty.");
        }
    }
    
    public void rotateLeft(AnyType element){
        if(root != null) {
            System.out.println("Attempting to rotate element " + element + " left .....");
            BinaryNode<AnyType> searchNode = searchNode(element, root, root);
            BinaryNode<AnyType> parentNode = parentNode(element, root, root);
            if(searchNode != null && searchNode == root && searchNode.right != null){
                BinaryNode<AnyType> tree1 = searchNode.right;
                searchNode.right = null;
                BinaryNode<AnyType> insertPosition = findMin(tree1);
                insertPosition.left = searchNode;
                root = tree1;
                System.out.println("Successful Rotation!");
            }else if(searchNode != null && parentNode != null && searchNode.right != null){
                BinaryNode<AnyType> tree1 = searchNode.right;
                if(parentNode.left != null && element.compareTo(parentNode.left.element) == 0){ // check if the parentNode has searchNode on the left side 
                   parentNode.left = tree1;
                   BinaryNode<AnyType> insertPosition = findMax(tree1);
                   searchNode.right = null;
                   insertPosition.left = searchNode;
                   System.out.println("Successful Rotation!");
                }else if(parentNode.right != null && element.compareTo(parentNode.right.element) == 0){
                   parentNode.right = tree1;
                   BinaryNode<AnyType> insertPosition = findMin(tree1);
                   searchNode.right = null;
                   insertPosition.left = searchNode;
                   System.out.println("Successful Rotation!");
                }
            }else{
                System.out.println("Unable to rotate left");
            }
        }else{
            System.out.println("Tree is empty.");
        }
    }
    public void printLevels(){
        if(root != null){
            Queue<BinaryNode<AnyType>> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()){
                BinaryNode<AnyType> currentNode = queue.peek();
                System.out.print(currentNode.element + " ");
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
                
                queue.remove();
            }
        }else{
            System.out.println("Tree is empty.");
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
    public static void main( String [ ] args )
    {
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

        System.out.println("__________isFull() Test__________");
        System.out.println("Tree 1 is full: " + tree1.isFull());


        System.out.println("_______compareStructures() Test_______");
        BinarySearchTree<Integer> tree2 = new BinarySearchTree<>();
        tree2.insert(60);
        tree2.insert(65);
        tree2.insert(32);
        tree2.insert(20);
        tree2.insert(25);
        System.out.print("Tree 2: ");
        tree2.printTree();
        System.out.println("\nTree 1 and Tree 2 have the same structure: " + tree2.compareStructure(tree1.root));


        System.out.println("________equals() Test________");
        BinarySearchTree<Integer> tree3 = new BinarySearchTree<>();
        tree3.insert(60);
        tree3.insert(65);
        tree3.insert(32);
        tree3.insert(20);
        tree3.insert(25);
        System.out.print("Tree 3: ");
        tree3.printTree();
        System.out.println("\nTree 2 and Tree 3 are equal to each other: " + tree3.equals(tree2.root));
        System.out.println("Tree 3 and Tree 1 are equal to each other: " + tree3.equals(tree1.root));
        
        
        System.out.println("____________Mirror and isMirror Test____________");
        BinarySearchTree<Integer> tree4 = new BinarySearchTree<>( );
        tree4.root = tree3.mirror();
        System.out.print("Tree 4: ");
        tree4.printTree();
        System.out.println("\nTree 4 is a mirror of Tree 3: " + tree4.isMirror(tree4.root, tree3.root));
        
    }
}
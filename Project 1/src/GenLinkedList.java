import java.util.List;

public class GenLinkedList<T extends Comparable<T>>{
    public static class Node<T>{
        private T payload;
        private Node<T> next;

        public Node(T payload) {
            this.payload = payload;
            next = null;
        }

        public T getPayload() {
            return payload;
        }

        public void setPayload(T payload) {
            this.payload = payload;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
    private Node<T> head;
    
    public void addFront(T payload){
        Node<T> newNode = new Node<>(payload); // create new node
        newNode.setNext(head); // set the head's previous node as the newNode
        head = newNode; //make the newNode as the new head
    }
    public void addEnd(T payload){  
        Node<T> currentNode = head; 
        if(currentNode == null){    //check if the linked list is empty
            currentNode = new Node<>(payload);  
            head = currentNode; // add the node into the linked list and set it as the head
        }else{  
            Node<T> newNode = new Node<>(payload); 
            while(currentNode.getNext() != null){ //traverse till you reach the end of the linked list
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode); // set the newNode at the end of the linked list 
        }
    }
    public void removeFront(){
        if(head != null){   
            head = head.getNext(); // move the head pointer to the head's next node. Garbage collection will take care of the rest
        }
    }
    public void removeEnd() {
        if (head != null) {
            Node<T> currentNode = head; //travers node
            Node<T> previousNode = null; //saves the previous node of currentNode
            while (currentNode.getNext() != null) {
                previousNode = currentNode; //save currentNode
                currentNode = currentNode.getNext(); //traverse
            }
            if (previousNode != null) { //make sure the previous is null, would never be the case
                previousNode.setNext(currentNode.getNext()); // set the previous node to point to currentNode's next node
            }
        }
    }
    public T get(int position){
        if(head != null) {
            if (position < getSize() && position >= 0) { //bounds check
                Node<T> currentNode = getNodeAtPosition(position); // get the node at the given position
                if (currentNode != null) { //check if it is null
                    return currentNode.getPayload(); //return payload
                }
            }
        }
        return null;
    }
    private Node<T> getNodeAtPosition(int pos){
        if(head != null){
            int counter = 0;
            Node<T> currentNode = head; //traverse node
            while(currentNode.getNext() != null && counter != pos){ // until you reach the end of the linked list or you reached the position of the node
                counter ++; 
                currentNode = currentNode.getNext(); //move to the next node
            }
            return currentNode;
        }
        return null;
    }
    private Node<T> getPreviousNodeAtPosition(int pos){ //same code as getNodeAtPosition() except we are returning the previous node of the currentNode
        if(head != null){
            int counter = 0;
            Node<T> previous = null;
            Node<T> currentNode = head;
            while(currentNode.getNext() != null && counter != pos){
                counter ++;
                previous = currentNode;
                currentNode = currentNode.getNext();
            }
            return previous;
        }
        return null;
    }
    
    public void set(int position, T payload) throws Exception {
        String exception = "";
        if (head != null) {
            if (position < getSize() && position >= 0) { //bounds check
                int counter = 0;
                Node<T> currentNode = head; //traverse node
                while (currentNode.getNext() != null && counter != position) { //traverse until we have reached desire position
                    counter++;
                    currentNode = currentNode.getNext();
                }
                currentNode.setPayload(payload); //at the current node, set the payload with the given payload
                
            }else{
                exception = "Required position is out of bounds";
            }
        }
        if(!exception.equals("")){
            throw new Exception(exception);
        }
    }
    
    public void swap(int pos1, int pos2) throws Exception {
        String exception = "";
        if(head != null){       // check if the head is not null
            if(getSize() > 0 && pos1 < getSize() && pos1 >= 0 && pos2 < getSize() && pos2 >= 0 && pos1 != pos2){ // If condition is True, we are guaranteeing a swap, so no exceptions are needed
                Node<T> node1 = getNodeAtPosition(pos1);              // get node1
                Node<T> node1Previous = getPreviousNodeAtPosition(pos1);      // get node1's previous node
                Node<T> node2 = getNodeAtPosition(pos2);              // get node2
                Node<T> node2Previous = getPreviousNodeAtPosition(pos2);      // get node2's previous node

                if(node1Previous == null){ 
                   head = node2;    // set head to point to node2
                }else{  
                    node1Previous.setNext(node2); // else set the previous node next pointer to point to node2
                }
                
                // basically same code as above but now we need to check if node1 is the head
                if(node2Previous == null){
                    head = node1;   
                }else{
                    node2Previous.setNext(node1);
                }
                
                // Standard swap 
                if(node1 != null && node2 != null) { //null check
                    Node<T> temp = node2.getNext(); // save node2.getNext() since it will change when we set node2's next to node1's next
                    node2.setNext(node1.getNext()); //link node2 next pointer to node1's next
                    node1.setNext(temp); // set the temp to node1 to finish the swap
                }else{
                    exception = "Null Node Found";
                }
                
                
            }else if(getSize() == 0) {  // if there are no nodes in the list
                exception = "Linked List is Empty."; 
            }else if (pos1 == pos2) {
                exception = "No swap performed since pos1 = pos2.";
            }else{ // this can be the only other case why we can't swap nodes, out of bounds exception
                exception = "Position 1 or Position 2 is out of bounds, " + 
                        "must be between 0 and " + (getSize()-1) + " .";
            }
            if(!exception.equals("")){
                throw new Exception(exception); // throw exception if the exception string is not empty
            }
        }
    }
    private void shift_buffer(Node<T> head1, Node<T> head2, int counter){
        while (head2 != null && counter != getSize()) {  // traverse through the current linked list
            Node<T> deleteNode = getNodeAtPosition(counter); // get the current node to be deleted
            if (deleteNode != null) { // as long as the node is not null
                removeMatching(deleteNode.getPayload()); // delete it from the master linked list
            }
            head2 = head2.getNext(); // traverse
        }
        // at this point we have removed the values that are need to be shifted. head2 will now be in front of head1
        head2 = head; // reset the head
        if (head1 != null) { //check if the head is null
            Node<T> temp = head1; // temp will be used to make head1 the official head of the master linked list.
            while (head1.getNext() != null) { // traverse to end of head1 linked list
                head1 = head1.getNext();
            }
            head1.setNext(head2); // at the end of the chained nodes, set head1's next pointer to head2. All nodes are now in the desired position and the shift is complete
            head = temp; // set the temp as the official head of the master linked list
        }
    }
    public void shift(int shiftVal) throws Exception {
        String exception = "";
        if(head != null) {
            Node<T> head2 = head;
            if (shiftVal > 0) { // if you shift left
                if(shiftVal < getSize()) { // if the shift is within bounds
                    Node<T> head1 = getNodeAtPosition(shiftVal); //get the node at the shift val. Assign as head1 because that is where the linked list will now start from
                    int counter = Math.abs(shiftVal); 
                    shift_buffer(head1, head2, counter); // pass the current head of the linked list, the finial head of the linked list and the shift amount 
                }else{
                    exception = "Shift val is out of bounds, too positive"; // invalid shift val
                }
            } else if (shiftVal < 0) { // if you shift right
                if(shiftVal > -1*getSize()) { // if the shift is within bounds
                    int counter = getSize() - Math.abs(shiftVal); // counter = Current size of linked list - abs(shift_val). This will give us the correct position of the new head
                    Node<T> head1 = getNodeAtPosition(counter); // get the node at calculated position
                    shift_buffer(head1, head2, counter); // pass the current head of the linked list, the finial head of the linked list and the shift amount 
                }else{
                    exception = "Shift Val is out of bounds, too negative."; //invalid shift val
                }
            }
            if (!exception.equals("")) {
                throw new Exception(exception); // throw exception if needed
            }
        }
    }
        
        //??
    public void print(){ // public print
        if(getSize() != 0) {
            System.out.print("Current Linked List: ");
            printList(head); // pass head into recursive print
            System.out.println();
        }else{
            System.out.println("Linked List is Currently Empty.");
        }
    }
    private void printList(Node<T> node){
        if(node != null){ // check if the current node does not equal null
            System.out.print(node.getPayload() + " ");  // print the payload in the current node
            printList(node.getNext()); // pass in the next node
        }
    }
    public int getSize(){
        if(head != null) {
            int size = 0;
            Node<T> currentNode = head;
            while(currentNode != null){ // traverse through linked list
                currentNode = currentNode.getNext();
                size ++; // add the number of nodes
            }
            return size; // return the current size of the linked list
        }
        return 0; // return 0 if the linked list is empty
    }

    /*********************************************************/
    public void removeMatching(T payload){
         if(head != null){
             Node<T> currentNode = head;
             Node<T> previousNode = null;
             while(currentNode != null){
                 if(currentNode.getPayload() == payload){
                     if(currentNode == head){
                         head = head.getNext();
                     }else if(previousNode != null){
                         previousNode.setNext(currentNode.getNext());
                     }
                 }else{
                     previousNode = currentNode;
                 }
                 currentNode = currentNode.getNext();
             }
         }
    }
    
    public void erase(int position, int limit) throws Exception {
        String exception = "";
        if(head != null){
            if(getSize() > 0 && position < getSize() && position >= 0 && limit < getSize() && limit >= 0 && position != limit){
                Node<T> currentNode = getNodeAtPosition(position); //pointing to the same address
                Node<T> previousNode = getPreviousNodeAtPosition(position);
                int counter = 0;
                if (currentNode != null) {
                    while (currentNode != null && counter != limit) {
                        if (currentNode == head) {
                            head = head.getNext();
                        } else if (previousNode != null) {
                            previousNode.setNext(currentNode.getNext());
                        }
                        previousNode = getPreviousNodeAtPosition(position);
                        currentNode = currentNode.getNext(); 
                        counter++;
                    }
                }
            }else if (limit >= getSize()){
                exception = "Limit index is too large." +
                        "\nMust be Limit < LL Size." +
                        "\nOut of Bounds Exception. Limit : " + limit + ", LL Size: " + getSize();
            }else if (position == limit){
                exception = "Erase cannot be performed due to invalid erase 0. Position : " + position + ", Limit: " + limit;
            }
            if(!exception.equals("")){
                throw new Exception(exception);
            }
        }
    }
    public void insertList(GenLinkedList<T> list, int position){
        String exception = "";
        if(head != null){
            if(position <= getSize() && position >= 0){
                if(position == 0){
                    Node<T> head2 = list.head;
                    Node<T> temp = head2;
                    while(head2.getNext() != null){
                        head2 = head2.getNext();
                    }
                    head2.setNext(head);
                    head = temp;
                    
                }else if(position == getSize()){
                    Node<T> currentNode = head;
                    while(currentNode.getNext() != null){
                        currentNode = currentNode.getNext();
                    }
                    currentNode.setNext(list.head);
                }else{
                    Node<T> currentNode = head;
                    Node<T> next = currentNode.getNext();
                    int counter = 1;
                    while(currentNode.getNext() != null && counter != position){
                        currentNode = currentNode.getNext();
                        counter++;
                        if(next != null){
                            next = currentNode.getNext();
                        }
                    }
                    currentNode.setNext(list.head);
                    currentNode = head;
                    while(currentNode.getNext() != null){
                        currentNode = currentNode.getNext();
                    }
                    currentNode.setNext(next);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        GenLinkedList<Integer> test = new GenLinkedList<>();
//        System.out.println("Test is type Integer linked list.\n");
//
//        System.out.println("____________ addFront() and addEnd() Test ____________");
//        test.print();
//
//        System.out.println("\nAdding to the front");
//        test.addFront(1);
//        test.print();
//        test.addFront(2);
//        test.print();
//        test.addFront(1310);
//        test.print();
//        test.addFront(29029);
//        test.print();
//
//        System.out.println("\nAdding to the end");
//        test.addEnd(10);
//        test.print();
//        test.addEnd(20);
//        test.print();
//        test.addEnd(30);
//        test.print();
//        test.addEnd(40);
//        test.print();
//
//        System.out.println("\n____________ removeFront() and removeEnd() Test ____________");
//        test.print();
//
//        System.out.println("\nRemoving from Front");
//        test.removeFront();
//        test.print(); 
//        test.removeFront();
//        test.print();
//
//        System.out.println("\nRemoving from End");
//        test.removeEnd();
//        test.print(); 
//        test.removeEnd();
//        test.print();
//
//        System.out.println("\n____________ get() and set() Test ____________");
//        test.print();
//        System.out.println("\nGetting Node Payload");
//        if(test.get(2) != null){
//            System.out.println("Payload at position 2: " + test.get(2));
//        }else{
//            System.out.println("Required position is out of bounds.");
//        }
//
//        System.out.println("\nGetting Node payload at index that is out of bounds");
//        if(test.get(10) != null){
//            System.out.println("Payload at position 2: " + test.get(2));
//        }else{
//            System.out.println("Required position is out of bounds.");
//        }
//        //Output: Required position is out of bounds.
//        System.out.println("\nSetting Node Payload");
//        test.set(2,99);
//        test.print();
//        test.set(1,420);
//        test.print(); //Output: Current Linked List: 56 420 99 20
//
//        System.out.println("\n____________ Swap Test ____________");
//
//        try{
//            test.print();
//            System.out.println("\nSwap Nodes 2 and 0");     // pos1 > pos2 test case
//            test.swap(2,0);
//            test.print();
//
//            System.out.println("\nSwap Nodes 1 and 2");     // base case
//            test.swap(1,2);
//            test.print();
//
//            System.out.println("\nSwap Nodes 1 and 3");     // swap middle node and last node
//            test.swap(1,3);
//            test.print();
//
//            System.out.println("\nSwap Nodes 0 and 2");     // swap head and middle node
//            test.swap(0,2);
//            test.print();
//
//            System.out.println("\nSwap Nodes 0 and 3");     // swap head and last node
//            test.swap(0,3);
//            test.print();
//
//            System.out.println("\nSwap Same Position Exception");     // Exception sample test case
//            test.swap(0,0);
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//        test.print();

        System.out.println("\n____________ removeMatching() Test ____________");
        GenLinkedList<String> test1 = new GenLinkedList<>();
        System.out.println("Test 1 is type String linked list.");
        
        //creating new Linked List
        
        test1.addEnd("10");
        test1.addEnd("20");
        test1.addEnd("30");
        test1.addEnd("20");
        test1.addEnd("40");
        test1.addEnd("20");
        test1.print();
        
        System.out.println("\nMore than 1 delete");
        test1.removeMatching("20");
        test1.print();
        
        System.out.println("\n1 delete");
        test1.removeMatching("40"); 
        test1.print();

        System.out.println("\nRemoving payload that does not exist");
        test1.removeMatching("99");
        test1.print();

        System.out.println("\n____________ erase() Test ____________");
        test1.addFront("Bob");
        test1.addFront("Jack");
        test1.addFront("C");
        test1.addFront("32");
        test1.addFront("Henry");
        test1.addFront("David");
        test1.print();

        System.out.println();
        test1.erase(1,3);
        test1.print();

        System.out.println();
        test1.erase(3,4);
        test1.print();
        try {
            System.out.println("\nErasing from position 0 to limit 0");
            test1.erase(2, 2); //This will give me an Exception
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            test1.print();
        }
        
        try{
            System.out.println("\nErasing from position 2 to limit 100"); //
            test1.erase(2,100); // This will give me an Exception
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            test1.print();
        }
        

        System.out.println("\n____________ shift () Test ____________");
        test1.removeFront();
        test1.removeFront();
        test1.removeFront();

        test1.addEnd("A");
        test1.addEnd("B");
        test1.addEnd("C");
        test1.addEnd("D");
        test1.addEnd("E");
        test1.addEnd("F");
        test1.print();
        
        System.out.println("\nShift + 3 (left)");
        test1.shift(3);
        test1.print();
        System.out.println("\nShift + 5 (left)");
        test1.shift(5);
        test1.print();
        System.out.println("\nShift + 2 (left)");
        test1.shift(2);
        test1.print();
        System.out.println("\nShift + 2 (left)");
        test1.shift(1);
        test1.print();

        System.out.println("\nShift 0");
        try {
            test1.shift(0);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        test1.print();


        System.out.println("\nShift -3 (right)");
        test1.addEnd("G");
        
        test1.shift(-3);
        test1.print();

        System.out.println("\nShift -5 (right)");
        test1.shift(-5);
        test1.print();

        System.out.println("\n____________ insertList() Test ____________");
        System.out.println("Creating Sample Linked List");
        test1.removeEnd();
        test1.removeEnd();
        test1.removeEnd();
        test1.removeEnd();
        System.out.println("Linked List 1 (Shorted With Deletes):");
        test1.print();

        System.out.println("List 2:");
        GenLinkedList<String> test2 = new GenLinkedList<>();
        test2.addEnd("Bob");
        test2.addEnd("Tom");
        test2.addEnd("David");
        test2.addEnd("Jack");
        test2.print();

        System.out.println("List 3:");
        GenLinkedList<String> test3 = new GenLinkedList<>();
        test3.addEnd("1");
        test3.addEnd("2");
        test3.addEnd("3");
        test3.addEnd("4");
        test3.print();

        System.out.println("List 4:");
        GenLinkedList<String> test4 = new GenLinkedList<>();
        test4.addEnd("Hello");
        test4.addEnd("To");
        test4.addEnd("My");
        test4.addEnd("Grader");
        test4.addEnd("Bob");
        test4.print();

        System.out.println("List 5:");
        GenLinkedList<String> test5 = new GenLinkedList<>();
        test5.addEnd("Bob");
        test5.print();


        System.out.println("\nInsert List 2 at pos 0 in List 1. (Beginning of the linked list insert test)");
        test1.insertList(test2, 0);
        test1.print();

        System.out.println("\nInsert List 3 at pos 7 in List 1. (End of the linked list insert test)");
        test1.insertList(test3, 7);
        test1.print();

        System.out.println("\nInsert List 5 at pos 4 in List 1. (Middle of the linked list insert test)");
        test1.insertList(test4, 4);
        test1.print();
        
    }
}

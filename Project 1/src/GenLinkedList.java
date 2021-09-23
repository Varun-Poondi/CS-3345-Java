//Name: Varun Poondi
//Net-ID: VMP190003
//Prof: Greg Ozbrin
//Date: 9/23/2021

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
        if(head != null) {
            if (pos < getSize() && pos >= 0) {
                int counter = 0;
                Node<T> currentNode = head; //traverse node
                while (currentNode.getNext() != null && counter != pos) { // until you reach the end of the linked list or you reached the position of the node
                    counter++;
                    currentNode = currentNode.getNext(); //move to the next node
                }
                return currentNode;
            }
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
    private void shift_buffer(Node<T> head1, Node<T> head2, int counter) throws Exception {
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
                    exception = "Shift val is out of bounds, too positive." +
                            "\nShift Val " + shiftVal + " , LL Size: " + getSize(); // invalid shift val
                }
            } else if (shiftVal < 0) { // if you shift right
                if(shiftVal > -1*getSize()) { // if the shift is within bounds
                    int counter = getSize() - Math.abs(shiftVal); // counter = Current size of linked list - abs(shift_val). This will give us the correct position of the new head
                    Node<T> head1 = getNodeAtPosition(counter); // get the node at calculated position
                    shift_buffer(head1, head2, counter); // pass the current head of the linked list, the finial head of the linked list and the shift amount 
                }else{
                    exception = "Shift val is out of bounds, too negative." +
                            "\nShift Val " + shiftVal + " , LL Size: " + getSize(); //invalid shift val
                }
            }else{
                exception = "Shift Val cannot be 0. No Shift was performed.";
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
    public void removeMatching(T payload) throws Exception {
        String exception = "";
         if(head != null){
             Node<T> currentNode = head; // traverse node
             Node<T> previousNode = null;
             boolean found = false;
             while(currentNode != null){
                 if(currentNode.getPayload() == payload){ // if payload was found 
                     found = true;
                     if(currentNode == head){ // if the current node is the head
                         head = head.getNext(); // move the head to next node and delete current node
                     }else if(previousNode != null){  
                         previousNode.setNext(currentNode.getNext()); // set the previous node to point to the currentNode's next node, thus deleting currentNode
                     }
                 }else{
                     previousNode = currentNode; // update 
                 }
                 currentNode = currentNode.getNext(); // traverse
             }
             if(!found){
                 exception = "Payload " + payload + " was not found in Linked List";
             }
         }
         if(!exception.equals("")){
             throw new Exception(exception);
         }
         
    }
    
    public void erase(int position, int limit) throws Exception {
        String exception = "";
        if(head != null){
            if(getSize() > 0 && position < getSize() && position >= 0 && limit < getSize() && limit > 0){ // check to see if all arguments are valid
                Node<T> currentNode = getNodeAtPosition(position); // get the current node at position
                Node<T> previousNode = getPreviousNodeAtPosition(position); // get previous node at position
                int counter = 0;
                if (currentNode != null) {  // check if given node is not null
                    while (currentNode != null && counter != limit) { // traverse until the current node is null or you have reached the delete limit has been reached
                        if (currentNode == head) { 
                            head = head.getNext(); // delete the currentNode if its the head
                        } else if (previousNode != null) {
                            previousNode.setNext(currentNode.getNext()); // delete the currentNode if it's in the middle of the list
                        }
                        previousNode = getPreviousNodeAtPosition(position); // update the previous to maintain previous delete position
                        currentNode = currentNode.getNext(); // traverse
                        counter++; // update
                    }
                }
            }else if (limit >= getSize()){ // check to see if limit is too large
                exception = "Limit index is too large." +
                        "\nMust be Limit < LL Size." +
                        "\nOut of Bounds Exception. Limit : " + limit + ", LL Size: " + getSize();
            }else if (limit == 0){ // no erase is done since limit is 0
                exception = "Erase cannot be performed due to invalid erase 0. Position : " + position + ", Limit: " + limit;
            }
            if(!exception.equals("")){
                throw new Exception(exception); // throw exception
            }
        }
    }
    public void insertList(GenLinkedList<T> list, int position) throws Exception {
        String exception = "";
        if(head != null){
            if(position <= getSize() && position >= 0){
                if(position == 0){ // add list to beginning of master list
                    Node<T> head2 = list.head; // traverse node
                    Node<T> temp = head2; // save head to update master head
                    while(head2.getNext() != null){ // traverse to the end of list
                        head2 = head2.getNext();
                    }
                    head2.setNext(head); // add the master list at the end of list
                    head = temp; // update the master list head to to the temp saved
                    
                }else if(position == getSize()){
                    Node<T> currentNode = head; // traverse node
                    while(currentNode.getNext() != null){ // traverse to tge end of the list
                        currentNode = currentNode.getNext();
                    }
                    currentNode.setNext(list.head); // add the list to the end of the master list
                }else{
                    Node<T> currentNode = head; // traverse
                    Node<T> next = currentNode.getNext(); // the next node after the currentNode. Used to link both ends of the list to the master list
                    int counter = 1; 
                    while(currentNode.getNext() != null && counter != position){ // traverse until you have reached the position
                        currentNode = currentNode.getNext(); // traverse
                        counter++; // update
                        if(next != null){ //check if next is null
                            next = currentNode.getNext(); // update the next node
                        }
                    }
                    currentNode.setNext(list.head); // add the new list to the end of the master list. This will case part of the list to be de referenced from memory
                    currentNode = head; // reset currentNode
                    while(currentNode.getNext() != null){ // travel to the end of the list
                        currentNode = currentNode.getNext();
                    }
                    currentNode.setNext(next); // add the next node and the other chain of nodes to the end of the linked list, thus finishing the insert
                }
            }else if(position < 0){
                exception = "Position is too negative, position is out of bounds." +
                        "\nPosition: " + position + " , LL Size:" + getSize();
            }else{
                exception = "Position is too positive, position is out of bounds." +
                        "\nPosition: " + position + " , LL Size:" + getSize();
            }
        }
        if(!exception.equals("")){
            throw new Exception(exception); // throw exception if needed
        }
    }

    public static void main(String[] args) throws Exception {
        GenLinkedList<Integer> test = new GenLinkedList<>();
        System.out.println("____________ addFront() and addEnd() Test ____________");
        test.print();

        System.out.println("\nTest #1:\nAdding to the front");
        test.addFront(1);
        test.print();
        test.addFront(2);
        test.print();
        test.addFront(1310);
        test.print();
        test.addFront(29029);
        test.print();

        System.out.println("\nTest #2:\nAdding to the end");
        test.addEnd(10);
        test.print();
        test.addEnd(20);
        test.print();
        test.addEnd(30);
        test.print();
        test.addEnd(40);
        test.print();

        System.out.println("\n____________ removeFront() and removeEnd() Test ____________");
        test.print();

        System.out.println("\nTest #1:\nRemoving from Front");
        test.removeFront();
        test.print(); 
        test.removeFront();
        test.print();

        System.out.println("\nTest #2:\nRemoving from End");
        test.removeEnd();
        test.print(); 
        test.removeEnd();
        test.print();

        System.out.println("\n____________ get() and set() Test ____________");
        test.print();
        System.out.println("\nTest #1:\nGetting Node Payload");
        if(test.get(2) != null){
            System.out.println("Payload at position 2: " + test.get(2));
        }else{
            System.out.println("Required position is out of bounds.");
        }

        System.out.println("\nTest #2:\nGetting Node payload at index that is out of bounds. Get Node at pos 10");
        if(test.get(10) != null){
            System.out.println("Payload at position 2: " + test.get(2));
        }else{
            System.out.println("Required position is out of bounds.");
        }
        //Output: Required position is out of bounds.
        System.out.println("\nTest #1:\nSetting Node Payload at pos 2 to 99");
        test.set(2,99);
        test.print();
        System.out.println("\nTest #2:\nSetting Node Payload at pos 1 to 420");
        test.set(1,420);
        test.print(); //Output: Current Linked List: 56 420 99 20
        
        System.out.println("\n____________ shift () Test ____________");
        test.removeFront();
        test.removeFront();

        test.addEnd(1);
        test.addEnd(2);
        test.addEnd(3);
        test.addEnd(4);
        test.addEnd(5);
        test.addEnd(6);
        test.print();

        System.out.println("\nTest #1:\nShift + 5 (left)");
        test.shift(5);
        test.print();

        System.out.println("\nTest #2:\nShift + 2 (left)");
        test.shift(2);
        test.print();

        System.out.println("\nTest #3:\nShift + 8 (left) -> Exception handled");
        try {
            test.shift(8);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        test.print();

        System.out.println("\nTest #4:\nShift 0 -> Exception handled");
        try {
            test.shift(0);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        test.print();

        test.addEnd(7);
        System.out.println("\nAdded Payload G to Linked List 1");
        test.print();

        System.out.println("\nTest #5:\nShift -3 (right)");

        test.shift(-3);
        test.print();

        System.out.println("\nTest #6:\nShift -5 (right)");
        test.shift(-5);
        test.print();

        System.out.println("\nTest #7:\nShift -9 (left) -> Exception handled");
        try {
            test.shift(-9);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        test.print();
        
        
        System.out.println("\n____________ Swap Test ____________");

        try{
            test.print();
            System.out.println("\nTest #1:\nSwap Nodes index 2 and 0");     // pos1 > pos2 test case
            test.swap(2,0);
            test.print();

            System.out.println("\nTest #2:\nSwap Nodes index 1 and 2");     // base case
            test.swap(1,2);
            test.print();

            System.out.println("\nTest #3:\nSwap Nodes index 1 and 3");     // swap middle node and last node
            test.swap(1,3);
            test.print();

            System.out.println("\nTest #4:\nSwap Nodes index 0 and 2");     // swap head and middle node
            test.swap(0,2);
            test.print();

            System.out.println("\nTest #5:\nSwap Nodes index 0 and 8");     // swap head and last node
            test.swap(0,8);
            test.print();

            System.out.println("\nTest #6:\nSwap Same Position Exception. Swap Nodes index 0 and 0");     // Exception sample test case
            test.swap(0,0);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        test.print();

        System.out.println("\n____________ removeMatching() Test ____________");
        GenLinkedList<String> list1 = new GenLinkedList<>();
        System.out.println("Test 1 is type String linked list.");
        
        //creating new Linked List
        System.out.println("Creating List 1:");
        list1.addEnd("10");
        list1.addEnd("20");
        list1.addEnd("30");
        list1.addEnd("20");
        list1.addEnd("40");
        list1.addEnd("20");
        list1.print();
        
        System.out.println("\nTest #1:\nMore than 1 delete.\nDelete 20.");
        list1.removeMatching("20");
        list1.print();
        
        System.out.println("\nTest #2:\n1 delete.\nDelete 40.");
        list1.removeMatching("40"); 
        list1.print();

        System.out.println("\nTest #3:\nRemoving payload that does not exist.\nDelete 99.");
        try {
            list1.removeMatching("99");   
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        list1.print();

        System.out.println("\n____________ erase() Test ____________");
        list1.addFront("Bob");
        list1.addFront("Jack");
        list1.addFront("C");
        list1.addFront("32");
        list1.addFront("Henry");
        list1.addFront("David");
        list1.print();

        System.out.println("\nTest #1\nErase 3 nodes at pos 1");
        list1.erase(1,3);
        list1.print();

        System.out.println("\nTest #2\nErase 4 nodes at pos 3");
        list1.erase(3,4);
        list1.print();

        System.out.println("\nTest #3\nErase 0 nodes at pos 2 -> Exception handled");
        try {
            list1.erase(2, 0); //This will give me an Exception
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            list1.print();
        }

        System.out.println("\nTest #4\nErasing from position 2 to limit 100 -> Exception handled"); 
        try{
            list1.erase(2,100); // This will give me an Exception
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            list1.print();
        }

        System.out.println("\n______________ insertList() Test ______________");
        
        System.out.println("Creating Sample Linked List\n");
        list1.removeEnd();
        list1.removeEnd();
        list1.removeEnd();
        list1.removeEnd();
        System.out.println("Linked List 1 (Shorted With Deletes):");
        list1.print();

        System.out.println("\nList 2:");
        GenLinkedList<String> list2 = new GenLinkedList<>();
        list2.addEnd("Bob");
        list2.addEnd("Tom");
        list2.addEnd("David");
        list2.addEnd("Jack");
        list2.print();

        System.out.println("\nList 3:");
        GenLinkedList<String> list3 = new GenLinkedList<>();
        list3.addEnd("1");
        list3.addEnd("2");
        list3.addEnd("3");
        list3.addEnd("4");
        list3.print();

        System.out.println("\nList 4:");
        GenLinkedList<String> list4 = new GenLinkedList<>();
        list4.addEnd("Hello");
        list4.addEnd("To");
        list4.addEnd("My");
        list4.addEnd("Grader");
        list4.addEnd("Bob");
        list4.print();

        System.out.println("\nList 5:");
        GenLinkedList<String> list5 = new GenLinkedList<>();
        list5.addEnd("Bob");
        list5.print();


        System.out.println("\nTest #1:\nInsert List 2 at pos 0 in List 1. (Beginning of the linked list insert test)");
        list1.insertList(list2, 0);
        list1.print();

        System.out.println("\nTest #2:\nInsert List 3 at pos 2 in List 1. (End of the linked list insert test)");
        list1.insertList(list3, 5);
        list1.print();

        System.out.println("\nTest #3:\nInsert List 4 at pos 4 in List 1. (Middle of the linked list insert test)");
        list1.insertList(list4, 4);
        list1.print();

        System.out.println("\nTest #4:\nInsert List 5 at pos 100 in List 1. (Out of bounds exception test)");
        try {
            list1.insertList(list5, 100);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}

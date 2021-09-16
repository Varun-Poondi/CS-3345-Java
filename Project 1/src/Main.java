public class Main {
    public static void main(String[] args) {
        GenLinkedList<Integer> test = new GenLinkedList<>();
        //Testing Phase
        
        /***********************/
        //Create Linked List using front and back methods.
        test.print(); //Linked List is Currently Empty.
        test.addEnd(10);
        test.addEnd(20);
        test.addEnd(30);
        test.addEnd(40);
        
        test.addFront(33);
        test.addFront(56);
        test.addFront(72);
        test.addFront(93);
        
        test.print(); //Output: Current Linked List: 93 72 56 33 10 20 30 40 
        /***********************/
        //Remove nodes from the front and the end of the linked list.
        test.removeFront(); 
        test.print(); //Output: Current Linked List: 72 56 33 10 20 30 40
        test.removeFront(); 
        test.print(); //Output: Current Linked List: 56 33 10 20 30 40
        
        test.removeEnd(); 
        test.print(); //Output: Current Linked List: 56 33 10 20 30 
        test.removeEnd(); 
        test.print(); //Output: Current Linked List: 56 33 10 20 
        /***********************/
        //Get and Set Payload at given position
        if(test.get(2) != null){
            System.out.println("Payload at position 2: " + test.get(2));
        }else{
            System.out.println("Required position is out of bounds.");
        }
        //Output: Payload at position 2: 10

        if(test.get(10) != null){
            System.out.println("Payload at position 2: " + test.get(2));
        }else{
            System.out.println("Required position is out of bounds.");
        }
        //Output: Required position is out of bounds.
        
        test.set(2,99);
        test.set(1,420);
        test.print(); //Output: Current Linked List: 56 420 99 20

        /***********************/
        //Swap two nodes
        test.swap(1,2); 
        test.print(); ////Output: Current Linked List: 56 99 420 20 
        test.swap(1,4); //Should give error message 
        test.swap(0,3);
        test.print();
        /***********************/
        //Tests removeMatching, erase, insertList methods 
        GenLinkedList<Integer> newTest = new GenLinkedList<>();
        System.out.println("\nLinked List 2");
        newTest.addFront(29);
        newTest.addFront(33);
        newTest.addFront(29);
        newTest.addFront(87);
        newTest.addFront(29);
        newTest.addFront(389);
        newTest.addFront(29);
        newTest.print();
        
        newTest.removeMatching(29);
        newTest.print();
        
        newTest.addFront(87);
        newTest.print();
        newTest.removeMatching(87);
        newTest.print();
        
        System.out.println("Current Linked List Size: " + newTest.getSize());
        newTest.print();
        
        
        
        
        //When do we use current.getNext() vs current when we check if isn't null? 
        
        
        
        
        
        
    }
}

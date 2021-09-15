import java.util.List;

public class GenLinkedList<T extends Comparable<T>>{
    public static class Node<T extends Comparable <T>> implements Comparable<Node<T>>{
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

        @Override
        public int compareTo(Node<T> obj) {
            return this.payload.compareTo(obj.payload);
        }
    }
    private Node<T> head;
    
    public void addFront(T payload){
        Node<T> newNode = new Node<>(payload);
        newNode.setNext(head);
        head = newNode;
    }
    public void addEnd(T payload){
        Node<T> currentNode = head;
        if(currentNode == null){
            currentNode = new Node<>(payload);
            head = currentNode;
        }else{
            Node<T> newNode = new Node<>(payload);
            while(currentNode.getNext() != null){
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
        }
    }
    public void removeFront(){
        if(head != null){
            head = head.getNext();
        }
    }
    public void removeEnd() {
        if (head != null) {
            Node<T> currentNode = head;
            Node<T> previousNode = null;
            while (currentNode.getNext() != null) {
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            if (previousNode != null) {
                previousNode.setNext(currentNode.getNext());
            }
        }
    }
    public void set(int position, T payload){
        if (head != null) {
            if (position < getSize() && position >= 0) {
                int counter = 0;
                Node<T> currentNode = head;
                while (currentNode.getNext() != null && counter != position) {
                    counter++;
                    currentNode = currentNode.getNext();
                }
                currentNode.setPayload(payload);
            }else{
                System.out.println("Required position is out of bounds");
            }
        }
    }
    public T get(int position){
        if(head != null) {
            Node<T> currentNode = getNodeAtPosition(position);
            if(currentNode != null){
                return currentNode.getPayload();
            }
        }
        return null;
    }
    public Node<T> getNodeAtPosition(int pos){
        if(head != null){
            int counter = 0;
            Node<T> currentNode = head;
            while(currentNode.getNext() != null && counter != pos){
                counter ++;
                currentNode = currentNode.getNext();
            }
            return currentNode;
        }
        return null;
    }
    public void setNodeAtPosition(int pos, T payload){
        if(head != null){
            int counter = 0;
            Node<T> currentNode = head;
            while(currentNode.getNext() != null && counter != pos){
                counter ++;
                currentNode = currentNode.getNext();
            }
            currentNode.setPayload(payload);
        }
    }
    public void swap(int pos1, int pos2){
        if(head != null){
            if(getSize() > 0 && pos1 <= getSize() && pos1 >= 0 && pos2 <= getSize() && pos2 >= 0){
                setNodeAtPosition(pos1, getNodeAtPosition(pos1).getPayload());
                setNodeAtPosition(pos2, getNodeAtPosition(pos2).getPayload());
            }else if(getSize() == 0) {
                System.out.println("Linked List is Empty");
            }else{
                System.out.println("Position 1 or Position 2 is out of bounds\n" +
                        "Must be between 0 and " + getSize());
            }
        }
    }
    public void shift(int shiftVal){
        //??
    }
    public void print(){
        System.out.print("Current Linked List: ");
        printList(head);
        System.out.println();
    }
    public void printList(Node<T> node){
        if(node != null){
            System.out.print(node.getPayload() + " ");
            printList(node.getNext());
        }
    }
    public int getSize(){
        if(head != null) {
            int size = 0;
            Node<T> currentNode = head;
            while(currentNode.getNext() != null){
                currentNode = currentNode.getNext();
                size ++;
            }
            return size;
        }
        return 0;
    }

    /*********************************************************/
    public void removeMatching(T payload){
         if(head != null){
             Node<T> currentNode = head;
             Node<T> previousNode = null;
             while(currentNode.getNext() != null){
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
    
    public void erase(int position, int limit){
        if(head != null){
            if(getSize() > 0 && position <= getSize() && position >= 0 && limit <= getSize() && limit >= 0){
                if(position < limit) {
                    Node<T> currentNode = getNodeAtPosition(position); //pointing to the same address
                    Node<T> previousNode = null;
                    int counter = position;
                    while (currentNode.getNext() != null && counter != limit) {
                        if(currentNode == head){
                            head = head.getNext();
                        }else if(previousNode != null){
                            previousNode.setNext(currentNode.getNext());
                        }
                        previousNode = currentNode;
                        currentNode = currentNode.getNext();
                        counter ++;
                    }
                }else{
                    System.out.println("Your position value cannot be greater than limit");
                }
            }
        }
    }
    public void insertList(List<T> list){
        
    }
    
    
    
}

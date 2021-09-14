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
        
    }
    public T get(int position){
        return null;
    }
    public void swap(int pos1, int pos2){
        
    }
    public void shift(int shiftVal){
        
    }
    public void print(){
        System.out.print("Current Linked List: ");
        printList(head);
    }
    public void printList(Node<T> node){
        if(node != null){
            System.out.print(node.getPayload() + " ");
            printList(node.getNext());
        }
    }
    /*********************************************************/
    public void removeMatching(T payload){
        
    }
    public void erase(int position, int limit){
        
    }
    public void insertList(List<T> list){
        
    }
    
    
    
}

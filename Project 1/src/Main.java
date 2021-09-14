public class Main {
    public static void main(String[] args) {
        GenLinkedList<Integer> test = new GenLinkedList<>();
        test.addEnd(0);
        test.addEnd(1);
        test.addFront(10);
        test.print();
    }
}

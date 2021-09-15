public class Main {
    public static void main(String[] args) {
        GenLinkedList<Integer> test = new GenLinkedList<>();
        test.addEnd(0);
        test.addEnd(1);
        test.addEnd(10);
        test.addEnd(15);
        test.addEnd(44);
        test.addFront(72);
        test.addFront(88);
        test.print();
        test.set(5,3340);
        test.print();
    }
}

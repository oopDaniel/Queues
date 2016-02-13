import edu.princeton.cs.algs4.StdIn;

public class Subset {
    public static void main(String[] args) {
        int limit = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rq.enqueue(s);
        }

        for (int i = 0; i < limit; i++) {
            System.out.println(rq.dequeue());
        }
    }
}

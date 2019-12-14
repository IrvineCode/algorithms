import java.util.*;

class datastructures {
  public static void main(String[] args) {

    // Stack
    // FILO
    Stack<Integer> stack = new Stack<>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.pop();
    System.out.println("stack: " + stack);

    // Queue
    // FIFO
    Queue<Integer> queue = new LinkedList<>();
    queue.add(1);
    queue.add(2);
    queue.add(3);
    queue.remove();
    System.out.println("Queue: " + queue);

    // Deque
    // stack + queue
    Deque<Integer> deque = new LinkedList<>();
    deque.addFirst(1);
    deque.add(2);
    deque.addLast(3);
    deque.addLast(4);
    deque.removeFirst();
    deque.removeLast();
    System.out.println("Deque: " + deque);

    // TreeMap
    // Keeps "keys" sorted
    TreeMap<Integer, Integer> tmap = new TreeMap<>((a, b) -> b - a);
    tmap.put(10, 2);
    tmap.put(2, 2);
    tmap.put(50, 1);
    System.out.println("treemap: " + tmap);

    // Priority Queue
    // poll in order                                                                                                                                                                     
    PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
    q.add(1);
    q.add(5);
    q.add(2);
    q.add(3);
    q.add(4);
    // polling. (in sorted order)
    while (q.size() > 0) {
      System.out.println(q.poll());
    }
  }
}
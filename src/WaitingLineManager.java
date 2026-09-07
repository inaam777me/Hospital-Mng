import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * WaitingLineManager: FIFO queue that manages patients waiting to be seen.
 */
public class WaitingLineManager {

    private final Queue<PatientProfile> queue = new LinkedList<>();

    public void enqueue(PatientProfile profile) {
        queue.offer(profile);
    }

    public PatientProfile dequeue() {
        return queue.poll();
    }

    public PatientProfile peek() {
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public List<PatientProfile> showAll() {
        return new LinkedList<>(queue);
    }
}

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * TreatmentLog: LIFO stack that records treatments performed, most
 * recent first (useful for "undo last treatment" style operations).
 */
public class TreatmentLog {

    public static class Record {
        public final int patientId;
        public final String treatment;

        public Record(int patientId, String treatment) {
            this.patientId = patientId;
            this.treatment = treatment;
        }

        @Override
        public String toString() {
            return "Patient #" + patientId + " -> " + treatment;
        }
    }

    private final Deque<Record> stack = new ArrayDeque<>();

    public void push(Record record) {
        stack.push(record);
    }

    public Record pop() {
        return stack.isEmpty() ? null : stack.pop();
    }

    public Record peek() {
        return stack.isEmpty() ? null : stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    public List<Record> showAll() {
        // most recent first (Deque used as a stack already iterates head-first)
        return new ArrayList<>(stack);
    }
}


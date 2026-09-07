import java.util.ArrayList;
import java.util.List;

/**
 * PatientHistoryChain: singly linked list storing a chronological
 * history of visits/events for a single patient.
 */
public class PatientHistoryChain {

    private static class HistoryNode {
        String entry;
        HistoryNode next;

        HistoryNode(String entry) {
            this.entry = entry;
        }
    }

    private HistoryNode head;
    private int count;

    public void addEntry(String entry) {
        HistoryNode node = new HistoryNode(entry);
        if (head == null) {
            head = node;
        } else {
            HistoryNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        count++;
    }

    public boolean removeEntry(String entry) {
        HistoryNode prev = null;
        HistoryNode current = head;
        while (current != null) {
            if (current.entry.equals(entry)) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                count--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return count;
    }

    public List<String> toList() {
        List<String> result = new ArrayList<>();
        HistoryNode current = head;
        while (current != null) {
            result.add(current.entry);
            current = current.next;
        }
        return result;
    }
}

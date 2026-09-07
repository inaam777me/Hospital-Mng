import java.util.ArrayList;
import java.util.List;

/**
 * PatientBST: Binary Search Tree keyed on patient ID, used to store
 * and quickly locate PatientProfile records.
 */
public class PatientBST {

    private static class Node {
        PatientProfile profile;
        Node left;
        Node right;

        Node(PatientProfile profile) {
            this.profile = profile;
        }
    }

    private Node root;

    public void insert(PatientProfile profile) {
        root = insert(root, profile);
    }

    private Node insert(Node node, PatientProfile profile) {
        if (node == null) {
            return new Node(profile);
        }
        if (profile.getPatientId() < node.profile.getPatientId()) {
            node.left = insert(node.left, profile);
        } else if (profile.getPatientId() > node.profile.getPatientId()) {
            node.right = insert(node.right, profile);
        } else {
            // same id -> update existing record
            node.profile = profile;
        }
        return node;
    }

    public List<PatientProfile> inorder() {
        List<PatientProfile> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node node, List<PatientProfile> result) {
        if (node != null) {
            inorder(node.left, result);
            result.add(node.profile);
            inorder(node.right, result);
        }
    }

    public PatientProfile search(int patientId) {
        Node node = search(root, patientId);
        return node == null ? null : node.profile;
    }

    private Node search(Node node, int patientId) {
        if (node == null) {
            return null;
        }
        if (patientId == node.profile.getPatientId()) {
            return node;
        }
        return patientId < node.profile.getPatientId()
                ? search(node.left, patientId)
                : search(node.right, patientId);
    }

    public void delete(int patientId) {
        root = delete(root, patientId);
    }

    private Node delete(Node node, int patientId) {
        if (node == null) {
            return null;
        }
        if (patientId < node.profile.getPatientId()) {
            node.left = delete(node.left, patientId);
        } else if (patientId > node.profile.getPatientId()) {
            node.right = delete(node.right, patientId);
        } else {
            // node found
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // two children: replace with inorder successor
            Node successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            node.profile = successor.profile;
            node.right = delete(node.right, successor.profile.getPatientId());
        }
        return node;
    }
}

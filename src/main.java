import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main menu: ties together PatientProfile/PatientBST, WaitingLineManager
 * (Queue), TreatmentLog (Stack), and PatientHistoryChain (Linked List)
 * into one interactive hospital management program.
 */
public class main {

    private static final PatientBST patientBst = new PatientBST();
    private static final WaitingLineManager waitingLine = new WaitingLineManager();
    private static final TreatmentLog treatmentLog = new TreatmentLog();
    private static final Map<Integer, PatientHistoryChain> historyChains = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": addPatient(); break;
                case "2": searchPatient(); break;
                case "3": deletePatient(); break;
                case "4": showWaitingLine(); break;
                case "5": serveNextPatient(); break;
                case "6": undoLastTreatment(); break;
                case "7": showPatientHistory(); break;
                case "8": listAllPatients(); break;
                case "0":
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Hospital Management System ---");
        System.out.println("1. Add patient");
        System.out.println("2. Search patient");
        System.out.println("3. Delete patient");
        System.out.println("4. Show waiting line");
        System.out.println("5. Serve next patient");
        System.out.println("6. Undo last treatment");
        System.out.println("7. Show patient history");
        System.out.println("8. List all patients");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    private static void addPatient() {
        int id = readInt("Patient ID: ");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        int age = readInt("Age: ");
        System.out.print("Ailment: ");
        String ailment = scanner.nextLine();

        PatientProfile profile = new PatientProfile(id, name, age, ailment);
        patientBst.insert(profile);
        historyChains.putIfAbsent(id, new PatientHistoryChain());
        waitingLine.enqueue(profile);
        System.out.println("Added and queued patient " + id + ".");
    }

    private static void searchPatient() {
        int id = readInt("Patient ID to search: ");
        PatientProfile profile = patientBst.search(id);
        System.out.println(profile != null ? profile : "Not found.");
    }

    private static void deletePatient() {
        int id = readInt("Patient ID to delete: ");
        patientBst.delete(id);
        historyChains.remove(id);
        System.out.println("Deleted patient " + id + " (if existed).");
    }

    private static void showWaitingLine() {
        for (PatientProfile p : waitingLine.showAll()) {
            System.out.println(p);
        }
    }

    private static void serveNextPatient() {
        PatientProfile profile = waitingLine.dequeue();
        if (profile == null) {
            System.out.println("Waiting line is empty.");
            return;
        }
        System.out.println("Now serving: " + profile);
        System.out.print("Enter treatment given: ");
        String treatment = scanner.nextLine();
        treatmentLog.push(new TreatmentLog.Record(profile.getPatientId(), treatment));
        historyChains.putIfAbsent(profile.getPatientId(), new PatientHistoryChain());
        historyChains.get(profile.getPatientId()).addEntry(treatment);
    }

    private static void undoLastTreatment() {
        TreatmentLog.Record record = treatmentLog.pop();
        System.out.println(record != null ? "Undid: " + record : "No treatments to undo.");
    }

    private static void showPatientHistory() {
        int id = readInt("Patient ID: ");
        PatientHistoryChain chain = historyChains.get(id);
        if (chain == null || chain.size() == 0) {
            System.out.println("No history found.");
            return;
        }
        int i = 1;
        for (String entry : chain.toList()) {
            System.out.println(i++ + ". " + entry);
        }
    }

    private static void listAllPatients() {
        for (PatientProfile p : patientBst.inorder()) {
            System.out.println(p);
        }
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}

# Hospital Management System

A console-based Java application for managing hospital patient records, waiting lines, treatments, and treatment histories.

## Features

- Add a patient with an ID, name, age, and ailment
- Search for a patient by patient ID
- Delete a patient record
- View all patients in the waiting line
- Serve the next patient in order
- Record a treatment for a served patient
- Undo the most recently recorded treatment
- View an individual patient's treatment history
- List all registered patients ordered by patient ID

## Data Structures Used

| Data structure | Purpose in the system |
| --- | --- |
| Binary Search Tree (BST) | Stores patient profiles by patient ID for efficient insertion, searching, deletion, and sorted listing. |
| Queue | Manages the waiting line using First-In, First-Out (FIFO) order. |
| Stack | Stores recent treatment records and supports undoing the latest treatment. |
| Singly Linked List | Keeps each patient's treatment history in chronological order. |
| HashMap | Associates each patient ID with its treatment-history list. |

## Project Structure

```text
src/
├── main.java                 # Menu-driven application entry point
├── PatientProfile.java       # Patient data model
├── PatientBST.java           # Binary Search Tree for patient records
├── WaitingLineManager.java   # Queue for waiting patients
├── TreatmentLog.java         # Stack for treatment records
└── PatientHistoryChain.java  # Linked list for treatment history
```

## Requirements

- Java Development Kit (JDK) 8 or later

## How to Run

1. Open a terminal in the project folder.
2. Compile the source files:

   ```powershell
   javac src\*.java
   ```

3. Run the application:

   ```powershell
   java -cp src main
   ```

## Menu Options

```text
1. Add patient
2. Search patient
3. Delete patient
4. Show waiting line
5. Serve next patient
6. Undo last treatment
7. Show patient history
8. List all patients
0. Exit
```

## Example Workflow

1. Add several patients to register them and place them in the waiting line.
2. Use **Show waiting line** to see the order in which patients will be served.
3. Select **Serve next patient** and enter the treatment given.
4. Use **Show patient history** to view the saved treatments for a patient.
5. If the last treatment was entered incorrectly, use **Undo last treatment**.

## Author

Created as a Java data-structures project.

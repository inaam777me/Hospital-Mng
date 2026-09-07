/**
 * PatientProfile: holds one patient's data.
 */
public class PatientProfile {
    private final int patientId;
    private final String name;
    private final int age;
    private final String ailment;

    public PatientProfile(int patientId, String name, int age, String ailment) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.ailment = ailment;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAilment() {
        return ailment;
    }

    @Override
    public String toString() {
        return "ID: " + patientId + " | Name: " + name +
                " | Age: " + age + " | Ailment: " + ailment;
    }
}

import java.util.*;
class SpeechTherapySystem {
    static Map<Integer, Integer> dp = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    static class Patient {
        int id;
        String name;
        int progress;
        Patient(int id, String name) {
            this.id = id;
            this.name = name;
            this.progress = 0;
        }
    }
    static class Therapist {
        String name;
        Therapist(String name) {
            this.name = name;
        }
        void assignTherapy(Patient patient) {
            System.out.println("Therapist " + name + " assigned therapy to " + patient.name);
        }
        int trackProgress(int sessions) {
            if (sessions <= 0) return 0; 
            if (dp.containsKey(sessions)) return dp.get(sessions);
            int bestProgress = Math.max(trackProgress(sessions - 1) + 5, trackProgress(sessions - 2) + 8);
            dp.put(sessions, bestProgress);
            return bestProgress;
        }
    }
    static class Supervisor {
        String name;
        Supervisor(String name) {
            this.name = name;
        }
        void evaluateProgress(Patient patient, int sessions) {
            Therapist autoTherapist = new Therapist("Auto");
            int finalProgress = autoTherapist.trackProgress(sessions);
            patient.progress = finalProgress; 
            System.out.println("Supervisor " + name + " evaluated " + patient.name + " with progress: " + finalProgress + "%");
            generateReport(patient, sessions);
        }
        void generateReport(Patient patient, int sessions) {
            System.out.println("\n===== Therapy Report =====");
            System.out.println("Patient Name: " + patient.name);
            System.out.println("Total Sessions: " + sessions);
            System.out.println("Therapy Progress: " + patient.progress + "%");
            System.out.println("Supervisor: " + name);
            System.out.println("==========================");
        }
    }
    public static void main(String[] args) {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Patient Name: ");
        String patientName = scanner.nextLine();
        System.out.print("Enter Therapist Name: ");
        String therapistName = scanner.nextLine();
        System.out.print("Enter Supervisor Name: ");
        String supervisorName = scanner.nextLine();
        System.out.print("Enter Number of Therapy Sessions: ");
        int numSessions = scanner.nextInt();
        Patient p1 = new Patient(patientId, patientName);
        Therapist t1 = new Therapist(therapistName);
        Supervisor s1 = new Supervisor(supervisorName);
        t1.assignTherapy(p1);
        s1.evaluateProgress(p1, numSessions);
    }
}
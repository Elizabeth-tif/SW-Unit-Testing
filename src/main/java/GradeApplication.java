import java.util.Scanner;

public class GradeApplication {
    private final Scanner scanner;
    private final GradeProcessingModule processingModule;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradeApplication application = new GradeApplication(scanner);

        try {
            application.run();
        } finally {
            scanner.close();
        }
    }

    public GradeApplication(Scanner scanner) {
        this.scanner = scanner;
        this.processingModule = new GradeProcessingModule(scanner);
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    processingModule.process();
                    break;
                case "2":
                    running = false;
                    System.out.println("Program selesai. Terima kasih.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih 1 atau 2.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Program Pengolahan Nilai Mahasiswa ===");
        System.out.println("1. Input & Proses Nilai Mahasiswa");
        System.out.println("2. Keluar");
        System.out.print("Pilih menu (1/2): ");
    }
}
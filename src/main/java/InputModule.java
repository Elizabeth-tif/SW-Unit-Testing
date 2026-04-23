import java.util.Scanner;

public class InputModule {
    private final Scanner scanner;
    private final ValidationModule validationModule;

    public InputModule(Scanner scanner, ValidationModule validationModule) {
        this.scanner = scanner;
        this.validationModule = validationModule;
    }

    public ScoreInput requestValidScores() {
        while (true) {
            double tugas = readDouble("Masukkan nilai tugas (0-100): ");
            double uts = readDouble("Masukkan nilai UTS (0-100): ");
            double uas = readDouble("Masukkan nilai UAS (0-100): ");

            boolean valid = validationModule.isValid(tugas, uts, uas);
            if (valid) {
                return new ScoreInput(tugas, uts, uas);
            }

            System.out.println("Data tidak valid. Silakan input ulang sesuai aturan.");
            System.out.println("Aturan: nilai 0-100, tidak boleh semua bernilai 0.");
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String raw = scanner.nextLine().trim();
            try {
                return Double.parseDouble(raw);
            } catch (NumberFormatException ex) {
                System.out.println("Input harus berupa angka. Coba lagi.");
            }
        }
    }
}

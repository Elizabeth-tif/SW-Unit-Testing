import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradeProcessingModule processingModule = new GradeProcessingModule(scanner);

        boolean running = true;
        while (running) {
            System.out.println("\n=== Program Pengolahan Nilai Mahasiswa ===");
            System.out.println("1. Input & Proses Nilai Mahasiswa");
            System.out.println("2. Keluar");
            System.out.print("Pilih menu (1/2): ");

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

        scanner.close();
    }

    static class ScoreInput {
        final double tugas;
        final double uts;
        final double uas;

        ScoreInput(double tugas, double uts, double uas) {
            this.tugas = tugas;
            this.uts = uts;
            this.uas = uas;
        }
    }

    static class InputModule {
        private final Scanner scanner;
        private final ValidationModule validationModule;

        InputModule(Scanner scanner, ValidationModule validationModule) {
            this.scanner = scanner;
            this.validationModule = validationModule;
        }

        ScoreInput requestValidScores() {
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

    static class ValidationModule {
        boolean isValid(double tugas, double uts, double uas) {
            boolean allInRange = isInRange(tugas) && isInRange(uts) && isInRange(uas);
            if (!allInRange) {
                return false;
            }

            // Semua 0 dianggap belum input, jadi tidak valid.
            return !(tugas == 0 && uts == 0 && uas == 0);
        }

        private boolean isInRange(double value) {
            return value >= 0 && value <= 100;
        }
    }

    static class FinalScoreModule {
        private final ValidationModule validationModule;

        FinalScoreModule(ValidationModule validationModule) {
            this.validationModule = validationModule;
        }

        double calculate(double tugas, double uts, double uas) {
            if (!validationModule.isValid(tugas, uts, uas)) {
                return -1;
            }

            double finalScore = (0.3 * tugas) + (0.3 * uts) + (0.4 * uas);
            if (finalScore > 100) {
                return -1;
            }
            return finalScore;
        }
    }

    static class GradeModule {
        String determineGrade(double finalScore) {
            if (finalScore >= 85) {
                return "A";
            }
            if (finalScore >= 70) {
                return "B";
            }
            if (finalScore >= 60) {
                return "C";
            }
            if (finalScore >= 50) {
                return "D";
            }
            return "E";
        }
    }

    static class GraduationModule {
        String determineStatus(double finalScore) {
            return finalScore >= 60 ? "Lulus" : "Tidak Lulus";
        }
    }

    static class GradeProcessingModule {
        private final InputModule inputModule;
        private final FinalScoreModule finalScoreModule;
        private final GradeModule gradeModule;
        private final GraduationModule graduationModule;

        GradeProcessingModule(Scanner scanner) {
            ValidationModule validationModule = new ValidationModule();
            this.inputModule = new InputModule(scanner, validationModule);
            this.finalScoreModule = new FinalScoreModule(validationModule);
            this.gradeModule = new GradeModule();
            this.graduationModule = new GraduationModule();
        }

        void process() {
            System.out.println("\n--- Input Data Nilai Mahasiswa ---");
            ScoreInput scoreInput = inputModule.requestValidScores();

            double finalScore = finalScoreModule.calculate(
                scoreInput.tugas,
                scoreInput.uts,
                scoreInput.uas
            );

            if (finalScore < 0) {
                System.out.println("Terjadi error saat menghitung nilai akhir.");
                return;
            }

            String grade = gradeModule.determineGrade(finalScore);
            String status = graduationModule.determineStatus(finalScore);

            System.out.println("\n--- Hasil Pengolahan Nilai ---");
            System.out.printf("Nilai Akhir : %.2f%n", finalScore);
            System.out.println("Grade       : " + grade);
            System.out.println("Status      : " + status);
        }
    }
}

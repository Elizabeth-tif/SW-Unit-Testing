import java.util.Scanner;

public class GradeProcessingModule {
    private final InputModule inputModule;
    private final FinalScoreModule finalScoreModule;
    private final GradeModule gradeModule;
    private final GraduationModule graduationModule;

    public GradeProcessingModule(Scanner scanner) {
        ValidationModule validationModule = new ValidationModule();
        this.inputModule = new InputModule(scanner, validationModule);
        this.finalScoreModule = new FinalScoreModule(validationModule);
        this.gradeModule = new GradeModule();
        this.graduationModule = new GraduationModule();
    }

    public void process() {
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

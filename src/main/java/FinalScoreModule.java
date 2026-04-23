public class FinalScoreModule {
    private final ValidationModule validationModule;

    public FinalScoreModule(ValidationModule validationModule) {
        this.validationModule = validationModule;
    }

    public double calculate(double tugas, double uts, double uas) {
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

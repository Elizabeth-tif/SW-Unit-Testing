public class GradeModule {
    public String determineGrade(double finalScore) {
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

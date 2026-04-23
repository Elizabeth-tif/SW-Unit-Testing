public class ValidationModule {
    public boolean isValid(double tugas, double uts, double uas) {
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("TC-VL: ValidationModule Tests")
public class ValidationModuleTest {

    private final ValidationModule validationModule = new ValidationModule();

    @Test
    @DisplayName("TC-VL01 | Memvalidasi Tiga Nilai dalam Rentang dan Tidak Semuanya Nol")
    void shouldAcceptScoresWithinRangeWhenNotAllZero() {
        // (1) setup (arrange, build)

        // (2) exercise (act, operate)
        boolean result = validationModule.isValid(80, 70, 60);

        // (3) verify (assert, check)
        assertTrue(result);
    }

    @Test
    @DisplayName("TC-VL02 | Tolak Input Jika Ada Nilai di Luar Rentang")
    void shouldRejectScoresOutsideRange() {
        // (1) setup (arrange, build)

        // (2) exercise (act, operate)
        boolean result = validationModule.isValid(-1, 70, 60);

        // (3) verify (assert, check)
        assertFalse(result);
    }

    @Test
    @DisplayName("TC-VL03 | Tolak Input Jika Semua Nilai Nol")
    void shouldRejectWhenAllScoresAreZero() {
        // (1) setup (arrange, build)

        // (2) exercise (act, operate)
        boolean result = validationModule.isValid(0, 0, 0);

        // (3) verify (assert, check)
        assertFalse(result);
    }
}
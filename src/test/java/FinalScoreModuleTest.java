import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test Script: FinalScoreModule
 * PIC     : Fathan
 * Tanggal : 12-Apr-26
 *
 * Mencakup path FS1 dan FS2 dari test case design.
 */
@DisplayName("TC-FS: FinalScoreModule Tests")
public class FinalScoreModuleTest {

    private ValidationModule validationModule;
    private FinalScoreModule finalScoreModule;

    @BeforeEach
    void setUp() {
        validationModule = new ValidationModule();
        finalScoreModule = new FinalScoreModule(validationModule);
    }

    @Test
    @DisplayName("TC-FS01 | FS1 | Hitung Nilai Akhir untuk Tiga Nilai Valid")
    void shouldCalculateFinalScoreForValidInputs() {
        // (1) setup (arrange, build)

        // (2) exercise (act, operate)
        double result = finalScoreModule.calculate(80, 80, 80);

        // (3) verify (assert, check)
        assertEquals(80.0, result, 0.001);
    }

    @Test
    @DisplayName("TC-FS02 | FS2 | Tolak Perhitungan untuk Input Tidak Valid")
    void shouldReturnErrorCodeForInvalidInputs() {
        // (1) setup (arrange, build)

        // (2) exercise (act, operate)
        double result = finalScoreModule.calculate(-10, 80, 80);

        // (3) verify (assert, check)
        assertEquals(-1.0, result, 0.001);
    }
}

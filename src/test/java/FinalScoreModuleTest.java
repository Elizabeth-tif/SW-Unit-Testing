import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test Script: FinalScoreModule
 * PIC     : Fathan
 * Tanggal : 12-Apr-26
 *
 * Mencakup path FS1 dan FS2 dari test case design (path testing).
 */
@DisplayName("TC-FS: FinalScoreModule Tests")
public class FinalScoreModuleTest {

    private Main.ValidationModule validationModule;
    private Main.FinalScoreModule finalScoreModule;

    @BeforeEach
    void setUp() {
        validationModule = new Main.ValidationModule();
        finalScoreModule = new Main.FinalScoreModule(validationModule);
    }

    /**
     * TC-FS01 | Path FS1
     * Kalkulasi nilai akhir dengan parameter valid (Tugas=80, UTS=80, UAS=80).
     * Expected: (0.3*80) + (0.3*80) + (0.4*80) = 80.0
     */
    @Test
    @DisplayName("TC-FS01 | FS1 | Kalkulasi Nilai Akhir - Parameter Valid")
    void testCalculateValidScore() {
        double result = finalScoreModule.calculate(80, 80, 80);
        assertEquals(80.0, result, 0.001);
    }

    /**
     * TC-FS02 | Path FS2
     * Uji keamanan: memanggil calculate() langsung dengan nilai negatif
     * (bypass validasi dari InputModule). Expected: return -1.0 (error code).
     */
    @Test
    @DisplayName("TC-FS02 | FS2 | Bypass Validasi Kalkulasi - Parameter Invalid (Tugas=-10)")
    void testCalculateInvalidParameter() {
        double result = finalScoreModule.calculate(-10, 80, 80);
        assertEquals(-1.0, result, 0.001);
    }
}

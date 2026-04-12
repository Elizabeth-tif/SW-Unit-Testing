import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test Script: GradeModule
 * PIC     : Fathan (TC-GR01 ~ TC-GR03), Elizabeth (TC-GR04 ~ TC-GR06)
 * Tanggal : 12-Apr-26
 *
 * Mencakup semua independent path GR1 s.d. GR5. Path GR6 adalah infeasible
 * sehingga tidak diuji.
 */
@DisplayName("TC-GR: GradeModule Tests")
public class GradeModuleTest {

    private Main.GradeModule gradeModule;

    @BeforeEach
    void setUp() {
        gradeModule = new Main.GradeModule();
    }

    /**
     * TC-GR01 | Path GR1
     * Nilai batas bawah Grade A: finalScore = 85 → expected "A"
     */
    @Test
    @DisplayName("TC-GR01 | GR1 | Grade A - Batas Bawah (finalScore = 85)")
    void testGradeA_lowerBound() {
        assertEquals("A", gradeModule.determineGrade(85));
    }

    /**
     * TC-GR02 | Path GR2
     * Nilai batas bawah Grade B: finalScore = 70 → expected "B"
     */
    @Test
    @DisplayName("TC-GR02 | GR2 | Grade B - Batas Bawah (finalScore = 70)")
    void testGradeB_lowerBound() {
        assertEquals("B", gradeModule.determineGrade(70));
    }

    /**
     * TC-GR03 | Path GR2
     * Nilai batas atas Grade B: finalScore = 84.99 → expected "B"
     */
    @Test
    @DisplayName("TC-GR03 | GR2 | Grade B - Batas Atas (finalScore = 84.99)")
    void testGradeB_upperBound() {
        assertEquals("B", gradeModule.determineGrade(84.99));
    }

    /**
     * TC-GR04 | Path GR3
     * Nilai tengah Grade C: finalScore = 65 → expected "C"
     */
    @Test
    @DisplayName("TC-GR04 | GR3 | Grade C - Nilai Tengah (finalScore = 65)")
    void testGradeC_midValue() {
        assertEquals("C", gradeModule.determineGrade(65));
    }

    /**
     * TC-GR05 | Path GR4
     * Nilai batas bawah Grade D: finalScore = 50 → expected "D"
     *
     * Catatan Simulasi: Pada kondisi terdapat bug operator logika (misal '>='
     * diganti '>'), nilai 50 akan salah dipetakan ke "E". Dalam kode saat ini
     * implementasi sudah benar, sehingga test PASS. Jika ditemukan failure,
     * catat sebagai INC-001.
     */
    @Test
    @DisplayName("TC-GR05 | GR4 | Grade D - Batas Bawah (finalScore = 50)")
    void testGradeD_lowerBound() {
        assertEquals("D", gradeModule.determineGrade(50));
    }

    /**
     * TC-GR06 | Path GR5
     * Nilai rendah Grade E: finalScore = 30 → expected "E"
     */
    @Test
    @DisplayName("TC-GR06 | GR5 | Grade E - Nilai Rendah (finalScore = 30)")
    void testGradeE_lowValue() {
        assertEquals("E", gradeModule.determineGrade(30));
    }
}

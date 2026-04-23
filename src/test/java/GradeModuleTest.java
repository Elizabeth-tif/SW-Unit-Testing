import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    private GradeModule gradeModule;

    @BeforeEach
    void setUp() {
        gradeModule = new GradeModule();
    }

    @Test
    @DisplayName("TC-GR01 | GR1 | Klasifikasi Grade A pada Batas Bawah")
    void shouldReturnGradeAAtLowerBoundary() {
        // (1) setup (arrange, build)

        // (2) exercise (act, operate)
        assertEquals("A", gradeModule.determineGrade(85));

        // (3) verify (assert, check)
    }

    @Test
    @DisplayName("TC-GR02 | GR2 | Klasifikasi Grade B pada Batas Bawah")
    void shouldReturnGradeBAtLowerBoundary() {
        // (1) setup (arrange, build)

        // (2) exercise (act, operate)
        assertEquals("B", gradeModule.determineGrade(70));

        // (3) verify (assert, check)
    }

    @Test
    @DisplayName("TC-GR03 | GR2 | Klasifikasi Grade B pada Batas Atas")
    void shouldReturnGradeBAtUpperBoundary() {
        // (1) setup (arrange, build)

        // (2) exercise (act, operate)
        assertEquals("B", gradeModule.determineGrade(84.99));

        // (3) verify (assert, check)
    }

    @Test
    @DisplayName("TC-GR04 | GR3 | Klasifikasi Grade C pada Nilai Tengah")
    void shouldReturnGradeCForMidScore() {
        // (1) setup (arrange, build)

        // (2) exercise (act, operate)
        assertEquals("C", gradeModule.determineGrade(65));

        // (3) verify (assert, check)
    }

    @Test
    @DisplayName("TC-GR05 | GR4 | Klasifikasi Grade D pada Batas Bawah")
    void shouldReturnGradeDAtLowerBoundary() {
        // (1) setup (arrange, build)

        // (2) exercise (act, operate)
        assertEquals("D", gradeModule.determineGrade(50));

        // (3) verify (assert, check)
    }

    @Test
    @DisplayName("TC-GR06 | GR5 | Klasifikasi Grade E pada Nilai Rendah")
    void shouldReturnGradeEForLowScore() {
        // (1) setup (arrange, build)

        // (2) exercise (act, operate)
        assertEquals("E", gradeModule.determineGrade(30));

        // (3) verify (assert, check)
    }
}

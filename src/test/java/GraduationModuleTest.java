import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test Script: GraduationModule
 * PIC     : Elizabeth
 * Tanggal : 12-Apr-26
 *
 * Mencakup path GD1 dan GD2. Path GD3 adalah infeasible sehingga tidak diuji.
 */
@DisplayName("TC-GD: GraduationModule Tests")
public class GraduationModuleTest {

    private GraduationModule graduationModule;

    @BeforeEach
    void setUp() {
        graduationModule = new GraduationModule();
    }

    @Test
    @DisplayName("TC-GD01 | GD1 | Status Lulus pada Batas Kelulusan")
    void shouldReturnLulusAtPassingScore() {
        // (1) setup (arrange, build)

        // (2) exercise (act, operate)
        String result = graduationModule.determineStatus(60);

        // (3) verify (assert, check)
        assertEquals("Lulus", result);
    }

    @Test
    @DisplayName("TC-GD02 | GD2 | Status Tidak Lulus di Bawah Batas Kelulusan")
    void shouldReturnTidakLulusBelowPassingScore() {
        // (1) setup (arrange, build)

        // (2) exercise (act, operate)
        String result = graduationModule.determineStatus(59.99);

        // (3) verify (assert, check)
        assertEquals("Tidak Lulus", result);
    }
}

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test Script: GraduationModule
 * PIC     : Elizabeth
 * Tanggal : 12-Apr-26
 *
 * Mencakup path GD1 dan GD2. Path GD3 adalah infeasible sehingga tidak diuji.
 */
@DisplayName("TC-GD: GraduationModule Tests")
public class GraduationModuleTest {

    private Main.GraduationModule graduationModule;

    @BeforeEach
    void setUp() {
        graduationModule = new Main.GraduationModule();
    }

    /**
     * TC-GD01 | Path GD1
     * Nilai batas bawah kelulusan: finalScore = 60 → expected "Lulus"
     */
    @Test
    @DisplayName("TC-GD01 | GD1 | Kelulusan Lulus - Batas Bawah (finalScore = 60)")
    void testStatusLulus_boundary() {
        assertEquals("Lulus", graduationModule.determineStatus(60));
    }

    /**
     * TC-GD02 | Path GD2
     * Nilai tepat di bawah batas kelulusan: finalScore = 59.99 → expected "Tidak Lulus"
     */
    @Test
    @DisplayName("TC-GD02 | GD2 | Kelulusan Tidak Lulus - Batas Atas Gagal (finalScore = 59.99)")
    void testStatusTidakLulus_boundary() {
        assertEquals("Tidak Lulus", graduationModule.determineStatus(59.99));
    }
}

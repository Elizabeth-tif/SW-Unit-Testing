import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test Script: InputModule
 * PIC     : Faisal
 * Tanggal : 12-Apr-26
 *
 * Mencakup path IN1 dan IN2.
 * Simulasi stdin dilakukan menggunakan ByteArrayInputStream sehingga tidak
 * memerlukan interaksi keyboard selama eksekusi otomatis.
 */
@DisplayName("TC-IN: InputModule Tests")
public class InputModuleTest {

    private Scanner scannerFrom(String simulatedInput) {
        return new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
    }

    @Test
    @DisplayName("TC-IN01 | IN1 | Baca Tiga Nilai Valid dan Bentuk ScoreInput")
    void shouldReturnScoreInputWhenAllValuesAreValid() {
        // (1) setup (arrange, build)
        String simulatedInput = "100\n70.5\n0\n";
        Scanner scanner = scannerFrom(simulatedInput);

        ValidationModule validationModule = new ValidationModule();
        InputModule inputModule = new InputModule(scanner, validationModule);

        // (2) exercise (act, operate)
        ScoreInput result = inputModule.requestValidScores();

        // (3) verify (assert, check)
        assertNotNull(result);
        assertEquals(100.0, result.tugas, 0.001);
        assertEquals(70.5,  result.uts,   0.001);
        assertEquals(0.0,   result.uas,   0.001);
    }

    @Test
    @DisplayName("TC-IN02 | IN2 | Tolak Input di Luar Batas lalu Terima Input Ulang")
    void shouldRetryUntilUserProvidesValidScores() {
        // (1) setup (arrange, build)
        String simulatedInput = "-5\n200\n80\n80\n80\n80\n";
        Scanner scanner = scannerFrom(simulatedInput);

        ValidationModule validationModule = new ValidationModule();
        InputModule inputModule = new InputModule(scanner, validationModule);

        // (2) exercise (act, operate)
        ScoreInput result = inputModule.requestValidScores();

        // (3) verify (assert, check)
        assertNotNull(result);
        assertEquals(80.0, result.tugas, 0.001);
        assertEquals(80.0, result.uts,   0.001);
        assertEquals(80.0, result.uas,   0.001);
    }

    @Test
    @DisplayName("TC-IN03 | IN3 | Tolak Input Semua Nol lalu Terima Input Ulang")
    void shouldRetryWhenAllInputsAreZero() {
        // (1) setup (arrange, build)
        String simulatedInput = "0\n0\n0\n80\n80\n80\n";
        Scanner scanner = scannerFrom(simulatedInput);

        ValidationModule validationModule = new ValidationModule();
        InputModule inputModule = new InputModule(scanner, validationModule);

        // (2) exercise (act, operate)
        ScoreInput result = inputModule.requestValidScores();

        // (3) verify (assert, check)
        assertNotNull(result);
        assertEquals(80.0, result.tugas, 0.001);
        assertEquals(80.0, result.uts,   0.001);
        assertEquals(80.0, result.uas,   0.001);
    }
}

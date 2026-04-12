import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    /**
     * Membuat Scanner dari string simulasi input, dengan setiap baris dipisah '\n'.
     */
    private Scanner scannerFrom(String simulatedInput) {
        return new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
    }

    /**
     * TC-IN01 | Path IN1
     * Input nilai batas wajar: Tugas=100, UTS=70.5, UAS=0.
     * UAS=0 masih valid karena tidak semua nilai nol.
     * Expected: mengembalikan objek ScoreInput dengan nilai sesuai input.
     */
    @Test
    @DisplayName("TC-IN01 | IN1 | Input Nilai Valid (Tugas=100, UTS=70.5, UAS=0)")
    void testInputNilaiValid() {
        String simulatedInput = "100\n70.5\n0\n";
        Scanner scanner = scannerFrom(simulatedInput);

        Main.ValidationModule validationModule = new Main.ValidationModule();
        Main.InputModule inputModule = new Main.InputModule(scanner, validationModule);

        Main.ScoreInput result = inputModule.requestValidScores();

        assertNotNull(result);
        assertEquals(100.0, result.tugas, 0.001);
        assertEquals(70.5,  result.uts,   0.001);
        assertEquals(0.0,   result.uas,   0.001);
    }

    /**
     * TC-IN02 | Path IN2
     * Input pertama keluar dari batas (Tugas=-5, UTS=200, UAS=80) → invalid,
     * sistem menampilkan pesan error dan meminta ulang.
     * Input kedua valid (80, 80, 80).
     * Expected: mengembalikan objek ScoreInput dengan nilai input ulang.
     */
    @Test
    @DisplayName("TC-IN02 | IN2 | Input Out of Bounds lalu Valid (Tugas=-5, UTS=200, UAS=80 -> 80,80,80)")
    void testInputOutOfBounds_thenValid() {
        // Percobaan 1 tidak valid, percobaan 2 valid
        String simulatedInput = "-5\n200\n80\n80\n80\n80\n";
        Scanner scanner = scannerFrom(simulatedInput);

        Main.ValidationModule validationModule = new Main.ValidationModule();
        Main.InputModule inputModule = new Main.InputModule(scanner, validationModule);

        Main.ScoreInput result = inputModule.requestValidScores();

        assertNotNull(result);
        assertEquals(80.0, result.tugas, 0.001);
        assertEquals(80.0, result.uts,   0.001);
        assertEquals(80.0, result.uas,   0.001);
    }

    /**
     * TC-IN03 | Path IN2
     * Input semua nol (Tugas=0, UTS=0, UAS=0) → dianggap belum input → invalid,
     * sistem menampilkan pesan error aturan semua nol dan meminta ulang.
     * Input kedua valid (80, 80, 80).
     * Expected: mengembalikan objek ScoreInput dengan nilai input ulang.
     */
    @Test
    @DisplayName("TC-IN03 | IN2 | Input Semua Nol lalu Valid (0,0,0 -> 80,80,80)")
    void testInputSemuaNol_thenValid() {
        // Percobaan 1: semua 0 → invalid, percobaan 2 valid
        String simulatedInput = "0\n0\n0\n80\n80\n80\n";
        Scanner scanner = scannerFrom(simulatedInput);

        Main.ValidationModule validationModule = new Main.ValidationModule();
        Main.InputModule inputModule = new Main.InputModule(scanner, validationModule);

        Main.ScoreInput result = inputModule.requestValidScores();

        assertNotNull(result);
        assertEquals(80.0, result.tugas, 0.001);
        assertEquals(80.0, result.uts,   0.001);
        assertEquals(80.0, result.uas,   0.001);
    }
}

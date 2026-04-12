import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test Script: Main (Main Module / Loop Menu Utama)
 * PIC     : Faisal
 * Tanggal : 12-Apr-26
 *
 * Mencakup path M1, M2, dan M3.
 * Path M4 adalah infeasible (variabel 'running' selalu true pada iterasi pertama),
 * sehingga tidak diuji.
 *
 * Teknik: System.setIn / System.setOut digunakan untuk simulasi I/O,
 * dan selalu dipulihkan di @AfterEach agar tidak memengaruhi test lain.
 */
@DisplayName("TC-M: Main Module Tests")
public class MainTest {

    private InputStream originalIn;
    private PrintStream originalOut;
    private ByteArrayOutputStream capturedOut;

    @BeforeEach
    void redirectIO() {
        originalIn  = System.in;
        originalOut = System.out;
        capturedOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOut));
    }

    @AfterEach
    void restoreIO() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    /**
     * TC-M01 | Path M1
     * User memilih menu "2" (Keluar).
     * Expected: program berhenti dan mencetak "Program selesai. Terima kasih."
     */
    @Test
    @DisplayName("TC-M01 | M1 | Keluar Aplikasi - Pilih Menu 2")
    void testKeluarAplikasi() {
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));

        Main.main(new String[]{});

        String output = capturedOut.toString();
        assertTrue(output.contains("Program selesai. Terima kasih."),
                "Output harus mengandung pesan 'Program selesai. Terima kasih.'");
    }

    /**
     * TC-M02 | Path M2
     * User memilih menu "1" (Proses Nilai), memasukkan nilai valid, lalu keluar.
     * Expected: header "--- Input Data Nilai Mahasiswa ---" muncul di output.
     */
    @Test
    @DisplayName("TC-M02 | M2 | Masuk Menu Olah Nilai - Pilih Menu 1 lalu Keluar")
    void testMasukMenuOlahNilai() {
        // Menu 1 → input nilai valid → Menu 2 (keluar)
        String simulatedInput = "1\n80\n80\n80\n2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Main.main(new String[]{});

        String output = capturedOut.toString();
        assertTrue(output.contains("--- Input Data Nilai Mahasiswa ---"),
                "Output harus menampilkan header input nilai mahasiswa");
    }

    /**
     * TC-M03 | Path M3
     * User menginput opsi menu tidak valid (selain 1/2), lalu memilih menu 2 untuk keluar.
     * Expected: pesan "Pilihan tidak valid" muncul di output.
     */
    @Test
    @DisplayName("TC-M03 | M3 | Validasi Menu Invalid - Input '3' lalu Keluar")
    void testMenuInvalid() {
        // Input tidak valid "3", kemudian "2" untuk keluar
        String simulatedInput = "3\n2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Main.main(new String[]{});

        String output = capturedOut.toString();
        assertTrue(output.contains("Pilihan tidak valid"),
                "Output harus menampilkan pesan pilihan tidak valid");
    }
}

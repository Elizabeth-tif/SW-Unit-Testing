import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("TC-APP: GradeApplication Tests")
public class GradeApplicationTest {

    private InputStream originalIn;
    private PrintStream originalOut;
    private ByteArrayOutputStream capturedOut;

    @BeforeEach
    void redirectIO() {
        originalIn = System.in;
        originalOut = System.out;
        capturedOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOut));
    }

    @AfterEach
    void restoreIO() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("TC-APP01 | APP1 | Keluar Aplikasi dari Menu Utama")
    void shouldExitWhenUserChoosesMenuTwo() {
        // (1) setup (arrange, build)
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));

        // (2) exercise (act, operate)
        GradeApplication application = new GradeApplication(new Scanner(System.in));
        application.run();

        // (3) verify (assert, check)
        String output = capturedOut.toString();
        assertTrue(output.contains("Program selesai. Terima kasih."),
                "Output harus menampilkan pesan keluar aplikasi");
    }

    @Test
    @DisplayName("TC-APP02 | APP2 | Masuk ke Modul Pengolahan Nilai dari Menu Utama")
    void shouldOpenGradeProcessingFlowWhenUserChoosesMenuOne() {
        // (1) setup (arrange, build)
        String simulatedInput = "1\n80\n80\n80\n2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // (2) exercise (act, operate)
        GradeApplication application = new GradeApplication(new Scanner(System.in));
        application.run();

        // (3) verify (assert, check)
        String output = capturedOut.toString();
        assertTrue(output.contains("--- Input Data Nilai Mahasiswa ---"),
                "Output harus menampilkan awal alur input nilai");
    }

    @Test
    @DisplayName("TC-APP03 | APP3 | Validasi Input Menu Tidak Dikenal")
    void shouldShowInvalidMenuMessageWhenInputIsUnsupported() {
        // (1) setup (arrange, build)
        String simulatedInput = "3\n2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // (2) exercise (act, operate)
        GradeApplication application = new GradeApplication(new Scanner(System.in));
        application.run();

        // (3) verify (assert, check)
        String output = capturedOut.toString();
        assertTrue(output.contains("Pilihan tidak valid"),
                "Output harus menampilkan pesan pilihan menu tidak valid");
    }
}
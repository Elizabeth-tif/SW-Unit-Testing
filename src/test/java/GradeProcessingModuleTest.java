import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test Script: GradeProcessingModule (End-to-End Integration)
 * PIC     : Faisal
 * Tanggal : 12-Apr-26
 *
 * Mencakup path GP1.
 */
@DisplayName("TC-GP: GradeProcessingModule Tests")
public class GradeProcessingModuleTest {

    private PrintStream originalOut;
    private ByteArrayOutputStream capturedOut;
    private GradeProcessingModule processingModule;

    @BeforeEach
    void setUp() {
        String simulatedInput = "80\n80\n80\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        originalOut = System.out;
        capturedOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOut));

        processingModule = new GradeProcessingModule(scanner);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("TC-GP01 | GP1 | Alur End-to-End untuk Nilai Valid")
    void shouldProcessValidScoresEndToEnd() {
        // (1) setup (arrange, build)

        // (2) exercise (act, operate)
        processingModule.process();

        // (3) verify (assert, check)
        String output = capturedOut.toString();
        assertTrue(output.contains("80"),    "Output harus mengandung nilai akhir 80");
        assertTrue(output.contains("B"),     "Output harus mengandung Grade B");
        assertTrue(output.contains("Lulus"), "Output harus mengandung status Lulus");
    }
}

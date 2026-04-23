import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    @DisplayName("TC-GP01 | GP1 | Alur End-to-End untuk Nilai Valid")
    void shouldProcessValidScoresEndToEnd() {
        // (1) setup (arrange, build)
        String simulatedInput = "80\n80\n80\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // (2) exercise (act, operate)
            GradeProcessingModule processingModule = new GradeProcessingModule(scanner);
            processingModule.process();
        } finally {
            System.setOut(originalOut);
        }

        // (3) verify (assert, check)
        String output = outputStream.toString();
        assertTrue(output.contains("80"),    "Output harus mengandung nilai akhir 80");
        assertTrue(output.contains("B"),     "Output harus mengandung Grade B");
        assertTrue(output.contains("Lulus"), "Output harus mengandung status Lulus");
    }
}

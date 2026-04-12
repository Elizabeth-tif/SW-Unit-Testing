import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test Script: GradeProcessingModule (End-to-End Integration)
 * PIC     : Faisal
 * Tanggal : 12-Apr-26
 *
 * Mencakup path GP1.
 * Path GP2 adalah infeasible (InputModule menjamin nilai valid sehingga
 * FinalScoreModule tidak akan pernah mengembalikan -1), sehingga tidak diuji.
 */
@DisplayName("TC-GP: GradeProcessingModule Tests")
public class GradeProcessingModuleTest {

    /**
     * TC-GP01 | Path GP1
     * Alur E2E pengolahan data nilai valid: Tugas=80, UTS=80, UAS=80.
     * Expected output mencakup:
     *   - Nilai Akhir : 80.xx / 80,xx  (bergantung locale sistem)
     *   - Grade       : B
     *   - Status      : Lulus
     */
    @Test
    @DisplayName("TC-GP01 | GP1 | E2E Flow Nilai Valid (Tugas=80, UTS=80, UAS=80)")
    void testEndToEndFlowNilaiValid() {
        String simulatedInput = "80\n80\n80\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Tangkap System.out agar output dapat diverifikasi
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            Main.GradeProcessingModule processingModule = new Main.GradeProcessingModule(scanner);
            processingModule.process();
        } finally {
            System.setOut(originalOut);
        }

        String output = outputStream.toString();

        // Verifikasi setiap komponen output
        assertTrue(output.contains("80"),    "Output harus mengandung nilai akhir 80");
        assertTrue(output.contains("B"),     "Output harus mengandung Grade B");
        assertTrue(output.contains("Lulus"), "Output harus mengandung status Lulus");
    }
}

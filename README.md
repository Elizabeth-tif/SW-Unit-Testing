# SW Unit Testing

Program Pengolahan Nilai Mahasiswa (Java) untuk praktikum Software Unit Testing.

## Fitur
- Input nilai tugas, UTS, UAS
- Validasi nilai
- Perhitungan nilai akhir
- Penentuan grade
- Penentuan status kelulusan

## Prasyarat

- **Java JDK 17+** — pastikan sudah terinstal dan `JAVA_HOME` sudah dikonfigurasi
- **Git** — untuk clone repositori
- Tidak perlu menginstal Gradle secara manual, sudah tersedia via `gradlew`

## Cara Penggunaan

### 1. Clone Repositori

```bash
git clone <url-repositori>
cd SW-Unit-Testing
```

### 2. Jalankan Semua Unit Test

**Windows (CMD / PowerShell):**
```bash
.\gradlew test
```

**Linux / macOS:**
```bash
./gradlew test
```

### 3. Jalankan Test Kelas Tertentu

Ganti `NamaKelasTest` dengan nama file test yang ingin dijalankan, misalnya `GradeModuleTest`:

**Windows:**
```bash
.\gradlew test --tests "GradeModuleTest"
```

**Linux / macOS:**
```bash
./gradlew test --tests "GradeModuleTest"
```

Kelas test yang tersedia:
- `InputModuleTest`
- `FinalScoreModuleTest`
- `GradeModuleTest`
- `GradeProcessingModuleTest`
- `GraduationModuleTest`
- `MainTest`

### 4. Lihat Laporan Hasil Test

Setelah test selesai, laporan HTML tersedia di:

```
build/reports/tests/test/index.html
```

Buka file tersebut di browser untuk melihat hasil test secara lengkap.

### 5. Bersihkan Build (Opsional)

```bash
.\gradlew clean        # Windows
./gradlew clean        # Linux / macOS
```

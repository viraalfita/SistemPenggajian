import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class SistemPenggajian {

    // Hiasan
    static String RESET = "\u001B[0m";
    static String YELLOW = "\u001B[33m";
    static String GREEN = "\u001B[32m";
    static String RED = "\u001B[31m";
    static String MAGENTA = "\u001B[35m";
    static String Enter;

    static NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    static Scanner scan = new Scanner(System.in);

    static int jumlahKaryawan = 0;
    static String[][] dataKaryawan = new String[jumlahKaryawan][7];// Nama0, id karyawan1, Jabatan2, Email3,Alamat4,
                                                                   // No.hp5

    static ArrayList<int[]> gajiKaryawan = new ArrayList<>();
    static int[][] gajiPokokLembur = { { 2400000, 12000 }, { 1900000, 10000 }, { 2700000, 13000 }, { 3000000, 10000 },
            { 3850000, 12000 } };
    static int[] tunjanganMakanTransport = { 10000, 7000 }; // makan, transport

    public static void main(String[] args) throws Exception {
        boolean isValidLogin = login();

        while (isValidLogin) {
            String pilihMenu = displayMenu();

            switch (pilihMenu) {
                case "1":
                    String[][] dataKaryawan = tambahDataKaryawan();
                    System.out.println(dataKaryawan);
                    break;
                case "2":
                    cariDataKaryawan();
                    break;
                case "3":
                    tampilkanDataKaryawan();
                    break;
                case "4":
                    hitungGajiKaryawan();
                    break;
                case "5":
                    slipGaji();
                    break;
                case "6":
                    keluarProgram();
                    break;
                default:
                    menuTidakValid();
                    break;
            }

            if (pilihMenu.equals("6")) {
                break;
            }
        }

        scan.close();
    }

    public static boolean login() {
        String[] username = { "user", "admin" };
        String[] password = { "123", "admin" };

        String inputUsername, inputPassword;

        boolean isValidLogin = false;
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println();
        System.out.println(
                """
                        ╔────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╗
                        │███████╗██╗███████╗████████╗███████╗███╗   ███╗    ██████╗ ███████╗███╗   ██╗ ██████╗  ██████╗  █████╗      ██╗██╗ █████╗ ███╗   ██╗│
                        │██╔════╝██║██╔════╝╚══██╔══╝██╔════╝████╗ ████║    ██╔══██╗██╔════╝████╗  ██║██╔════╝ ██╔════╝ ██╔══██╗     ██║██║██╔══██╗████╗  ██║│
                        │███████╗██║███████╗   ██║   █████╗  ██╔████╔██║    ██████╔╝█████╗  ██╔██╗ ██║██║  ███╗██║  ███╗███████║     ██║██║███████║██╔██╗ ██║│
                        │╚════██║██║╚════██║   ██║   ██╔══╝  ██║╚██╔╝██║    ██╔═══╝ ██╔══╝  ██║╚██╗██║██║   ██║██║   ██║██╔══██║██   ██║██║██╔══██║██║╚██╗██║│
                        │███████║██║███████║   ██║   ███████╗██║ ╚═╝ ██║    ██║     ███████╗██║ ╚████║╚██████╔╝╚██████╔╝██║  ██║╚█████╔╝██║██║  ██║██║ ╚████║│
                        │╚══════╝╚═╝╚══════╝   ╚═╝   ╚══════╝╚═╝     ╚═╝    ╚═╝     ╚══════╝╚═╝  ╚═══╝ ╚═════╝  ╚═════╝ ╚═╝  ╚═╝ ╚════╝ ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝│
                        ╚────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╝                        """);

        // Login
        int loop = 0;
        while (loop < 3) {
            System.out.printf("%45s╔════════════════════════════════╗%n", "");
            System.out.printf("%45s║" + YELLOW + "           LOGIN PAGE           " + RESET + "║%n", "");
            System.out.printf("%45s╚════════════════════════════════╝%n", "");

            System.out.printf("%-47sMasukkan username : ", "");
            inputUsername = scan.nextLine();
            System.out.printf("%-47sMasukkan password : ", "");
            inputPassword = scan.nextLine();

            // Mengecek username dan password
            for (int i = 0; i < username.length; i++) {
                if (username[i].equalsIgnoreCase(inputUsername) && password[i].equals(inputPassword)) {
                    isValidLogin = true;
                    loop = 3;
                    break;
                }
            }
            if (!isValidLogin) {
                // Clear cmd
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.printf("%45s╔════════════════════════════════╗%n", "");
                System.out.printf("%45s║" + RED + "  Username / Password salah !!!" + RESET + " ║%n", "");
                System.out.printf("%45s╚════════════════════════════════╝%n", "");
                System.out.println();
            }
            loop++;
        }

        return isValidLogin;
    }

    public static String displayMenu() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("==================================");
        System.out.println(YELLOW + "                MENU   " + RESET);
        System.out.println("==================================");
        System.out.println("1. Tambahkan Data Karyawan");
        System.out.println("2. Cari Data Karyawan");
        System.out.println("3. Tampilkan Data Karyawan");
        System.out.println("4. Hitung Gaji Karyawan");
        System.out.println("5. Slip Gaji");
        System.out.println("6. Keluar");
        System.out.print("Pilih menu : ");
        String pilihMenu = scan.nextLine();
        System.out.println();

        System.out.print("\033[H\033[2J");
        System.out.flush();
        String Enter;

        return pilihMenu;
    }

    public static String[][] tambahDataKaryawan() {
        System.out.print("Masukkan jumlah karyawan baru : ");
        int jumlahKaryawanBaru = scan.nextInt();
        scan.nextLine();

        // array sementara untuk menyimpan data baru
        String[][] newDataKaryawan = new String[jumlahKaryawan + jumlahKaryawanBaru][7];

        // Copy data sebelumnya
        for (int i = 0; i < jumlahKaryawan; i++) {
            System.arraycopy(dataKaryawan[i], 0, newDataKaryawan[i], 0, 7);
        }

        // Input new data
        for (int i = jumlahKaryawan; i < jumlahKaryawan + jumlahKaryawanBaru; i++) {
            System.out.println("");
            System.out.println("Karyawan ke - " + (i + 1));
            System.out.print("Nama        : ");
            newDataKaryawan[i][0] = scan.nextLine();
            System.out.print("Id Karyawan : ");
            newDataKaryawan[i][1] = scan.nextLine();
            System.out.print("Email       : ");
            newDataKaryawan[i][3] = scan.nextLine();
            System.out.print("Alamat      : ");
            newDataKaryawan[i][4] = scan.nextLine();
            System.out.print("No Hp       : ");
            newDataKaryawan[i][5] = scan.nextLine();

            System.out.println("==================================");
            System.out.println(YELLOW + "               DIVISI  " + RESET);
            System.out.println("==================================");
            System.out.println("1. Front Office");
            System.out.println("2. House Keeping");
            System.out.println("3. FnB Service");
            System.out.println("4. Administrasi");
            System.out.print("Masukkan kategori divisi anda : ");
            newDataKaryawan[i][2] = String.valueOf(scan.nextInt());
            scan.nextLine();
        }
        // perbarui array utama dengan data baru
        dataKaryawan = newDataKaryawan;
        jumlahKaryawan += jumlahKaryawanBaru;

        return dataKaryawan;
    }

    public static void hitungGajiKaryawan() {
        System.out.println("==================================");
        System.out.println(GREEN + "       Hitung Gaji Karyawan    " + RESET);
        System.out.println("==================================");

        System.out.print("\nMasukkan nama karyawan : ");
        String cariNama = scan.nextLine();

        boolean ditemukan = false;
        for (int j = 0; j < jumlahKaryawan; j++) {
            if (dataKaryawan[j][0].equalsIgnoreCase(cariNama)) {
                ditemukan = true;
                System.out.println();
                System.out.println("Nama           : " + dataKaryawan[j][0]);
                tampilkanDivisi(j);
                String gajiAkhir = dataKaryawan[j][6];
                if (gajiAkhir == null || gajiAkhir.isEmpty()) {
                    // Input informasi gaji
                    System.out.println();
                    System.out.println(MAGENTA + "════════ Periode ════════" + RESET);
                    System.out.print("Masukkan periode tahun : ");
                    int tahun = scan.nextInt();
                    System.out.print("Masukkan periode bulan : ");
                    int bulan = scan.nextInt();
                    System.out.println();
                    System.out.println(MAGENTA + "════════ Hari kerja dan jam lembur ════════" + RESET);
                    System.out.print("Masukkan jam lembur : ");
                    int jamLembur = scan.nextInt();
                    System.out.print("Masukkan hari kerja : ");
                    int hariKerja = scan.nextInt();

                    // Simpan data gaji karyawan ke dalam array
                    int[] gaji = { tahun, bulan, jamLembur, hariKerja };
                    gajiKaryawan.add(gaji);
                    scan.nextLine();

                    // perhitungan tunjangan
                    System.out.println();
                    System.out.println(MAGENTA + "════════ Tunjangan ════════" + RESET);
                    int jmlTunjMakan = hariKerja * tunjanganMakanTransport[0];
                    int jmlTunjTransport = hariKerja * tunjanganMakanTransport[1];
                    int totalTunj = jmlTunjMakan + jmlTunjTransport;

                    System.out.println("Tunjangan Makan      : " +
                            formatRupiah.format(tunjanganMakanTransport[0])
                            + " x " + hariKerja + " hari = " + formatRupiah.format(jmlTunjMakan));
                    System.out.println("Tunjangan Transport  : " +
                            formatRupiah.format(tunjanganMakanTransport[1])
                            + " x " + hariKerja + " hari = " + formatRupiah.format(jmlTunjTransport));
                    System.out.println("Total Tunjangan      : " + formatRupiah.format(totalTunj));

                    int[] tunjangan = { jmlTunjMakan, jmlTunjTransport, totalTunj };
                    gajiKaryawan.add(tunjangan);

                    // perhitungan potongan
                    System.out.println();
                    System.out.println(MAGENTA + "════════ Potongan ════════" + RESET);
                    System.out.print("Masukkan jumlah terlambat : ");
                    int terlambat = scan.nextInt();
                    System.out.print("Masukkan jumlah alpa      : ");
                    int alpa = scan.nextInt();
                    int jmlTerlambat = terlambat * 1000;
                    int jmlAlpa = alpa * 50000;
                    int jmlPotongan = jmlAlpa + jmlTerlambat;
                    System.out.println();
                    System.out
                            .println("Potongan terlambat  : " + terlambat + " x " + "Rp1.000,00 = "
                                    + formatRupiah.format(jmlTerlambat));
                    System.out.println("Potongan alpa       : " + alpa + " x " + "Rp50.000,00 = "
                            + formatRupiah.format(jmlAlpa));
                    System.out.println("Total Potongan      : " + formatRupiah.format(jmlPotongan));

                    int[] potongan = { terlambat, alpa, jmlTerlambat, jmlAlpa, jmlPotongan };
                    gajiKaryawan.add(potongan);
                    scan.nextLine();

                    // perhitungan total gaji sebelum pajak
                    int divisiIndex = Integer.parseInt(dataKaryawan[j][2]) - 1;
                    int gajiPokok = gajiPokokLembur[divisiIndex][0];
                    int gajiLembur = gajiPokokLembur[divisiIndex][1];
                    int jmlGajiPokok = gajiPokok;
                    int jmlGajiLembur = gajiLembur * jamLembur;

                    int totalGaji = jmlGajiPokok + jmlGajiLembur + totalTunj - jmlPotongan;

                    dataKaryawan[j][6] = String.valueOf(totalGaji); // Simpan total gaji

                    System.out.println("");
                    System.out.println(MAGENTA + "════════ Gaji pokok dan lembur ════════" + RESET);
                    System.out.println("Gaji Pokok   : " + formatRupiah.format(gajiPokok));
                    System.out.println("Gaji Lembur  : " + jamLembur + " x " + formatRupiah.format(gajiLembur)
                            + " = " + formatRupiah.format(jmlGajiLembur));
                    System.out.println("Total Gaji   : " + formatRupiah.format(totalGaji));

                    int[] jmlGaji = { jmlGajiPokok, jmlGajiLembur, totalGaji };
                    gajiKaryawan.add(jmlGaji);
                    scan.nextLine();

                    // perhitungan pajak
                    System.out.println();
                    System.out.println(MAGENTA + "════════ Pajak ════════" + RESET);
                    int gajiSetelahPajak;
                    int potonganPajak = 0;
                    if (totalGaji >= 3000000) {
                        potonganPajak = totalGaji * 5 / 100;
                        gajiSetelahPajak = totalGaji - potonganPajak;

                        System.out.println("Potongan pajak              : "
                                + formatRupiah.format(totalGaji) + " x 5% = "
                                + formatRupiah.format(potonganPajak));
                        System.out.println(
                                "Gaji setelah dipotong pajak : " + formatRupiah.format(gajiSetelahPajak));
                    } else {
                        gajiSetelahPajak = totalGaji - 0;
                        System.out.println("Karyawan tidak dikenakan pajak penghasilan");
                    }

                    int[] pajak = { gajiSetelahPajak, potonganPajak };
                    gajiKaryawan.add(pajak);

                    System.out.println();
                    System.out.println("__________________________________________");
                    System.out.println("Gaji yang diterima  : " + formatRupiah.format(gajiSetelahPajak));
                    System.out.println("==========================================");
                    System.out.println();
                    dataKaryawan[j][6] = String.valueOf(totalGaji);
                } else {
                    System.out.println("Data karyawan tidak ditemukan atau gaji sudah diinput!");
                }
            }

        }
        System.out.print(YELLOW + "Enter untuk melanjutkan" + RESET);
        Enter = scan.nextLine();

    }

    public static void tampilkanDataKaryawan() {
        System.out.printf("%45s╔════════════════════════════════╗%n", "");
        System.out.printf("%45s║" + GREEN + "           Data Karyawan        " + RESET + "║%n", "");
        System.out.printf("%45s╚════════════════════════════════╝%n", "");
        System.out.println(
                "╔═════════════════╦═════════════════╦═════════════════╦═══════════════════════════╦═════════════════╦══════════════╗");
        System.out.printf("║ %-15s ║ %-15s ║ %-15s ║ %-25s ║ %-15s ║ %-12s ║%n",
                "Nama", "ID Karyawan", "Jabatan", "Email", "Alamat", "No. HP");
        System.out.println(
                "╠═════════════════╬═════════════════╬═════════════════╬═══════════════════════════╬═════════════════╬══════════════╣");

        for (int i = 0; i < jumlahKaryawan; i++) {
            String jabatan;
            switch (Integer.parseInt(dataKaryawan[i][2])) {
                case 1:
                    jabatan = "Front Office";
                    break;
                case 2:
                    jabatan = "House Keeping";
                    break;
                case 3:
                    jabatan = "FnB Service";
                    break;
                case 4:
                    jabatan = "Administrasi";
                    break;
                default:
                    jabatan = "";
            }
            System.out.printf("║ %-15s ║ %-15s ║ %-15s ║ %-25s ║ %-15s ║ %-12s ║%n",
                    dataKaryawan[i][0], // Nama
                    dataKaryawan[i][1], // ID Karyawan
                    jabatan, // Jabatan
                    dataKaryawan[i][3], // Email
                    dataKaryawan[i][4], // Alamat
                    dataKaryawan[i][5]); // No. HP
        }
        System.out.println(
                "╚═════════════════╩═════════════════╩═════════════════╩═══════════════════════════╩═════════════════╩══════════════╝");

        System.out.print(YELLOW + "Enter untuk melanjutkan" + RESET);
        Enter = scan.nextLine();
    }

    public static void cariDataKaryawan() {
        System.out.println("==================================");
        System.out.println(GREEN + "     Search Karyawan" + RESET);
        System.out.println("==================================");
        System.out.print("\nMasukkan nama karyawan yang ingin dicari: ");
        String cariNama = scan.nextLine();

        boolean ditemukan = false;
        for (int j = 0; j < jumlahKaryawan; j++) {
            if (dataKaryawan[j][0].equalsIgnoreCase(cariNama)) {
                ditemukan = true;
                System.out.println("\nKaryawan dengan nama " + cariNama + " ditemukan:");
                System.out.println("Nama           : " + dataKaryawan[j][0]);
                System.out.println("ID Karyawan    : " + dataKaryawan[j][1]);
                System.out.println("Email          : " + dataKaryawan[j][3]);
                System.out.println("Alamat         : " + dataKaryawan[j][4]);
                System.out.println("No. HP         : " + dataKaryawan[j][5]);
                System.out.println("Divisi         : " + tampilkanDivisi(j));
                // String divisi = "";
                // tampilkanDivisi(j);
                System.out.println();
                String gajiAkhir = dataKaryawan[j][6];
                if (gajiAkhir != null && !gajiAkhir.isEmpty()) {
                    int[] gaji1 = gajiKaryawan.get(0); // Dapatkan array untuk karyawan saat ini
                    int periodeTahun = gaji1[0];
                    int periodeBulan = gaji1[1];
                    int jamLembur = gaji1[2];
                    int hariKerja = gaji1[3];

                    System.out.println();
                    System.out.println(MAGENTA + "════════ Periode ════════" + RESET);
                    System.out.println("Periode tahun       : " + periodeTahun);
                    System.out.println("Periode bulan       : " + periodeBulan);
                    System.out.println();
                    System.out.println(MAGENTA + "════════ Hari kerja dan jam lembur ════════" + RESET);
                    System.out.println("Jam lembur          : " + jamLembur + " jam");
                    System.out.println("Hari kerja          : " + hariKerja + " hari");

                    int[] gaji2 = gajiKaryawan.get(1);

                    int jmlTunjMakan = gaji2[0];
                    int jmlTunjTransport = gaji2[1];
                    int totalTunj = gaji2[2];
                    System.out.println();
                    System.out.println(MAGENTA + "════════ Tunjangan ════════" + RESET);
                    System.out.println("Tunjangan makan     : " + formatRupiah.format(jmlTunjMakan));
                    System.out
                            .println("Tunjangan transport : " + formatRupiah.format(jmlTunjTransport));
                    System.out.println("Total tunjangan     : " + formatRupiah.format(totalTunj));

                    int[] gaji3 = gajiKaryawan.get(2);

                    int terlambat = gaji3[0];
                    int alpa = gaji3[1];
                    int jmlTerlambat = gaji3[2];
                    int jmlAlpa = gaji3[3];
                    int jmlPotongan = gaji3[4];
                    System.out.println();
                    System.out.println(MAGENTA + "════════ Potongan ════════" + RESET);
                    System.out.println("Terlambat         : " + terlambat + " menit");
                    System.out.println("Alpa              : " + alpa + " hari");
                    System.out.println("Total terlambat   : " + formatRupiah.format(jmlTerlambat));
                    System.out.println("Total alpa        : " + formatRupiah.format(jmlAlpa));
                    System.out.println("Total potongan    : " + formatRupiah.format(jmlPotongan));

                    int[] gaji4 = gajiKaryawan.get(3);

                    int jmlGajiPokok = gaji4[0];
                    int jmlGajiLembur = gaji4[1];
                    int totalGaji = gaji4[2];

                    System.out.println();
                    System.out.println(MAGENTA + "════════ Gaji pokok dan lembur ════════" + RESET);
                    System.out
                            .println("Jumlah gaji pokok           : " + formatRupiah.format(jmlGajiPokok));
                    System.out
                            .println("Jumlah gaji lembur          : " + formatRupiah.format(jmlGajiLembur));
                    System.out.println("Total gaji pokok dan lembur : " + formatRupiah.format(totalGaji));

                    int[] gaji5 = gajiKaryawan.get(4);

                    int gajiSetelahPajak = gaji5[0];
                    int potonganPajak = gaji5[1];

                    System.out.println();
                    System.out.println(MAGENTA + "════════ Pajak ════════" + RESET);
                    System.out.println("Potongan pajak     : " + formatRupiah.format(potonganPajak));
                    System.out.println("Gaji setelah pajak : " + formatRupiah.format(gajiSetelahPajak));

                    System.out.println();
                    System.out.println("__________________________________________");
                    System.out.println("Gaji yang diterima  : " + formatRupiah.format(gajiSetelahPajak));
                    System.out.println("==========================================");
                    System.out.println();
                } else {
                    System.out.println("Gaji Bulan Ini  : " + RED + "Belum ditentukan" + RESET);
                }
                System.out.println();

                break;
            }
        }

        if (!ditemukan) {
            System.out.println("\nKaryawan dengan nama " + cariNama + " tidak ditemukan.");
            System.out.println();
        }
        System.out.print(YELLOW + "Enter untuk melanjutkan" + RESET);
        Enter = scan.nextLine();
    }

    public static String tampilkanDivisi(int index) {
        String divisi = "";

        switch (Integer.parseInt(dataKaryawan[index][2])) {
            case 1:
                divisi = "Front Office";
                break;
            case 2:
                divisi = "House Keeping";
                break;
            case 3:
                divisi = "FnB Service";
                break;
            case 4:
                divisi = "Administrasi";
                break;
            default:
                divisi = "";
        }
        // System.out.println("Divisi : " + divisi);
        return divisi;
    }

    public static void slipGaji() {
        System.out.println("==================================");
        System.out.println(GREEN + "     Slip Gaji" + RESET);
        System.out.println("==================================");
        System.out.print("\nMasukkan nama karyawan yang ingin dicetak slip gajinya: ");
        String cariNama = scan.nextLine();

        boolean ditemukan = false;
        for (int j = 0; j < jumlahKaryawan; j++) {
            if (dataKaryawan[j][0].equalsIgnoreCase(cariNama)) {
                ditemukan = true;
                System.out.println("\nKaryawan dengan nama " + cariNama + " ditemukan:");
                String gajiAkhir = dataKaryawan[j][6];
                if (gajiAkhir != null && !gajiAkhir.isEmpty()) {
                    int[] gaji1 = gajiKaryawan.get(0); // Dapatkan array untuk karyawan saat ini
                    int periodeTahun = gaji1[0];
                    int periodeBulan = gaji1[1];
                    int jamLembur = gaji1[2];
                    int hariKerja = gaji1[3];
                    int[] gaji2 = gajiKaryawan.get(1);
                    int jmlTunjMakan = gaji2[0];
                    int jmlTunjTransport = gaji2[1];
                    int totalTunj = gaji2[2];
                    int[] gaji3 = gajiKaryawan.get(2);
                    int terlambat = gaji3[0];
                    int alpa = gaji3[1];
                    int jmlTerlambat = gaji3[2];
                    int jmlAlpa = gaji3[3];
                    int jmlPotongan = gaji3[4];
                    int[] gaji4 = gajiKaryawan.get(3);
                    int jmlGajiPokok = gaji4[0];
                    int jmlGajiLembur = gaji4[1];
                    int totalGaji = gaji4[2];
                    int[] gaji5 = gajiKaryawan.get(4);
                    int gajiSetelahPajak = gaji5[0];
                    int potonganPajak = gaji5[1];

                    String indexDivisi = tampilkanDivisi(j);

                    System.out.printf(
                            "╔════════════════════════════════════════════════════════════════════════════════════════════╗%n");
                    System.out.printf(GREEN + "║%37sSLIP GAJI KARYAWAN%37s║%n" + RESET, "", "");
                    System.out.printf(
                            "╠════════════════════════════════════════════════════════════════════════════════════════════╣%n");
                    System.out.printf("║ Nama Karyawan : %-32s Periode : %-31s ║%n", dataKaryawan[j][0],
                            periodeBulan + "/" + periodeTahun);
                    System.out.printf("║ ID Karyawan   : %-32s Email   : %-31s ║%n", dataKaryawan[j][1],
                            dataKaryawan[j][3]);
                    System.out.printf("║ Jabatan       : %-32s Alamat  : %-31s ║%n",
                            indexDivisi,
                            dataKaryawan[j][4]);
                    System.out.printf(
                            "╠════════════════════════════════════════════════════════════════════════════════════════════╣%n");
                    System.out.printf("║ Pendapatan :%32s Potongan :%35s ║%n", "", "");
                    System.out.printf(
                            "╠════════════════════════════════════════════════════════════════════════════════════════════╣%n");
                    System.out.printf("║ Gaji Pokok   : %-29s║ Terlambat    : %-30s║%n",
                            formatRupiah.format(jmlGajiPokok),
                            terlambat + " x " + "Rp1.000,00 = " + formatRupiah.format(jmlTerlambat));
                    System.out.printf("║ Lembur       : %-29s║ Alfa         : %-30s║%n",
                            jamLembur + " x " + formatRupiah.format(gajiPokokLembur[j][1])
                                    + " = " + formatRupiah.format(jmlGajiLembur),
                            alpa + " x " + "Rp50.000,00 = "
                                    + formatRupiah.format(jmlAlpa));
                    System.out.printf(
                            "║ Tunjangan                                   ║ PPH 21       : %-30s║%n",
                            formatRupiah.format(totalGaji) + " x 5% = " + formatRupiah.format(potonganPajak));
                    System.out.printf("║ Makan        : %-29s║%46s║%n", formatRupiah.format(tunjanganMakanTransport[0])
                            + " x " + hariKerja + " hari = " + formatRupiah.format(jmlTunjMakan), "");
                    // spaces
                    System.out.printf("║ Transportasi : %-29s║%46s║%n", formatRupiah.format(tunjanganMakanTransport[1])
                            + " x " + hariKerja + " hari = " + formatRupiah.format(jmlTunjTransport), "");

                    System.out.printf("║%-45s║%46s║%n", "", "");
                    System.out.printf(
                            "╠════════════════════════════════════════════════════════════════════════════════════════════╣%n");
                    System.out.printf(GREEN + "║ Gaji Diterima   : %-73s║%n" + RESET,
                            formatRupiah.format(gajiSetelahPajak));
                    System.out.printf(
                            "╚════════════════════════════════════════════════════════════════════════════════════════════╝%n");

                } else {
                    System.out.println("╔════════════════════════════════╗");
                    System.out.println("║   Karyawan " + cariNama + " Belum Gajian ║");
                    System.out.println("╚════════════════════════════════╝");
                }
                System.out.println();

                break;
            }
        }

        if (!ditemukan) {
            System.out.println("\nKaryawan dengan nama " + cariNama + " tidak ditemukan.");
            System.out.println();
        }
        System.out.print(YELLOW + "Enter untuk melanjutkan" + RESET);
        Enter = scan.nextLine();
    }

    public static void keluarProgram() {
        System.out.println("╔═══════════════════════════════╗");
        System.out.println("║" + RED + "         Exit Program         " + RESET + " ║");
        System.out.println("╚═══════════════════════════════╝");
    }

    public static void menuTidakValid() {
        System.out.println("╔════════════════════════════════╗");
        System.out.println("║" + RED + "! Input yang dimasukkan tidak valid !" + RESET + " ║");
        System.out.println("╚════════════════════════════════╝");
    }

}

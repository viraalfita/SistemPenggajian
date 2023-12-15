import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
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
    static String[][] dataKaryawan = new String[jumlahKaryawan][10];// Nama0, id karyawan1, Jabatan2, Email3,Alamat4,
                                                                    // No.hp5,user,pass

    static ArrayList<int[]> gajiKaryawan = new ArrayList<>();
    static List<Object[]> historiGaji = new ArrayList<>();

    static int[][] gajiPokokLembur = { { 2400000, 12000 }, { 1900000, 10000 }, { 2700000, 13000 }, { 3000000, 10000 },
            { 3850000, 12000 } };
    static int[] tunjanganMakanTransport = { 10000, 7000 }; // makan, transport
    static int[] tunjanganKesehatanKeluarga = { 150000, 200000 };

    public static void main(String[] args) throws Exception {
        int inputLogin = pilihLogin();
        while (inputLogin != 0) {
            inputLogin = validasiLogin(inputLogin);
        }
    }

    public static int pilihLogin() {
        clear();
        welcome();
        System.out.printf("%45s╔════════════════════════════════╗%n", "");
        System.out.printf("%45s║" + YELLOW + "           LOGIN PAGE           " + RESET + "║%n", "");
        System.out.printf("%45s╚════════════════════════════════╝%n", "");
        System.out.printf("%-47s1. Admin%n", "");
        System.out.printf("%-47s2. Karyawan%n", "");
        System.out.printf("%-47sMasukkan pilihan login : ", "");
        int inputLogin = scan.nextInt();
        scan.nextLine();

        clear();
        return inputLogin;
    }

    public static int validasiLogin(int inputLogin) {
        if (inputLogin == 1) {
            boolean isValidLogin = login();
            while (isValidLogin) {
                String pilihMenu = displayMenu();

                switch (pilihMenu) {
                    case "1":
                        informasiKaryawan();
                        break;
                    case "2":
                        cariDataKaryawan();
                        break;
                    case "3":
                        hitungGajiKaryawan();
                        break;
                    case "4":
                        slipGaji();
                        break;
                    case "5":
                        tampilkanRiwayatGaji();
                        break;
                    case "6":
                        clear();
                        return -1; // Keluar ke fungsi main
                    case "7":
                        keluarProgram();
                    default:
                        menuTidakValid();
                        break;
                }

                if (pilihMenu.equals("7")) {
                    return 0; // Keluar ke fungsi main
                }
            }
        } else if (inputLogin == 2) {
            loginKaryawan();
        }
        return pilihLogin(); // Meminta input ulang setelah selesai menjalankan validasi
    }

    public static boolean login() {
        welcome();
        String[] username = { "admin" };
        String[] password = { "admin" };

        String inputUsername, inputPassword;

        boolean isValidLogin = false;
        // Login
        int loop = 0;
        while (loop < 3) {
            System.out.printf("%45s╔════════════════════════════════╗%n", "");
            System.out.printf("%45s║" + YELLOW + "           LOGIN ADMIN          " + RESET + "║%n", "");
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
                clear();
                System.out.printf("%45s╔════════════════════════════════╗%n", "");
                System.out.printf("%45s║" + RED + "  Username / Password salah !!!" + RESET + " ║%n", "");
                System.out.printf("%45s╚════════════════════════════════╝%n", "");
                System.out.println();
            }
            loop++;
        }

        return isValidLogin;
    }

    public static void loginKaryawan() {
        String inputUsernameKaryawan, inputPasswordKaryawan;
        // Login
        int loop = 0;
        while (loop < 3) {
            welcome();
            System.out.printf("%45s╔════════════════════════════════╗%n", "");
            System.out.printf("%45s║" + YELLOW + "           LOGIN KARYAWAN       " + RESET + "║%n", "");
            System.out.printf("%45s╚════════════════════════════════╝%n", "");
            System.out.printf("%-47sMasukkan username : ", "");
            inputUsernameKaryawan = scan.nextLine();
            System.out.printf("%-47sMasukkan password : ", "");
            inputPasswordKaryawan = scan.nextLine();

            // Mengecek username dan password
            for (int i = 0; i < dataKaryawan.length; i++) {
                if (dataKaryawan[i][8].equalsIgnoreCase(inputUsernameKaryawan)
                        && dataKaryawan[i][9].equals(inputPasswordKaryawan)) {
                    slipGajiKaryawan(inputUsernameKaryawan);
                    loop = 3;
                    break;
                }
            }
            clear();
            loop++;
        }
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void welcome() {
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

    }

    public static String displayMenu() {
        clear();
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║" + YELLOW + "             MENU             " + RESET + "║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println("1. Kelola Data Karyawan");
        System.out.println("2. Cari Data Karyawan");
        System.out.println("3. Hitung Gaji Karyawan");
        System.out.println("4. Slip Gaji");
        System.out.println("5. Riwayat Gaji Karyawan");
        System.out.println("6. Log out");
        System.out.println("7. Keluar");
        System.out.print("Pilih menu : ");
        String pilihMenu = scan.nextLine();
        System.out.println();

        String Enter;

        return pilihMenu;
    }

    public static String[][] tambahDataKaryawan() {
        clear();
        System.out.print("Masukkan jumlah karyawan baru : ");
        int jumlahKaryawanBaru = scan.nextInt();
        scan.nextLine();

        // array sementara untuk menyimpan data baru
        String[][] newDataKaryawan = new String[jumlahKaryawan + jumlahKaryawanBaru][10];

        // Copy data sebelumnya
        for (int i = 0; i < jumlahKaryawan; i++) {
            System.arraycopy(dataKaryawan[i], 0, newDataKaryawan[i], 0, 10);
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
            System.out.print("Username    : ");
            newDataKaryawan[i][8] = scan.nextLine();
            System.out.print("Password    : ");
            newDataKaryawan[i][9] = scan.nextLine();

            System.out.println("╔══════════════════════════════╗");
            System.out.println("║" + YELLOW + "              Divisi         " + RESET + " ║");
            System.out.println("╚══════════════════════════════╝");
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
        informasiKaryawan();

        return dataKaryawan;
    }

    public static void hitungGajiKaryawan() {
        clear();
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║" + GREEN + "     HITUNG GAJI KARYAWAN     " + RESET + "║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("Masukkan Nama Karyawan : ");
        String cariNama = scan.nextLine();
        boolean ditemukan = false;
        for (int j = 0; j < jumlahKaryawan; j++) {
            if (dataKaryawan[j][0].equalsIgnoreCase(cariNama)) {
                ditemukan = true;
                System.out.println();
                System.out.println("Nama           : " + dataKaryawan[j][0]);
                tampilkanDivisi(j);
                String gajiAkhir = dataKaryawan[j][6];

                // Input informasi gaji
                System.out.println();
                System.out.println(MAGENTA + "════════ Periode ════════" + RESET);
                System.out.print("Masukkan periode tahun : ");
                int tahun = scan.nextInt();
                System.out.print("Masukkan periode bulan : ");
                int bulan = scan.nextInt();

                // pengecekan gaji dari periode sebelumnya
                boolean periodeExist = false;
                for (Object[] histori : historiGaji) {
                    int historiTahun = (int) histori[1];
                    int historiBulan = (int) histori[2];

                    if (historiTahun == tahun && historiBulan == bulan) {
                        periodeExist = true;
                        System.out.println("Gaji untuk periode " + tahun + "-" + bulan + " sudah dihitung sebelumnya!");
                        break;
                    }
                }

                if (!periodeExist) {
                    System.out.println();
                    System.out.println(MAGENTA + "════════ Hari kerja dan jam lembur ════════" + RESET);
                    System.out.print("Masukkan jam lembur : ");
                    int jamLembur = scan.nextInt();
                    System.out.print("Masukkan hari kerja : ");
                    int hariKerja = scan.nextInt();

                    // simpan data gaji karyawan ke dalam array
                    int[] gaji = { tahun, bulan, jamLembur, hariKerja };
                    gajiKaryawan.add(gaji);
                    scan.nextLine();

                    // perhitungan tunjangan
                    System.out.println();
                    System.out.println(MAGENTA + "════════ Tunjangan ════════" + RESET);
                    int jmlTunjMakan = hariKerja * tunjanganMakanTransport[0];
                    int jmlTunjTransport = hariKerja * tunjanganMakanTransport[1];
                    int tunjKesehatan = tunjanganKesehatanKeluarga[0];
                    int tunjKeluarga = tunjanganKesehatanKeluarga[1];

                    int totalTunj = jmlTunjMakan + jmlTunjTransport + tunjKesehatan + tunjKeluarga;

                    System.out.println("Tunjangan Makan      : " +
                            formatRupiah.format(tunjanganMakanTransport[0])
                            + " x " + hariKerja + " hari = " + formatRupiah.format(jmlTunjMakan));
                    System.out.println("Tunjangan Transport  : " +
                            formatRupiah.format(tunjanganMakanTransport[1])
                            + " x " + hariKerja + " hari = " + formatRupiah.format(jmlTunjTransport));
                    System.out.println("Tunjangan Kesehatan  : " + formatRupiah.format(tunjKesehatan));
                    System.out.println("Tunjangan Keluarga   : " + formatRupiah.format(tunjKeluarga));
                    System.out.println("Total Tunjangan      : " + formatRupiah.format(totalTunj));

                    int[] tunjangan = { jmlTunjMakan, jmlTunjTransport, totalTunj, tunjKesehatan, tunjKeluarga };
                    gajiKaryawan.add(tunjangan);

                    // perhitungan potongan
                    System.out.println();
                    System.out.println(MAGENTA + "════════ Potongan ════════" + RESET);
                    System.out.print("Masukkan jumlah terlambat (menit) : ");
                    int terlambat = scan.nextInt();
                    System.out.print("Masukkan jumlah alpa      (hari)  : ");
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
                    scan.nextLine();
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

                    // Save salary history
                    Object[] periodeGaji = { dataKaryawan[j][0], tahun, bulan, totalGaji }; // Include employee name
                    historiGaji.add(periodeGaji);
                }

                // ... (existing code)
            }
        }

        // ... (existing code)
    }

    // Function to display salary history
    public static void tampilkanRiwayatGaji() {
        clear();
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("║" + GREEN + "        RIWAYAT GAJI KARYAWAN        " + RESET + "║");
        System.out.println("╚═════════════════════════════════════╝");

        System.out.print("Masukkan Nama Karyawan : ");
        String cariNama = scan.nextLine();
        boolean ditemukan = false;

        for (int j = 0; j < jumlahKaryawan; j++) {
            if (dataKaryawan[j][0].equalsIgnoreCase(cariNama)) {
                ditemukan = true;

                // Tampilkan header tabel
                System.out.println();
                System.out.println("╔══════════════╦══════════════════════╗");
                System.out.printf("║ %-12s ║ %-20s ║%n", "Periode", "Gaji Diterima");
                System.out.println("╠══════════════╬══════════════════════╣");

                // Tampilkan riwayat gaji
                for (Object[] periodeGaji : historiGaji) {
                    if (periodeGaji[0].equals(cariNama)) {
                        System.out.printf("║ %-12s ║ %-20s ║%n", periodeGaji[2] + "/" + periodeGaji[1],
                                formatRupiah.format(periodeGaji[3]));
                    }
                }
                System.out.println("╚══════════════╩══════════════════════╝");

                System.out.print(YELLOW + "Enter untuk melanjutkan" + RESET);
                Enter = scan.nextLine();
            }
        }
    }

    public static void tampilkanDataKaryawan() {
        System.out.printf("%50s╔═══════════════════════════════════╗%n", "");
        System.out.printf("%50s║" + GREEN + "        Kelola Data Karyawan       " + RESET + "║%n", "");
        System.out.printf("%50s╚═══════════════════════════════════╝%n", "");
        System.out.println();
        System.out.println(
                "╔═════════════════╦═════════════════╦═════════════════╦═════════════════╦══════════════╦═══════════════╦══════════════╦══════════════╗");
        System.out.printf("║ %-15s ║ %-15s ║ %-15s ║ %-15s ║ %-12s ║ %-13s ║ %-12s ║ %-12s ║%n",
                "Nama", "ID Karyawan", "Jabatan", "Email", "Alamat", "No. HP", "Username", "Password");
        System.out.println(
                "╠═════════════════╬═════════════════╬═════════════════╬═════════════════╬══════════════╬═══════════════╬══════════════╬══════════════╣");

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
            System.out.printf("║ %-15s ║ %-15s ║ %-15s ║ %-15s ║ %-12s ║ %-13s ║ %-12s ║ %-12s ║%n",
                    dataKaryawan[i][0], // Nama
                    dataKaryawan[i][1], // ID Karyawan
                    jabatan, // Jabatan
                    dataKaryawan[i][3], // Email
                    dataKaryawan[i][4], // Alamat
                    dataKaryawan[i][5], // No. HP
                    dataKaryawan[i][8], // Username
                    dataKaryawan[i][9]);// Password

        }
        System.out.println(
                "╚═════════════════╩═════════════════╩═════════════════╩═════════════════╩══════════════╩═══════════════╩══════════════╩══════════════╝");

    }

    public static void cariDataKaryawan() {
        clear();
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║" + GREEN + "       Search Karyawan        " + RESET + "║");
        System.out.println("╚══════════════════════════════╝");
        System.out.printf("Masukkan Nama Karyawan : ");
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
                System.out.println("Username       : " + dataKaryawan[j][8]);
                System.out.println("Password       : " + dataKaryawan[j][9]);
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
                    int tunjKesehatan = gaji2[2];
                    int tunjKeluarga = gaji2[2];

                    System.out.println();
                    System.out.println(MAGENTA + "════════ Tunjangan ════════" + RESET);
                    System.out.println("Tunjangan makan     : " + formatRupiah.format(jmlTunjMakan));
                    System.out
                            .println("Tunjangan transport : " + formatRupiah.format(jmlTunjTransport));
                    System.out
                            .println("Tunjangan kesehatan : " + formatRupiah.format(tunjKesehatan));
                    System.out
                            .println("Tunjangan keluarga  : " + formatRupiah.format(tunjKeluarga));
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
        return divisi;
    }

    public static void slipGaji() {
        clear();
        System.out.println("╔════════════════════════════════╗");
        System.out.println("║" + GREEN + "       SLIP GAJI KARYAWAN       " + RESET + "║");
        System.out.println("╚════════════════════════════════╝");
        System.out.print("Masukkan Nama Karyawan : ");
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
                    int tunjKesehatan = gaji2[3];
                    int tunjKeluarga = gaji2[4];
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
                    System.out.printf("║%37sSLIP GAJI KARYAWAN%37s║%n", "", "");
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
                    System.out.printf("║ Gaji Pokok   : %-29s║ TerLambat    : %-30s║%n",
                            formatRupiah.format(jmlGajiPokok), formatRupiah.format(jmlTerlambat));
                    System.out.printf("║ Lembur       : %-29s║ Alfa         : %-30s║%n",
                            formatRupiah.format(jmlGajiLembur), formatRupiah.format(jmlAlpa));
                    System.out.printf(
                            "║ Tunjangan                                   ║ PPH 21       : %-30s║%n",
                            formatRupiah.format(potonganPajak));
                    System.out.printf("║ Makan        : %-29s║%46s║%n", formatRupiah.format(jmlTunjMakan), "");
                    System.out.printf("║ Transportasi : %-29s║%46s║%n", formatRupiah.format(jmlTunjTransport), "");
                    System.out.printf("║ Kesehatan    : %-29s║%46s║%n", formatRupiah.format(tunjKesehatan), "");
                    System.out.printf("║ Keluarga     : %-29s║%46s║%n", formatRupiah.format(tunjKeluarga), "");

                    System.out.printf("║%-45s║%46s║%n", "", "");
                    System.out.printf("║ Total Pendapatan : %-25s║ Total Potongan : %-27s ║%n",
                            formatRupiah.format(jmlGajiPokok + jmlGajiLembur + jmlTunjMakan + jmlTunjTransport
                                    + tunjKeluarga + tunjKesehatan),
                            formatRupiah.format(jmlTerlambat + jmlAlpa + potonganPajak));
                    System.out.printf(
                            "╠════════════════════════════════════════════════════════════════════════════════════════════╣%n");
                    System.out.printf("║ Gaji Diterima   : %-73s║%n", formatRupiah.format(gajiSetelahPajak));
                    System.out.printf(
                            "╚════════════════════════════════════════════════════════════════════════════════════════════╝%n");

                } else {
                    System.out.println("╔════════════════════════════════╗");
                    System.out.println("║   Karyawan " + cariNama + " Belum Gajian   ║");
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

    public static String[][] editDataKaryawan() {
        clear();
        // Tampilkan data karyawan terlebih dahulu
        tampilkanDataKaryawan();

        // Input ID Karyawan yang akan diubah
        System.out.print("Masukkan ID Karyawan yang akan diubah: ");
        String idKaryawanToEdit = scan.nextLine();

        // Cari indeks data karyawan berdasarkan ID
        int indexToEdit = -1;
        for (int i = 0; i < jumlahKaryawan; i++) {
            if (dataKaryawan[i][1].equals(idKaryawanToEdit)) {
                indexToEdit = i;
                break;
            }
        }

        if (indexToEdit == -1) {
            System.out.println("ID Karyawan tidak ditemukan.");
        } else {
            // Tampilkan opsi pengeditan
            System.out.println("Pilih data yang ingin diubah:");
            System.out.println("1. Nama");
            System.out.println("2. ID Karyawan");
            System.out.println("3. Jabatan");
            System.out.println("4. Email");
            System.out.println("5. Alamat");
            System.out.println("6. No. HP");
            System.out.println("7. Username");
            System.out.println("8. Password");
            System.out.print("Masukkan nomor opsi yang dipilih: ");
            int opsi = scan.nextInt();
            scan.nextLine();

            // Edit data sesuai opsi yang dipilih
            switch (opsi) {
                case 1:
                    System.out.print("Masukkan nama baru: ");
                    dataKaryawan[indexToEdit][0] = scan.nextLine();
                    break;
                case 2:
                    System.out.print("Masukkan ID Karyawan baru: ");
                    dataKaryawan[indexToEdit][1] = scan.nextLine();
                    break;
                case 3:
                    System.out.println("Pilih jabatan baru:");
                    System.out.println("1. Front Office");
                    System.out.println("2. House Keeping");
                    System.out.println("3. FnB Service");
                    System.out.println("4. Administrasi");
                    System.out.print("Masukkan kategori divisi baru: ");
                    dataKaryawan[indexToEdit][2] = String.valueOf(scan.nextInt());
                    scan.nextLine(); // Membersihkan newline
                    break;
                case 4:
                    System.out.print("Masukkan email baru: ");
                    dataKaryawan[indexToEdit][3] = scan.nextLine();
                    break;
                case 5:
                    System.out.print("Masukkan alamat baru: ");
                    dataKaryawan[indexToEdit][4] = scan.nextLine();
                    break;
                case 6:
                    System.out.print("Masukkan No. HP baru: ");
                    dataKaryawan[indexToEdit][5] = scan.nextLine();
                    break;
                case 7:
                    System.out.print("Masukkan username baru: ");
                    dataKaryawan[indexToEdit][8] = scan.nextLine();
                    break;
                case 8:
                    System.out.print("Masukkan password baru: ");
                    dataKaryawan[indexToEdit][9] = scan.nextLine();
                default:
                    System.out.println("Opsi tidak valid.");
            }
        }
        clear();
        informasiKaryawan();
        return dataKaryawan;
    }

    public static String informasiKaryawan() {
        clear();
        tampilkanDataKaryawan();

        System.out.println();
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║" + YELLOW + "             MENU             " + RESET + "║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println("1. Tambahkan Data Karyawan");
        System.out.println("2. Update Data Karyawan");
        System.out.println("3. Hapus data Karyawan");
        System.out.println("4. Kembali");
        System.out.print("Pilih menu : ");
        String infoMenu = scan.nextLine();
        System.out.println();
        switch (infoMenu) {
            case "1":
                String[][] dataKaryawan = tambahDataKaryawan();
                System.out.println(dataKaryawan);
                break;
            case "2":
                editDataKaryawan();
                break;
            case "3":
                hapusDataKaryawan();
                break;
            case "4":
                break;
            default:
                break;
        }
        clear();
        String Enter;

        return infoMenu;
    }

    public static String[][] hapusDataKaryawan() {
        clear();
        // Tampilkan data karyawan terlebih dahulu
        tampilkanDataKaryawan();

        // Input ID Karyawan yang akan dihapus
        System.out.print("Masukkan ID Karyawan yang akan dihapus: ");
        String idKaryawanToDelete = scan.nextLine();

        // Cari indeks data karyawan berdasarkan ID
        int indexToDelete = -1;
        for (int i = 0; i < jumlahKaryawan; i++) {
            if (dataKaryawan[i][1].equals(idKaryawanToDelete)) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == -1) {
            System.out.println("ID Karyawan tidak ditemukan.");
        } else {
            System.out.print("Apakah Anda yakin ingin menghapus data karyawan?(Ya/Tidak) : ");
            String konfir = scan.nextLine();
            // Hapus
            if (konfir.equalsIgnoreCase("Ya")) {
                for (int i = indexToDelete; i < jumlahKaryawan - 1; i++) {
                    System.arraycopy(dataKaryawan[i + 1], 0, dataKaryawan[i], 0, 7);
                }
                jumlahKaryawan--;
                System.out.println("Data Karyawan berhasil dihapus.");
            } else if (konfir.equalsIgnoreCase("Tidak")) {
                System.out.println("Data karyawan tidak jadi dihapus");
            }
        }
        clear();
        informasiKaryawan();
        return dataKaryawan;
    }

    public static void slipGajiKaryawan(String inputUsernameKaryawan) {
        for (int j = 0; j < jumlahKaryawan; j++) {
            if (dataKaryawan[j][8].equalsIgnoreCase(inputUsernameKaryawan)) {
                String gajiAkhir = dataKaryawan[j][6];
                System.out.println("Selamat Datang " + dataKaryawan[j][8] + " !");
                System.out.println("Menu");
                System.out.println("1. Slip Gaji");
                System.out.println("2. Riwayat Gaji");
                System.out.print("Pilih menu :");
                int pilihMenu = scan.nextInt();

                if (pilihMenu == 1) {
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
                                "%15s╔════════════════════════════════════════════════════════════════════════════════════════════╗%n",
                                "");
                        System.out.printf("%15s║%37sSLIP GAJI KARYAWAN%37s║%n", "", "", "");
                        System.out.printf(
                                "%15s╠════════════════════════════════════════════════════════════════════════════════════════════╣%n",
                                "");
                        System.out.printf("%15s║ Nama Karyawan : %-32s Periode : %-31s ║%n", "", dataKaryawan[j][0],
                                periodeBulan + "/" + periodeTahun);
                        System.out.printf("%15s║ ID Karyawan   : %-32s Email   : %-31s ║%n", "", dataKaryawan[j][1],
                                dataKaryawan[j][3]);
                        System.out.printf("%15s║ Jabatan       : %-32s Alamat  : %-31s ║%n", "",
                                indexDivisi,
                                dataKaryawan[j][4]);
                        System.out.printf(
                                "%15s╠════════════════════════════════════════════════════════════════════════════════════════════╣%n",
                                "");
                        System.out.printf("%15s║ Pendapatan :%32s Potongan :%35s ║%n", "", "", "");
                        System.out.printf(
                                "%15s╠════════════════════════════════════════════════════════════════════════════════════════════╣%n",
                                "");
                        System.out.printf("%15s║ Gaji Pokok   : %-29s║ TerLambat    : %-30s║%n", "",
                                formatRupiah.format(jmlGajiPokok), formatRupiah.format(jmlTerlambat));
                        System.out.printf("%15s║ Lembur       : %-29s║ Alfa         : %-30s║%n", "",
                                formatRupiah.format(jmlGajiLembur), formatRupiah.format(jmlAlpa));
                        System.out.printf(
                                "%15s║ Tunjangan                                   ║ PPH 21       : %-30s║%n", "",
                                formatRupiah.format(potonganPajak));
                        System.out.printf("%15s║ Makan        : %-29s║%46s║%n", "",
                                formatRupiah.format(jmlTunjMakan),
                                "");
                        System.out.printf("%15s║ Transportasi : %-29s║%46s║%n", "",
                                formatRupiah.format(jmlTunjTransport), "");

                        System.out.printf("%15s║%-45s║%46s║%n", "", "", "");
                        System.out.printf("%15s║ Total Pendapatan : %-25s║ Total Potongan : %-27s ║%n", "",
                                formatRupiah
                                        .format(jmlGajiPokok + jmlGajiLembur + jmlTunjMakan + jmlTunjTransport),
                                formatRupiah.format(jmlTerlambat + jmlAlpa + potonganPajak));
                        System.out.printf(
                                "%15s╠════════════════════════════════════════════════════════════════════════════════════════════╣%n",
                                "");
                        System.out.printf("%15s║ Gaji Diterima   : %-73s║%n", "",
                                formatRupiah.format(gajiSetelahPajak));
                        System.out.printf(
                                "%15s╚════════════════════════════════════════════════════════════════════════════════════════════╝%n",
                                "");
                        scan.nextLine();

                    } else {
                        System.out.println("╔══════════════════════════════╗");
                        System.out.println("║     Anda Belum Gajian !!!    ║");
                        System.out.println("╚══════════════════════════════╝");
                        scan.nextLine();
                    }
                } else if (pilihMenu == 2) {
                    // Tampilkan header tabel
                    System.out.println();
                    System.out.println("╔══════════════╦══════════════════════╗");
                    System.out.printf("║ %-12s ║ %-20s ║%n", "Periode", "Gaji Diterima");
                    System.out.println("╠══════════════╬══════════════════════╣");

                    // Tampilkan riwayat gaji
                    for (Object[] periodeGaji : historiGaji) {
                        if (periodeGaji[0].equals(inputUsernameKaryawan)) {
                            System.out.printf("║ %-12s ║ %-20s ║%n", periodeGaji[2] + "/" + periodeGaji[1],
                                    formatRupiah.format(periodeGaji[3]));
                        }
                    }
                    System.out.println("╚══════════════╩══════════════════════╝");

                    System.out.print(YELLOW + "Enter untuk melanjutkan" + RESET);
                    Enter = scan.nextLine();
                } else {
                    menuTidakValid();
                }

                System.out.println();

                break;
            }
        }
    }
}

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class SistemPenggajian {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        String[] username = { "viraalfita", "dioandika", "taufikdimas", "admin" };
        String[] password = { "admin1", "admin2", "admin3", "admin" };

        String inputUsername, inputPassword;
        // Hiasan
        String RESET = "\u001B[0m";
        String YELLOW = "\u001B[33m";
        String GREEN = "\u001B[32m";
        String RED = "\u001B[31m";

        boolean isValidLogin = false;
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Login
        int loop = 0;
        while (loop < 3) {
            System.out.println("==================================");
            System.out.println(YELLOW + "           LOGIN PAGE            " + RESET);
            System.out.println("==================================");
            System.out.print("Masukkan username : ");
            inputUsername = scan.nextLine();
            System.out.print("Masukkan password : ");
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
                System.out.println("==================================");
                System.out.println(RED + "! Username atau password salah !" + RESET);
                System.out.println("==================================");
            }
            loop++;
        }

        int jumlahKaryawan = 0;
        String[][] dataKaryawan = new String[jumlahKaryawan][5]; // Nama, Alamat, Divisi, Total Gaji, Jam Lembur
        int[][] gajiPokokLembur = { { 1500000, 12000 }, { 1400000, 13000 }, { 1600000, 10000 }, { 1700000, 12000 } };
        int[] tunjanganMakanTransport = { 10000, 7000 };

        while (isValidLogin) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("==================================");
            System.out.println(YELLOW + "                MENU   " + RESET);
            System.out.println("==================================");
            System.out.println("1. Tambahkan Data Karyawan");
            System.out.println("2. Cari Data Karyawan");
            System.out.println("3. Tampilkan Data Karyawan");
            System.out.println("4. Hitung Gaji Karyawan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu : ");
            String pilihMenu = scan.nextLine();
            System.out.println();
            // scan.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            String Enter;

            switch (pilihMenu) {
                case "1":
                    System.out.print("Masukkan jumlah karyawan : ");
                    jumlahKaryawan = scan.nextInt();
                    dataKaryawan = new String[jumlahKaryawan][7]; // Nama, Alamat, Divisi, Total Gaji, Jam Lembur,
                                                                  // Periode Tahun, Periode Bulan
                    scan.nextLine();

                    for (int i = 0; i < jumlahKaryawan; i++) {
                        System.out.println("");
                        System.out.println("Karyawan ke - " + (i + 1));
                        System.out.print("Nama    : ");
                        dataKaryawan[i][0] = scan.nextLine();
                        System.out.print("Alamat  : ");
                        dataKaryawan[i][1] = scan.nextLine();
                        System.out.println("==================================");
                        System.out.println(YELLOW + "               DIVISI  " + RESET);
                        System.out.println("==================================");
                        System.out.println("1. Front Office");
                        System.out.println("2. House Keeping");
                        System.out.println("3. Food and Beverage Service");
                        System.out.println("4. Administrasi");
                        System.out.print("Masukkan kategori divisi anda : ");
                        dataKaryawan[i][2] = String.valueOf(scan.nextInt());

                        scan.nextLine();
                    }
                    break;
                case "4":
                    System.out.println("==================================");
                    System.out.println(GREEN + "       Hitung Gaji Karyawan    " + RESET);
                    System.out.println("==================================");

                    for (int j = 0; j < jumlahKaryawan; j++) {
                        System.out.println("Karyawan ke - " + (j + 1));
                        System.out.println("Nama                 : " + dataKaryawan[j][0]);
                        String divisi = "";
                        switch (Integer.parseInt(dataKaryawan[j][2])) {
                            case 1:
                                divisi = "Front Office";
                                break;
                            case 2:
                                divisi = "House Keeping";
                                break;
                            case 3:
                                divisi = "Food and Beverage Service";
                                break;
                            case 4:
                                divisi = "Administrasi";
                                break;
                            default:
                                divisi = "";
                        }
                        System.out.println("Divisi               : " + divisi);
                        System.out.println();
                        System.out.print("Periode Tahun        : ");
                        int tahun = scan.nextInt();
                        scan.nextLine();
                        System.out.print("Periode Bulan        : ");
                        String bulan = scan.nextLine();
                        System.out.println();
                        System.out.print("Masukkan hari kerja  : ");
                        int hariKerja = scan.nextInt();
                        System.out.print("Masukkan jam lembur  : ");
                        int jamLembur = scan.nextInt();
                        dataKaryawan[j][4] = String.valueOf(jamLembur); // Simpan jam lembur
                        dataKaryawan[j][5] = String.valueOf(tahun); // Simpan periode tahun
                        dataKaryawan[j][6] = String.valueOf(bulan); // Simpan periode bulan

                        int divisiIndex = Integer.parseInt(dataKaryawan[j][2]) - 1;
                        int gajiPokok = gajiPokokLembur[divisiIndex][0];
                        int gajiLembur = gajiPokokLembur[divisiIndex][1];
                        int jmlGajiPokok = gajiPokok;
                        int jmlGajiLembur = gajiLembur * jamLembur;

                        // Hitung Tunjangan
                        int jmlTunjMakan = tunjanganMakanTransport[0] * hariKerja;
                        int jmlTunjTransport = tunjanganMakanTransport[1] * hariKerja;
                        int totalTunj = jmlTunjMakan + jmlTunjTransport;

                        // Hitung Potongan
                        System.out.println();
                        System.out.println("--- Potongan ---");
                        System.out.print("Masukkan jumlah terlambat (menit) : ");
                        int jmlTerlambat = scan.nextInt();
                        System.out.print("Masukkan jumlah alpa (hari)       : ");
                        int jmlAlpa = scan.nextInt();

                        int potonganTerlambat, potonganAlpa;

                        potonganTerlambat = jmlTerlambat * 1000;
                        potonganAlpa = jmlAlpa * 50000;

                        int totalGaji = jmlGajiPokok + jmlGajiLembur + totalTunj - potonganAlpa - potonganTerlambat;

                        // Hitung Pajak
                        System.out.println();
                        System.out.println("--- Pajak ---");

                        double gajiSetelahPajak;
                        if (totalGaji >= 3000000) {
                            gajiSetelahPajak = totalGaji - (totalGaji * 0.05);

                            System.out.println("Gaji karyawan setelah dipotong pajak : " + gajiSetelahPajak);
                            System.out.println();
                        } else {
                            gajiSetelahPajak = totalGaji - 0;
                            System.out.println("Karyawan tidak dikenakan pajak penghasilan");
                        }

                        double totalGajiBersih = totalGaji - gajiSetelahPajak;
                        dataKaryawan[j][3] = String.valueOf(totalGajiBersih); // Simpan total gaji bersih

                        System.out.println("");
                        System.out.println("============== GAJI ===============");
                        System.out.println("Gaji Periode        : " + bulan + " " + tahun);
                        System.out.println("Gaji Pokok          : " + formatRupiah.format(jmlGajiPokok));
                        System.out
                                .println("Gaji Lembur         : " + jamLembur + " jam x "
                                        + formatRupiah.format(gajiLembur)
                                        + " = " + formatRupiah.format(jmlGajiLembur));
                        System.out.println();
                        System.out.println("--- Total Tunjangan ---");
                        System.out.println("Tunjangan Makan        : " + formatRupiah.format(tunjanganMakanTransport[0])
                                + " x " + hariKerja + " hari = " + formatRupiah.format(jmlTunjMakan));
                        System.out.println("Tunjangan Transportasi : " + formatRupiah.format(tunjanganMakanTransport[1])
                                + " x " + hariKerja + " hari = " + formatRupiah.format(jmlTunjTransport));
                        System.out.println("Total Tunjangan        : " + formatRupiah.format(totalTunj));
                        System.out.println();
                        System.out.println("--- Total Potongan ---");
                        System.out.println(
                                "Potongan Terlambat  : " + jmlTerlambat + " menit x " + formatRupiah.format(1000)
                                        + " = " + formatRupiah.format(potonganTerlambat));
                        System.out
                                .println("Potongan Alpa       : " + jmlAlpa + " hari x " + formatRupiah.format(50000)
                                        + " = " + formatRupiah.format(jmlGajiLembur));
                        System.out.println();
                        System.out.println("__________________________________+");
                        System.out.println("Total Gaji   : " + formatRupiah.format(totalGajiBersih));
                        System.out.println("==================================");
                        System.out.println();
                        scan.nextLine();

                    }
                    System.out.print(YELLOW + "Enter untuk melanjutkan" + RESET);
                    Enter = scan.nextLine();

                    break;

                case "3":
                    System.out.println("==================================");
                    System.out.println(GREEN + "          DATA KARYAWAN " + RESET);
                    System.out.println("==================================");

                    for (int i = 0; i < jumlahKaryawan; i++) {
                        System.out.println();
                        System.out.println("Karyawan ke-" + (i + 1));
                        System.out.println();
                        System.out.println("Nama Karyawan : " + dataKaryawan[i][0]);
                        System.out.println("Alamat Karyawan : " + dataKaryawan[i][1]);
                        int divisiIndex = Integer.parseInt(dataKaryawan[i][2]);
                        String divisi = "";
                        switch (divisiIndex) {
                            case 1:
                                divisi = "Front Office";
                                break;
                            case 2:
                                divisi = "House Keeping";
                                break;
                            case 3:
                                divisi = "Food and Beverage Service";
                                break;
                            case 4:
                                divisi = "Administrasi";
                                break;
                            default:
                                divisi = "Tidak valid";
                                break;
                        }
                        int gajiInt = dataKaryawan[i][3] != null && !dataKaryawan[i][3].isEmpty()
                                ? Integer.parseInt(dataKaryawan[i][3])
                                : 0;

                        System.out.println("Divisi Karyawan : " + divisi);
                        System.out.print("Periode Gaji : ");
                        System.out.println(dataKaryawan[i][6] != null && !dataKaryawan[i][6].isEmpty()
                                ? dataKaryawan[i][6] + " " + dataKaryawan[i][5]
                                : RED + "Belum Ditentukan" + RESET);
                        System.out.print("Total Gaji : ");
                        System.out.println(dataKaryawan[i][3] != null && !dataKaryawan[i][3].isEmpty()
                                ? formatRupiah.format(gajiInt)
                                : RED + "Belum Ditentukan" + RESET);

                    }
                    System.out.print(YELLOW + "Enter untuk melanjutkan" + RESET);
                    Enter = scan.nextLine();
                    break;

                case "2":
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
                            System.out.println("Alamat Karyawan : " + dataKaryawan[j][1]);
                            String divisi = "";
                            switch (Integer.parseInt(dataKaryawan[j][2])) {
                                case 1:
                                    divisi = "Front Office";
                                    break;
                                case 2:
                                    divisi = "House Keeping";
                                    break;
                                case 3:
                                    divisi = "Food and Beverage Service";
                                    break;
                                case 4:
                                    divisi = "Administrasi";
                                    break;
                                default:
                                    divisi = "Tidak valid";
                                    break;
                            }
                            System.out.println("Divisi Karyawan : " + divisi);
                            String gajiString = dataKaryawan[j][3];
                            if (gajiString != null && !gajiString.isEmpty()) {
                                int totalGaji = Integer.parseInt(gajiString);
                                System.out.println("Gaji Bulan Ini  : " + formatRupiah.format(totalGaji));
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
                    break;

                case "5":
                    System.out.println("==================================");
                    System.out.println(RED + "       Anda Berhasil Logout !" + RESET);
                    System.out.println("==================================");
                    break;
                default:
                    System.out.println("==================================");
                    System.out.println(RED + "! Input yang dimasukkan tidak valid !" + RESET);
                    System.out.println("==================================");
                    break;
            }
            if (pilihMenu.equals("5")) {
                break;
            }
        }

        scan.close();
    }
}

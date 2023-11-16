import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class SistemPenggajian {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        String[] username = { "user", "admin" };
        String[] password = { "123", "admin" };

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
        String[][] dataKaryawan = new String[jumlahKaryawan][12]; // Nama, Alamat, Divisi, Total Gaji, Jam Lembur,
                                                                  // Hari kerja, Tahun periode, bulan periode, gender,
                                                                  // nomer hp, email, id karyawan, tunjangan, potongan,
                                                                  // pajak, gaji lembur, gaji bersih
        int[][] gajiPokokLembur = { { 2400000, 12000 }, { 1900000, 10000 }, { 2700000, 13000 }, { 3000000, 10000 },
                { 3850000, 12000 } };
        int[] tunjanganMakanTransport = { 10000, 7000 }; // makan, transport
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
                    System.out.print("Masukkan jumlah karyawan baru : ");
                    int jumlahKaryawanBaru = scan.nextInt();
                    scan.nextLine();

                    // Create a temporary array to store the new data
                    String[][] newDataKaryawan = new String[jumlahKaryawan + jumlahKaryawanBaru][5];

                    // Copy existing data to the temporary array
                    for (int i = 0; i < jumlahKaryawan; i++) {
                        System.arraycopy(dataKaryawan[i], 0, newDataKaryawan[i], 0, 5);
                    }

                    // Input new data
                    for (int i = jumlahKaryawan; i < jumlahKaryawan + jumlahKaryawanBaru; i++) {
                        System.out.println("");
                        System.out.println("Karyawan ke - " + (i + 1));
                        System.out.print("Nama    : ");
                        newDataKaryawan[i][0] = scan.nextLine();
                        System.out.print("Alamat  : ");
                        newDataKaryawan[i][1] = scan.nextLine();

                        System.out.println("==================================");
                        System.out.println(YELLOW + "               DIVISI  " + RESET);
                        System.out.println("==================================");
                        System.out.println("1. Front Office");
                        System.out.println("2. House Keeping");
                        System.out.println("3. Food and Beverage Service");
                        System.out.println("4. Administrasi");
                        System.out.print("Masukkan kategori divisi anda : ");
                        newDataKaryawan[i][2] = String.valueOf(scan.nextInt());
                        scan.nextLine();
                    }

                    // Update the main array with the new data
                    dataKaryawan = newDataKaryawan;
                    jumlahKaryawan += jumlahKaryawanBaru;
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
                        System.out.print("Masukkan periode tahun : ");
                        int tahun = scan.nextInt();
                        scan.nextLine();
                        System.out.print("Masukkan periode bulan : ");
                        String bulan = scan.nextLine();
                        System.out.println();
                        System.out.print("Masukkan jam lembur    : ");
                        int jamLembur = scan.nextInt();
                        System.out.print("Masukkan hari kerja    : ");
                        int hariKerja = scan.nextInt();
                        dataKaryawan[j][4] = String.valueOf(jamLembur); // Simpan jam lembur

                        // perhitungan tunjangan
                        System.out.println();
                        System.out.println("-- Tunjangan --");
                        int jmlTunjMakan = hariKerja * tunjanganMakanTransport[0];
                        int jmlTunjTransport = hariKerja * tunjanganMakanTransport[1];
                        int totalTunj = jmlTunjMakan + jmlTunjTransport;

                        System.out.println("Tunjangan Makan      : " +
                                formatRupiah.format(tunjanganMakanTransport[0])
                                + " x " + hariKerja + " hari = " + jmlTunjMakan);
                        System.out.println("Tunjangan Transport  : " +
                                formatRupiah.format(tunjanganMakanTransport[1])
                                + " x " + hariKerja + " hari = " + jmlTunjTransport);
                        System.out.println("Total Tunjangan      : " + formatRupiah.format(totalTunj));

                        // perhitungan potongan
                        System.out.println();
                        System.out.println("-- Potongan --");
                        System.out.print("Masukkan jumlah terlambat : ");
                        int terlambat = scan.nextInt();
                        System.out.print("Masukkan jumlah alpa      : ");
                        int alpa = scan.nextInt();
                        int jmlTerlambat = terlambat * 1000;
                        int jmlAlpa = alpa * 50000;
                        int jmlPotongan = jmlAlpa + jmlTerlambat;
                        System.out.println();
                        System.out.println("Potongan terlambat  : " + terlambat + " x " + "Rp1.000 = " + jmlTerlambat);
                        System.out.println("Potongan alpa       : " + alpa + " x " + "Rp50.000 = " + jmlAlpa);
                        System.out.println("Total Potongan      : " + jmlPotongan);

                        // perhitungan total gaji sebelum pajak
                        int divisiIndex = Integer.parseInt(dataKaryawan[j][2]) - 1;
                        int gajiPokok = gajiPokokLembur[divisiIndex][0];
                        int gajiLembur = gajiPokokLembur[divisiIndex][1];
                        int jmlGajiPokok = gajiPokok;
                        int jmlGajiLembur = gajiLembur * jamLembur;

                        int totalGaji = jmlGajiPokok + jmlGajiLembur + totalTunj - jmlPotongan;

                        dataKaryawan[j][3] = String.valueOf(totalGaji); // Simpan total gaji
                        System.out.println("");
                        System.out.println("Gaji Pokok   : " + formatRupiah.format(gajiPokok));
                        System.out.println("Gaji Lembur  : " + jamLembur + " x " + formatRupiah.format(gajiLembur)
                                + " = " + formatRupiah.format(jmlGajiLembur));

                        // perhitungan pajak
                        System.out.println();
                        System.out.println("-- Pajak --");
                        double gajiSetelahPajak;
                        if (totalGaji >= 3000000) {
                            gajiSetelahPajak = totalGaji - (totalGaji * 0.05);

                            System.out.println(
                                    "Gaji karyawan setelah dipotong pajak : " + formatRupiah.format(gajiSetelahPajak));
                        } else {
                            gajiSetelahPajak = totalGaji - 0;
                            System.out.println("Karyawan tidak dikenakan pajak penghasilan");
                        }
                        System.out.println();
                        System.out.println("__________________________________+");
                        System.out.println("Gaji yang diterima  : " + formatRupiah.format(gajiSetelahPajak));
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
                        System.out.println("Divisi Karyawan : " + divisi);
                        String gajiString = dataKaryawan[i][3];
                        if (gajiString != null && !gajiString.isEmpty()) {
                            int totalGaji = Integer.parseInt(gajiString);
                            System.out.println("Gaji Bulan Ini  : " + formatRupiah.format(totalGaji));
                        } else {
                            System.out.println("Gaji Bulan Ini  : " + RED + "Belum ditentukan" + RESET);
                        }
                        System.out.println();
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
                    System.out.println(RED + "       Exit Program" + RESET);
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

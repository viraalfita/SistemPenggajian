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
        int[][] gajiPokokLembur = { { 80000, 12000 }, { 70000, 13000 }, { 60000, 10000 }, { 85000, 12000 } };
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
                    dataKaryawan = new String[jumlahKaryawan][5]; // Nama, Alamat, Divisi, Total Gaji, Jam Lembur
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
                        System.out.print("Masukkan hari kerja  : ");
                        int hariKerja = scan.nextInt();
                        System.out.print("Masukkan jam lembur  : ");
                        int jamLembur = scan.nextInt();
                        dataKaryawan[j][4] = String.valueOf(jamLembur); // Simpan jam lembur

                        int divisiIndex = Integer.parseInt(dataKaryawan[j][2]) - 1;
                        int gajiPokok = gajiPokokLembur[divisiIndex][0];
                        int gajiLembur = gajiPokokLembur[divisiIndex][1];
                        int jmlGajiPokok = gajiPokok * hariKerja;
                        int jmlGajiLembur = gajiLembur * jamLembur;

                        int totalGaji = jmlGajiPokok + jmlGajiLembur;

                        dataKaryawan[j][3] = String.valueOf(totalGaji); // Simpan total gaji
                        System.out.println("");
                        System.out.println("Gaji Pokok   : " + hariKerja + " x " + formatRupiah.format(gajiPokok)
                                + " = " + formatRupiah.format(jmlGajiPokok));
                        System.out.println("Gaji Lembur  : " + jamLembur + " x " + formatRupiah.format(gajiLembur)
                                + " = " + formatRupiah.format(jmlGajiLembur));
                        System.out.println("__________________________________+");
                        System.out.println("Total Gaji   : " + formatRupiah.format(totalGaji));
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
                        // System.out.println("Total Gaji : " + dataKaryawan[i][3]);
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
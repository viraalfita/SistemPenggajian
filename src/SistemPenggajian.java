import java.util.Scanner;

public class SistemPenggajian {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        String[] username = { "viraalfita", "dioandika", "taufikdimas" };
        String[] password = { "admin1", "admin2", "admin3" };

        String inputUsername, inputPassword;

        // Bubble Sort untuk mengurutkan array username
        for (int i = 0; i < username.length - 1; i++) {
            for (int j = 0; j < username.length - i - 1; j++) {
                if (username[j].compareToIgnoreCase(username[j + 1]) > 0) {
                    // Tukar username
                    String tempUsername = username[j];
                    username[j] = username[j + 1];
                    username[j + 1] = tempUsername;

                    // Tukar password yang sesuai dengan username
                    String tempPassword = password[j];
                    password[j] = password[j + 1];
                    password[j + 1] = tempPassword;
                }
            }
        }

        System.out.print("Masukkan username : ");
        inputUsername = scan.nextLine();
        System.out.print("Masukkan password : ");
        inputPassword = scan.nextLine();

        boolean isValidLogin = false;

        // Mencari username yang sesuai setelah Bubble Sort
        for (int i = 0; i < username.length; i++) {
            if (username[i].equalsIgnoreCase(inputUsername) && password[i].equals(inputPassword)) {
                isValidLogin = true;
                break;
            }
        }

        if (isValidLogin) {
            System.out.println("Berhasil login");
            System.out.println();
            System.out.print("Masukkan jumlah karyawan : ");
            int jumlahKaryawan = scan.nextInt();
            scan.nextLine();

            String[][] dataKaryawan = new String[jumlahKaryawan][5]; // Nama, Alamat, Divisi, Total Gaji, Jam Lembur
            int[][] gajiPokokLembur = { { 80000, 12000 }, { 70000, 13000 }, { 60000, 10000 }, { 85000, 12000 } };

            for (int i = 0; i < jumlahKaryawan; i++) {
                System.out.println("");
                System.out.println("Karyawan ke - " + (i + 1));
                System.out.print("Nama    : ");
                dataKaryawan[i][0] = scan.nextLine();
                System.out.print("Alamat  : ");
                dataKaryawan[i][1] = scan.nextLine();
                System.out.println("------- Divisi -------");
                System.out.println("1. Front Office");
                System.out.println("2. House Keeping");
                System.out.println("3. Food and Beverage Service");
                System.out.println("4. Administrasi");
                System.out.print("Masukkan kategori divisi anda : ");
                dataKaryawan[i][2] = String.valueOf(scan.nextInt());
                System.out.println("------------------------------");
                scan.nextLine();
            }

            while (true) {
                System.out.println();
                System.out.println("----------------     Menu     ---------------");
                System.out.println("1. Hitung Gaji Karyawan");
                System.out.println("2. Tampilkan Data Karyawan");
                System.out.println("3. Cari Data");
                System.out.println("4. Keluar");
                System.out.print("Pilih menu : ");
                int pilihMenu = scan.nextInt();
                System.out.println();
                scan.nextLine();

                switch (pilihMenu) {
                    case 1:
                        System.out.println("");
                        System.out.println("---------------- Hitung Gaji Karyawan ---------------");

                        for (int j = 0; j < jumlahKaryawan; j++) {
                            System.out.println("Karyawan ke - " + (j + 1));
                            System.out.println("Nama : " + dataKaryawan[j][0]);
                            System.out.print("Masukkan hari kerja: ");
                            int hariKerja = scan.nextInt();
                            System.out.print("Masukkan jam lembur: ");
                            int jamLembur = scan.nextInt();
                            dataKaryawan[j][4] = String.valueOf(jamLembur); // Simpan jam lembur

                            int divisiIndex = Integer.parseInt(dataKaryawan[j][2]) - 1;
                            int gajiPokok = gajiPokokLembur[divisiIndex][0];
                            int gajiLembur = gajiPokokLembur[divisiIndex][1];
                            int jmlGajiPokok = gajiPokok * hariKerja;
                            int jmlGajiLembur = gajiLembur * jamLembur;

                            int totalGaji = jmlGajiPokok + jmlGajiLembur;

                            dataKaryawan[j][3] = String.valueOf(totalGaji); // Simpan total gaji

                            System.out.println("Total Gaji : " + totalGaji);
                            System.out.println();
                            scan.nextLine();
                        }

                        break;

                    case 2:
                        System.out.println();
                        System.out.println("---------------- Data Karyawan ---------------");

                        for (int i = 0; i < jumlahKaryawan; i++) {
                            System.out.println();
                            System.out.println("Karyawan ke-" + (i + 1));
                            System.out.println();
                            System.out.println("Nama Karyawan : " + dataKaryawan[i][0]);
                            System.out.println("Alamat Karyawan : " + dataKaryawan[i][1]);
                            int divisiIndex = Integer.parseInt(dataKaryawan[i][2]) - 1;
                            String divisi = "";
                            switch (divisiIndex) {
                                case 0:
                                    divisi = "Front Office";
                                    break;
                                case 1:
                                    divisi = "House Keeping";
                                    break;
                                case 2:
                                    divisi = "Food and Beverage Service";
                                    break;
                                case 3:
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
                        break;

                    case 3:
                        System.out.println();
                        System.out.println("\n---------------- Search Karyawan ---------------");
                        System.out.print("\nMasukkan nama karyawan yang ingin dicari: ");
                        String cariNama = scan.nextLine();

                        boolean ditemukan = false;
                        for (int j = 0; j < jumlahKaryawan; j++) {
                            if (dataKaryawan[j][0].equalsIgnoreCase(cariNama)) {
                                ditemukan = true;
                                System.out.println("\nKaryawan dengan nama " + cariNama + " ditemukan:");
                                System.out.println("Alamat Karyawan : " + dataKaryawan[j][1]);
                                System.out.println("Divisi Karyawan : " + dataKaryawan[j][2]);
                                System.out.println("Total Gaji : " + dataKaryawan[j][3]);
                                System.out.println();
                                break;
                            }
                        }

                        if (!ditemukan) {
                            System.out.println("\nKaryawan dengan nama " + cariNama + " tidak ditemukan.");
                            System.out.println();
                        }

                        break;

                    case 4:
                        System.out.println();
                        System.out.println("Anda berhasil exit");
                        break;
                    default:
                        System.out.println("Input yang dimasukkan tidak valid");
                        break;
                }
                if (pilihMenu == 4) {
                    break;
                }
            }
        } else {
            System.out.println("Username atau password salah");
        }

        scan.close();
    }
}
    import java.util.Scanner;

    public class SistemPenggajian {
        public static void main(String[] args) throws Exception {
            Scanner scan = new Scanner(System.in);

            String[] username = { "viraalfita", "dioandika", "taufikdimas" };
            String[] password = { "admin", "admin", "admin" };

            String inputUsername, inputPassword;
            int hasil = -1; // untuk pengecekan username terdapat dalam array

            System.out.print("Masukkan username : ");
            inputUsername = scan.nextLine();
            System.out.print("Masukkan password : ");
            inputPassword = scan.nextLine();

            for (int i = 0; i < username.length; i++) {
                if (username[i].equalsIgnoreCase(inputUsername)) {
                    hasil = i;
                    break;
                }
            }

            if (hasil == -1) {
                System.out.println("Username tidak ditemukan");
            } else {
                System.out.println("Berhasil login");
                System.out.println();
                int jumlahKaryawan;
                System.out.println("---------------- Input Karyawan ---------------");
                System.out.println("");
                System.out.print("Masukkan jumlah karyawan yang akan di input : ");
                jumlahKaryawan = scan.nextInt();
                scan.nextLine();

                String[] namaKaryawan = new String[jumlahKaryawan];
                String[] alamatKaryawan = new String[jumlahKaryawan];
                int[] divisiKaryawan = new int[jumlahKaryawan];
                int[] gajiKaryawan = new int[jumlahKaryawan];

                for (int j = 0; j < jumlahKaryawan; j++) {
                    System.out.println("");
                    System.out.println("Karyawan ke - " + (j + 1));
                    System.out.print("Nama    : ");
                    namaKaryawan[j] = scan.nextLine();
                    System.out.print("Alamat  : ");
                    alamatKaryawan[j] = scan.nextLine();
                    System.out.println("------- Divisi -------");
                    System.out.println("1. Front Office");
                    System.out.println("2. House Keeping");
                    System.out.println("3. Food and Beverage Service");
                    System.out.println("4. Administrasi");
                    System.out.print("Masukkan kategori divisi anda : ");
                    divisiKaryawan[j] = scan.nextInt();
                    System.out.println("----------------------");

                    System.out.println("--- Perhitungan Gaji Pokok ---");
                    System.out.print("Masukkan hari kerja: ");
                    int hariKerja = scan.nextInt();
                    System.out.print("Masukkan jam lembur: ");
                    int jamLembur = scan.nextInt();
                    System.out.println();
                    scan.nextLine();

                    int gajiPokok, gajiLembur, jmlGajiPokok, jmlGajiLembur, totalGaji;
                    switch (divisiKaryawan[j]) {
                        case 1:
                            gajiPokok = 80000;
                            gajiLembur = 12000;
                            jmlGajiPokok = gajiPokok * hariKerja;
                            jmlGajiLembur = gajiLembur * jamLembur;

                            totalGaji = jmlGajiPokok + jmlGajiLembur;

                            break;
                        case 2:
                            gajiPokok = 70000;
                            gajiLembur = 13000;
                            jmlGajiPokok = gajiPokok * hariKerja;
                            jmlGajiLembur = gajiLembur * jamLembur;

                            totalGaji = jmlGajiPokok + jmlGajiLembur;

                            break;
                        case 3:
                            gajiPokok = 60000;
                            gajiLembur = 10000;
                            jmlGajiPokok = gajiPokok * hariKerja;
                            jmlGajiLembur = gajiLembur * jamLembur;

                            totalGaji = jmlGajiPokok + jmlGajiLembur;
                            break;
                        case 4:
                            gajiPokok = 85000;
                            gajiLembur = 12000;
                            jmlGajiPokok = gajiPokok * hariKerja;
                            jmlGajiLembur = gajiLembur * jamLembur;

                            totalGaji = jmlGajiPokok + jmlGajiLembur;
                            break;
                        default:
                            totalGaji = 0;
                            break;
                    }
                    gajiKaryawan[j] = totalGaji;
                    System.out.println("Total Gaji : " + totalGaji);
                }

                System.out.println("");
                System.out.println("Daftar Karyawan");

                for (int j = 0; j < jumlahKaryawan; j++) {
                    System.out.println();
                    System.out.println("Karyawan ke-" + (j + 1));
                    System.out.println();
                    System.out.println("Nama Karyawan : " + namaKaryawan[j]);
                    System.out.println("Alamat Karyawan : " + alamatKaryawan[j]);
                    System.out.println("Divisi Karyawan : " + divisiKaryawan[j]);
                    System.out.println("Total Gaji : " + gajiKaryawan[j]);
                }

                System.out.println("\n---------------- Search Karyawan ---------------");
                System.out.print("\nMasukkan nama karyawan yang ingin dicari: ");
                String cariNama = scan.nextLine();

                boolean ditemukan = false;
                for (int j = 0; j < jumlahKaryawan; j++) {
                    if (namaKaryawan[j].equalsIgnoreCase(cariNama)) {
                        ditemukan = true;
                        System.out.println("\nKaryawan dengan nama " + cariNama + " ditemukan:");
                        System.out.println("Alamat Karyawan : " + alamatKaryawan[j]);
                        System.out.println("Divisi Karyawan : " + divisiKaryawan[j]);
                        System.out.println("Total Gaji : " + gajiKaryawan[j]);
                        break;
                    }
                }

                if (!ditemukan) {
                    System.out.println("\nKaryawan dengan nama " + cariNama + " tidak ditemukan.");
                }

            }

            scan.close();
        }
    }
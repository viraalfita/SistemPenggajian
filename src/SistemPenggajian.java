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
            System.out.println("---------------- Input Karyawan ---------------");
            System.out.println("");
            System.out.print("Masukkan jumlah karyawan yang akan di input : ");
            int jumlahKaryawan = scan.nextInt();
            scan.nextLine();

            String[][] dataKaryawan = new String[jumlahKaryawan][5]; // Nama, Alamat, Divisi, Total Gaji, Jam Lembur
            int[][] gajiPokokLembur = { { 80000, 12000 }, { 70000, 13000 }, { 60000, 10000 }, { 85000, 12000 } };

            for (int j = 0; j < jumlahKaryawan; j++) {
                System.out.println("");
                System.out.println("Karyawan ke - " + (j + 1));
                System.out.print("Nama    : ");
                dataKaryawan[j][0] = scan.nextLine();
                System.out.print("Alamat  : ");
                dataKaryawan[j][1] = scan.nextLine();
                System.out.println("------- Divisi -------");
                System.out.println("1. Front Office");
                System.out.println("2. House Keeping");
                System.out.println("3. Food and Beverage Service");
                System.out.println("4. Administrasi");
                System.out.print("Masukkan kategori divisi anda : ");
                dataKaryawan[j][2] = String.valueOf(scan.nextInt());
                System.out.println("------------------------------");

                System.out.println("--- Perhitungan Gaji Pokok ---");
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
                scan.nextLine();
            }

            System.out.println("");
            System.out.println("Daftar Karyawan");

            for (int j = 0; j < jumlahKaryawan; j++) {
                System.out.println();
                System.out.println("Karyawan ke-" + (j + 1));
                System.out.println();
                System.out.println("Nama Karyawan : " + dataKaryawan[j][0]);
                System.out.println("Alamat Karyawan : " + dataKaryawan[j][1]);
                System.out.println("Divisi Karyawan : " + dataKaryawan[j][2]);
                System.out.println("Total Gaji : " + dataKaryawan[j][3]);
            }

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
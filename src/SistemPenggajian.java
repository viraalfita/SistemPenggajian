import java.util.Scanner;

public class SistemPenggajian {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        String[] username = { "viraalfita", "dioandika", "taufikdimas" };
        String[] password = { "virapass", "diopass", "taufikpass" };

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
            String[] divisiKaryawan = new String[jumlahKaryawan];

            for (int j = 0; j < jumlahKaryawan; j++) {
                System.out.println("");
                System.out.println("Karyawan ke - " + (j + 1));
                System.out.print("Nama    : ");
                namaKaryawan[j] = scan.nextLine();
                System.out.print("Alamat  : ");
                alamatKaryawan[j] = scan.nextLine();
                System.out.print("Divisi  : ");
                divisiKaryawan[j] = scan.nextLine();
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

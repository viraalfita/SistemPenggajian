import java.util.Scanner;

public class SistemPenggajian {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        String[] username = { "viraalfita", "dioandika", "taufikdimas" };
        String[] password = { "virapass", "diopass", "taufikpass" };

        String inputUsername, inputPassword;
        boolean auth = false;
        int percobaanMax = 3, userIndex;

        for (int i = 0; i < percobaanMax; i++) {
            System.out.print("Masukkan username : ");
            inputUsername = scan.nextLine();
            System.out.print("Masukkan password : ");
            inputPassword = scan.nextLine();

            userIndex = -1;

            for (int j = 0; j < username.length; j++) {
                if (inputUsername.equalsIgnoreCase(username[j]) && inputPassword.equalsIgnoreCase(password[j])) {
                    userIndex = j;
                    break;
                }
            }
            if (userIndex != -1) {
                auth = true;
                System.out.println("Selamat datang, " + username[userIndex]);
                System.out.println("");

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
                    System.out.print("Alamat    : ");
                    alamatKaryawan[j] = scan.nextLine();
                    System.out.print("Divisi    : ");
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

                break;

            } else {
                System.out.println("Username atau password salah");
                System.out.println("Sisa percobaan : " + (percobaanMax - i - 1));
            }
        }

        if (!auth) {
            System.out.println("Maaf anda gagal login.");
        }

        scan.close();
    }
}

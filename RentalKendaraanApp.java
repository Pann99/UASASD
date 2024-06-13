import java.util.LinkedList;
import java.util.Scanner;

public class RentalKendaraanApp {
    static LinkedList<BarangRental> daftarKendaraan = new LinkedList<>();
    static LinkedList<TransaksiRental> daftarTransaksi = new LinkedList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        daftarKendaraan.add(new BarangRental("S 4567 YV", "Honda Beat", "Motor", 2017, 10000));
        daftarKendaraan.add(new BarangRental("N 4511 VS", "Honda Vario", "Motor",2018, 10000));
        daftarKendaraan.add(new BarangRental("N 1453 AA", "Toyota Yaris", "Mobil", 2022, 30000));
        daftarKendaraan.add(new BarangRental("AB 4321 A", "Toyota Innova", "Mobil", 2019, 60000));
        daftarKendaraan.add(new BarangRental("B 1234 AG", "Toyota Avanza", "Mobil", 2021, 25000));
        
        int pilihan;
        do {
            tampilkanMenu();
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    tampilkanDaftarKendaraan();
                    break;
                case 2:
                    prosesPeminjaman();
                    break;
                case 3:
                    tampilkanSemuaTransaksi();
                    break;
                case 4:
                    urutkanTransaksiTNKB();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan aplikasi Rental Kendaraan!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 0);
    }

    static void tampilkanMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Daftar Kendaraan");
        System.out.println("2. Peminjaman");
        System.out.println("3. Tampilkan Seluruh Transaksi");
        System.out.println("4. Urutkan Transaksi (TNKB)");
        System.out.println("0. Keluar");
        System.out.println("++++++++++++++++++++++++++++++++");
        System.out.print("Pilih Menu (1/2/3/4/0): ");
    }

    static void tampilkanDaftarKendaraan() {
        if (daftarKendaraan.isEmpty()) {
            System.out.println("Belum ada kendaraan yang terdaftar.");
            return;
        }
        System.out.println("\n++++++++++++++++++++++++++++++++");
        System.out.println("\tDaftar Kendaraan:");
        System.out.println("++++++++++++++++++++++++++++++++");
        for (BarangRental br : daftarKendaraan) {
            System.out.println("TNKB: " + br.nomorTNKB + 
                               ", Nama: " + br.namaKendaraan + 
                               ", Jenis: " + br.jenis + 
                               ", Tahun: " + br.tahun +
                               ", Biaya/jam: " + br.biayaSewaPerJam);
        }
    }

    static void prosesPeminjaman() {
        System.out.println("++++++++++++++++++++++++++++++++");
        System.out.print("Nama Peminjam: ");
        String namaPeminjam = input.nextLine();

        System.out.print("Nomor TNKB: ");
        String nomorTNKB = input.nextLine().toUpperCase(); 

        BarangRental kendaraan = cariKendaraan(nomorTNKB);
        if (kendaraan == null) {
            System.out.println("Kendaraan tidak ditemukan.");
            return;
        }

        System.out.print("Lama Pinjam (jam): ");
        int lamaPinjam = input.nextInt();
        input.nextLine();

        TransaksiRental transaksi = new TransaksiRental(kendaraan, namaPeminjam, lamaPinjam);
        daftarTransaksi.add(transaksi);

        System.out.println("++++++++++++++++++++++++++++++++");
        System.out.println("Transaksi berhasil!");
        System.out.println("Kode Transaksi: " + transaksi.kodeTransaksi);
        System.out.println("Total Biaya: " + transaksi.totalBiaya);
    }

    static void tampilkanSemuaTransaksi() {
        if (daftarTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }

        double totalPendapatan = 0;
        System.out.println("++++++++++++++++++++++++++++++++");
        System.out.println("Daftar Transaksi:");
        for (TransaksiRental transaksi : daftarTransaksi) {
            System.out.println("Kode: " + transaksi.kodeTransaksi + 
                               ", TNKB: " + transaksi.br.nomorTNKB + 
                               ", Nama: " + transaksi.namaPeminjam + 
                               ", Lama: " + transaksi.lamaPinjam + " jam" +
                               ", Biaya: " + transaksi.totalBiaya);
            totalPendapatan += transaksi.totalBiaya;
        }
        System.out.println("Total Pendapatan: " + totalPendapatan);
    }

    static void urutkanTransaksiTNKB() {
        int n = daftarTransaksi.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (daftarTransaksi.get(j).br.nomorTNKB.compareTo(daftarTransaksi.get(j + 1).br.nomorTNKB) > 0) {
                    
                    TransaksiRental temp = daftarTransaksi.get(j);
                    daftarTransaksi.set(j, daftarTransaksi.get(j + 1));
                    daftarTransaksi.set(j + 1, temp);
                }
            }
        }
        System.out.println("Transaksi berhasil diurutkan berdasarkan nomor TNKB.");
    }

    static BarangRental cariKendaraan(String nomorTNKB) {
        for (BarangRental br : daftarKendaraan) {
            if (br.nomorTNKB.equalsIgnoreCase(nomorTNKB)) {
                return br;
            }
        }
        return null;
    }
}
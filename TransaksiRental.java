class TransaksiRental {
    static int kodeTransaksiTerakhir = 0;
    int kodeTransaksi;
    BarangRental br;
    String namaPeminjam;
    int lamaPinjam;
    double totalBiaya;

    TransaksiRental(BarangRental br, String namaPeminjam, int lamaPinjam) {
        this.kodeTransaksi = ++kodeTransaksiTerakhir;
        this.br = br;
        this.namaPeminjam = namaPeminjam;
        this.lamaPinjam = lamaPinjam;
        this.totalBiaya = hitungTotalBiaya();
    }

    double hitungTotalBiaya() {
        double biaya = br.biayaSewaPerJam * lamaPinjam;
        return biaya;
    }
}
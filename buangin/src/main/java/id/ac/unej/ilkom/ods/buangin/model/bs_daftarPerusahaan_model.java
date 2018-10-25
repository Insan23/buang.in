package id.ac.unej.ilkom.ods.buangin.model;

public class bs_daftarPerusahaan_model {
    String perusahaan, pemilik, alamat;
    int gambar;

    public bs_daftarPerusahaan_model(String perusahaan, String pemilik, String alamat, int gambar) {
        this.perusahaan = perusahaan;
        this.pemilik = pemilik;
        this.alamat = alamat;
        this.gambar = gambar;
    }

    public String getPerusahaan() {
        return perusahaan;
    }

    public String getPemilik() {
        return pemilik;
    }

    public String getAlamat() {
        return alamat;
    }

    public int getGambar() {
        return gambar;
    }
}

package id.ac.unej.ilkom.ods.buangin.model;

public class v_daftarBank_model {
    private String namaBank, pemilik, alamat;
    private int gambar;

    public v_daftarBank_model(String namaBank, String pemilik, String alamat, int gambar) {
        this.namaBank = namaBank;
        this.pemilik = pemilik;
        this.alamat = alamat;
        this.gambar = gambar;
    }

    public String getNamaBank() {
        return namaBank;
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

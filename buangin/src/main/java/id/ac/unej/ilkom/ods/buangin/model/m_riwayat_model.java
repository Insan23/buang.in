package id.ac.unej.ilkom.ods.buangin.model;

public class m_riwayat_model {
    private String namaMitra, poin, tanggalTukar;
    private int gambar;

    public m_riwayat_model(String namaMitra, String poin, String tanggalTukar, int gambar) {
        this.namaMitra = namaMitra;
        this.poin = poin;
        this.tanggalTukar = tanggalTukar;
        this.gambar = gambar;
    }

    public String getNamaMitra() {
        return namaMitra;
    }

    public String getPoin() {
        return poin;
    }

    public String getTanggalTukar() {
        return tanggalTukar;
    }

    public int getGambar() {
        return gambar;
    }
}

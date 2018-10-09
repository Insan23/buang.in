package id.ac.unej.ilkom.ods.buangin.Model;

public class v_daftarSampah_model {
    private String tanggal, waktu, status;
    private int gambar;

    public v_daftarSampah_model(String tanggal, String waktu, String status, int gambar) {

        this.tanggal = tanggal;
        this.waktu = waktu;
        this.status = status;
        this.gambar=gambar;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }
}

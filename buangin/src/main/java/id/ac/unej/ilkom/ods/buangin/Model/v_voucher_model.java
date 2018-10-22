package id.ac.unej.ilkom.ods.buangin.Model;

public class v_voucher_model {
    private String namaMitra, poin, jumlah;
    private int gambar;

    public v_voucher_model(String namaMitra, String poin, String jumlah, int gambar) {
        this.namaMitra = namaMitra;
        this.poin = poin;
        this.jumlah = jumlah;
        this.gambar = gambar;
    }

    public String getNamaMitra() {
        return namaMitra;
    }

    public String getPoin() {
        return poin;
    }

    public String getJumlah() {
        return jumlah;
    }

    public int getGambar() {
        return gambar;
    }
}

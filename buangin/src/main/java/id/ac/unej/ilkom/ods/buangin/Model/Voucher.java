package id.ac.unej.ilkom.ods.buangin.Model;

public class Voucher {

    public static final int VOUCHER_BERLAKU = 1;
    public static final int VOUCHER_TIDAK_BERLAKU = 0;

    private String url_foto;
    private String namaVoucher;
    private String deskripsi;
    private String hargaPoin;
    private String jumlahKuota;
    private String UIDPemilik;
    private int statusVoucher;

    public Voucher() {

    }

    public Voucher(String url_foto, String namaVoucher, String deskripsi, String hargaPoin, String jumlahKuota, String UIDPemilik, int statusVoucher) {
        this.url_foto = url_foto;
        this.namaVoucher = namaVoucher;
        this.deskripsi = deskripsi;
        this.hargaPoin = hargaPoin;
        this.jumlahKuota = jumlahKuota;
        this.UIDPemilik = UIDPemilik;
        this.statusVoucher = statusVoucher;
    }

    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }

    public String getNamaVoucher() {
        return namaVoucher;
    }

    public void setNamaVoucher(String namaVoucher) {
        this.namaVoucher = namaVoucher;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getHargaPoin() {
        return hargaPoin;
    }

    public void setHargaPoin(String hargaPoin) {
        this.hargaPoin = hargaPoin;
    }

    public String getJumlahKuota() {
        return jumlahKuota;
    }

    public void setJumlahKuota(String jumlahKuota) {
        this.jumlahKuota = jumlahKuota;
    }

    public String getUIDPemilik() {
        return UIDPemilik;
    }

    public void setUIDPemilik(String UIDPemilik) {
        this.UIDPemilik = UIDPemilik;
    }

    public int getStatusVoucher() {
        return statusVoucher;
    }

    public void setStatusVoucher(int statusVoucher) {
        this.statusVoucher = statusVoucher;
    }
}

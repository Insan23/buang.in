package id.ac.unej.ilkom.ods.buangin.Model;

public class Verifikasi {

    private String kodeSampah,
            uidVolunteer,
            nama,
            jenisSampah,
            poinSampah,
            tanggalSubmit,
            hargaSampah,
            beratSampah,
            statusVerifikasi;


    public Verifikasi() {

    }

    public Verifikasi(String kodeSampah, String uidVolunteer, String nama, String jenisSampah, String poinSampah, String tanggalSubmit, String hargaSampah, String beratSampah, String statusVerifikasi) {
        this.kodeSampah = kodeSampah;
        this.uidVolunteer = uidVolunteer;
        this.nama = nama;
        this.jenisSampah = jenisSampah;
        this.poinSampah = poinSampah;
        this.tanggalSubmit = tanggalSubmit;
        this.hargaSampah = hargaSampah;
        this.beratSampah = beratSampah;
        this.statusVerifikasi = statusVerifikasi;
    }

    public String getKodeSampah() {
        return kodeSampah;
    }

    public void setKodeSampah(String kodeSampah) {
        this.kodeSampah = kodeSampah;
    }

    public String getUidVolunteer() {
        return uidVolunteer;
    }

    public void setUidVolunteer(String uidVolunteer) {
        this.uidVolunteer = uidVolunteer;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisSampah() {
        return jenisSampah;
    }

    public void setJenisSampah(String jenisSampah) {
        this.jenisSampah = jenisSampah;
    }

    public String getPoinSampah() {
        return poinSampah;
    }

    public void setPoinSampah(String poinSampah) {
        this.poinSampah = poinSampah;
    }

    public String getTanggalSubmit() {
        return tanggalSubmit;
    }

    public void setTanggalSubmit(String tanggalSubmit) {
        this.tanggalSubmit = tanggalSubmit;
    }

    public String getHargaSampah() {
        return hargaSampah;
    }

    public void setHargaSampah(String hargaSampah) {
        this.hargaSampah = hargaSampah;
    }

    public String getBeratSampah() {
        return beratSampah;
    }

    public void setBeratSampah(String beratSampah) {
        this.beratSampah = beratSampah;
    }

    public String getStatusVerifikasi() {
        return statusVerifikasi;
    }

    public void setStatusVerifikasi(String statusVerifikasi) {
        this.statusVerifikasi = statusVerifikasi;
    }
}
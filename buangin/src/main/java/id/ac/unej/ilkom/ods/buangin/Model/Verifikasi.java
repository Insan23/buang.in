package id.ac.unej.ilkom.ods.buangin.Model;

public class Verifikasi {

    private String kodeSampah, uidVolunteer, nama;

    public Verifikasi() {
    }

    public Verifikasi(String kodeSampah, String uidVolunteer, String nama) {

        this.kodeSampah = kodeSampah;
        this.uidVolunteer = uidVolunteer;
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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
}

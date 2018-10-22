package id.ac.unej.ilkom.ods.buangin.Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Sampah {

    public static final String VERIFIKASI_MENUNGGU = "Menunggu Verifikasi";
    public static final String VERIFIKASI_DITERIMA = "Verifikasi Diterima";
    public static final String VERIFIKASI_DITOLAK = "Verifikasi Ditolak";

    private String id;
    private String kodeSampah;
    private String urlFoto;
    private String tanggalSubmit;
    private String tanggalBerakhir;
    private String uidVolunteer;
    private String statusVerifikasi;
    private String poin;

    public Sampah() {

    }

    public Sampah(String kodeSampah, String urlFoto, String tanggalSubmit, String tanggalBerakhir, String uidVolunteer, String statusVerifikasi, String poin) {
        this.kodeSampah = kodeSampah;
        this.urlFoto = urlFoto;
        this.tanggalSubmit = tanggalSubmit;
        this.tanggalBerakhir = tanggalBerakhir;
        this.uidVolunteer = uidVolunteer;
        this.statusVerifikasi = statusVerifikasi;
        this.poin = poin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKodeSampah() {
        return kodeSampah;
    }

    public void setKodeSampah(String kodeSampah) {
        this.kodeSampah = kodeSampah;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getTanggalSubmit() {
        return tanggalSubmit;
    }

    public void setTanggalSubmit(String tanggalSubmit) {
        this.tanggalSubmit = tanggalSubmit;
    }

    public String getTanggalBerakhir() {
        return tanggalBerakhir;
    }

    public void setTanggalBerakhir(String tanggalBerakhir) {
        this.tanggalBerakhir = tanggalBerakhir;
    }

    public String getUidVolunteer() {
        return uidVolunteer;
    }

    public void setUidVolunteer(String uidVolunteer) {
        this.uidVolunteer = uidVolunteer;
    }

    public String getStatusVerifikasi() {
        return statusVerifikasi;
    }

    public void setStatusVerifikasi(String statusVerifikasi) {
        this.statusVerifikasi = statusVerifikasi;
    }

    public String getPoin() {
        return poin;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }
}

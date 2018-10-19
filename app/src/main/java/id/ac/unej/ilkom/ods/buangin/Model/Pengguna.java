package id.ac.unej.ilkom.ods.buangin.Model;

import android.widget.ScrollView;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Pengguna {

    public static final String VOLUNTEER = "volunteer";
    public static final String BANK_SAMPAH = "bank_sampah";
    public static final String MITRA = "mitra";
    public static final String PERUSAHAAN = "perusahaan";

    private String id;
    private String nama;
    private String email;
    private String level;
    private int poin;

    public Pengguna() {
        // Konstruktor default dibutuhkan untuk dapat dipanggil oleh DataSnapshot.getValue(User.class)
    }

    public Pengguna(String nama, String email, String level, int poin) {
        this.nama = nama;
        this.email = email;
        this.level = level;
        this.poin = poin;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoin() {
        return this.poin;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }
}

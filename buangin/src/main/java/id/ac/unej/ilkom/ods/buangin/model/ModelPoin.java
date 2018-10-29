package id.ac.unej.ilkom.ods.buangin.model;

public class ModelPoin {

    private String uid;
    private String poin;
    private String dari;
    private String sumber;

    public ModelPoin() {
    }

    public ModelPoin(String uid, String poin, String dari, String sumber) {
        this.uid = uid;
        this.poin = poin;
        this.dari = dari;
        this.sumber = sumber;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPoin() {
        return poin;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }

    public String getDari() {
        return dari;
    }

    public void setDari(String dari) {
        this.dari = dari;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }
}

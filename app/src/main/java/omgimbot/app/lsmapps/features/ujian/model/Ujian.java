package omgimbot.app.lsmapps.features.ujian.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ujian implements Serializable {

    @SerializedName("id_ujian")
    private String id_ujian;

    @SerializedName("kode_ujian")
    private String kode_ujian;

    @SerializedName("judul_ujian")
    private String judul_ujian;

    @SerializedName("tanggal_mulai")
    private String tanggal_mulai;

    @SerializedName("jam_mulai")
    private String jam_mulai;

    @SerializedName("jam_selesai")
    private String jam_selesai;

    @SerializedName("pengajar")
    private String pengajar;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("create_at")
    private String create_at;

    @SerializedName("update_at")
    private String update_at;

    public String getId_ujian() {
        return id_ujian;
    }

    public void setId_ujian(String id_ujian) {
        this.id_ujian = id_ujian;
    }

    public String getKode_ujian() {
        return kode_ujian;
    }

    public void setKode_ujian(String kode_ujian) {
        this.kode_ujian = kode_ujian;
    }

    public String getJudul_ujian() {
        return judul_ujian;
    }

    public void setJudul_ujian(String judul_ujian) {
        this.judul_ujian = judul_ujian;
    }

    public String getTanggal_mulai() {
        return tanggal_mulai;
    }

    public void setTanggal_mulai(String tanggal_mulai) {
        this.tanggal_mulai = tanggal_mulai;
    }

    public String getJam_mulai() {
        return jam_mulai;
    }

    public void setJam_mulai(String jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public String getJam_selesai() {
        return jam_selesai;
    }

    public void setJam_selesai(String jam_selesai) {
        this.jam_selesai = jam_selesai;
    }

    public String getPengajar() {
        return pengajar;
    }

    public void setPengajar(String pengajar) {
        this.pengajar = pengajar;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }
}

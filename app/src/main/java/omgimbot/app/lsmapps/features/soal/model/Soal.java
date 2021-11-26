package omgimbot.app.lsmapps.features.soal.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Soal implements Serializable {
    @SerializedName("nilai")
    private String nilai;

    @SerializedName("id_soal")
    private String id_soal;

    @SerializedName("id_ujian")
    private String id_ujian;

    @SerializedName("kode_soal")
    private String kode_soal;

    @SerializedName("judul_soal")
    private String judul_soal;

    @SerializedName("soal")
    private String soal;

    @SerializedName("pilihan_a")
    private String pilihan_a;

    @SerializedName("pilihan_gambar_a")
    private String pilihan_gambar_a;

    @SerializedName("pilihan_b")
    private String pilihan_b;

    @SerializedName("pilihan_gambar_b")
    private String pilihan_gambar_b;

    @SerializedName("pilihan_c")
    private String pilihan_c;

    @SerializedName("pilihan_gambar_c")
    private String pilihan_gambar_c;


    @SerializedName("pilihan_d")
    private String pilihan_d;

    @SerializedName("pilihan_gambar_d")
    private String pilihan_gambar_d;

    @SerializedName("dipilih")
    private String dipilih;

    @SerializedName("pilihan_benar")
    private String pilihan_benar;

    @SerializedName("bobot_soal")
    private String bobot_soal;

    @SerializedName("create_at")
    private String create_at;

    @SerializedName("update_at")
    private String update_at;

    @SerializedName("selected")
    private boolean selected;

    @SerializedName("koreksi")
    private boolean koreksi;

    @SerializedName("selesaiMenjawab")
    private boolean selesaiMenjawab;

    public boolean isSelesaiMenjawab() {
        return selesaiMenjawab;
    }

    public void setSelesaiMenjawab(boolean selesaiMenjawab) {
        this.selesaiMenjawab = selesaiMenjawab;
    }

    public boolean isKoreksi() {
        return koreksi;
    }

    public void setKoreksi(boolean koreksi) {
        this.koreksi = koreksi;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getDipilih() {
        return dipilih;
    }

    public void setDipilih(String dipilih) {
        this.dipilih = dipilih;
    }

    public String getId_soal() {
        return id_soal;
    }

    public void setId_soal(String id_soal) {
        this.id_soal = id_soal;
    }

    public String getId_ujian() {
        return id_ujian;
    }

    public void setId_ujian(String id_ujian) {
        this.id_ujian = id_ujian;
    }

    public String getKode_soal() {
        return kode_soal;
    }

    public void setKode_soal(String kode_soal) {
        this.kode_soal = kode_soal;
    }

    public String getJudul_soal() {
        return judul_soal;
    }

    public void setJudul_soal(String judul_soal) {
        this.judul_soal = judul_soal;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getPilihan_a() {
        return pilihan_a;
    }

    public void setPilihan_a(String pilihan_a) {
        this.pilihan_a = pilihan_a;
    }

    public String getPilihan_gambar_a() {
        return pilihan_gambar_a;
    }

    public void setPilihan_gambar_a(String pilihan_gambar_a) {
        this.pilihan_gambar_a = pilihan_gambar_a;
    }

    public String getPilihan_b() {
        return pilihan_b;
    }

    public void setPilihan_b(String pilihan_b) {
        this.pilihan_b = pilihan_b;
    }

    public String getPilihan_gambar_b() {
        return pilihan_gambar_b;
    }

    public void setPilihan_gambar_b(String pilihan_gambar_b) {
        this.pilihan_gambar_b = pilihan_gambar_b;
    }

    public String getPilihan_c() {
        return pilihan_c;
    }

    public void setPilihan_c(String pilihan_c) {
        this.pilihan_c = pilihan_c;
    }

    public String getPilihan_gambar_c() {
        return pilihan_gambar_c;
    }

    public void setPilihan_gambar_c(String pilihan_gambar_c) {
        this.pilihan_gambar_c = pilihan_gambar_c;
    }

    public String getPilihan_d() {
        return pilihan_d;
    }

    public void setPilihan_d(String pilihan_d) {
        this.pilihan_d = pilihan_d;
    }

    public String getPilihan_gambar_d() {
        return pilihan_gambar_d;
    }

    public void setPilihan_gambar_d(String pilihan_gambar_d) {
        this.pilihan_gambar_d = pilihan_gambar_d;
    }

    public String getPilihan_benar() {
        return pilihan_benar;
    }

    public void setPilihan_benar(String pilihan_benar) {
        this.pilihan_benar = pilihan_benar;
    }

    public String getBobot_soal() {
        return bobot_soal;
    }

    public void setBobot_soal(String bobot_soal) {
        this.bobot_soal = bobot_soal;
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

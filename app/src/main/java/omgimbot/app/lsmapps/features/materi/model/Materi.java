package omgimbot.app.lsmapps.features.materi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Materi implements Serializable {

    @SerializedName("id_materi")
    private String id_materi;

    @SerializedName("kode_materi")
    private String kode_materi;

    @SerializedName("author")
    private String author;

    @SerializedName("judul_materi")
    private String judul_materi;

    @SerializedName("deskripsi_materi")
    private String deskripsi_materi;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("content")
    private String content;

    @SerializedName("create_at")
    private String create_at;

    @SerializedName("update_at")
    private String update_at;

    public String getId_materi() {
        return id_materi;
    }

    public void setId_materi(String id_materi) {
        this.id_materi = id_materi;
    }

    public String getKode_materi() {
        return kode_materi;
    }

    public void setKode_materi(String kode_materi) {
        this.kode_materi = kode_materi;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getJudul_materi() {
        return judul_materi;
    }

    public void setJudul_materi(String judul_materi) {
        this.judul_materi = judul_materi;
    }

    public String getDeskripsi_materi() {
        return deskripsi_materi;
    }

    public void setDeskripsi_materi(String deskripsi_materi) {
        this.deskripsi_materi = deskripsi_materi;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

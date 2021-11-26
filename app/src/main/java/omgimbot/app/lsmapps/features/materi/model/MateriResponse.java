package omgimbot.app.lsmapps.features.materi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MateriResponse {

    @SerializedName("rc")
    private String mRc;

    @SerializedName("message")
    private String mRm;

    @SerializedName("status")
    private Boolean mStatus;

    @SerializedName("result")
    private List<Materi> result;


    public String getmRc() {
        return mRc;
    }

    public void setmRc(String mRc) {
        this.mRc = mRc;
    }

    public String getmRm() {
        return mRm;
    }

    public void setmRm(String mRm) {
        this.mRm = mRm;
    }

    public Boolean getSuccess() {
        return mStatus;
    }

    public void setSuccess(Boolean mStatus) {
        this.mStatus = mStatus;
    }

    public Boolean getmStatus() {
        return mStatus;
    }

    public void setmStatus(Boolean mStatus) {
        this.mStatus = mStatus;
    }

    public List<Materi> getResult() {
        return result;
    }

    public void setResult(List<Materi> result) {
        this.result = result;
    }
}

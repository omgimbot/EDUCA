package omgimbot.app.lsmapps.features.ujian;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import omgimbot.app.lsmapps.features.materi.IMateriView;
import omgimbot.app.lsmapps.features.materi.model.MateriResponse;
import omgimbot.app.lsmapps.features.ujian.model.UjianResponse;
import omgimbot.app.lsmapps.network.NetworkService;
import omgimbot.app.lsmapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UjianPresenter {
    final IUjianView view;
    public final Retrofit restService;

    public UjianPresenter(IUjianView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    public void showUjian() {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).getUjian()
                .enqueue(new Callback<UjianResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<UjianResponse> call, Response<UjianResponse> response) {
                        view.hideLoadingIndicator();
                        view.onDataReady(response.body().getResult());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<UjianResponse> call, Throwable t) {
                        Log.d("erornya", new Gson().toJson(call.request()));
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }


    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }
}

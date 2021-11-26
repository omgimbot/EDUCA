package omgimbot.app.lsmapps.features.soal;

import androidx.annotation.NonNull;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import omgimbot.app.lsmapps.features.materi.IMateriView;
import omgimbot.app.lsmapps.features.materi.model.MateriResponse;
import omgimbot.app.lsmapps.features.soal.model.SoalResponse;
import omgimbot.app.lsmapps.network.NetworkService;
import omgimbot.app.lsmapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SoalPresenter {
    final ISoalView view;
    public final Retrofit restService;

    public SoalPresenter(ISoalView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    public void showSoal(String idUjian) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).getSoal(idUjian)
                .enqueue(new Callback<SoalResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<SoalResponse> call, Response<SoalResponse> response) {
                        view.hideLoadingIndicator();
                        view.onDataReady(response.body().getResult());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<SoalResponse> call, Throwable t) {
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

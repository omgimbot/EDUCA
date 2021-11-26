package omgimbot.app.lsmapps.features.materi;

import androidx.annotation.NonNull;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import omgimbot.app.lsmapps.features.materi.model.MateriResponse;
import omgimbot.app.lsmapps.network.NetworkService;
import omgimbot.app.lsmapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MateriPresenter {
    final IMateriView view;
    public final Retrofit restService;

    public MateriPresenter(IMateriView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    public void showMateri() {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).getMateri()
                .enqueue(new Callback<MateriResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<MateriResponse> call, Response<MateriResponse> response) {
                        view.hideLoadingIndicator();
                        view.onDataReady(response.body().getResult());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<MateriResponse> call, Throwable t) {
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

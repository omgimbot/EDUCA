package omgimbot.app.lsmapps.features.auth.regist;

import omgimbot.app.lsmapps.Utils.CommonRespon;
import omgimbot.app.lsmapps.features.auth.login.model.Users;
import omgimbot.app.lsmapps.network.NetworkService;
import omgimbot.app.lsmapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistPresenter {
    final IRegistView view;
    public final Retrofit restService;
    public RegistPresenter(IRegistView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    void signup(Users registModel) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).signup(registModel)
                .enqueue(new Callback<CommonRespon>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonRespon> call, Response<CommonRespon> response) {
                        view.hideLoadingIndicator();
                        if (response.body().getSuccess())
                            view.onRegistSuccess();
                        else {
                            view.onRegistFailed(response.body().getmRm());
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonRespon> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

}

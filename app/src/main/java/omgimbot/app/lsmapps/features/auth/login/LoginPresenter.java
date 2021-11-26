package omgimbot.app.lsmapps.features.auth.login;

import java.util.HashMap;

import omgimbot.app.lsmapps.App;
import omgimbot.app.lsmapps.Prefs;
import omgimbot.app.lsmapps.network.NetworkService;
import omgimbot.app.lsmapps.network.RestService;
import omgimbot.app.lsmapps.features.auth.login.model.LoginResponse;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginPresenter {
    final ILoginView view;
    public final Retrofit restService;
    public LoginPresenter(ILoginView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    boolean isLoggedIn() {
        return App.getPref().getBoolean(Prefs.PREF_IS_LOGEDIN, false);
    }

    void storeAccessToken(String token) {
        App.getPref().put(Prefs.PREF_ACCESS_TOKEN, token);
    }

    void storeProfile(String data) {
        App.getPref().put(Prefs.PREF_STORE_PROFILE, data);
        App.getPref().put(Prefs.PREF_IS_LOGEDIN, true);
    }

    void login(String username, String password) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("username", username );
        params.put("password", password );
        view.showLoadingIndicator();
        restService.create(NetworkService.class).signin(params).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(retrofit2.Call<LoginResponse> call, Response<LoginResponse> response) {
                view.hideLoadingIndicator();
                if (response.body().getSuccess()) {
                    App.getPref().put(Prefs.PREF_IS_LOGEDIN, true);
                    view.onSigninSuccess(response.body());
                } else
                    view.onSigninFailed(response.body().getRm());
            }

            @Override
            public void onFailure(retrofit2.Call<LoginResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }
}

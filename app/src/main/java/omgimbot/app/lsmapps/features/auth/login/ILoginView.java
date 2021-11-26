package omgimbot.app.lsmapps.features.auth.login;

import omgimbot.app.lsmapps.features.auth.login.model.LoginResponse;

public interface ILoginView {
    void initViews();

    void onSigninSuccess(LoginResponse response);

    void onSigninFailed(String rm);

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();
}

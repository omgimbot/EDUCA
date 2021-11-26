package omgimbot.app.lsmapps.features.auth.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import omgimbot.app.lsmapps.App;
import omgimbot.app.lsmapps.Prefs;
import omgimbot.app.lsmapps.Utils.GsonHelper;
import omgimbot.app.lsmapps.features.dashboard.DashboardActivity;
import omgimbot.app.lsmapps.ui.SweetDialogs;
import omgimbot.app.sidangapps.R;
import omgimbot.app.lsmapps.features.auth.login.model.LoginResponse;
import omgimbot.app.lsmapps.features.auth.regist.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    @BindView(R.id.mBtnRegis)
    Button mBtnRegis;
    @BindView(R.id.signin)
    Button signin;
    @BindView(R.id.mUsername)
    EditText mUsername;
    @BindView(R.id.mPassword)
    EditText mPassword;
    LoginPresenter presenter;
    SweetAlertDialog sweetAlertDialog;
    LoginResponse mProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
        if (presenter.isLoggedIn()) {
            mProfile = (LoginResponse) GsonHelper.parseGson(
                    App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                    new LoginResponse()
            );
            if (mProfile.getResult().getRole() == 0) {
                this.goToDashboard();
            } else if (mProfile.getResult().getRole()== 1 ) {
                this.goToDashboard();
            }
        } else {
            this.initViews();
        }

    }

    @Override
    public void initViews() {
        mBtnRegis.setOnClickListener(view -> this.goToRegist());
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading ...");
    }

    @Override
    public void onSigninSuccess(LoginResponse response) {
        Log.d("login",new Gson().toJson(response));
        presenter.storeProfile(new Gson().toJson(response));
        if (response.getResult().getRole() == 0) {
            this.goToDashboard();
        } else if (response.getResult().getRole()== 1 ) {
            this.goToDashboard();
        }

    }

    @Override
    public void onSigninFailed(String rm) {
        Toast.makeText(this, rm, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetworkError(String cause) {
        Log.e("errornya", cause);
        SweetDialogs.endpointError(this);
    }

    @Override
    public void showLoadingIndicator() {
        sweetAlertDialog.show();
    }

    @Override
    public void hideLoadingIndicator() {
        sweetAlertDialog.dismiss();
    }

    @OnClick(R.id.signin)
    void login() {
        String username = mUsername.getText().toString();
        String pass = mPassword.getText().toString();
        if (username.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Nik tidak boleh kosong", Toast.LENGTH_LONG).show();
        } else if (pass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Password tidak boleh kosong", Toast.LENGTH_LONG).show();
        } else {
            presenter.login(username, pass);
        }

    }

    public void goToRegist() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    public void goToDashboard() {
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }

    public void goToDashboardMhs() {
//        startActivity(new Intent(this, DashboardMhsActivity.class));
//        finish();
    }
}

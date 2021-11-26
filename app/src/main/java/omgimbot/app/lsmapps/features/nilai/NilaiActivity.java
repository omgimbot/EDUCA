package omgimbot.app.lsmapps.features.nilai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import omgimbot.app.lsmapps.features.dashboard.DashboardActivity;
import omgimbot.app.sidangapps.R;

public class NilaiActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    Dialog dialog;
    ImageButton closePopup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Nilai");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToDashboard();
    }

    public void goToDashboard() {
        Intent a = new Intent(this, DashboardActivity.class);
        startActivity(a);
        finish();
    }

    public void showDetail() {
        dialog = new Dialog(this, R.style.AppDialogTheme);
        dialog.getWindow().getAttributes().windowAnimations = R.style.AppDialogTheme;
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_detailnilai);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.50);
        dialog.getWindow().setLayout(width, height);


        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        closePopup = dialog.findViewById(R.id.mClose);
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
    }

    @OnClick(R.id.mDetail)
    void gotoDetail(){
        this.showDetail();
    }
}

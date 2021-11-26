package omgimbot.app.lsmapps.features.ujian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import omgimbot.app.lsmapps.App;
import omgimbot.app.lsmapps.features.dashboard.DashboardActivity;
import omgimbot.app.lsmapps.features.materi.ContentMateriActivity;
import omgimbot.app.lsmapps.features.materi.MateriAdapter;
import omgimbot.app.lsmapps.features.materi.model.Materi;
import omgimbot.app.lsmapps.features.soal.SoalActivity;
import omgimbot.app.lsmapps.features.ujian.model.Ujian;
import omgimbot.app.lsmapps.ui.SweetDialogs;
import omgimbot.app.sidangapps.R;

public class UjianActivity extends AppCompatActivity implements IUjianView , UjianAdapter.onSelected{
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    SweetAlertDialog sweetAlertDialog;
    Dialog dialog;
    ImageButton closePopup;
    TextView ikutUjian;
    UjianPresenter presenter ;
    UjianAdapter adapter ;
    @BindView(R.id.empty_store)
    LinearLayout empty_store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ujian);
        ButterKnife.bind(this);
        presenter = new UjianPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Ujian");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.initViews();
        presenter.showUjian();
    }

    @Override
    public void initViews(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
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

    public void showDetail(Ujian data) {
        dialog = new Dialog(this, R.style.AppDialogTheme);
        dialog.getWindow().getAttributes().windowAnimations = R.style.AppDialogTheme;
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_detailujian);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.70);
        dialog.getWindow().setLayout(width, height);
        TextView mJudul = dialog.findViewById(R.id.mJudul);
        TextView mKodeUjian = dialog.findViewById(R.id.mKodeUjian);
        TextView mPengajar = dialog.findViewById(R.id.mPengajar);
        TextView mTgl = dialog.findViewById(R.id.mTgl);
        TextView mJamMulai = dialog.findViewById(R.id.mJamMulai);
        TextView mJamSelesai = dialog.findViewById(R.id.mJamSelesai);

        mJudul.setText(data.getJudul_ujian());
        mKodeUjian.setText(data.getKode_ujian());
        mPengajar.setText(data.getPengajar());
        mTgl.setText(data.getTanggal_mulai());
        mJamMulai.setText(data.getJam_mulai());
        mJamSelesai.setText(data.getJam_selesai());


        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        ikutUjian = dialog.findViewById(R.id.mBtnIkutUjian);
        closePopup = dialog.findViewById(R.id.mClose);
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        ikutUjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UjianActivity.this , SoalActivity.class);
                i.putExtra("idUjian" , data.getId_ujian() );
//                startActivity(new Intent(UjianActivity.this , SoalActivity.class));
                startActivity(i);
                finish();

            }
        });
    }



    @Override
    public void showLoadingIndicator() {
        sweetAlertDialog.show();
    }

    @Override
    public void hideLoadingIndicator() {
        sweetAlertDialog.dismiss();
    }

    @Override
    public void onDataReady(List<Ujian> result) {
        Log.d("datanyawoi", ""+result.size());
        adapter = new UjianAdapter(result, this, this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (result.isEmpty()){
            empty_store.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }else {
            empty_store.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onNetworkError(String cause) {
        Log.d("Error", cause);
        SweetDialogs.endpointError(this);
    }



    @Override
    public void onDetail(Ujian data) {
            this.showDetail(data);
    }
}

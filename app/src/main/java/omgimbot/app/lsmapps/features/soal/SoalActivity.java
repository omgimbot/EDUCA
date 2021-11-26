package omgimbot.app.lsmapps.features.soal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.delight.android.webview.AdvancedWebView;
import omgimbot.app.lsmapps.App;
import omgimbot.app.lsmapps.features.dashboard.DashboardActivity;
import omgimbot.app.lsmapps.features.soal.model.Soal;
import omgimbot.app.lsmapps.features.ujian.UjianActivity;
import omgimbot.app.lsmapps.features.ujian.UjianAdapter;
import omgimbot.app.lsmapps.features.ujian.model.Ujian;
import omgimbot.app.lsmapps.ui.SweetDialogs;
import omgimbot.app.sidangapps.R;

public class SoalActivity extends AppCompatActivity implements ISoalView, SoalAdapter.onSelected, View.OnClickListener {
    String TEST_PAGE_URL = "https://wayangedu.com/siswa/ujian.php?id=4&soal=4";

    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSubmit)
    Button mSubmit;
    @BindView(R.id.mNilai)
    TextView mNilai;
    SweetAlertDialog sweetAlertDialog;
    SoalAdapter adapter ;
    SoalPresenter presenter ;
    List<Soal> soals = new ArrayList<>();
    long nilai ;
    Bundle bundle ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);
        ButterKnife.bind(this);
        presenter = new SoalPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Ujian");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.initViews();
        bundle = getIntent().getExtras();
        if(bundle != null )
            presenter.showSoal(bundle.getString("idUjian"));

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
        mSubmit.setOnClickListener(this);
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
    public void onDataReady(List<Soal> result) {
        Log.d("datanyani", new Gson().toJson(result));

        adapter = new SoalAdapter(result, this,this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void onNetworkError(String cause) {
        Log.d("Error", cause);
        SweetDialogs.endpointError(this);
    }



    @Override
    public void onBackPressed() {
        this.gotoDashboard();
        super.onBackPressed();
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

    void gotoDashboard() {
        Intent a = new Intent(SoalActivity.this, UjianActivity.class);
        startActivity(a);
        finish();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onNilai(Soal data) {
        Log.d("datasss" , new Gson().toJson(data));
//        soals.addAll(Collections.singleton(data));
        nilai = this.getTotalNilai();
        soals.add(data);


    }

    @Override
    public void onSubmit(){
        SweetDialogs.commonSuccess(this,"Berhasil memuat permintaan" , true);
        mNilai.setText(String.valueOf(nilai));
        mSubmit.setEnabled(false);
        mSubmit.setBackgroundColor(Color.GRAY);
        adapter.swap(soals);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private long getTotalNilai() {
        long subTotal = adapter.ruts.stream().filter(word -> word.isSelected() == true).mapToLong(i -> Long.parseLong(i.getNilai())).sum();
//        if (subTotal != 0) {
//            mCheckout.setEnabled(true);
//            mCheckout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
//        } else {
//            mCheckout.setEnabled(false);
//            mCheckout.setBackgroundColor(getResources().getColor(R.color.grey));
//        }
//        SweetDialogs.commonSuccess(this,"Berhasil memuat permintaan" , true);

        return subTotal;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mSubmit:
                SweetDialogs.confirmDialog(SoalActivity.this, "Apakah anda yakin ?", "Pastikan jawaban anda sudah benar.", "Berhasil memuat Permintaan", string -> {
                   this.onSubmit();
                });
//                SweetDialogs.commonWarning(this,"asdsa","dsadsadsa",false);

                break;
        }
    }
}

package omgimbot.app.lsmapps.features.materi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import omgimbot.app.lsmapps.App;
import omgimbot.app.lsmapps.Utils.LinkedHashMapAdapter;
import omgimbot.app.lsmapps.features.dashboard.DashboardActivity;
import omgimbot.app.lsmapps.features.materi.model.Materi;
import omgimbot.app.lsmapps.ui.SweetDialogs;
import omgimbot.app.sidangapps.R;

public class MateriActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener , IMateriView, MateriAdapter.onSelected {
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;

    @BindView(R.id.mMt)
    Spinner mMt;

    @BindView(R.id.empty_store)
    LinearLayout empty_store;

//    @BindView(R.id.data1)
//    CardView data1;
//
//    @BindView(R.id.data2)
//    CardView data2;
//
//    @BindView(R.id.data3)
//    CardView data3;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    SweetAlertDialog sweetAlertDialog;
    String value;
    String idSpinner;
    private LinkedHashMap<String, String> mt;
    private LinkedHashMapAdapter<String, String> adapterSpinner;
    MateriPresenter presenter ;
    MateriAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);
        ButterKnife.bind(this);
        presenter = new MateriPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Materi");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mt = new LinkedHashMap<String, String>();
        mt.put("1", "Semua Materi");
        mt.put("2", "Video");

        adapterSpinner = new LinkedHashMapAdapter<String, String>(this, R.layout.spinnermt, mt);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mMt.setAdapter(adapterSpinner);
        mMt.setOnItemSelectedListener(MateriActivity.this);
//        data1.setOnClickListener(view ->this.gotoContentMateri());
        this.initViews();
        presenter.showMateri();
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

    void gotoContentMateri() {
        startActivity(new Intent(MateriActivity.this, ContentMateriActivity.class));
        finish();
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
        this.goToDashboard();
    }
    @Override
    public void goToDashboard() {
        Intent a = new Intent(this, DashboardActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.mMt:
                Map.Entry<String, String> itemMt = (Map.Entry<String, String>) mMt.getSelectedItem();
                idSpinner = itemMt.getKey();
                value = itemMt.getValue();
//                if (!idSpinner.equals("1")) {
//                    data1.setVisibility(View.GONE);
//                    data2.setVisibility(View.GONE);
//                } else {
//                    data1.setVisibility(View.VISIBLE);
//                    data2.setVisibility(View.VISIBLE);
//                    data3.setVisibility(View.VISIBLE);
//                }

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
    public void onDataReady(List<Materi> result) {

        adapter = new MateriAdapter(result, this, this);
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
    public void onClick(Materi data) {
        Intent i = new Intent(this, ContentMateriActivity.class);
        i.putExtra("data", (Serializable) data);
        startActivity(i);
        finish();
    }
}

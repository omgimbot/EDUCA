package omgimbot.app.lsmapps.features.dashboard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.mindorks.placeholderview.PlaceHolderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import omgimbot.app.lsmapps.App;
import omgimbot.app.lsmapps.Utils.AdapterSliderBanner;
import omgimbot.app.lsmapps.Utils.GlideApp;
import omgimbot.app.lsmapps.Utils.ModelSliderBanner;
import omgimbot.app.lsmapps.features.materi.MateriActivity;
import omgimbot.app.lsmapps.features.nilai.NilaiActivity;
import omgimbot.app.lsmapps.features.ujian.UjianActivity;
import omgimbot.app.lsmapps.ui.DrawerHeader;
import omgimbot.app.lsmapps.ui.DrawerMenuItem;
import omgimbot.app.sidangapps.R;

public class DashboardActivity extends AppCompatActivity {
    @BindView(R.id.drawerView)
    PlaceHolderView mDrawerView;
    @BindView(R.id.mainMenu)
    ImageButton mainMenuDashboard;
    @BindView(R.id.toolbar)
    Toolbar toolbarMain;
    @BindView(R.id.imgtest)
    ImageView imgtest;

    private DrawerLayout drawer;
    private ActionBarDrawerToggle mTogle;
    ImageView banners;
    ViewPager viewPager;
    LinearLayout indicatorDot;
    AdapterSliderBanner adapter;
    List<ModelSliderBanner> models;
    private int dotsCount;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        this.initViews();


    }


    public void initViews(){
        this.setupDrawer();
        drawer = findViewById(R.id.dashboard);
        mTogle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.setDrawerListener(mTogle);
        mTogle.syncState();
        ((AppCompatActivity)this).setSupportActionBar(toolbarMain);
        ((AppCompatActivity)this).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
    private void setupDrawer() {
        mDrawerView
                .addView(new DrawerHeader(this))
//                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_PROFILE))
//                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_ABOUT))
//                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_RESETPASSWORD))
                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_LOGOUT));

        mainMenuDashboard.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                if (!drawer.isDrawerOpen(Gravity.END)) drawer.openDrawer(Gravity.END);
                else drawer.closeDrawer(Gravity.START);
            }
        });
    }

    @OnClick(R.id.mCardMateri)
    void gotoMateri() {
        startActivity(new Intent(this , MateriActivity.class));
        finish();
    }

    @OnClick(R.id.mCardUjian)
    void gotoUjian() {
        startActivity(new Intent(this , UjianActivity.class));
        finish();
    }

    @OnClick(R.id.mCardNilai)
    void gotoNilai() {
        startActivity(new Intent(this , NilaiActivity.class));
        finish();
    }

}

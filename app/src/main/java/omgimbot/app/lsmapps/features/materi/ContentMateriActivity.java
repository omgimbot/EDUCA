package omgimbot.app.lsmapps.features.materi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import omgimbot.app.lsmapps.App;
import omgimbot.app.lsmapps.features.dashboard.DashboardActivity;
import omgimbot.app.lsmapps.features.materi.model.Materi;
import omgimbot.app.sidangapps.R;

public class ContentMateriActivity extends AppCompatActivity {
 @BindView(R.id.text_view)
    TextView textView ;
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    Bundle bundle;
    Materi data ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_materi);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Materi");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = this.getIntent();
        bundle = intent.getExtras();
        if (bundle != null) {

                data = (Materi) getIntent().getExtras().getSerializable("data");

        }
        textView.setText(Html.fromHtml(data.getContent()));
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
    public void goToDashboard() {
        Intent a = new Intent(this, MateriActivity.class);
        startActivity(a);
        finish();
    }
}

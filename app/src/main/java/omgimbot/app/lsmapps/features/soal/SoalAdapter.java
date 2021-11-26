package omgimbot.app.lsmapps.features.soal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.CompoundButtonCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import org.sufficientlysecure.htmltextview.HtmlAssetsImageGetter;
import org.sufficientlysecure.htmltextview.HtmlFormatter;
import org.sufficientlysecure.htmltextview.HtmlFormatterBuilder;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlResImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
;
import omgimbot.app.lsmapps.App;
import omgimbot.app.lsmapps.Utils.GlideApp;
import omgimbot.app.lsmapps.Utils.Utils;
import omgimbot.app.lsmapps.features.materi.model.Materi;
import omgimbot.app.lsmapps.features.soal.model.Soal;
import omgimbot.app.lsmapps.features.ujian.UjianAdapter;
import omgimbot.app.lsmapps.features.ujian.model.Ujian;
import omgimbot.app.sidangapps.R;

import static omgimbot.app.lsmapps.App.TAG;
import static omgimbot.app.lsmapps.App.getContext;


public class SoalAdapter extends RecyclerView.Adapter<SoalAdapter.ViewHolder> {
    public List<Soal> ruts;
    Activity context;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    CharSequence t;
    String imageUrl;
    private SoalAdapter.onSelected listener;
    CheckBox boxA, boxB, boxC, boxD;
    Spanned soal;

    boolean selesaiSubmit;

    public SoalAdapter(List<Soal> data, Activity context, SoalAdapter.onSelected listener) {
        this.ruts = data;
        this.context = context;
        this.listener = listener;
    }

    public interface onSelected {
        void onNilai(Soal data);
    }

    @Override
    public SoalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_soal, parent, false);
        SoalAdapter.ViewHolder viewHolder = new SoalAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final SoalAdapter.ViewHolder holder, final int position) {
        final Soal data = ruts.get(position);
        imageUrl = null;
        data.setSelected(false);
//        data.setKoreksi(false);
        if(selesaiSubmit){
            if (data.isKoreksi() == false) {
                holder.layoutKoreksi.setVisibility(View.VISIBLE);
                holder.mJawabanBenar.setText("Jawaban Benar : "+data.getPilihan_benar());
                holder.mImgKoreksi.setImageResource(R.drawable.salah);
            } else{
                holder.layoutKoreksi.setVisibility(View.VISIBLE);
                holder.mImgKoreksi.setImageResource(R.drawable.bener);
                holder.mJawabanBenar.setVisibility(View.GONE);
            }
        }
        int nomor = position + 1;
        holder.mNo.setText(String.valueOf(nomor));
        holder.mBobot.setText("BOBOT SOAL " + data.getBobot_soal());
//        holder.mSoal.setText(Html.fromHtml(data.getSoal()));
//        holder.mSoal.setHtml(String.valueOf(Html.fromHtml(data.getSoal())),
//                new HtmlAssetsImageGetter(holder.mSoal));
//        Spanned formattedHtml = HtmlFormatter.formatHtml(new HtmlFormatterBuilder().setHtml(data.getSoal()).setImageGetter(new HtmlHttpImageGetter(holder.mSoal)));
//        holder.mSoal.setText(Html.fromHtml(data.getSoal()));
//        soal = Html.fromHtml(data.getSoal());
        holder.mSoal.setText(Html.fromHtml(data.getSoal(), new ImageGetter(), null));
//        Toast.makeText(context, "zzzz", Toast.LENGTH_SHORT).show();
        if (imageUrl != null) {
            holder.icon_image.setVisibility(View.VISIBLE);
//            Log.d("Drawnya" , "http://dsakdjksakdsa") ;
            Glide.with(context)
                    .load(Uri.parse(imageUrl))
//                      .load(App.getApplication().RestService.)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Log.d("errornya", e.getMessage());
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .thumbnail(0.1f)
                    .apply(new RequestOptions().placeholder(R.drawable.loading_ios))
                    .diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true)
                    .into(holder.icon_image);
        } else {
            holder.icon_image.setVisibility(View.GONE);
        }
        holder.mPilihanA.setText(data.getPilihan_a());
        holder.mPilihanB.setText(data.getPilihan_b());
        holder.mPilihanC.setText(data.getPilihan_c());
        holder.mPilihanD.setText(data.getPilihan_d());


        Glide.with(context)
                .load(Uri.parse(App.getApplication().getResources().getString(R.string.img_end_point) + data.getPilihan_gambar_a()))
                .thumbnail(0.1f)
                .apply(new RequestOptions().placeholder(R.drawable.loading_ios))
                .diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true)
                .into(holder.mImgA);

        Glide.with(context)
                .load(Uri.parse(App.getApplication().getResources().getString(R.string.img_end_point) + data.getPilihan_gambar_b()))
                .thumbnail(0.1f)
                .apply(new RequestOptions().placeholder(R.drawable.loading_ios))
                .diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true)
                .into(holder.mImgB);

        Glide.with(context)
                .load(Uri.parse(App.getApplication().getResources().getString(R.string.img_end_point) + data.getPilihan_gambar_c()))
                .thumbnail(0.1f)
                .apply(new RequestOptions().placeholder(R.drawable.loading_ios))
                .diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true)
                .into(holder.mImgC);

        Glide.with(context)
                .load(Uri.parse(App.getApplication().getResources().getString(R.string.img_end_point) + data.getPilihan_gambar_d()))
                .thumbnail(0.1f)
                .apply(new RequestOptions().placeholder(R.drawable.loading_ios))
                .diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true)
                .into(holder.mImgD);


        holder.mCheckboxA.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        holder.mCheckboxB.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        holder.mCheckboxC.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        holder.mCheckboxD.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        holder.mCheckboxA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    cart.setSelected(true);
//
//                } else {
//                    cart.setSelected(false);
//                }
//                cart.setJumlahBeli(Integer.parseInt(holder.mQty.getNumber()));
//                listener.onCheckbox();

                if (isChecked) {
                    data.setSelected(true);
                    holder.mCheckboxA.setChecked(true);
                    holder.mCheckboxB.setChecked(false);
                    holder.mCheckboxC.setChecked(false);
                    holder.mCheckboxD.setChecked(false);
                    data.setDipilih("A");
                    if (data.getDipilih().equals(data.getPilihan_benar())) {

//                        nilai =+ Integer.parseInt(data.getBobot_soal()) ;
                        data.setNilai(data.getBobot_soal());
                        data.setKoreksi(true);


                    } else {
                        data.setNilai("0");
                        data.setKoreksi(true);
                    }
                    listener.onNilai(data);

                }
            }
        });

        holder.mCheckboxB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    data.setSelected(true);
                    holder.mCheckboxA.setChecked(false);
                    holder.mCheckboxB.setChecked(true);
                    holder.mCheckboxC.setChecked(false);
                    holder.mCheckboxD.setChecked(false);
                    data.setDipilih("B");
                    if (data.getDipilih().equals(data.getPilihan_benar())) {

//                        nilai =+ Integer.parseInt(data.getBobot_soal()) ;
                        data.setNilai(data.getBobot_soal());
                        data.setKoreksi(true);

                    } else {
                        data.setNilai("0");
                        data.setKoreksi(false);
                    }
                    listener.onNilai(data);

                }
            }
        });

        holder.mCheckboxC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    data.setSelected(true);
                    holder.mCheckboxA.setChecked(false);
                    holder.mCheckboxB.setChecked(false);
                    holder.mCheckboxC.setChecked(true);
                    holder.mCheckboxD.setChecked(false);
                    data.setDipilih("C");
                    if (data.getDipilih().equals(data.getPilihan_benar())) {

//                        nilai =+ Integer.parseInt(data.getBobot_soal()) ;
                        data.setNilai(data.getBobot_soal());
                        data.setKoreksi(true);

                    } else {
                        data.setNilai("0");
                        data.setKoreksi(false);
                    }
                    listener.onNilai(data);

                }
            }
        });

        holder.mCheckboxD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    data.setSelected(true);
                    holder.mCheckboxA.setChecked(false);
                    holder.mCheckboxB.setChecked(false);
                    holder.mCheckboxC.setChecked(false);
                    holder.mCheckboxD.setChecked(true);
                    data.setDipilih("D");
                    if (data.getDipilih().equals(data.getPilihan_benar())) {

//                        nilai =+ Integer.parseInt(data.getBobot_soal()) ;
                        data.setNilai(data.getBobot_soal());
                        data.setKoreksi(true);

                    } else {
                        data.setNilai("0");
                        data.setKoreksi(false);
                    }
                    listener.onNilai(data);

                }
            }
        });

        holder.mNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nilai = 0;
                if (data.getDipilih() != null && data.getDipilih().length() > 0) {
                    if (data.getDipilih().equals(data.getPilihan_benar())) {

                        nilai = +Integer.parseInt(data.getBobot_soal());
                        data.setNilai(data.getBobot_soal());
                        Toast.makeText(context, String.valueOf(nilai), Toast.LENGTH_SHORT).show();

                    } else
                        Toast.makeText(context, "salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        boxA = holder.mCheckboxA;
//        boxB = holder.mCheckboxB;
//        boxC = holder.mCheckboxC;
//        boxD = holder.mCheckboxD;
//        CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
////                if (isChecked) {
////                    cart.setSelected(true);
////
////                } else {
////                    cart.setSelected(false);
////                }
////                cart.setJumlahBeli(Integer.parseInt(holder.mQty.getNumber()));
////                listener.onCheckbox();
//
//                switch (buttonView.getId()){
//                    case R.id.mCheckboxA:
//                        if (isChecked) {
//                            holder.mCheckboxA.setChecked(true);
//                            holder.mCheckboxB.setChecked(false);
//                            holder.mCheckboxC.setChecked(false);
//                            holder.mCheckboxD.setChecked(false);
//
//                        }
//                        break;
//
//                    case R.id.mCheckboxB:
//                        if (isChecked) {
//                            holder.mCheckboxA.setChecked(false);
//                            holder.mCheckboxB.setChecked(true);
//                            holder.mCheckboxC.setChecked(false);
//                            holder.mCheckboxD.setChecked(false);
//
//                        }
//                        break;
//
//                    case R.id.mCheckboxC:
//                        if (isChecked) {
//                            holder.mCheckboxA.setChecked(false);
//                            holder.mCheckboxB.setChecked(false);
//                            holder.mCheckboxC.setChecked(true);
//                            holder.mCheckboxD.setChecked(false);
//
//                        }
//                        break;
//
//                    case R.id.mCheckboxD:
//                        if (isChecked) {
//                            holder.mCheckboxA.setChecked(false);
//                            holder.mCheckboxB.setChecked(false);
//                            holder.mCheckboxC.setChecked(false);
//                            holder.mCheckboxD.setChecked(true);
//
//                        }
//                        break;
//                }
//            }
//        });
//        CheckBox.OnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//            }
//        });
//        CheckBox.OnCheckedChangeListener onCheckedChangeListener
//                = new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                switch (buttonView.getId()) {
//                    case R.id.mCheckboxA:
//                        if (isChecked) {
//                            holder.mCheckboxA.setChecked(true);
//                            holder.mCheckboxB.setChecked(false);
//                            holder.mCheckboxC.setChecked(false);
//                            holder.mCheckboxD.setChecked(false);
//
//                        }
//                        break;
//
//                    case R.id.mCheckboxB:
//                        if (isChecked) {
//                            holder.mCheckboxA.setChecked(false);
//                            holder.mCheckboxB.setChecked(true);
//                            holder.mCheckboxC.setChecked(false);
//                            holder.mCheckboxD.setChecked(false);
//
//                        }
//                        break;
//
//                    case R.id.mCheckboxC:
//                        if (isChecked) {
//                            holder.mCheckboxA.setChecked(false);
//                            holder.mCheckboxB.setChecked(false);
//                            holder.mCheckboxC.setChecked(true);
//                            holder.mCheckboxD.setChecked(false);
//
//                        }
//                        break;
//
//                    case R.id.mCheckboxD:
//                        if (isChecked) {
//                            holder.mCheckboxA.setChecked(false);
//                            holder.mCheckboxB.setChecked(false);
//                            holder.mCheckboxC.setChecked(false);
//                            holder.mCheckboxD.setChecked(true);
//
//                        }
//                        break;
//                }
//            }
//        };

    }

    public void swap(List<Soal> datas) {

        if (datas == null || datas.size() == 0)
            return;
        if (ruts != null && ruts.size() > 0)
            ruts.clear();
        ruts.addAll(datas);
        notifyDataSetChanged();
        selesaiSubmit = true ;

    }


    private class ImageGetter implements Html.ImageGetter {

        public Drawable getDrawable(String source) {
            int id;
//            Log.d("Drawnya" , source) ;
            imageUrl = source;
            id = context.getResources().getIdentifier(source, "drawable", context.getPackageName());

            if (id == 0) {
                // the drawable resource wasn't found in our package, maybe it is a stock android drawable?
                id = context.getResources().getIdentifier(source, "drawable", "android");
            }

            if (id == 0) {
                // prevent a crash if the resource still can't be found
                return null;
            } else {
                Drawable d = context.getResources().getDrawable(id);
                d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
                return d;
            }
        }

    }


    @Override
    public int getItemCount() {
        if (ruts == null)
            return 0;
        else
            return ruts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout onClick;
        TextView mSoal, mDeskripsi, mTime, mPilihanA, mPilihanB, mPilihanC, mPilihanD, mNo, mBobot,mJawabanBenar;
        ImageView icon_image, mImgA, mImgB, mImgC, mImgD,mImgKoreksi;
        CheckBox mCheckboxA, mCheckboxB, mCheckboxC, mCheckboxD;
        LinearLayout layoutKoreksi ;

        ViewHolder(View view) {
            super(view);
//            onClick = view.findViewById(R.id.main_layout);
            mSoal = view.findViewById(R.id.mSoal);
            mImgA = view.findViewById(R.id.mImgA);
            mImgB = view.findViewById(R.id.mImgB);
            mImgC = view.findViewById(R.id.mImgC);
            mImgD = view.findViewById(R.id.mImgD);
            mNo = view.findViewById(R.id.mNo);
            mBobot = view.findViewById(R.id.mBobot);
            mPilihanA = view.findViewById(R.id.mPilihanA);
            mPilihanB = view.findViewById(R.id.mPilihanB);
            mPilihanC = view.findViewById(R.id.mPilihanC);
            mPilihanD = view.findViewById(R.id.mPilihanD);
            icon_image = view.findViewById(R.id.icon_image);
            mCheckboxA = view.findViewById(R.id.mCheckboxA);
            mCheckboxB = view.findViewById(R.id.mCheckboxB);
            mCheckboxC = view.findViewById(R.id.mCheckboxC);
            mCheckboxD = view.findViewById(R.id.mCheckboxD);
            layoutKoreksi = view.findViewById(R.id.layoutKoreksi);
            mJawabanBenar = view.findViewById(R.id.mJawabanBenar);
            mImgKoreksi = view.findViewById(R.id.mImgKoreksi);


        }

    }


}

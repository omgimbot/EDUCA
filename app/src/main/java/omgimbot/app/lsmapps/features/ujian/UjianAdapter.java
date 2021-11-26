package omgimbot.app.lsmapps.features.ujian;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import omgimbot.app.lsmapps.Utils.Utils;
import omgimbot.app.lsmapps.features.ujian.model.Ujian;
import omgimbot.app.sidangapps.R;


public class UjianAdapter extends RecyclerView.Adapter<UjianAdapter.ViewHolder> {
    public List<Ujian> ruts;
    Activity context;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    private UjianAdapter.onSelected listener;

    public interface onSelected {
        void onDetail(Ujian data);
    }


    public UjianAdapter(List<Ujian> data, Activity context, UjianAdapter.onSelected listener) {
        this.ruts = data;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public UjianAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ujian, parent, false);
        UjianAdapter.ViewHolder viewHolder = new UjianAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final UjianAdapter.ViewHolder holder, final int position) {
        final Ujian data = ruts.get(position);

        holder.mJudul.setText(data.getJudul_ujian());
        holder.mPengajar.setText(data.getPengajar());
        if (data.getDeskripsi().length() > 0)
            holder.mDeskripsi.setText(data.getDeskripsi());
        else
            holder.mDeskripsi.setText("-");

//        holder.mTime.setText(Utils.convertMongoDateWithoutTIme(data.getCreate_at()));


        holder.mDetail.setOnClickListener(View -> listener.onDetail(data));

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
        TextView mJudul, mDeskripsi, mTime,mPengajar;
        ImageView icon_image;
        Button mDetail;


        ViewHolder(View view) {
            super(view);
//            onClick = view.findViewById(R.id.main_layout);
            mJudul = view.findViewById(R.id.mJudul);
            mDeskripsi = view.findViewById(R.id.mDeskripsi);
            mDetail = view.findViewById(R.id.mDetail);
            mPengajar = view.findViewById(R.id.mPengajar);
//            mTime = view.findViewById(R.id.mTime);
            icon_image = view.findViewById(R.id.icon_image);

        }

    }


}

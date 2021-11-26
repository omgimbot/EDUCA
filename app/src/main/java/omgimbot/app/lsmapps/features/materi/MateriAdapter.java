package omgimbot.app.lsmapps.features.materi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import omgimbot.app.lsmapps.Utils.Utils;
import omgimbot.app.lsmapps.features.materi.model.Materi;
import omgimbot.app.sidangapps.R;


public class MateriAdapter extends RecyclerView.Adapter<MateriAdapter.ViewHolder> {
    public List<Materi> ruts;
    Activity context;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    private MateriAdapter.onSelected listener;

    public interface onSelected {
        void onClick(Materi data);
    }


    public MateriAdapter(List<Materi> data, Activity context, MateriAdapter.onSelected listener) {
        this.ruts = data;
        this.context = context;
        this.listener = listener ;
    }

    @Override
    public MateriAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_materi, parent, false);
        MateriAdapter.ViewHolder viewHolder = new MateriAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final MateriAdapter.ViewHolder holder, final int position) {
        final Materi data = ruts.get(position);

        holder.mJudul.setText(data.getJudul_materi());
        holder.mDeskripsi.setText(data.getDeskripsi_materi());
        holder.mTime.setText(Utils.convertMongoDateWithoutTIme(data.getCreate_at()));


        holder.onClick.setOnClickListener(View -> listener.onClick(data));

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
        TextView mJudul,mDeskripsi, mTime;
        ImageView icon_image;


        ViewHolder(View view) {
            super(view);
            onClick = view.findViewById(R.id.main_layout);
            mJudul = view.findViewById(R.id.mJudul);
            mDeskripsi = view.findViewById(R.id.mDeskripsi);
            mTime = view.findViewById(R.id.mTime);
            icon_image = view.findViewById(R.id.icon_image);

        }

    }


}

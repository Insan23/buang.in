package id.ac.unej.ilkom.ods.buangin.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.ac.unej.ilkom.ods.buangin.Model.v_daftarSampah_model;
import id.ac.unej.ilkom.ods.buangin.R;

public class v_daftarSampah_adapter extends RecyclerView.Adapter<v_daftarSampah_adapter.MyViewHolder> {

    private Context context;
    private List<v_daftarSampah_model> modelList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_volunteer_daftar_sampah, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        v_daftarSampah_model model = modelList.get(position);
        holder.tanggal.setText(model.getTanggal());
        holder.waktu.setText(model.getWaktu());
        holder.status.setText(model.getStatus());
        holder.gambar.setImageURI(Uri.parse(model.getGambar()));
    }

    @Override
    public int getItemCount() {
        return modelList.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tanggal, waktu, status;
        public ImageView gambar;

        public MyViewHolder(View itemView) {
            super(itemView);
            tanggal = (TextView) itemView.findViewById(R.id.volunteer_tanggal_daftarSampah);
            waktu = (TextView) itemView.findViewById(R.id.volunteer_waktu_daftarSampah);
            status = (TextView) itemView.findViewById(R.id.volunteer_status_daftarSampah);
            gambar = (ImageView) itemView.findViewById(R.id.volunteer_gambar_daftarSampah);
        }
    }

    public v_daftarSampah_adapter(Context context, List<v_daftarSampah_model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }
}
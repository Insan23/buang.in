package id.ac.unej.ilkom.ods.buangin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.model.v_daftarBank_model;

public class v_daftarBank_adapter extends RecyclerView.Adapter<v_daftarBank_adapter.MyViewHolder> {
    private Context context;
    private List<v_daftarBank_model> modelList;

    public v_daftarBank_adapter(Context context, List<v_daftarBank_model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_volunteer_daftarbank, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        v_daftarBank_model model = modelList.get(position);
        holder.namaBank.setText(model.getNamaBank());
        holder.pemilik.setText(model.getPemilik());
        holder.alamat.setText(model.getAlamat());
        holder.gambar.setImageResource(model.getGambar());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView namaBank, pemilik, alamat;
        private ImageView gambar;

        public MyViewHolder(View itemView) {
            super(itemView);
            namaBank = (TextView) itemView.findViewById(R.id.volunteer_namaBank_daftarBank);
            pemilik = (TextView) itemView.findViewById(R.id.volunteer_pemilik_daftarBank);
            alamat = (TextView) itemView.findViewById(R.id.volunteer_alamat_daftarBank);
            gambar = (ImageView) itemView.findViewById(R.id.volunteer_gambar_daftarBank);
        }
    }
}

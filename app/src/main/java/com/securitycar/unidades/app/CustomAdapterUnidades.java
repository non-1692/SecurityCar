package com.securitycar.unidades.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.securitycar.R;
import com.securitycar.app.MydataUnidades;
import com.securitycar.seguimiento.Seguimiento;

import java.util.ArrayList;

/**
 * Created by noe on 21/11/17.
 */

public class CustomAdapterUnidades extends RecyclerView.Adapter<CustomAdapterUnidades.ViewHolder> {
    private Context context;
    private ArrayList<MydataUnidades> my_data;

    public CustomAdapterUnidades(Context context, ArrayList<MydataUnidades> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_unidades, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final MydataUnidades item = my_data.get(position);


        holder.tvEco.setText(my_data.get(position).getEco());
        holder.tvPlacas.setText(""+item.getPlacas());
        holder.tvFecha.setText(my_data.get(position).getFecha());
        holder.tvHora.setText(my_data.get(position).getHora());
        holder.tvDir.setText(""+item.getDir());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("onBindViewHolderMain", "lat: " + item.getLatitud());
                Log.d("onBindViewHolderMain", "lng: " + item.getLongitud());

                Intent myIntent = new Intent(context, Seguimiento.class);
                myIntent.putExtra("item", (Parcelable) item);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //android.support.v4.util.Pair<View, String> p1 = android.support.v4.util.Pair.create(imvShare, StringShare);
                    //ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, p1);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context);
                    context.startActivity(myIntent, options.toBundle());
                } else {
                    context.startActivity(myIntent);
                }

            }
        });

        if (item.getStatusMotor().equals(context.getString(R.string.motor_enc))) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.imvStatus.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorStatusS));
            }else {
                //holder.imvStatus.setBackground(context.getResources().getDrawable(R.drawable.lyt_circle_rojo));
            }
        }else if (item.getStatusMotor().equals(context.getString(R.string.motor_apa))){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.imvStatus.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorStatusF));
            }else {
             ////holder.imvStatus.setBackground(context.getResources().getDrawable(R.drawable.lyt_circle_rojo));
            }
        }
        else if (item.getStatusMotor().equals(context.getString(R.string.sin_status))){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.imvStatus.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorAccent));
            }else {
                //imvStatus.setBackground(R);
            }
        }
    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout imvStatus;
        public TextView tvEco, tvPlacas, tvFecha, tvHora, tvDir;


        public ViewHolder(final View itemView) {
            super(itemView);

            imvStatus = (LinearLayout) itemView.findViewById(R.id.imvStatus);
            tvEco = (TextView) itemView.findViewById(R.id.tvEco);
            tvPlacas = (TextView) itemView.findViewById(R.id.tvPlacas);
            tvFecha = (TextView) itemView.findViewById(R.id.tvFecha);
            tvHora = (TextView) itemView.findViewById(R.id.tvHora);
            tvDir = (TextView) itemView.findViewById(R.id.tvDir);
        }
    }

    public void setFilter(ArrayList<MydataUnidades> my_datas) {
        my_data = new ArrayList<>();
        my_data.addAll(my_datas);
        notifyDataSetChanged();
    }


}

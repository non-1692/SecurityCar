package com.securitycar.tikets.app;

import android.content.Context;
import android.os.Parcelable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.securitycar.R;
import com.securitycar.tikets.AddTicktes;

import java.util.ArrayList;

/**
 * Created by noe on 21/11/17.
 */

public class CustomAdapterTickets extends RecyclerView.Adapter<CustomAdapterTickets.ViewHolder> {
    private Context context;
    private ArrayList<MydataTicket> my_data;

    public CustomAdapterTickets(Context context, ArrayList<MydataTicket> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_tick, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final MydataTicket item = my_data.get(position);

        holder.tvNomTick.setText(item.getNombre());
        holder.tvDes.setText(item.getDes());
        holder.imvImage.setImageResource(item.getImagen());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //BottomSheetDialogFragment bottomSheetDialogFragment = new AddTicktes(context, my_data.get(position));
                BottomSheetDialogFragment bottomSheetDialogFragment = new AddTicktes(context, item);
                bottomSheetDialogFragment.setCancelable(true);
                bottomSheetDialogFragment.show(((FragmentActivity) context).getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
                //showAddTick(item);
            }
        });
    }

/*    private void showAddTick(MydataTicket item) {
        BottomSheetDialogFragment bottomSheetDialogFragment = new AddTicktes(context,
                item);
        bottomSheetDialogFragment.setCancelable(true);
        bottomSheetDialogFragment.show(((FragmentActivity) context).getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
    }*/

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNomTick, tvDes;
        public ImageView imvImage;

        public ViewHolder(final View itemView) {
            super(itemView);

            imvImage = (ImageView) itemView.findViewById(R.id.imvImage);
            tvNomTick = (TextView) itemView.findViewById(R.id.tvNomTick);
            tvDes = (TextView) itemView.findViewById(R.id.tvDes);

        }
    }

    public void setFilter(ArrayList<MydataTicket> my_datas) {
        my_data = new ArrayList<>();
        my_data.addAll(my_datas);
        notifyDataSetChanged();
    }


}

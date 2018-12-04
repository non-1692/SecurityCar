package com.securitycar.tikets;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.securitycar.R;
import com.securitycar.tikets.app.CustomAdapterTickets;
import com.securitycar.tikets.app.MydataTicket;
import com.securitycar.unidades.app.CustomAdapterUnidades;
import com.vipulasri.ticketview.TicketView;

import java.util.ArrayList;

public class TiketsFragment extends Fragment {

    private View rootView;
    private RecyclerView rvTickets;
    private GridLayoutManager gLMTicktes;
    private CustomAdapterTickets adapterTickets;
    private ArrayList<MydataTicket> listTickets;

    private TicketView ticketView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tikets, container, false);


        //ticketView = (TicketView) rootView.findViewById(R.id.ticketView);

        rvTickets = (RecyclerView)rootView.findViewById(R.id.rvTickets);

        listTickets = new ArrayList<>();
        gLMTicktes = new GridLayoutManager(rootView.getContext(),1);
        rvTickets.setLayoutManager(gLMTicktes);
        adapterTickets = new CustomAdapterTickets(rootView.getContext(),listTickets);
        rvTickets.setAdapter(adapterTickets);

        loadTickets();



        //ticketView.setOrientation();

        return rootView;
    }

    private void loadTickets() {

        listTickets.add(new MydataTicket(1, "Nueva instalación", "Solicita una nueva instalación", "instalacion", R.drawable.ic_instalacion));
        listTickets.add(new MydataTicket(2, "Revisión de unidad ", "Solicita una revisión", "revision", R.drawable.ic_revicion));
        listTickets.add(new MydataTicket(3, "Otro ", "Si necesita algo más especifico", "otro", R.drawable.ic_logo));

        adapterTickets.notifyDataSetChanged();

    }
}

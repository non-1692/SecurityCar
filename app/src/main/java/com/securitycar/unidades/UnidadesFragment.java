package com.securitycar.unidades;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.securitycar.R;
import com.securitycar.app.MydataUnidades;
import com.securitycar.unidades.app.CustomAdapterUnidades;

import java.util.ArrayList;

public class UnidadesFragment extends Fragment {

    private View rootView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvUnidades;
    private ProgressBar progressBar;
    private GridLayoutManager gLMUnidades;
    private CustomAdapterUnidades adapterUnidades;
    private ArrayList<MydataUnidades> listUnidades;


    private RelativeLayout lytAlert;
    private ImageView imvAlert;
    private TextView tvAlert, tvAlertExtra;

    private TabLayout tabLayout;
    private String totalUnidades = "4 Unidades", on = "2 Online", off = "1 Offline";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_unidades, container, false);

        rvUnidades = (RecyclerView)rootView.findViewById(R.id.rvUnidades);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        lytAlert = (RelativeLayout) rootView.findViewById(R.id.lytAlert);
        imvAlert = (ImageView) rootView.findViewById(R.id.imvAlert);
        tvAlert = (TextView) rootView.findViewById(R.id.tvAlert);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);

        listUnidades = new ArrayList<>();
        gLMUnidades = new GridLayoutManager(rootView.getContext(),1);
        rvUnidades.setLayoutManager(gLMUnidades);
        adapterUnidades = new CustomAdapterUnidades(rootView.getContext(),listUnidades);
        rvUnidades.setAdapter(adapterUnidades);
        //rvUnidades.setHasFixedSize(true);

        loadUnidades();
        initTabLayout();
        progressBar.setVisibility(View.GONE);

        return rootView;
    }

    private void initTabLayout() {
      /*  tabLayout.addTab(tabLayout.newTab().setText(totalUnidades).setIcon(iconVeiculo));
        tabLayout.addTab(tabLayout.newTab().setText(on).setIcon(iconSuccess));
        tabLayout.addTab(tabLayout.newTab().setText(off).setIcon(iconFail));*/
        tabLayout.addTab(tabLayout.newTab().setText(totalUnidades));
        tabLayout.addTab(tabLayout.newTab().setText(on));
        tabLayout.addTab(tabLayout.newTab().setText(off));
        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    final ArrayList<MydataUnidades> filteredModelList = filter(listUnidades, "", 1);
                    adapterUnidades.setFilter(filteredModelList);
                }
                if (tab.getPosition() == 1){
                    final ArrayList<MydataUnidades> filteredModelList = filter(listUnidades, getString(R.string.motor_enc), 1);
                    adapterUnidades.setFilter(filteredModelList);
                }
                if (tab.getPosition() == 2){
                    final ArrayList<MydataUnidades> filteredModelList = filter(listUnidades, getString(R.string.motor_apa), 1);
                    adapterUnidades.setFilter(filteredModelList);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private ArrayList<MydataUnidades> filter(ArrayList<MydataUnidades> models, String query, int search) {
        query = query.toLowerCase();final ArrayList<MydataUnidades> filteredModelList = new ArrayList<>();

        if(search == 0){
            for (MydataUnidades model : models) {
                final String Eco = model.getEco().toLowerCase();
                final String Placas = model.getPlacas().toLowerCase();
                final String Vel = model.getId().toLowerCase();
                if (Eco.contains(query) || Placas.contains(query) | Placas.contains(query) | Vel.contains(query)) {
                    filteredModelList.add(model);
                }
            }
            return filteredModelList;
        }
        if (search == 1){
            for (MydataUnidades model : models) {
                final String Status = model.getStatusMotor().toLowerCase();
                if (Status.contains(query)) {
                    filteredModelList.add(model);
                }
            }
            return filteredModelList;
        }
        return filteredModelList;
    }

    private void loadUnidades() {

        listUnidades.add(new MydataUnidades("01", "Unidad 11", "444SDD", "21 de Novimebre 2018", "12:10:00", "", "Motor Encendido", "",
                19.022085, -98.214309, "10", "Av las Forjadores 55 Puebla Puebla, Mex.", null));

        listUnidades.add(new MydataUnidades("02", "Unidad 01", "FGSRF789", "22 de Novimebre 2018", "04:10:00", "", "Motor Apagado", "",
                19.067117, -98.329049, "100", "Av las Margaritas 5485 Puebla Puebla, Mex.", null));

        listUnidades.add(new MydataUnidades("01", "Unidad 06", "ssdddf", "20 de Novimebre 2018", "02:10:00", "", "Motor Encendido", "",
                19.022085, -98.214309, "60", "Av las Animas 396 Tlax, Tlaxcala, Mex.", null));

        listUnidades.add(new MydataUnidades("01", "Unidad 13", "SDJDF5F8 S", "30 de Octubre 2018", "16:10:00", "", "Indeterminado", "",
                19.022085, -98.214309, "77", "No disponible.", null));

        adapterUnidades.notifyDataSetChanged();

    }

}

package com.securitycar.tikets;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.securitycar.MainActivity;
import com.securitycar.R;
import com.securitycar.app.AppConfig;
import com.securitycar.app.MydataUnidades;
import com.securitycar.email.GMailSender;
import com.securitycar.tikets.app.MydataTicket;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.tapadoo.alerter.Alert;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class AddTicktes extends BottomSheetDialogFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private final MydataTicket item;
    private View contentView;
    private Context context;
    private TextView tvTitulo;

    private String nombre;
    private LinearLayout add, revision, otro;
    private Button btnCancel, btnEnviar;
    private Spinner spTipoVehiculo, spUnidad;
    private EditText inputComent;

    private ArrayList<MydataUnidades> listUnidades;

    private String tipoUnidad = "";

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };

    public AddTicktes(Context context, MydataTicket item) {
        this.context = context;
        this.item = item;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        contentView = View.inflate(getContext(), R.layout.add_ticktes, null);
        dialog.setContentView(contentView);

        listUnidades = new ArrayList<>();

        add = contentView.findViewById(R.id.add);
        revision = contentView.findViewById(R.id.revision);
        otro = contentView.findViewById(R.id.otro);

        btnCancel = contentView.findViewById(R.id.btnCancel);
        btnEnviar = contentView.findViewById(R.id.btnEnviar);

        inputComent = contentView.findViewById(R.id.inputComent);

        spTipoVehiculo = contentView.findViewById(R.id.spTipoVehiculo);
        spUnidad = contentView.findViewById(R.id.spUnidad);

        Log.d("AddTicktes", "Tipo" + item.getTipo());

        loadunidades();

        switch (item.getId()){
            case 1:
                add.setVisibility(View.VISIBLE);
                revision.setVisibility(View.GONE);
                otro.setVisibility(View.GONE);
                initSpinnerTipo();
                break;
            case 2:
                add.setVisibility(View.GONE);
                revision.setVisibility(View.VISIBLE);
                otro.setVisibility(View.GONE);
                initSpinnerUnidades();
                break;
            case 3:
                add.setVisibility(View.GONE);
                revision.setVisibility(View.GONE);
                otro.setVisibility(View.VISIBLE);
                break;
        }

        tvTitulo = (TextView) contentView.findViewById(R.id.tvTitulo);
        tvTitulo.setText(item.getNombre());

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
        btnCancel.setOnClickListener(this);
        btnEnviar.setOnClickListener(this);
    }

    private void loadunidades() {

        listUnidades.add(new MydataUnidades("01", "Unidad 11", "444SDD", "21 de Novimebre 2018", "12:10:00", "", "Motor Encendido", "",
                19.022085, -98.214309, "10", "Av las Forjadores 55 Puebla Puebla, Mex.", null));

        listUnidades.add(new MydataUnidades("02", "Unidad 01", "FGSRF789", "22 de Novimebre 2018", "04:10:00", "", "Motor Apagado", "",
                19.067117, -98.329049, "100", "Av las Margaritas 5485 Puebla Puebla, Mex.", null));

        listUnidades.add(new MydataUnidades("01", "Unidad 06", "ssdddf", "20 de Novimebre 2018", "02:10:00", "", "Motor Encendido", "",
                19.022085, -98.214309, "60", "Av las Animas 396 Tlax, Tlaxcala, Mex.", null));

        listUnidades.add(new MydataUnidades("01", "Unidad 13", "SDJDF5F8 S", "30 de Octubre 2018", "16:10:00", "", "Indeterminado", "",
                19.022085, -98.214309, "77", "No disponible.", null));

    }

    private void initSpinnerUnidades(){
        ArrayAdapter<MydataUnidades> adapterUnidades = new ArrayAdapter<MydataUnidades>(getContext(), R.layout.simple_spinner_item, listUnidades);
        adapterUnidades.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spUnidad.setAdapter(adapterUnidades);
        spUnidad.setOnItemSelectedListener(this);
    }

    private void initSpinnerTipo() {
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(getContext(), R.layout.simple_spinner_item, getResources().getStringArray(R.array.arrarTipoUnidad));
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spTipoVehiculo.setAdapter(adapter);
        spTipoVehiculo.setOnItemSelectedListener(this);
    }


/*    protected void sendEmail() {
        String contenido = "Empresa: Empresa Prueba \n" +
                            "Usuario: " + "Juan Hernandez \n" +
                            "Telefono: " + "24878554669 \n" +
                            "Email: " + "demo@demo.com \n\n"+

                            "Unidad: " + tipoUnidad+ " \n"+
                            inputComent.getText().toString()
                ;
        String[] TO = {"non.rojas.flores@gmail.com"}; //aquí pon tu correo
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, item.getTipo());
        emailIntent.putExtra(Intent.EXTRA_TEXT, contenido);

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
            //finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context,
                    "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
        }
    }*/


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCancel:
                dismiss();
                break;
            case R.id.btnEnviar:
                submitSend();
                break;
        }
    }

    private void submitSend() {
        if (!validateUser()) {
            return;
        }
        sendEmail();
    }

    private void sendEmail() {

        final String contenido = "Empresa: Empresa Prueba \n" +
                "Usuario: " + "Juan Hernandez \n" +
                "Telefono: " + "24878554669 \n" +
                "Email: " + "demo@demo.com \n\n"+

                "Unidad: " + tipoUnidad+ " \n"+
                inputComent.getText().toString()
                ;

        //getActivity().runOnUiThread(new Runnable() {
            new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("non.rojas.flores@gmail.com","W200nonr");
                    Log.e("SendMail", sender.toString());
                    sender.sendMail(item.getNombre(), contenido, "iscnoerojas@gmail.com", "iscnoerojas@gmail.com");
                    Log.e("SendMail", "OK");
                    //FancyToast.makeText(getContext(), "Envio de ticket correcto", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                    //dismiss();
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                    //FancyToast.makeText(getContext(), "Error al enviar el ticket", FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                }
            }
        //});
        }).start();
    }

    private boolean validateUser() {
        if (inputComent.getText().toString().trim().isEmpty()) {
            Alert alert = AppConfig.alertsCustom(getContext(), "", getString(R.string.err_comentario), "error", 6000, 0);
            //inputLayoutUser.setError(getString(R.string.err_user));
            return false;
        } else {
            //inputLayoutUser.setErrorEnabled(false);
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Object item = adapterView.getItemAtPosition(i);
        switch (adapterView.getId()){
            case R.id.spTipoVehiculo:
                tipoUnidad = item.toString();
                break;
            case R.id.spUnidad:
                tipoUnidad = item.toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

}

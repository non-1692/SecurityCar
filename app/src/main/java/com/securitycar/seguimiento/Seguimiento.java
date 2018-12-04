package com.securitycar.seguimiento;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.securitycar.R;
import com.securitycar.app.MydataUnidades;

public class Seguimiento extends AppCompatActivity {

    MydataUnidades item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.detalle_unidad));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            item = extras.getParcelable("item");
        }

        //Log.d("Seguimiento", "item.Eco" + item.getEco());

        Bundle args = new Bundle();
        args.putParcelable("item", item);

        SeguimientoFragment newFragment = new SeguimientoFragment();
        newFragment.setArguments(args);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, newFragment); //donde fragmentContainer_id es el ID del FrameLayout donde tu Fragment está contenido.
        fragmentTransaction.commit();

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case android.R.id.home:
                // Obtener intent de la actividad padre
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                // Comprobar si DetailActivity no se creó desde CourseActivity
                if (NavUtils.shouldUpRecreateTask(this, upIntent) || this.isTaskRoot()) {

                    // Construir de nuevo la tarea para ligar ambas actividades
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Terminar con el método correspondiente para Android 5.x
                    this.finishAfterTransition();
                    return true;
                }
                // Dejar que el sistema maneje el comportamiento del up button
                return false;
/*            case R.id.action_stree:
                if (list.size() > 0) {
                    int value = list.size();
                    Log.d("onOptionsItemSelected", "value: " + value);
                    Intent intent = new Intent(this, StreetViewActivity.class);
                    intent.putExtra("Longitud", list.get(value - 1).longitude);
                    intent.putExtra("Latitud", list.get(value - 1).latitude);
                    intent.putExtra("key", 1);
                    startActivity(intent);
                }
                return true;*/
        }
    }

}

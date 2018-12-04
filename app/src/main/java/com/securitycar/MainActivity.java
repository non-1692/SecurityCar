package com.securitycar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.securitycar.config.ConfigFragment;
import com.securitycar.login.LoginActivity;
import com.securitycar.tikets.TiketsFragment;
import com.securitycar.unidades.UnidadesFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;

    private int widthPixels, heightPixels;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        widthPixels = dm.widthPixels;
        heightPixels = dm.heightPixels;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        final FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.contenedor_frame, new UnidadesFragment()).commit(); // Unidades original
        //fm.beginTransaction().replace(R.id.contenedor_frame, new TiketsFragment()).commit();

        loadInfo();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction().replace(R.id.contenedor_frame, new TiketsFragment()).commit();
            }
        });
    }

    private void loadInfo() {

        View hView = navigationView.getHeaderView(0);
        TextView tvNom = (TextView) hView.findViewById(R.id.tvNom);
        TextView tvEmail = (TextView) hView.findViewById(R.id.tvEmail);
        ImageView imageView = (ImageView) hView.findViewById(R.id.imageView);

        Glide.with(this)
                .load(R.drawable.ic_avatar_default) // add your image url
                .apply(RequestOptions.circleCropTransform())
                .into(imageView);

        android.view.ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width =  widthPixels / 4;
        layoutParams.height =  widthPixels / 4;
        imageView.setLayoutParams(layoutParams);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            goLoggin();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fm = getSupportFragmentManager();

        int id = item.getItemId();

        if (id == R.id.nav_unidades) {
            fm.beginTransaction().replace(R.id.contenedor_frame, new UnidadesFragment()).commit();

        } else if (id == R.id.nav_tikets) {
            fm.beginTransaction().replace(R.id.contenedor_frame, new TiketsFragment()).commit();

        } else if (id == R.id.nav_config) {
            fm.beginTransaction().replace(R.id.contenedor_frame, new ConfigFragment()).commit();

        } else if (id == R.id.nav_exit) {
            cerrarSesion();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cerrarSesion() {

        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .title(R.string.app_name)
                .content(R.string.exit_app)
                .positiveText(R.string.salir)
                .negativeText(R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        dialog.cancel();
                        // logout();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        dialog.cancel();
                    }
                });

        MaterialDialog dialog = builder.build();
        dialog.show();
    }

    private void goLoggin() {
        Intent myIntent = new Intent(this, LoginActivity.class);
        startActivity(myIntent);
        //finish();
    }

}

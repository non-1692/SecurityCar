package com.securitycar.app;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

/**
 * Created by noe on 21/11/17.
 */

public class MydataUnidades implements Parcelable {

    private String id, eco, placas, fecha, hora, datafh, statusMotor, imagen, vel, dir;
    private double latitud, longitud;
    private JSONObject sensor;

    public MydataUnidades(String id, String eco, String placas, String fecha, String hora, String datafh,
                          String statusMotor, String imagen, double latitud, double longitud, String vel, String dir, JSONObject sensor){

        this.id = id;
        this.eco = eco;
        this.placas = placas;
        this.fecha = fecha;
        this.hora = hora;
        this.datafh = datafh;
        this.statusMotor = statusMotor;
        this.imagen = imagen;
        this.latitud = latitud;
        this.longitud = longitud;
        this.vel = vel;
        this.dir = dir;
        this.sensor = sensor;
    }

    protected MydataUnidades(Parcel in) {
        id = in.readString();
        eco = in.readString();
        placas = in.readString();
        fecha = in.readString();
        hora = in.readString();
        datafh = in.readString();
        statusMotor = in.readString();
        imagen = in.readString();
        vel = in.readString();
        dir = in.readString();
        latitud = in.readDouble();
        longitud = in.readDouble();
    }

    public static final Creator<MydataUnidades> CREATOR = new Creator<MydataUnidades>() {
        @Override
        public MydataUnidades createFromParcel(Parcel in) {
            return new MydataUnidades(in);
        }

        @Override
        public MydataUnidades[] newArray(int size) {
            return new MydataUnidades[size];
        }
    };

    //id
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    //Eco
    public String getEco() {
        return eco;
    }
    public void setEco(String eco) {
        this.eco = eco;
    }

    //placas
    public String getPlacas() {
        return placas;
    }
    public void setPlacas(String placas) {
        this.placas = placas;
    }

    //fecha
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    //hora
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }

    //status
    public String getStatusMotor() {
        return statusMotor;
    }
    public void setStatusMotor(String statusMotor) {
        this.statusMotor = statusMotor;
    }

    public Double getLatitud() {
        return latitud;
    }
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    //status
    public String getVel() {
        return vel;
    }
    public void setVel(String vel) {
        this.vel = vel;
    }

    //status
    public String getDir() {
        return dir;
    }
    public void setDir(String dir) {
        this.dir = dir;
    }

    //status
    public String getDatafh() {
        return datafh;
    }
    public void setDatafh(String datafh) {
        this.datafh = datafh;
    }

    //Sensores
    public JSONObject getSensor() {
        return sensor;
    }

    public void setSensor(JSONObject sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        String Eco = eco;
        String Placas = placas;
        String busqueda = "";

        return eco;
    }

    public boolean equals(Object obj) {
        if(obj instanceof MydataUnidades){
            MydataUnidades c = (MydataUnidades)obj;
            //if(c.getEco().equals(eco) && c.getPlacas().equals(placas) ) return true;
            if(c.getEco().equals(eco) && c.getId()==id ) return true;
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(eco);
        parcel.writeString(placas);
        parcel.writeString(fecha);
        parcel.writeString(hora);
        parcel.writeString(datafh);
        parcel.writeString(statusMotor);
        parcel.writeString(imagen);
        parcel.writeString(vel);
        parcel.writeString(dir);
        parcel.writeDouble(latitud);
        parcel.writeDouble(longitud);
    }
}

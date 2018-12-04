package com.securitycar.tikets.app;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MydataTicket extends ArrayList<MydataTicket> implements Parcelable {

    private int id, imagen;
    private String nombre, des, tipo;


    public MydataTicket(int id, String nombre, String des, String tipo, int imagen) {
        this.id = id;
        this.nombre = nombre;
        this.des = des;
        this.tipo = tipo;
        this.imagen = imagen;
    }

    protected MydataTicket(Parcel in) {
        id = in.readInt();
        imagen = in.readInt();
        nombre = in.readString();
        des = in.readString();
        tipo = in.readString();
    }

    public static final Creator<MydataTicket> CREATOR = new Creator<MydataTicket>() {
        @Override
        public MydataTicket createFromParcel(Parcel in) {
            return new MydataTicket(in);
        }

        @Override
        public MydataTicket[] newArray(int size) {
            return new MydataTicket[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(imagen);
        parcel.writeString(nombre);
        parcel.writeString(des);
        parcel.writeString(tipo);
    }
}

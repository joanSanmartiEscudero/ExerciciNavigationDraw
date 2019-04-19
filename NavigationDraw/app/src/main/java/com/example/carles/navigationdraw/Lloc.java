package com.example.carles.navigationdraw;

public class Lloc {
    private int _id, tipus, telefon;
    private String nom, address, foto, url, comentari;
    private double longitud;
    private double latitud;
    private float valoracio;

    public Lloc(){

    };

    // Constructor
    public Lloc(int tipus, int telefon, String nom, String address,
                double longitud, double latitud, float valoracio, String comentari,
                String url, String foto){
        super();
        this.nom = nom;
        this.address = address;
        this.telefon = telefon;
        this.tipus = tipus;
        this.longitud = longitud;
        this.latitud = latitud;
        this.valoracio = valoracio;
        this.foto = foto;
        this.comentari = comentari;
        this.url = url;
    }

    // Getters & Setters
    public int get_id(){
        return _id;
    }
    public void set_id(int id){
        this._id = id;
    }
    public String getNom(){
        return nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public int getTelefon(){
        return telefon;
    }
    public void setTelefon(int telefon){
        this.telefon = telefon;
    }
    public double getLongitud(){
        return longitud;
    }
    public void setLongitud(double longitud){
        this.longitud = longitud;
    }
    public double getLatitud(){
        return latitud;
    }
    public void setLatitud(double latitud){
        this.latitud = latitud;
    }
    public int getTipus(){
        return tipus;
    }
    public void setTipus(int tipus){
        this.tipus = tipus;
    }
    public String getFoto(){
        return foto;
    }
    public void setFoto(String foto){
        this.foto = foto;
    }
    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getComentari(){
        return comentari;
    }
    public void setComentari(String comentari){
        this.comentari = comentari;
    }
    public float getValoracio(){
        return valoracio;
    }
    public void setValoracio(float valoracio){
        this.valoracio = valoracio;
    }


}

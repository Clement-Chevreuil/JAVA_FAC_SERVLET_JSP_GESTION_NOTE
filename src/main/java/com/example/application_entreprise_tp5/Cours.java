package com.example.application_entreprise_tp5;

import java.io.Serializable;

public class Cours implements Serializable {

    private int cours_id;
    private String intitule;
    private int coef;
    private int duree;

    public Cours() {}

    public Cours(int id, String intitule, int coef, int duree) {
        this.cours_id=id;
        this.intitule=intitule;
        this.coef=coef;
        this.duree=duree;
    }

    public Cours(String intitule, int coef, int duree)
    {
        this.intitule=intitule;
        this.coef=coef;
        this.duree=duree;
    }

    public Cours(int id) {this.cours_id=id;}

    public int getCoef() {return coef;}
    public int getCours_id() {return cours_id;}
    public int getDuree() {return duree;}
    public String getIntitule() {return intitule;}

    public void setCoef(int coef) {this.coef = coef;}
    public void setCours_id(int cours_id) {this.cours_id = cours_id;}
    public void setDuree(int duree) {this.duree = duree;}
    public void setIntitule(String intitule) {this.intitule = intitule;}
}

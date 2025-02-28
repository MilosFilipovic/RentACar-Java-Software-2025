/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package klase;

import java.io.Serializable;

/**
 *
 * @author Miloš
 */
public class StavkaRezervacije implements Serializable{
    
    private int idrezervacije;
    private int rb;
    private String nazivStavke;
    private String napomena;
    private double cenaVozila;
    private int brojDana;
    private double iznosStavke;
    private int idVozilo;
    private Vozilo vozilo;
    

    public StavkaRezervacije() {
    }

    public StavkaRezervacije(int idrezervacije, int rb, String nazivStavke, String napomena, double cenaVozila, int brojDana, double iznosStavke, int idVozilo, Vozilo vozilo) {
        this.idrezervacije = idrezervacije;
        this.rb = rb;
        this.nazivStavke = nazivStavke;
        this.napomena = napomena;
        this.cenaVozila = cenaVozila;
        this.brojDana = brojDana;
        this.iznosStavke = iznosStavke;
        this.idVozilo = idVozilo;
        this.vozilo = vozilo;
    }

    

    

    

    

    public double getIznosStavke() {
        return iznosStavke;
    }

    public void setIznosStavke(double iznosStavke) {
        this.iznosStavke = iznosStavke;
    }

    

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public String getNazivStavke() {
        return nazivStavke;
    }

    public void setNazivStavke(String nazivStavke) {
        this.nazivStavke = nazivStavke;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public double getCenaVozila() {
        return cenaVozila;
    }

    public void setCenaVozila(double cenaVozila) {
        this.cenaVozila = cenaVozila;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(int brojDana) {
        this.brojDana = brojDana;
    }

    public int getIdVozilo() {
        return idVozilo;
    }

    public void setIdVozilo(int idVozilo) {
        this.idVozilo = idVozilo;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }

    public int getIdrezervacije() {
        return idrezervacije;
    }

    public void setIdrezervacije(int idrezervacije) {
        this.idrezervacije = idrezervacije;
    }

    
    
}

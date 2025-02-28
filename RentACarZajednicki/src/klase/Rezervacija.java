/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package klase;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Miloš
 */
public class Rezervacija implements Serializable{
    private int idRezervacija;
    private LocalDate datumPreuzimanja;
    private LocalDate datumVracanja;
    private double iznosRezervacije;
    private int idKlijent;
    private Klijent klijent;
    private int idZaposleni;
    private List<StavkaRezervacije> listaStavki;

    public Rezervacija() {
    }

    public Rezervacija(int idRezervacija, LocalDate datumPreuzimanja, LocalDate datumVracanja, double iznosRezervacije, int idKlijent, Klijent klijent, int idZaposleni, List<StavkaRezervacije> listaStavki) {
        this.idRezervacija = idRezervacija;
        this.datumPreuzimanja = datumPreuzimanja;
        this.datumVracanja = datumVracanja;
        this.iznosRezervacije = iznosRezervacije;
        this.idKlijent = idKlijent;
        this.klijent = klijent;
        this.idZaposleni = idZaposleni;
        this.listaStavki = listaStavki;
    }

    
    
    

    

    public double getIznosRezervacije() {
        return iznosRezervacije;
    }

    public void setIznosRezervacije(double iznosRezervacije) {
        this.iznosRezervacije = iznosRezervacije;
    }

    public int getIdRezervacija() {
        return idRezervacija;
    }

    public void setIdRezervacija(int idRezervacija) {
        this.idRezervacija = idRezervacija;
    }

    

    public int getIdKlijent() {
        return idKlijent;
    }

    public void setIdKlijent(int idKlijent) {
        this.idKlijent = idKlijent;
    }

    public int getIdZaposleni() {
        return idZaposleni;
    }

    public void setIdZaposleni(int idZaposleni) {
        this.idZaposleni = idZaposleni;
    }

    public LocalDate getDatumPreuzimanja() {
        return datumPreuzimanja;
    }

    public void setDatumPreuzimanja(LocalDate datumPreuzimanja) {
        this.datumPreuzimanja = datumPreuzimanja;
    }

    public LocalDate getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(LocalDate datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public List<StavkaRezervacije> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<StavkaRezervacije> listaStavki) {
        this.listaStavki = listaStavki;
    }
    
    
}

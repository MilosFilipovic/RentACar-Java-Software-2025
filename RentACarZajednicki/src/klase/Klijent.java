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
public class Klijent implements Serializable{
    private int idKlijent;
    private String ime;
    private String prezime;
    private String telefon;
    private int idMesta;

    public Klijent() {
    }

    public Klijent(int idKlijent, String ime, String prezime, String telefon, int idMesta) {
        this.idKlijent = idKlijent;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.idMesta = idMesta;
    }

    

    

    

    public int getIdKlijent() {
        return idKlijent;
    }

    public void setIdKlijent(int idKlijent) {
        this.idKlijent = idKlijent;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public int getIdMesta() {
        return idMesta;
    }

    public void setIdMesta(int idMesta) {
        this.idMesta = idMesta;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    
    
    
    
}

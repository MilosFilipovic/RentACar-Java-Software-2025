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
public class Mesto implements Serializable{
    private int idMesta;
    private String nazivMesta;
    private String adresa;

    public Mesto() {
    }

    public Mesto(int idMesta, String nazivMesta, String adresa) {
        this.idMesta = idMesta;
        this.nazivMesta = nazivMesta;
        this.adresa = adresa;
    }

    

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNazivMesta() {
        return nazivMesta;
    }

    public void setNazivMesta(String nazivMesta) {
        this.nazivMesta = nazivMesta;
    }

    public int getIdMesta() {
        return idMesta;
    }

    public void setIdMesta(int idMesta) {
        this.idMesta = idMesta;
    }

    @Override
    public String toString() {
        return nazivMesta + ", " + adresa;
    }
    
    
}

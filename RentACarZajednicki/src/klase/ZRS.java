/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package klase;

import java.io.Serializable;
import java.time.LocalDate;


/**
 *
 * @author Miloš
 */
public class ZRS implements Serializable{
    private LocalDate datumRada;
    private int idZaposleni;
    private int idSmena;
    

    public ZRS() {
    }

    public ZRS(LocalDate datumRada, int idZaposleni, int idSmena) {
        this.datumRada = datumRada;
        this.idZaposleni = idZaposleni;
        this.idSmena = idSmena;
        
    }

    

    

    public int getIdSmena() {
        return idSmena;
    }

    public void setIdSmena(int idSmena) {
        this.idSmena = idSmena;
    }

    

    public int getIdZaposleni() {
        return idZaposleni;
    }

    public void setIdZaposleni(int idZaposleni) {
        this.idZaposleni = idZaposleni;
    }

    public LocalDate getDatumRada() {
        return datumRada;
    }

    public void setDatumRada(LocalDate datumRada) {
        this.datumRada = datumRada;
    }

    
    
}

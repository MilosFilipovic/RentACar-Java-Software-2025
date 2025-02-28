/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package klase;

import java.io.Serializable;
import java.time.LocalTime;


/**
 *
 * @author Miloš
 */
public class RadnaSmena implements Serializable{
    private int idSmena;
    private LocalTime pocetakSmene;
    private LocalTime krajSmene;
    

    public RadnaSmena() {
    }

    public RadnaSmena(int idSmena, LocalTime pocetakSmene, LocalTime krajSmene) {
        this.idSmena = idSmena;
        this.pocetakSmene = pocetakSmene;
        this.krajSmene = krajSmene;
    }

    public int getIdSmena() {
        return idSmena;
    }

    public void setIdSmena(int idSmena) {
        this.idSmena = idSmena;
    }

    public LocalTime getPocetakSmene() {
        return pocetakSmene;
    }

    public void setPocetakSmene(LocalTime pocetakSmene) {
        this.pocetakSmene = pocetakSmene;
    }

    public LocalTime getKrajSmene() {
        return krajSmene;
    }

    public void setKrajSmene(LocalTime krajSmene) {
        this.krajSmene = krajSmene;
    }

    @Override
    public String toString() {
        return String.valueOf(idSmena);
    }

    

}

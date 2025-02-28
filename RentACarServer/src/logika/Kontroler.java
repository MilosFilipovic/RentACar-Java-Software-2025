/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import baza.DBBroker;
import klase.Zaposleni;
import klase.Mesto;
import klase.RadnaSmena;
import klase.Rezervacija;
import klase.StavkaRezervacije;
import klase.Vozilo;
import klase.ZRS;
import klase.Klijent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Kontroler {

    private static Kontroler instance;
    private DBBroker dbb;

    private Kontroler() {
        dbb = new DBBroker();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Zaposleni login(String username, String password) {
        return dbb.login(username, password);
    }

    public ArrayList<Zaposleni> vratiZaposlene() {
        return dbb.vratiZaposlene();
    }

    public boolean dodajZaposlenog(Zaposleni z) {
        
        try {
            return dbb.dodajZaposlenog(z);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    public boolean obrisiZaposlenog(int idz) {
        return dbb.obrisiZaposlenog(idz);
    }

    public boolean izmeniZaposlenog(Zaposleni izmenjenZaposleni) {
        try {
            return dbb.izmeniZaposlenog(izmenjenZaposleni);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<Zaposleni> pretraziZaposlene(String ime) {
        return dbb.pretraziZaposlene(ime);
    }
    
    /* ///////////////////////////////////////////////////////////////////////////////////////////////// */

    public ArrayList<RadnaSmena> vratiSmene() {
        return dbb.vratiSmene();
    }

    public boolean dodajRadnuSmenu(RadnaSmena rs) {
        try {
            return dbb.dodajRadnuSmenu(rs);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public boolean obrisiSmenu(int idrs) {
        return dbb.obrisiSmenu(idrs);
    }

    public boolean izmeniSmenu(RadnaSmena izmenjenaSmena) {
        try {
            return dbb.izmeniSmenu(izmenjenaSmena);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public ArrayList<RadnaSmena> pretraziRS(String pocetak) {
        return dbb.pretraziSmene(pocetak);
    }
    
     /* ///////////////////////////////////////////////////////////////////////////////////////////////// */

    public ArrayList<Zaposleni> vratiCBZaposlene() {
        return dbb.vratiCBZaposlene();
    }

    public ArrayList<RadnaSmena> vratiCBRadnaSmena() {
        return dbb.vratiCBRadnaSmena();
    }

    public boolean dodajZrs(ZRS zrs) {
        try {
            return dbb.dodajZrs(zrs);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public ArrayList<ZRS> vratiZRSTabelu() {
        return dbb.vratiZRSTabelu();
    }

    public boolean obrisiZRS(ZRS raspored) {
        return dbb.obrisiZRS(raspored);
    }
    
     /* ///////////////////////////////////////////////////////////////////////////////////////////////// */
    

    public ArrayList<Mesto> vratiMesta() {
        return dbb.vratiMesta();
    }

    public boolean dodajMesto(Mesto m) {
        try {
            return dbb.dodajMesto(m);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean obrisiMesto(int id) {
        return dbb.obrisiMesto(id);
    }

    public boolean izmeniMesto(Mesto izmenjenoMesto) {
        try {
            return dbb.izmeniMesto(izmenjenoMesto);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<Mesto> pretraziMesta(String grad) {
        return dbb.pretraziMesta(grad);
    }
    
     /* ///////////////////////////////////////////////////////////////////////////////////////////////// */

    public ArrayList<Klijent> vratiKlijente() {
        return dbb.vratiKlijente();
    }

    public boolean kreirajKlijenta(Klijent k) {
        try {
            return dbb.kreirajKlijenta(k);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean obrisiKlijenta(int idk) {
        return dbb.obrisiKlijenta(idk);
    }

    public boolean izmeniKlijenta(Klijent izmenjenKlijent) {
        try {
            return dbb.izmeniKlijenta(izmenjenKlijent);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<Klijent> pretraziKlijente(String imek) {
        return dbb.pretraziKlijente(imek);
    }
    
    public ArrayList<Mesto> vratiAdrese() {
        return dbb.vratiAdrese();
    }
    
     /* ///////////////////////////////////////////////////////////////////////////////////////////////// */

    public ArrayList<Vozilo> vratiVozila() {
        return dbb.vratiVozila();
    }

    public boolean dodajVozilo(Vozilo v) {
        try {
            return dbb.dodajVozilo(v);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean obrisiVozilo(int idv) {
        return dbb.obrisiVozilo(idv);
    }

    public boolean izmeniVozilo(Vozilo vozilo) {
        try {
            return dbb.izmeniVozilo(vozilo);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Vozilo> pretraziVozila(String model) {
        return dbb.pretraziVozila(model);
    }

    /* ///////////////////////////////////////////////////////////////////////////////////////////////// */

    public ArrayList<Rezervacija> vratiRezervacije() {
        return dbb.vratiRezervacije();
    }


    public boolean kreirajStavkuRez(StavkaRezervacije sr) {
        try {
            return dbb.kreirajStavkuRezervacije(sr);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public boolean kreirajRez(Rezervacija r) {
        try {
            return dbb.kreirajRezervaciju(r);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public boolean obrisiRezervaciju(int idr) {
        return dbb.obrisiRezervaciju(idr);
    }

    public ArrayList<Rezervacija> pretraziRez(LocalDate datum) {
        return dbb.pretraziRezervacije(datum);
    }

    public ArrayList<StavkaRezervacije> vratiStavkeRez(int id) {
        return dbb.vratiStavkeRez(id);
    }

    public boolean obrisiStavku(int rb) {
        return dbb.obrisiStavku(rb);
    }

    public boolean apdejtujIznos(int id) throws SQLException {
        return dbb.apdejtujIznosRezervacije(id);
    }

    
    
    

    

    

    

    

    

    

    

}

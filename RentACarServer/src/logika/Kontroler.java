/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import baza.DBBroker;
import baza.DBBrokerNew;
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
import so.klijent.SOGetListKlijent;
import so.klijent.SOIzmeniKlijenta;
import so.klijent.SOKreirajKlijenta;
import so.klijent.SOObrisiKlijenta;
import so.klijent.SOPretraziKlijentaID;
import so.klijent.SOPretraziKlijentaNaziv;
import so.mesto.SOGetListMesto;
import so.mesto.SOIzmeniMesto;
import so.mesto.SOKreirajMesto;
import so.mesto.SOObrisiMesto;
import so.mesto.SOPretraziMestaNaziv;
import so.mesto.SOUcitajMestoID;
import so.radnasmena.SOGetListRS;
import so.radnasmena.SOIzmeniRS;
import so.radnasmena.SOKreirajRS;
import so.radnasmena.SOObrisiRS;
import so.radnasmena.SOPretraziRSID;
import so.radnasmena.SOPretraziRSPocetak;
import so.rezervacija.SOGetListRezervacija;
import so.rezervacija.SOIzmeniRez;
import so.rezervacija.SOKreirajRez;
import so.rezervacija.SOObrisiRezervaciju;
import so.rezervacija.SOPretraziRezID;
import so.rezervacija.SOPretraziRezPoKlijentID;
import so.rezervacija.SOPretraziRezPoVozilu;
import so.rezervacija.SOPretraziRezPoZap;
import so.stavke.SOGetListStavke;
import so.stavke.SOIzmeniStavku;
import so.stavke.SOKreirajStavku;
import so.stavke.SOObrisiStavku;
import so.stavke.SOVratiStavkeRez;
import so.vozilo.SOGetListVozilo;
import so.vozilo.SOIzmeniVozilo;
import so.vozilo.SOKreirajVozilo;
import so.vozilo.SOObrisiVozilo;
import so.vozilo.SOPretraziVozilaNaziv;
import so.vozilo.SOPretraziVoziloID;
import so.zaposleni.SOGetListZaposleni;
import so.zaposleni.SOIzmeniZaposlenog;
import so.zaposleni.SOKreirajZaposlenog;
import so.zaposleni.SOObrisiZaposlenog;
import so.zaposleni.SOPretraziZapID;
import so.zaposleni.SOPretraziZapNaziv;
import so.zrs.SOGetListZRS;
import so.zrs.SOKreirajZRS;
import so.zrs.SOObrisiZRS;


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
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Zaposleni login(String username, String password) {
        Zaposleni z = DBBrokerNew.getInstance().login(username, password);
        
        return z;
    }

    public ArrayList<Zaposleni> vratiZaposlene() throws Exception {
        SOGetListZaposleni so = new SOGetListZaposleni();
        so.templateExecute(new Zaposleni());
        ArrayList<Zaposleni> zaposleni = so.getZaposleni();
        if (zaposleni.isEmpty()) {
            throw new Exception("Sistem nije uspeo ucita zaposlene");
        }
        return zaposleni;
    }
    
    public boolean dodajZaposlenog(Zaposleni z) throws Exception {
        SOKreirajZaposlenog so = new SOKreirajZaposlenog();
        so.templateExecute(z);
        int id = so.getId();
        if (id == -1) {
            throw new Exception("Mesto nije dodato!!!");
        }
        return true;
    }


    public boolean obrisiZaposlenog(Zaposleni zap) throws Exception {
        try{
            SOObrisiZaposlenog so = new SOObrisiZaposlenog();
        so.templateExecute(zap);
        int affectedRows = so.getAffectedRows();
        if (affectedRows == 0) {
            throw new Exception("Sistem nije uspeo da izbrise zaposlenog");
            
        }
        }catch(Exception e){
            throw new Exception("Ne mozete izbrisati zaposlenog");
        }
        
        return true;
    }
    


    public boolean izmeniZaposlenog(Zaposleni izmenjenZaposleni) throws Exception {
        SOIzmeniZaposlenog so = new SOIzmeniZaposlenog();
        so.templateExecute(izmenjenZaposleni);
        int affectedRows = so.getAffectedRows();
        if (affectedRows == 0) {
            throw new Exception("Sistem nije uspeo da izmeni zaposlenog");
        }
        return true;
    }
    
    public ArrayList<Zaposleni> pretraziNazivZaposleni(Zaposleni nazivZap) throws Exception {
        SOPretraziZapNaziv so = new SOPretraziZapNaziv();
        so.templateExecute(nazivZap);
        ArrayList<Zaposleni> zaposleniPoNazivu = so.getZaposleni();
        if (zaposleniPoNazivu.isEmpty()) {
            throw new Exception("Sistem nije uspeo ucita zaposlene");
        }
        return zaposleniPoNazivu;
    }
    
    public Zaposleni pretraziIDZaposleni(Zaposleni idZap) throws Exception {
        SOPretraziZapID so = new SOPretraziZapID();
        so.templateExecute(idZap);
        Zaposleni z = so.getZ();
        
        if (z==null) {
            throw new Exception("Sistem nije uspeo da ucita zaposlenog");
        }
        return z;
    }
    
    /* ///////////////////////////////////////////////////////////////////////////////////////////////// */

    public ArrayList<RadnaSmena> vratiSmene() throws Exception {
        SOGetListRS so = new SOGetListRS();
        so.templateExecute(new RadnaSmena());
        ArrayList<RadnaSmena> smene = so.getRS();
        if (smene.isEmpty()) {
            throw new Exception("Sistem nije uspeo ucita smene");
        }
        return smene;
    }

    public boolean dodajRadnuSmenu(RadnaSmena rs) throws Exception {
        SOKreirajRS so = new SOKreirajRS();
        so.templateExecute(rs);
        int id = so.getId();
        if (id == -1) {
            throw new Exception("SMena nije dodata!!!");
        }
        return true;
    }

    public boolean obrisiSmenu(RadnaSmena idrs) throws Exception {
        try{
            SOObrisiRS so = new SOObrisiRS();
        so.templateExecute(idrs);
        int affectedRows = so.getAffectedRows();
        if (affectedRows == 0) {
            throw new Exception("Sistem nije uspeo da izbrise smenu");
            
        }
        }catch(Exception e){
            throw new Exception("Ne mozete izbrisati smenu");
        }
        
        return true;
    }
    


    public boolean izmeniSmenu(RadnaSmena izmenjenaSmena) throws Exception {
        SOIzmeniRS so = new SOIzmeniRS();
        so.templateExecute(izmenjenaSmena);
        int affectedRows = so.getAffectedRows();
        if (affectedRows == 0) {
            throw new Exception("Sistem nije uspeo da izmeni smenu");
        }
        return true;
    }
    
    public ArrayList<RadnaSmena> pretraziRS(RadnaSmena pocetakRS) throws Exception {
        SOPretraziRSPocetak so = new SOPretraziRSPocetak();
        so.templateExecute(pocetakRS);
        ArrayList<RadnaSmena> smenePocetak = so.getRS();
        if (smenePocetak.isEmpty()) {
            throw new Exception("Sistem nije uspeo ucita smene");
        }
        return smenePocetak;
    }
    
  
    public RadnaSmena pretraziIDRS(RadnaSmena idsmena) throws Exception {
        SOPretraziRSID so = new SOPretraziRSID();
        so.templateExecute(idsmena);
        RadnaSmena rs = so.getRs();
        
        if (rs==null) {
            throw new Exception("Sistem nije uspeo da ucita smenu");
        }
        return rs;
    }
    
     /* ///////////////////////////////////////////////////////////////////////////////////////////////// */



    public boolean dodajZRS(ZRS zrs) throws Exception {
        SOKreirajZRS so = new SOKreirajZRS();
        so.templateExecute(zrs);
        int id = so.getId();
        if (id == -1) {
            throw new Exception("Raspored nije dodat!!!");
        }
        return true;
    }
    
    

    public ArrayList<ZRS> vratiZRS() throws Exception {
        SOGetListZRS so = new SOGetListZRS();
        so.templateExecute(new ZRS());
        ArrayList<ZRS> raspored = so.getZRS();
        if (raspored.isEmpty()) {
            throw new Exception("Sistem nije uspeo ucita smene");
        }
        return raspored;
    }
    
    

    public boolean obrisiZRS(ZRS raspored) throws Exception {
        try{
            SOObrisiZRS so = new SOObrisiZRS();
        so.templateExecute(raspored);
        int affectedRows = so.getAffectedRows();
        if (affectedRows == 0) {
            throw new Exception("Sistem nije uspeo da izbrise smenu");
            
        }
        }catch(Exception e){
            throw new Exception("Ne mozete izbrisati smenu");
        }
        
        return true;
    }
    
     /* ///////////////////////////////////////////////////////////////////////////////////////////////// */
   
    
    public ArrayList<Mesto> getListMesta() throws Exception{
        SOGetListMesto so = new SOGetListMesto();
        so.templateExecute(new Mesto());
        ArrayList<Mesto> mesta = so.getMesta();
        if (mesta.isEmpty()) {
            throw new Exception("Sistem nije uspeo ucita sva mesta");
        }
        return mesta;
    }

    
    public boolean dodajMesto(Mesto m) throws Exception {

        SOKreirajMesto so = new SOKreirajMesto();
        so.templateExecute(m);
        int id = so.getId();
        if (id == -1) {
            throw new Exception("Mesto nije dodato!!!");
        }
        return true;
    }
    
    public boolean obrisiMesto(Mesto m) throws Exception {

        try{
        SOObrisiMesto so = new SOObrisiMesto();
        so.templateExecute(m);
        int affectedRows = so.getAffectedRows();
        if (affectedRows == 0) {
            throw new Exception("Sistem nije uspeo da izbrise mesto");
            
        }
        }catch(Exception e){
            throw new Exception("Ne mozete izbrisati mesto");
        }
        
        return true;
    }
    
    

    public boolean izmeniMesto(Mesto izmenjenoMesto) throws Exception {
        SOIzmeniMesto so = new SOIzmeniMesto();
        so.templateExecute(izmenjenoMesto);
        int affectedRows = so.getAffectedRows();
        if (affectedRows == 0) {
            throw new Exception("Sistem nije uspeo da izmeni mesto");
        }
        return true;
    }
    

    
    public Mesto pretraziIDMesta(Mesto idMesta) throws Exception {
        SOUcitajMestoID so = new SOUcitajMestoID();
        so.templateExecute(idMesta);
        Mesto m = so.getM();
        
        if (m==null) {
            throw new Exception("Sistem nije uspeo da ucita mesto");
        }
        return m;
    }
    
    public ArrayList<Mesto> pretraziNazivMesto(Mesto nazivM) throws Exception {
        SOPretraziMestaNaziv so = new SOPretraziMestaNaziv();
        so.templateExecute(nazivM);
        ArrayList<Mesto> mestaPoNazivu = so.getMesta();
        if (mestaPoNazivu.isEmpty()) {
            throw new Exception("Sistem nije uspeo ucita klijente");
        }
        return mestaPoNazivu;
    }
    

     /* ///////////////////////////////////////////////////////////////////////////////////////////////// */

    public ArrayList<Klijent> vratiKlijente() throws Exception {
        SOGetListKlijent so = new SOGetListKlijent();
        so.templateExecute(new Klijent());
        ArrayList<Klijent> klijenti = so.getKlijenti();
        if (klijenti.isEmpty()) {
            throw new Exception("Sistem nije uspeo ucita Klijente");
        }
        return klijenti;
    }

    public boolean dodajKlijenta(Klijent k) throws Exception {
        SOKreirajKlijenta so = new SOKreirajKlijenta();
        so.templateExecute(k);
        int id = so.getId();
        if (id == -1) {
            throw new Exception("Klijent nije dodato!!!");
        }
        return true;
    }
    
      public boolean obrisiKlijenta(Klijent obrisiK) throws Exception {
        try {
            SOObrisiKlijenta so = new SOObrisiKlijenta();
            so.templateExecute(obrisiK);
            int affectedRows = so.getAffectedRows();
            if (affectedRows == 0) {
                throw new Exception("Sistem nije uspeo da izbrise klijenta");

            }
        } catch (Exception e) {
            throw new Exception("Ne mozete izbrisati klijenta");
        }

        return true;
    }

    public boolean izmeniKlijenta(Klijent izmenjenKlijent) throws Exception {
        SOIzmeniKlijenta so = new SOIzmeniKlijenta();
        so.templateExecute(izmenjenKlijent);
        int affectedRows = so.getAffectedRows();
        if (affectedRows == 0) {
            throw new Exception("Sistem nije uspeo da izmeni klijenta");
        }
        return true;
    }
  
    public ArrayList<Klijent> pretraziNazivKlijent(Klijent nazivK) throws Exception {
        SOPretraziKlijentaNaziv so = new SOPretraziKlijentaNaziv();
        so.templateExecute(nazivK);
        ArrayList<Klijent> klijentiPoNazivu = so.getKlijenti();
        if (klijentiPoNazivu.isEmpty()) {
            throw new Exception("Sistem nije uspeo ucita klijente");
        }
        return klijentiPoNazivu;
    }

    
    
    public Klijent pretraziIDKlijent(Klijent idK) throws Exception {
        SOPretraziKlijentaID so = new SOPretraziKlijentaID();
        so.templateExecute(idK);
        Klijent k = so.getK();
        
        if (k==null) {
            throw new Exception("Sistem nije uspeo da ucita klijenta");
        }
        return k;
    }
    
    public ArrayList<Mesto> vratiAdrese() {
        return dbb.vratiAdrese();
    }
     /* ///////////////////////////////////////////////////////////////////////////////////////////////// */

    public ArrayList<Vozilo> getListVozila() throws Exception {
        SOGetListVozilo so = new SOGetListVozilo();
        so.templateExecute(new Vozilo());
        ArrayList<Vozilo> vozila = so.getVozila();
        if (vozila.isEmpty()) {
            throw new Exception("Sistem nije uspeo ucita sva vozila");
        }
        return vozila;
    }
    
    public boolean dodajVozilo(Vozilo v) throws Exception {
        SOKreirajVozilo so = new SOKreirajVozilo();
        so.templateExecute(v);
        int id = so.getId();
        if (id == -1) {
            throw new Exception("Vozilo nije dodato!!!");
        }
        return true;
    }

     public boolean obrisiVozilo(Vozilo voz) throws Exception {
        try {
            SOObrisiVozilo so = new SOObrisiVozilo();
            so.templateExecute(voz);
            int affectedRows = so.getAffectedRows();
            if (affectedRows == 0) {
                throw new Exception("Sistem nije uspeo da izbrise vozilo");

            }
        } catch (Exception e) {
            throw new Exception("Ne mozete izbrisati vozilo");
        }

        return true;
    }

     public boolean izmeniVozilo(Vozilo izmenjenoVozilo) throws Exception {
         SOIzmeniVozilo so = new SOIzmeniVozilo();
        so.templateExecute(izmenjenoVozilo);
        int affectedRows = so.getAffectedRows();
        if (affectedRows == 0) {
            throw new Exception("Sistem nije uspeo da izmeni vozilo");
        }
        return true;
     }





    public ArrayList<Vozilo> pretraziNazivVozilo(Vozilo nazivV) throws Exception {
        SOPretraziVozilaNaziv so = new SOPretraziVozilaNaziv();
        so.templateExecute(nazivV);
        ArrayList<Vozilo> vozilaPoNazivu = so.getVozila();
        if (vozilaPoNazivu.isEmpty()) {
            throw new Exception("Sistem nije uspeo ucita vozila");
        }
        return vozilaPoNazivu;
    }


    
    public Vozilo pretraziIDVozilo(Vozilo idV) throws Exception {
        SOPretraziVoziloID so = new SOPretraziVoziloID();
        so.templateExecute(idV);
        Vozilo v = so.getV();
        
        if (v==null) {
            throw new Exception("Sistem nije uspeo da ucita vozilo");
        }
        return v;
    }
    
    
    /* ///////////////////////////////////////////////////////////////////////////////////////////////// */

    

    public ArrayList<Rezervacija> getListRez() throws Exception {
        SOGetListRezervacija so = new SOGetListRezervacija();
        so.templateExecute(new Rezervacija());
        ArrayList<Rezervacija> rezervacije = so.getRezervacije();
        if (rezervacije.isEmpty()) {
            throw new Exception("Sistem nije uspeo ucita sve rez");
        }
        return rezervacije;
        
    }

    public boolean dodajStavku(StavkaRezervacije sr) throws Exception {
        SOKreirajStavku so = new SOKreirajStavku();
        so.templateExecute(sr);
        int id = so.getId();
        if (id == -1) {
            throw new Exception("Rez nije dodata!!!");
        }
        return true;
    }
    
    

    public boolean dodajRez(Rezervacija r) throws Exception {
        SOKreirajRez so = new SOKreirajRez();
        so.templateExecute(r);
        int id = so.getId();
        if (id == -1) {
            throw new Exception("Rez nije dodata!!!");
        }
        return true;
    }
    

    public boolean obrisiRezu(Rezervacija reza) throws Exception {
        try {
            SOObrisiRezervaciju so = new SOObrisiRezervaciju();
            so.templateExecute(reza);
            int affectedRows = so.getAffectedRows();
            if (affectedRows == 0) {
                throw new Exception("Sistem nije uspeo da izbrise rez");

            }
        } catch (Exception e) {
            throw new Exception("Ne mozete izbrisati rez");
        }
        return true;
    }
    
   

    public ArrayList<Rezervacija> pretraziRez(LocalDate datum) {
        return dbb.pretraziRezervacije(datum);
    }

    public ArrayList<StavkaRezervacije> getListStavke() throws Exception {
        SOGetListStavke so = new SOGetListStavke();
        so.templateExecute(new StavkaRezervacije());
        ArrayList<StavkaRezervacije> sr = so.getSr();
        if (sr.isEmpty()) {
            throw new Exception("Sistem nije uspeo ucita sve stavke");
        }
        return sr;
    }
    
    
    public boolean obrisiStavku(StavkaRezervacije stavka) throws Exception {
        try {
            SOObrisiStavku so = new SOObrisiStavku();
            so.templateExecute(stavka);
            int affectedRows = so.getAffectedRows();
            if (affectedRows == 0) {
                throw new Exception("Sistem nije uspeo da izbrise stavku");

            }
        } catch (Exception e) {
            throw new Exception("Ne mozete izbrisati stavku");
        }
        return true;
    }

    public boolean apdejtujIznos(int id) throws SQLException {
        return dbb.apdejtujIznosRezervacije(id);
    }

    public Rezervacija pretraziIDRez(Rezervacija idR) throws Exception {
        SOPretraziRezID so = new SOPretraziRezID();
        so.templateExecute(idR);
        Rezervacija r = so.getR();
        
        if (r==null) {
            throw new Exception("Sistem nije uspeo da ucita rezervaciju");
        }
        return r;
    }
    
    

    public ArrayList<Rezervacija> pretraziRezModelKlijent(String modelKlijent) {
        System.out.println("Unos iz text fielda: '" + modelKlijent + "'");

        return dbb.vratiRezModelKlijent(modelKlijent);
    }

    

    public boolean izmeniRez(Rezervacija rez) throws Exception {
         SOIzmeniRez so = new SOIzmeniRez();
        so.templateExecute(rez);
        int affectedRows = so.getAffectedRows();
        if (affectedRows == 0) {
            throw new Exception("Sistem nije uspeo da izmeni rezervaciju");
        }
        return true;
    }

    public boolean izmeniStavkuRez(StavkaRezervacije srez) throws Exception {
        SOIzmeniStavku so = new SOIzmeniStavku();
        so.templateExecute(srez);
        int affectedRows = so.getAffectedRows();
        if (affectedRows == 0) {
            throw new Exception("Sistem nije uspeo da izmeni stavku");
        }
        return true;
    }

    public ArrayList<StavkaRezervacije> getListStavkiPoID(StavkaRezervacije sr) throws Exception {
        SOVratiStavkeRez so = new SOVratiStavkeRez();
        so.templateExecute(sr);
        ArrayList<StavkaRezervacije> stavkeRez = so.getStavke();
        if (stavkeRez.isEmpty()) {
            throw new Exception("Sistem nije uspeo ucita stavke");
        }
        return stavkeRez;
    }

    public ArrayList<Rezervacija> pretraziZapID(Rezervacija zapR) throws Exception {
        SOPretraziRezPoZap so = new SOPretraziRezPoZap();
        so.templateExecute(zapR);
        ArrayList<Rezervacija> zapPoID = so.getReeze();
        if (zapPoID.isEmpty()) {
            throw new Exception("Sistem nije uspeo ucita rez");
        }
        return zapPoID;
    }

    public ArrayList<Rezervacija> pretraziRezIDKlijent(Rezervacija klijentR) throws Exception {
        SOPretraziRezPoKlijentID so = new SOPretraziRezPoKlijentID();
        so.templateExecute(klijentR);
        ArrayList<Rezervacija> klijentID = so.getReeze();
        if (klijentID.isEmpty()) {
            throw new Exception("Sistem nije uspeo ucita rez");
        }
        return klijentID;
    }

    public ArrayList<Rezervacija> pretraziRezModel(Rezervacija modelR) throws Exception {
        SOPretraziRezPoVozilu so = new SOPretraziRezPoVozilu();
        so.templateExecute(modelR);
        ArrayList<Rezervacija> rezModel = so.getReze();
        if (rezModel.isEmpty()) {
            throw new Exception("Sistem nije uspeo ucita rez");
        }
        return rezModel;
    }

    

    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import klase.Zaposleni;
import klase.Mesto;
import klase.RadnaSmena;
import klase.Rezervacija;
import klase.StavkaRezervacije;
import klase.Vozilo;
import klase.ZRS;
import klase.Klijent;
import konstante.Operacije;
import logika.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

public class ObradaKlijentskihZahteva extends Thread {

    private Socket s;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            switch (kz.getOperacija()) {
                case Operacije.LOGIN:
                    HashMap<Integer, String> mapa = (HashMap<Integer, String>) kz.getParametar();
                    String username = mapa.get(1);
                    String password = mapa.get(2);
                    Zaposleni zaposleni = Kontroler.getInstance().login(username, password);
                    so.setOdgovor(zaposleni);
                    break;

                case Operacije.VRATI_ZAPOSLENE:
                    ArrayList<Zaposleni> listaZaposlenih = null;
                    try {
                        listaZaposlenih = Kontroler.getInstance().vratiZaposlene();
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(listaZaposlenih);

                    break;    
                    
                    
                case Operacije.DODAJ_ZAPOSLENOG:
                    Zaposleni z = (Zaposleni) kz.getParametar();
                    boolean uspesno = false;
                    try {
                        uspesno = Kontroler.getInstance().dodajZaposlenog(z);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(uspesno);

                    break;

                case Operacije.OBRISI_ZAPOSLENOG:

                    Zaposleni zap = (Zaposleni) kz.getParametar();

                    boolean obrisan = false;
                    try {
                        obrisan = Kontroler.getInstance().obrisiZaposlenog(zap);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(obrisan);

                    break;

                case Operacije.IZMENI_ZAPOSLENOG:
                    Zaposleni izmenjenZaposleni = (Zaposleni) kz.getParametar();
             
                    boolean izmenjen = false;
                    try {
                        izmenjen = Kontroler.getInstance().izmeniZaposlenog(izmenjenZaposleni);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(izmenjen);
                    
                    break;    

                case Operacije.PRETRAZI_ZAPOSLENE:
                    Zaposleni nazivZap = (Zaposleni) kz.getParametar();
                    ArrayList<Zaposleni> pretrazeniZ = new ArrayList<>();
                    try {
                         pretrazeniZ = Kontroler.getInstance().pretraziNazivZaposleni(nazivZap);

                        
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(pretrazeniZ);

                    break;

                    
                 case Operacije.PRETRAZI_IDZAPOSLENOG:
                    Zaposleni idZap = (Zaposleni) kz.getParametar();
                    ArrayList<Zaposleni> pretrazeniZap = new ArrayList<>();
                    try {
                        idZap = Kontroler.getInstance().pretraziIDZaposleni(idZap);

                        pretrazeniZap.add(idZap);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(pretrazeniZap);

                    break;
                    
                /*    ////////////////////////////////////////////////////////////////////////////////////////////  */
                case Operacije.POPUNI_RS:
                    ArrayList<RadnaSmena> listaRS = null;
                    try {
                        listaRS = Kontroler.getInstance().vratiSmene();
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(listaRS);

                    break;
                
                    
                case Operacije.DODAJ_RS:
                    RadnaSmena rs = (RadnaSmena) kz.getParametar();
                    boolean dodataSmena = false;
                    try {
                        dodataSmena = Kontroler.getInstance().dodajRadnuSmenu(rs);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(dodataSmena);

                    break;
 
                case Operacije.OBRISI_RS:

                    RadnaSmena idrs = (RadnaSmena) kz.getParametar();

                    boolean obrisanaSmena = false;
                    try {
                        obrisanaSmena = Kontroler.getInstance().obrisiSmenu(idrs);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(obrisanaSmena);

                    break;


                case Operacije.IZMENI_SMENU:
                    RadnaSmena izmenjenaSmena = (RadnaSmena) kz.getParametar();
             
                    boolean izmenjenaRS = false;
                    try {
                        izmenjenaRS = Kontroler.getInstance().izmeniSmenu(izmenjenaSmena);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(izmenjenaRS);
                    
                    break; 
                


                case Operacije.PRETRAZI_SMENE:
                    RadnaSmena pocetakRS = (RadnaSmena) kz.getParametar();
                    ArrayList<RadnaSmena> pretrazeneRS = new ArrayList<>();
                    try {
                         pretrazeneRS = Kontroler.getInstance().pretraziRS(pocetakRS);

                        
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(pretrazeneRS);

                    break;    
                    

                case Operacije.PRETRAZI_IDSMENE:
                    RadnaSmena idsmena = (RadnaSmena) kz.getParametar();
                    ArrayList<RadnaSmena> pretrazenaRS = new ArrayList<>();
                    try {
                        idsmena = Kontroler.getInstance().pretraziIDRS(idsmena);

                        pretrazenaRS.add(idsmena);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(pretrazenaRS);

                    break;
                /*    ////////////////////////////////////////////////////////////////////////////////////////////  */

                case Operacije.VRATI_CB_ZAPOSLENIH:
                    ArrayList<Zaposleni> cbZap = null;
                    try {
                        cbZap = Kontroler.getInstance().vratiZaposlene();
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(cbZap);

                    break;

                case Operacije.VRATI_CB_SMENE:
                    ArrayList<RadnaSmena> cbSmene = null;
                    try {
                        cbSmene = Kontroler.getInstance().vratiSmene();
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(cbSmene);

                    break;

                case Operacije.DODAJ_ZRS:
                    ZRS zrs = (ZRS) kz.getParametar();
                    boolean dodataZRS = false;
                    try {
                        dodataZRS = Kontroler.getInstance().dodajZRS(zrs);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(dodataZRS);

                    break;

                case Operacije.POPUNI_ZRS:
                    ArrayList<ZRS> listaZRS = null;
                    try {
                        listaZRS = Kontroler.getInstance().vratiZRS();
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(listaZRS);
                    break;
                    
                case Operacije.OBRISI_ZRS:
                    ZRS idzrs = (ZRS) kz.getParametar();

                    boolean obrisanaZrs = false;
                    try {
                        obrisanaZrs = Kontroler.getInstance().obrisiZRS(idzrs);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(obrisanaZrs);
                    break;

                /*    ////////////////////////////////////////////////////////////////////////////////////////////  */
                case Operacije.VRATI_MESTA:
                    ArrayList<Mesto> listaMesta = null;
                    try {
                        listaMesta = Kontroler.getInstance().getListMesta();
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(listaMesta);

                    break;

                case Operacije.DODAJ_MESTO:
                    Mesto m = (Mesto) kz.getParametar();
                    boolean dodatoMesto = false;
                    try {
                        dodatoMesto = Kontroler.getInstance().dodajMesto(m);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(dodatoMesto);

                    break;

                case Operacije.OBRISI_MESTO:
                
                    Mesto meesto = (Mesto) kz.getParametar();
                    
                    
                    boolean obrisanoMesto = false;
                    try {
                        obrisanoMesto = Kontroler.getInstance().obrisiMesto(meesto);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(obrisanoMesto);
                    
                    break;


                case Operacije.IZMENI_MESTO:
                
                    Mesto izmenjenoMesto = (Mesto) kz.getParametar();
                    
                    
                    boolean izmenjenNaziv = false;
                    try {
                        izmenjenNaziv = Kontroler.getInstance().izmeniMesto(izmenjenoMesto);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(izmenjenNaziv);
                    
                    break;
//                

                case Operacije.PRETRAZI_IDMESTA:
                
                    Mesto idMesta = (Mesto) kz.getParametar();
                    ArrayList<Mesto> pretrazenoMesto = new ArrayList<>();
                    try {
                        idMesta = Kontroler.getInstance().pretraziIDMesta(idMesta);
                        
                        pretrazenoMesto.add(idMesta);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(pretrazenoMesto);
                    
                    break;

                    
                
                    
                case Operacije.PRETRAZI_MESTA:
                    Mesto nazivM = (Mesto) kz.getParametar();
                    ArrayList<Mesto> pretrazenaM = new ArrayList<>();
                    try {
                         pretrazenaM = Kontroler.getInstance().pretraziNazivMesto(nazivM);

                        
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(pretrazenaM);

                    break;
               
                /*    ////////////////////////////////////////////////////////////////////////////////////////////  */

                case Operacije.VRATI_KLIJENTE:
                    ArrayList<Klijent> listaKlijenata = null;
                    try {
                        listaKlijenata = Kontroler.getInstance().vratiKlijente();
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(listaKlijenata);

                    break;

                case Operacije.DODAJ_KLIJENTA:
                    Klijent k = (Klijent) kz.getParametar();
                    boolean klijent = false;
                    try {
                        klijent = Kontroler.getInstance().dodajKlijenta(k);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(klijent);
                    break;

                case Operacije.OBRISI_KLIJENTA:
                    Klijent obrisiK = (Klijent) kz.getParametar();

                    boolean obrisanKlijent = false;
                    try {
                        obrisanKlijent = Kontroler.getInstance().obrisiKlijenta(obrisiK);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(obrisanKlijent);

                    break;

                case Operacije.IZMENI_KLIJENTA:
                    Klijent izmenjenKlijent = (Klijent) kz.getParametar();
             
                    boolean izmenjenK = false;
                    try {
                        izmenjenK = Kontroler.getInstance().izmeniKlijenta(izmenjenKlijent);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(izmenjenK);

                    break;

                case Operacije.PRETRAZI_KLIJENTA:
                    Klijent nazivK = (Klijent) kz.getParametar();
                    ArrayList<Klijent> pretrazeniK = new ArrayList<>();
                    try {
                         pretrazeniK = Kontroler.getInstance().pretraziNazivKlijent(nazivK);

                        
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(pretrazeniK);

                    break;

                case Operacije.VRATI_CB_ADRESA:
                    ArrayList<Mesto> listaAdresa = Kontroler.getInstance().vratiAdrese();
                    so.setOdgovor(listaAdresa);

                    break;

                case Operacije.PRETRAZI_IDKLIJENTA:
                    Klijent idK = (Klijent) kz.getParametar();
                    ArrayList<Klijent> pretrazenKlijent = new ArrayList<>();
                    try {
                        idK = Kontroler.getInstance().pretraziIDKlijent(idK);
                        
                        pretrazenKlijent.add(idK);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(pretrazenKlijent);

                    break;

                /*    ////////////////////////////////////////////////////////////////////////////////////////////  */
                case Operacije.VRATI_VOZILA:
                    ArrayList<Vozilo> listaVozila = null;
                    try {
                        listaVozila = Kontroler.getInstance().getListVozila();
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(listaVozila);

                    break;
                
                case Operacije.DODAJ_VOZILO:
                    Vozilo v = (Vozilo) kz.getParametar();
                    boolean dodatoVozilo = false;
                    try {
                        dodatoVozilo = Kontroler.getInstance().dodajVozilo(v);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(dodatoVozilo);

                    break;

                case Operacije.OBRISI_VOZILO:
                
                    Vozilo voz = (Vozilo) kz.getParametar();
                    
                    
                    boolean obrisanoVozilo = false;
                    try {
                        obrisanoVozilo = Kontroler.getInstance().obrisiVozilo(voz);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(obrisanoVozilo);
                    
                    break;

                case Operacije.IZMENI_VOZILO:
                
                    Vozilo vozilo = (Vozilo) kz.getParametar();
                    
                    
                    boolean izmenjenoVozilo = false;
                    try {
                        izmenjenoVozilo = Kontroler.getInstance().izmeniVozilo(vozilo);   
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(izmenjenoVozilo);
                    
                    break;  

                case Operacije.PRETRAZI_IDVOZILA:
                    Vozilo idV = (Vozilo) kz.getParametar();
                    ArrayList<Vozilo> pretrazenoV = new ArrayList<>();
                    try {
                        idV = Kontroler.getInstance().pretraziIDVozilo(idV);

                        pretrazenoV.add(idV);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(pretrazenoV);

                    break;

                case Operacije.PRETRAZI_VOZILA:
                    Vozilo nazivV = (Vozilo) kz.getParametar();
                    ArrayList<Vozilo> pretrazenaV = new ArrayList<>();
                    try {
                         pretrazenaV = Kontroler.getInstance().pretraziNazivVozilo(nazivV);

                        
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(pretrazenaV);

                    break;
                    
                 
                /* ///////////////////////////////////////////////////////////////////////////////////////////// */

                case Operacije.VRATI_REZERVACIJE:
                    ArrayList<Rezervacija> listaRez = null;
                    try {
                        listaRez = Kontroler.getInstance().getListRez();
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(listaRez);

                    break;

                case Operacije.KREIRAJ_STAVKU_REZ:
                    StavkaRezervacije sr = (StavkaRezervacije) kz.getParametar();
                    boolean dodataStavka = false;
                    try {
                        dodataStavka = Kontroler.getInstance().dodajStavku(sr);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(dodataStavka);

                    break;

                case Operacije.KREIRAJ_REZERVACIJU:
                    Rezervacija r = (Rezervacija) kz.getParametar();
                    boolean dodataRez = false;
                    try {
                        dodataRez = Kontroler.getInstance().dodajRez(r);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(dodataRez);

                    break;

                    
                case Operacije.IZMENI_REZERVACIJU:
                    
                    Rezervacija rez = (Rezervacija) kz.getParametar();
                    
                    
                    boolean izmenjenaRez = false;
                    try {
                        izmenjenaRez = Kontroler.getInstance().izmeniRez(rez);   
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(izmenjenaRez);
                    break;
                    
                case Operacije.OBRISI_REZERVACIJU:
                    Rezervacija reza = (Rezervacija) kz.getParametar();
                    
                    
                    boolean obrisanaRez = false;
                    try {
                        obrisanaRez = Kontroler.getInstance().obrisiRezu(reza);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(obrisanaRez);

                    break;

                case Operacije.IZMENI_STAVKU:
                    
                    StavkaRezervacije srez = (StavkaRezervacije) kz.getParametar();
                    
                    
                    boolean izmenjenaStavka = false;
                    try {
                        izmenjenaStavka = Kontroler.getInstance().izmeniStavkuRez(srez);   
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(izmenjenaStavka);
                    break;
                    


                case Operacije.PRETRAZI_REZERVACIJUID:
                    Rezervacija idR = (Rezervacija) kz.getParametar();
                    ArrayList<Rezervacija> pretrazenaR = new ArrayList<>();
                    try {
                        idR = Kontroler.getInstance().pretraziIDRez(idR);

                        pretrazenaR.add(idR);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(pretrazenaR);

                    break;

                case Operacije.PRETRAZI_REZERVACIJE_PO_ZAP:
                    Rezervacija zapR = (Rezervacija) kz.getParametar();
                    ArrayList<Rezervacija> listaRezZap = new ArrayList<>();
                    try {
                         listaRezZap = Kontroler.getInstance().pretraziZapID(zapR);

                        
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(listaRezZap);

                    break;
                    
                    
                case Operacije.PRETRAZI_REZERVACIJE_PO_KLIJENTU:
                    Rezervacija klijentR = (Rezervacija) kz.getParametar();
                    ArrayList<Rezervacija> listaRezKlijent = new ArrayList<>();
                    try {
                         listaRezKlijent = Kontroler.getInstance().pretraziRezIDKlijent(klijentR);

                        
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(listaRezKlijent);

                    break;
                  
                case Operacije.PRETRAZI_REZERVACIJE_PO_MODELU:
                    Rezervacija modelR = (Rezervacija) kz.getParametar();
                    ArrayList<Rezervacija> listaRezModel = new ArrayList<>();
                    try {
                        listaRezModel = Kontroler.getInstance().pretraziRezModel(modelR);

                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(listaRezModel);

                    break;
                    
//
//                case Operacije.PRETRAZI_REZERVACIJE_PO_MODELU_ILI_KLIJENTU:
//                    String modelKlijent = (String) kz.getParametar();
//
//                    ArrayList<Rezervacija> pretrazeneRezervacije = Kontroler.getInstance().pretraziRezModelKlijent(modelKlijent);
//                    so.setOdgovor(pretrazeneRezervacije);
//
//                    break;

                case Operacije.VRATI_STAVKE_IDREZ:
                    StavkaRezervacije stavkerez = (StavkaRezervacije) kz.getParametar();
                    
                    ArrayList<StavkaRezervacije> listaStavkiRez = null;
                    try {
                        listaStavkiRez = Kontroler.getInstance().getListStavkiPoID(stavkerez);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(listaStavkiRez);

                    break;
                    
                case Operacije.VRATI_STAVKE:
                    ArrayList<StavkaRezervacije> listaStavki = null;
                    try {
                        listaStavki = Kontroler.getInstance().getListStavke();
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(listaStavki);

                    break;

                case Operacije.OBRISI_STAVKU:
                    StavkaRezervacije stavka = (StavkaRezervacije) kz.getParametar();
                    
                    
                    boolean obrisanaStavka = false;
                    try {
                        obrisanaStavka = Kontroler.getInstance().obrisiStavku(stavka);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    so.setOdgovor(obrisanaStavka);

                    break;

//                case Operacije.UPDATE_IZNOS:
//                    int idiznos = (int) kz.getParametar();
//                     {
//                        try {
//                            boolean izmenjenIznos = Kontroler.getInstance().apdejtujIznos(idiznos);
//                            so.setOdgovor(izmenjenIznos);
//                        } catch (SQLException ex) {
//                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//
//                    break;

            }
            posaljiOdgovor(so);
        }
    }

    private KlijentskiZahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {

            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

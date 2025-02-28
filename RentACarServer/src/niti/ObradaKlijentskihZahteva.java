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
                    ArrayList<Zaposleni> listaZaposlenih = Kontroler.getInstance().vratiZaposlene();
                    so.setOdgovor(listaZaposlenih);
                    
                    break;
                    
                case Operacije.DODAJ_ZAPOSLENOG:
                    Zaposleni z = (Zaposleni) kz.getParametar();
                    boolean uspesno = Kontroler.getInstance().dodajZaposlenog(z);
                    so.setOdgovor(uspesno);
                    
                    break;
                
                case Operacije.OBRISI_ZAPOSLENOG:
                    int idz = (int) kz.getParametar();
                    boolean obrisan = Kontroler.getInstance().obrisiZaposlenog(idz);
                    so.setOdgovor(obrisan);
                    
                    break;
                    
                case Operacije.IZMENI_ZAPOSLENOG:
                    Zaposleni izmenjenZaposleni = (Zaposleni) kz.getParametar();
                    boolean izmenjen = Kontroler.getInstance().izmeniZaposlenog(izmenjenZaposleni);
                    so.setOdgovor(izmenjen);
                    
                    break;
                    
                case Operacije.PRETRAZI_ZAPOSLENE:
                    String ime = (String) kz.getParametar();
                    ArrayList<Zaposleni> pretrazeniZ = Kontroler.getInstance().pretraziZaposlene(ime);
                    so.setOdgovor(pretrazeniZ);
                    
                    break;
                    
                /*    ////////////////////////////////////////////////////////////////////////////////////////////  */
                    
                case Operacije.POPUNI_RS:
                    ArrayList<RadnaSmena> listaRS = Kontroler.getInstance().vratiSmene();
                    so.setOdgovor(listaRS);
                    
                    break;
                    
                case Operacije.DODAJ_RS:
                    RadnaSmena rs = (RadnaSmena) kz.getParametar();
                    boolean dodataSmena = Kontroler.getInstance().dodajRadnuSmenu(rs);
                    so.setOdgovor(dodataSmena);
                    
                    break;
                    
                case Operacije.OBRISI_RS:
                    int idrs = (int) kz.getParametar();
                    boolean obrisanaSmena = Kontroler.getInstance().obrisiSmenu(idrs);
                    so.setOdgovor(obrisanaSmena);
                    
                    break;
                    
                case Operacije.IZMENI_SMENU:
                    RadnaSmena izmenjenaSmena = (RadnaSmena) kz.getParametar();
                    boolean izmenjenaRS = Kontroler.getInstance().izmeniSmenu(izmenjenaSmena);
                    so.setOdgovor(izmenjenaRS);
                    
                    break;
                    
                case Operacije.PRETRAZI_SMENE:
                    String pocetak = (String) kz.getParametar();
                    ArrayList<RadnaSmena> pretrazeneRS = Kontroler.getInstance().pretraziRS(pocetak);
                    so.setOdgovor(pretrazeneRS);
                    
                    break;
                    
                /*    ////////////////////////////////////////////////////////////////////////////////////////////  */
                    
                case Operacije.VRATI_CB_ZAPOSLENIH:
                    ArrayList<Zaposleni> listaZap = Kontroler.getInstance().vratiCBZaposlene();
                    so.setOdgovor(listaZap);
                    
                    break;
                    
                case Operacije.VRATI_CB_SMENE:
                    ArrayList<RadnaSmena> rsmena = Kontroler.getInstance().vratiCBRadnaSmena();
                    so.setOdgovor(rsmena);
                    
                    break;
                    
                case Operacije.DODAJ_ZRS:
                    ZRS zrs = (ZRS) kz.getParametar();
                    boolean dodataZrs = Kontroler.getInstance().dodajZrs(zrs);
                    so.setOdgovor(dodataZrs);
                    
                    break;
                    
                case Operacije.POPUNI_ZRS:
                    ArrayList<ZRS> listaZRS = Kontroler.getInstance().vratiZRSTabelu();
                    so.setOdgovor(listaZRS);
                    
                    break;
                    
                case Operacije.OBRISI_ZRS:
                    ZRS raspored = (ZRS) kz.getParametar();
                    boolean obrisanaZRS = Kontroler.getInstance().obrisiZRS(raspored);
                    so.setOdgovor(obrisanaZRS);
                    
                    break;
                    
                /*    ////////////////////////////////////////////////////////////////////////////////////////////  */
                    
                case Operacije.VRATI_MESTA:
                    ArrayList<Mesto> listaMesta = Kontroler.getInstance().vratiMesta();
                    so.setOdgovor(listaMesta);
                    
                    break;
                    
                case Operacije.DODAJ_MESTO:
                    Mesto m = (Mesto) kz.getParametar();
                    boolean dodatoMesto = Kontroler.getInstance().dodajMesto(m);
                    so.setOdgovor(dodatoMesto);
                    
                    break;
                    
                case Operacije.OBRISI_MESTO:
                    int idm = (int) kz.getParametar();
                    boolean obrisanoMesto = Kontroler.getInstance().obrisiMesto(idm);
                    so.setOdgovor(obrisanoMesto);
                    
                    break;
                    
                case Operacije.IZMENI_MESTO:
                    Mesto izmenjenoMesto = (Mesto) kz.getParametar();
                    boolean izmenjenNaziv = Kontroler.getInstance().izmeniMesto(izmenjenoMesto);
                    so.setOdgovor(izmenjenNaziv);
                    
                    break;
                    
                case Operacije.PRETRAZI_MESTA:
                    String grad = (String) kz.getParametar();
                    ArrayList<Mesto> pretrazenoM = Kontroler.getInstance().pretraziMesta(grad);
                    so.setOdgovor(pretrazenoM);
                    
                    break;
                    
                /*    ////////////////////////////////////////////////////////////////////////////////////////////  */
                    
                case Operacije.VRATI_KLIJENTE:
                    ArrayList<Klijent> listaKlijenata = Kontroler.getInstance().vratiKlijente();
                    so.setOdgovor(listaKlijenata);
                    
                    break;
                    
                case Operacije.DODAJ_KLIJENTA:
                    Klijent k = (Klijent) kz.getParametar();
                    boolean klijent = Kontroler.getInstance().kreirajKlijenta(k);
                    so.setOdgovor(klijent);
                    
                    break;
                    
                case Operacije.OBRISI_KLIJENTA:
                    int idk = (int) kz.getParametar();
                    boolean obrisanKlijent = Kontroler.getInstance().obrisiKlijenta(idk);
                    so.setOdgovor(obrisanKlijent);
                    
                    break;
                    
                case Operacije.IZMENI_KLIJENTA:
                    Klijent izmenjenKlijent = (Klijent) kz.getParametar();
                    boolean izmenjenK = Kontroler.getInstance().izmeniKlijenta(izmenjenKlijent);
                    so.setOdgovor(izmenjenK);
                    
                    break;
                    
                case Operacije.PRETRAZI_KLIJENTA:
                    String imek = (String) kz.getParametar();
                    ArrayList<Klijent> pretrazeniK = Kontroler.getInstance().pretraziKlijente(imek);
                    so.setOdgovor(pretrazeniK);
                    
                    break;
                    
                case Operacije.VRATI_CB_ADRESA:
                    ArrayList<Mesto> listaAdresa = Kontroler.getInstance().vratiAdrese();
                    so.setOdgovor(listaAdresa);
                    
                    break;
                    
                /*    ////////////////////////////////////////////////////////////////////////////////////////////  */
                    
                case Operacije.VRATI_VOZILA:
                    ArrayList<Vozilo> listaVozila = Kontroler.getInstance().vratiVozila();
                    so.setOdgovor(listaVozila);
                    
                    break;
                    
                case Operacije.DODAJ_VOZILO:
                    Vozilo v = (Vozilo) kz.getParametar();
                    boolean dodatoVozilo = Kontroler.getInstance().dodajVozilo(v);
                    so.setOdgovor(dodatoVozilo);
                    
                    break;
                    
                case Operacije.OBRISI_VOZILO:
                    int idv = (int) kz.getParametar();
                    boolean obrisanoVozilo = Kontroler.getInstance().obrisiVozilo(idv);
                    so.setOdgovor(obrisanoVozilo);
                    
                    break;
                    
                case Operacije.IZMENI_VOZILO:
                    Vozilo vozilo = (Vozilo) kz.getParametar();
                    boolean izmenjenoVozilo = Kontroler.getInstance().izmeniVozilo(vozilo);
                    so.setOdgovor(izmenjenoVozilo);
                    
                    break;
                    
                case Operacije.PRETRAZI_VOZILA:
                    String model = (String) kz.getParametar();
                    ArrayList<Vozilo> pretrazenaV = Kontroler.getInstance().pretraziVozila(model);
                    so.setOdgovor(pretrazenaV);
                    
                    break;
                    
                /* ///////////////////////////////////////////////////////////////////////////////////////////// */
                    
                case Operacije.VRATI_REZERVACIJE:
                    ArrayList<Rezervacija> listaRez = Kontroler.getInstance().vratiRezervacije();
                    so.setOdgovor(listaRez);
                    
                    break;
                    
                case Operacije.KREIRAJ_STAVKU_REZ:
                    StavkaRezervacije sr = (StavkaRezervacije) kz.getParametar();
                    boolean kreirano = Kontroler.getInstance().kreirajStavkuRez(sr);
                    so.setOdgovor(kreirano);
                    
                    break;
                    
                case Operacije.KREIRAJ_REZERVACIJU:
                    Rezervacija r = (Rezervacija) kz.getParametar();
                    boolean kreiranaRez = Kontroler.getInstance().kreirajRez(r);
                    so.setOdgovor(kreiranaRez);
                    
                    break;
                    
                case Operacije.OBRISI_REZERVACIJU:
                    int idr = (int) kz.getParametar();
                    boolean obrisanaRez = Kontroler.getInstance().obrisiRezervaciju(idr);
                    so.setOdgovor(obrisanaRez);
                    
                    break;
                    
                case Operacije.PRETRAZI_REZERVACIJE:
                    LocalDate datum = (LocalDate) kz.getParametar();
                    ArrayList<Rezervacija> pretrazeneRez = Kontroler.getInstance().pretraziRez(datum);
                    so.setOdgovor(pretrazeneRez);
                    
                    break;
                    
                case Operacije.VRATI_STAVKE:
                    int rezID = (int) kz.getParametar();
                    ArrayList<StavkaRezervacije> listaStavki = Kontroler.getInstance().vratiStavkeRez(rezID);
                    so.setOdgovor(listaStavki);
                    
                    break;
                    
                case Operacije.OBRISI_STAVKU:
                    int rb = (int) kz.getParametar();
                    boolean obrisanaStavka = Kontroler.getInstance().obrisiStavku(rb);
                    so.setOdgovor(obrisanaStavka);
                    
                    break;
                    
                case Operacije.UPDATE_IZNOS:
                    int id = (int) kz.getParametar();
                {
                    try {
                        boolean izmenjenIznos=Kontroler.getInstance().apdejtujIznos(id);
                        so.setOdgovor(izmenjenIznos);
                    } catch (SQLException ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    
                    break;

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

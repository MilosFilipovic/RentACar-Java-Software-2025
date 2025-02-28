/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import klase.Zaposleni;
import klase.Mesto;
import klase.RadnaSmena;
import klase.Rezervacija;
import klase.StavkaRezervacije;
import klase.Vozilo;
import klase.ZRS;
import klase.Klijent;
import java.sql.*;
import java.time.*;


public class DBBroker {


    public Zaposleni login(String username, String password) {
        String upit = "SELECT * FROM zaposleni";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

                Zaposleni zaposleni = new Zaposleni(rs.getInt("idZaposleni"),
                        rs.getString("ime"), rs.getString("prezime"), rs.getString("lozinka"),
                        rs.getString("korisnickoIme"));

                if (zaposleni.getKorisnickoIme().equals(username)
                        && zaposleni.getLozinka().equals(password)) {
                    return zaposleni;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public ArrayList<Zaposleni> vratiZaposlene() {
        ArrayList<Zaposleni> lista = new ArrayList<>();
        String upit = "SELECT * FROM zaposleni";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

                Zaposleni z = new Zaposleni(rs.getInt("idZaposleni"), rs.getString("ime"), rs.getString("prezime"), rs.getString("lozinka"), rs.getString("korisnickoIme"));
                lista.add(z);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean dodajZaposlenog(Zaposleni z) throws SQLException{
        String naredba = "INSERT INTO zaposleni VALUES (?,?,?,?,?)";
        int novaZaposleni = vratiZaposleniID();
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            z.setIdZaposleni(novaZaposleni);// OBAVEZNO SETUJEMO NA NOVI ID !
           
            ps.setInt(1, z.getIdZaposleni());
            ps.setString(2, z.getIme());
            ps.setString(3, z.getPrezime());
            ps.setString(4, z.getLozinka());
            ps.setString(5, z.getKorisnickoIme());

            ps.executeUpdate(); // INSERT INTO PROGNOZA VALUES (1, '2020-10-10','Super', 1)

            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private int vratiZaposleniID() {
        String upit = "SELECT MAX(idZaposleni) FROM zaposleni";
        int maxID = 0;
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                maxID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ++maxID;
    }
    
//    public boolean dodajZaposlenog(Zaposleni z){
//        String naredba = "INSERT INTO zaposleni (ime, prezime, lozinka, korisnickoIme) VALUES (?,?,?,?)";
//        try {
//            
//            PreparedStatement ps = Konekcija.getInstance().getConnection()
//                    // dodajemo u prepareStatement Statement.RETURN_GENERATED_KEYS
//                    // da bismo imali generisan kljuc u psu
//                    .prepareStatement(naredba, Statement.RETURN_GENERATED_KEYS);
//
//            ps.setString(1, z.getIme());
//            ps.setString(2, z.getPrezime());
//            ps.setString(3, z.getLozinka());
//            ps.setString(4, z.getKorisnickoIme());
//            
//            
//            ps.executeUpdate();
//
//            
//            // nakon executeUpdate se kljuc generise, pa uzimamo taj automatski generisan ID
//            ResultSet tableKeys = ps.getGeneratedKeys(); // vraca tabelu sa generisanim kljucem
//            tableKeys.next();  // ulazimo u red gde je novi ID
//            int autoZaposleniID = tableKeys.getInt(1); // uzimamo taj ID
//
//
//            z.setIdZaposleni(autoZaposleniID);// setujemo ga za nasu prognozu
//            Konekcija.getInstance().getConnection().commit();
//            
//            return true;
//
//        } catch (SQLException ex) {
//           if (Konekcija.getInstance().getConnection() != null) {
//            try {
//                Konekcija.getInstance().getConnection().rollback();  //Ovaj rollback će sada imati efekat
//                System.out.println("Rollback izvršen!");
//            } catch (SQLException rollbackEx) {
//                rollbackEx.printStackTrace();
//            }
//        }
//        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        return false;
//
//            
//    }
//    }

    public boolean obrisiZaposlenog(int idz) {
        String naredba = "DELETE FROM zaposleni WHERE idZaposleni = ?";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            ps.setInt(1, idz);
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
           if (Konekcija.getInstance().getConnection() != null) {
            try {
                Konekcija.getInstance().getConnection().rollback();  //Ovaj rollback će sada imati efekat
                System.out.println("Rollback izvršen!");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
                }
            }
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        return false;
        }
    
        
    }

    public boolean izmeniZaposlenog(Zaposleni izmenjenZaposleni) throws SQLException {
        String naredba = """
                         UPDATE zaposleni
                         SET ime = ?, prezime = ?, korisnickoIme = ?
                         WHERE idZaposleni = ?""";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            ps.setString(1, izmenjenZaposleni.getIme());
            ps.setString(2, izmenjenZaposleni.getPrezime());
            ps.setString(3, izmenjenZaposleni.getKorisnickoIme());
            ps.setInt(4, izmenjenZaposleni.getIdZaposleni());
            
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    public ArrayList<Zaposleni> pretraziZaposlene(String ime) {
        ArrayList<Zaposleni> lista = new ArrayList<>();
        String upit = "SELECT * FROM zaposleni WHERE ime LIKE ?";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, "%" + ime + "%"); // Postavljamo parametar u upit
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Zaposleni z = new Zaposleni(rs.getInt("idZaposleni"), rs.getString("ime"), 
                        rs.getString("prezime"), rs.getString("lozinka"), rs.getString("korisnickoIme"));
                lista.add(z);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
   

    public ArrayList<RadnaSmena> vratiSmene() {
        ArrayList<RadnaSmena> lista = new ArrayList<>();
        String upit = "SELECT * FROM radnasmena";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                
                int id = rs.getInt("idSmena");
               
                LocalTime pocetakSmene = rs.getObject("pocetakSmene", LocalTime.class);
                LocalTime krajSmene = rs.getObject("krajSmene", LocalTime.class);

                RadnaSmena radnaSmena = new RadnaSmena(id, pocetakSmene, krajSmene);
                lista.add(radnaSmena);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean dodajRadnuSmenu(RadnaSmena rs) throws SQLException {
        String naredba = "INSERT INTO radnasmena VALUES (?,?,?)";
        int novaSmena = vratiSmenuID();
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            rs.setIdSmena(novaSmena);// OBAVEZNO SETUJEMO NA NOVI ID !
           
            ps.setInt(1, rs.getIdSmena());
            ps.setTime(2, java.sql.Time.valueOf(rs.getPocetakSmene()));
            ps.setTime(3, java.sql.Time.valueOf(rs.getKrajSmene()));

            ps.executeUpdate(); // INSERT INTO PROGNOZA VALUES (1, '2020-10-10','Super', 1)

            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private int vratiSmenuID() {
        String upit = "SELECT MAX(idSmena) FROM radnasmena";
        int maxID = 0;
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                maxID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ++maxID;
    }

    public boolean obrisiSmenu(int idrs) {
        String naredba = "DELETE FROM radnasmena WHERE idSmena = ?";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            ps.setInt(1, idrs);
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
           if (Konekcija.getInstance().getConnection() != null) {
            try {
                Konekcija.getInstance().getConnection().rollback();  //Ovaj rollback će sada imati efekat
                System.out.println("Rollback izvršen!");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
                }
            }
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        return false;
        }
    }

    public boolean izmeniSmenu(RadnaSmena izmenjenaSmena) throws SQLException {
        String naredba = """
                         UPDATE radnasmena
                         SET pocetakSmene = ?, krajSmene = ?
                         WHERE idSmena = ?""";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            ps.setTime(1, java.sql.Time.valueOf(izmenjenaSmena.getPocetakSmene()));
            ps.setTime(2, java.sql.Time.valueOf(izmenjenaSmena.getKrajSmene()));
            ps.setInt(3, izmenjenaSmena.getIdSmena());
            
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<RadnaSmena> pretraziSmene(String pocetak) {
        ArrayList<RadnaSmena> lista = new ArrayList<>();
        String upit = "SELECT * FROM radnasmena WHERE pocetakSmene = ?";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setTime(1, java.sql.Time.valueOf(pocetak)); // Postavljamo parametar u upit
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("idSmena");
               
                LocalTime pocetakSmene = rs.getObject("pocetakSmene", LocalTime.class);
                LocalTime krajSmene = rs.getObject("krajSmene", LocalTime.class);

                RadnaSmena radnaSmena = new RadnaSmena(id, pocetakSmene, krajSmene);
                lista.add(radnaSmena);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public ArrayList<Zaposleni> vratiCBZaposlene() {
        ArrayList<Zaposleni> lista = new ArrayList<>();
        String upit = "SELECT * FROM zaposleni";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

                Zaposleni z = new Zaposleni(rs.getInt("idZaposleni"), rs.getString("ime"),
                        rs.getString("prezime"), rs.getString("lozinka"), rs.getString("korisnickoIme"));
                lista.add(z);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public ArrayList<RadnaSmena> vratiCBRadnaSmena() {
        ArrayList<RadnaSmena> lista = new ArrayList<>();
        String upit = "SELECT * FROM radnasmena";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

                int id = rs.getInt("idSmena");
               
                LocalTime pocetakSmene = rs.getObject("pocetakSmene", LocalTime.class);
                LocalTime krajSmene = rs.getObject("krajSmene", LocalTime.class);

                RadnaSmena rsmena = new RadnaSmena(id, pocetakSmene, krajSmene);
                lista.add(rsmena);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean dodajZrs(ZRS zrs) throws SQLException {
        String naredba = "INSERT INTO zrs VALUES (?,?,?)";
        
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            
           
            ps.setDate(1, java.sql.Date.valueOf(zrs.getDatumRada()));
            ps.setInt(2, zrs.getIdZaposleni());
            ps.setInt(3, zrs.getIdSmena());

            ps.executeUpdate(); // INSERT INTO PROGNOZA VALUES (1, '2020-10-10','Super', 1)

            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<ZRS> vratiZRSTabelu() {
        ArrayList<ZRS> lista = new ArrayList<>();
        String upit = "SELECT * FROM zrs";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

                LocalDate datum = rs.getObject("datumRada", LocalDate.class);
               
                
                ZRS zrsmena = new ZRS( datum, rs.getInt("idZaposlen"), rs.getInt("idSmena"));
                lista.add(zrsmena);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean obrisiZRS(ZRS raspored) {
        String naredba = "DELETE FROM zrs WHERE datumRada=? AND idZaposlen=? AND idSmena=?";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            ps.setDate(1, java.sql.Date.valueOf(raspored.getDatumRada()));
            ps.setInt(2, raspored.getIdZaposleni());
            ps.setInt(3, raspored.getIdSmena());
            
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
           if (Konekcija.getInstance().getConnection() != null) {
            try {
                Konekcija.getInstance().getConnection().rollback();  //Ovaj rollback će sada imati efekat
                System.out.println("Rollback izvršen!");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
                }
            }
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        return false;
        }
    }

    public ArrayList<Mesto> vratiMesta() {
        ArrayList<Mesto> lista = new ArrayList<>();
        String upit = "SELECT * FROM mesto";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                
//                int id = rs.getInt("idMesto");
               
                

                Mesto mesto = new Mesto(rs.getInt("idMesta"), rs.getString("nazivMesta"), rs.getString("adresa"));
                lista.add(mesto);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean dodajMesto(Mesto m) throws SQLException {
        String naredba = "INSERT INTO mesto VALUES (?,?,?)";
        int novID = vratiMestoID();
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            m.setIdMesta(novID);
           
            ps.setString(1, m.getNazivMesta());
            ps.setString(2, m.getAdresa());
            ps.setInt(3, m.getIdMesta());
            

            ps.executeUpdate(); // INSERT INTO PROGNOZA VALUES (1, '2020-10-10','Super', 1)

            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private int vratiMestoID() {
        String upit = "SELECT MAX(idMesta) FROM mesto";
        int maxID = 0;
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                maxID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ++maxID;
    }
    

    public boolean obrisiMesto(int idm) {
        String naredba = "DELETE FROM mesto WHERE idMesta = ?";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            ps.setInt(1, idm);
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
           if (Konekcija.getInstance().getConnection() != null) {
            try {
                Konekcija.getInstance().getConnection().rollback();  //Ovaj rollback će sada imati efekat
                System.out.println("Rollback izvršen!");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
                }
            }
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        return false;
        }
    }

    public boolean izmeniMesto(Mesto izmenjenoMesto) throws SQLException {
        String naredba = """
                         UPDATE mesto
                         SET nazivMesta = ?, adresa = ?
                         WHERE idMesta = ?""";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            
            
            ps.setString(1, izmenjenoMesto.getNazivMesta());
            ps.setString(2, izmenjenoMesto.getAdresa());
            
            ps.setInt(3, izmenjenoMesto.getIdMesta());
            
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<Mesto> pretraziMesta(String grad) {
        ArrayList<Mesto> lista = new ArrayList<>();
        String upit = "SELECT * FROM mesto WHERE nazivMesta LIKE ?";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, "%" + grad + "%"); // Postavljamo parametar u upit
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Mesto m = new Mesto(rs.getInt("idMesta"), rs.getString("nazivMesta"), rs.getString("adresa"));
                lista.add(m);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public ArrayList<Klijent> vratiKlijente() {
        ArrayList<Klijent> lista = new ArrayList<>();
        String upit = "SELECT * FROM klijent";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

                Klijent k = new Klijent(rs.getInt("idKlijent"), rs.getString("ime"), rs.getString("prezime"), rs.getString("telefon"), rs.getInt("idMesta"));
                lista.add(k);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
    
    public boolean kreirajKlijenta(Klijent k) throws SQLException {
        String naredba = "INSERT INTO klijent VALUES (?,?,?,?,?)";
        int novKlijent = vratiKlijentID();
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            k.setIdKlijent(novKlijent);// OBAVEZNO SETUJEMO NA NOVI ID !
           
            ps.setInt(1, k.getIdKlijent());
            ps.setString(2, k.getIme());
            ps.setString(3, k.getPrezime());
            ps.setString(4, k.getTelefon());
            ps.setInt(5, k.getIdMesta());

            ps.executeUpdate(); // INSERT INTO PROGNOZA VALUES (1, '2020-10-10','Super', 1)

            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private int vratiKlijentID() {
        String upit = "SELECT MAX(idKlijent) FROM klijent";
        int maxID = 0;
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                maxID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ++maxID;
    }

    public boolean obrisiKlijenta(int idk) {
        String naredba = "DELETE FROM klijent WHERE idKlijent = ?";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            ps.setInt(1, idk);
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
           if (Konekcija.getInstance().getConnection() != null) {
            try {
                Konekcija.getInstance().getConnection().rollback();  //Ovaj rollback će sada imati efekat
                System.out.println("Rollback izvršen!");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
                }
            }
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        return false;
        }
    }

    public boolean izmeniKlijenta(Klijent izmenjenKlijent) throws SQLException {
        String naredba = """
                         UPDATE klijent
                         SET ime = ?, prezime = ?, telefon = ?, idMesta = ?
                         WHERE idKlijent = ?""";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            ps.setString(1, izmenjenKlijent.getIme());
            ps.setString(2, izmenjenKlijent.getPrezime());
            ps.setString(3, izmenjenKlijent.getTelefon());
            ps.setInt(4, izmenjenKlijent.getIdMesta());
            
            ps.setInt(5, izmenjenKlijent.getIdKlijent());
            
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<Klijent> pretraziKlijente(String imek) {
        ArrayList<Klijent> lista = new ArrayList<>();
        String upit = "SELECT * FROM klijent WHERE ime LIKE ?";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, "%" + imek + "%"); // Postavljamo parametar u upit
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Klijent k = new Klijent(rs.getInt("idKlijent"), rs.getString("ime"), rs.getString("prezime"), rs.getString("telefon"), rs.getInt("idMesta"));
                lista.add(k);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
    
    public ArrayList<Mesto> vratiAdrese() {
        ArrayList<Mesto> lista = new ArrayList<>();
        String upit = "SELECT * FROM mesto";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

                Mesto m = new Mesto(rs.getInt("idMesta"), rs.getString("nazivMesta"), rs.getString("adresa"));
                lista.add(m);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public ArrayList<Vozilo> vratiVozila() {
        ArrayList<Vozilo> lista = new ArrayList<>();
        String upit = "SELECT * FROM vozilo";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

                Vozilo v = new Vozilo(rs.getInt("idVozilo"), rs.getString("model"), rs.getDouble("cenaDana"), rs.getString("karoserija"),
                        rs.getString("konjaza"), rs.getString("kubikaza"), rs.getString("boja"));
                lista.add(v);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean dodajVozilo(Vozilo v) throws SQLException {
        String naredba = "INSERT INTO vozilo VALUES (?,?,?,?,?,?,?)";
        int novoVozilo = vratiVoziloID();
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            v.setIdVozilo(novoVozilo);// OBAVEZNO SETUJEMO NA NOVI ID !
           
            ps.setInt(1, v.getIdVozilo());
            ps.setString(2, v.getModel());
            ps.setDouble(3, v.getCenaDana());
            ps.setString(4, v.getKaroserija());
            ps.setString(5, v.getKonjaza());
            ps.setString(6, v.getKubikaza());
            ps.setString(7, v.getBoja());

            ps.executeUpdate(); // INSERT INTO PROGNOZA VALUES (1, '2020-10-10','Super', 1)

            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private int vratiVoziloID() {
        String upit = "SELECT MAX(idVozilo) FROM vozilo";
        int maxID = 0;
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                maxID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ++maxID;
    }

    public boolean obrisiVozilo(int idv) {
        String naredba = "DELETE FROM vozilo WHERE idVozilo = ?";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            ps.setInt(1, idv);
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
           if (Konekcija.getInstance().getConnection() != null) {
            try {
                Konekcija.getInstance().getConnection().rollback();  //Ovaj rollback će sada imati efekat
                System.out.println("Rollback izvršen!");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
                }
            }
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        return false;
        }
    }

    public boolean izmeniVozilo(Vozilo vozilo) throws SQLException {
        String naredba = """
                         UPDATE vozilo
                         SET model = ?, cenaDana = ?, karoserija = ?, konjaza = ?, kubikaza = ?, boja = ?
                         WHERE idVozilo = ?""";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            ps.setString(1, vozilo.getModel());
            ps.setDouble(2, vozilo.getCenaDana());
            ps.setString(3, vozilo.getKaroserija());
            ps.setString(4, vozilo.getKonjaza());
            ps.setString(5, vozilo.getKubikaza());
            ps.setString(6, vozilo.getBoja());
            ps.setInt(7, vozilo.getIdVozilo());
            
            
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Vozilo> pretraziVozila(String model) {
        ArrayList<Vozilo> lista = new ArrayList<>();
        String upit = "SELECT * FROM vozilo WHERE model LIKE ?";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, "%" + model + "%"); // Postavljamo parametar u upit
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Vozilo v = new Vozilo(rs.getInt("idVozilo"), rs.getString("model"), rs.getDouble("cenaDana"),
                        rs.getString("karoserija"), rs.getString("konjaza"), rs.getString("kubikaza"), rs.getString("boja"));
                lista.add(v);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public ArrayList<Rezervacija> vratiRezervacije() {
        ArrayList<Rezervacija> lista = new ArrayList<>();
        String upit = "SELECT * FROM rezervacija";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

                LocalDate uzimanje = rs.getObject("datumPreuzimanja", LocalDate.class);
                LocalDate vracanje = rs.getObject("datumVracanja", LocalDate.class);
                
                Klijent klijent = vratiKlijenta(rs.getInt("idKlijent"));
                
                ArrayList<StavkaRezervacije> listaStavki = vratiStavkeRezervacije(rs.getInt("idRezervacije"));
                
                
                Rezervacija r = new Rezervacija(rs.getInt("idRezervacije"), uzimanje, vracanje, rs.getDouble("iznosRezervacije"),
                        rs.getInt("idKlijent"), klijent, rs.getInt("idZaposleni"), listaStavki);
                lista.add(r);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    private Klijent vratiKlijenta(int id) {

        Klijent klijent = null;
        String upit = "SELECT * FROM klijent WHERE idKlijent = ?";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                klijent = new Klijent(
                        rs.getInt("idKlijent"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("telefon"),
                        rs.getInt("idMesta")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return klijent;
    }

       private ArrayList<StavkaRezervacije> vratiStavkeRezervacije(int idRezervacija) {
        ArrayList<StavkaRezervacije> listaStavki = new ArrayList<>();
        String upit = "SELECT * FROM stavkarezervacije WHERE idRezervacije = ?";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, idRezervacija);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
               
                // Dohvatanje vozila prema ID-ju
                Vozilo vozilo = vratiVozilo(rs.getInt("idVozilo"));

                StavkaRezervacije stavka = new StavkaRezervacije( rs.getInt("idRezervacije"),rs.getInt("rb"), rs.getString("nazivStavke"), rs.getString("napomena"),
                        rs.getDouble("cenaVozila"), rs.getInt("brojDana"), rs.getDouble("iznosStavke"), rs.getInt("idVozilo"), vozilo);
                listaStavki.add(stavka);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaStavki;
    }

        private Vozilo vratiVozilo(int idVozilo) {
        Vozilo vozilo = null;
        String upit = "SELECT * FROM vozilo WHERE idVozilo = ?";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, idVozilo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                vozilo = new Vozilo(
                        rs.getInt("idVozilo"),
                        rs.getString("model"),
                        rs.getDouble("cenaDana"),
                        rs.getString("karoserija"),
                        rs.getString("konjaza"),
                        rs.getString("kubikaza"),
                        rs.getString("boja")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vozilo;
    }

    

    public boolean kreirajStavkuRezervacije(StavkaRezervacije sr) throws SQLException {
        String naredba = "INSERT INTO stavkarezervacije VALUES (?,?,?,?,?,?,?,?)";
        int novaStavka = vratiStavkaID();
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            sr.setRb(novaStavka);// OBAVEZNO SETUJEMO NA NOVI ID !
           
            ps.setInt(1, sr.getRb());
            ps.setString(2, sr.getNazivStavke());
            ps.setString(3, sr.getNapomena());
            ps.setDouble(4, sr.getCenaVozila());
            ps.setInt(5, sr.getBrojDana());
            ps.setDouble(6, sr.getIznosStavke());
            ps.setInt(7, sr.getIdVozilo());
            ps.setInt(8, sr.getIdrezervacije());

            ps.executeUpdate(); // INSERT INTO PROGNOZA VALUES (1, '2020-10-10','Super', 1)

            
            Konekcija.getInstance().getConnection().commit();

            boolean uspesno=apdejtujIznosRezervacije(sr.getIdrezervacije());
            
            
            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private int vratiStavkaID() {
        String upit = "SELECT MAX(rb) FROM stavkarezervacije";
        int maxID = 0;
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                maxID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ++maxID;
    }

    public boolean kreirajRezervaciju(Rezervacija r) throws SQLException {
        String naredba = "INSERT INTO rezervacija VALUES (?,?,?,?,?,?)";
        int novaRez = vratiRezID();
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            r.setIdRezervacija(novaRez);// OBAVEZNO SETUJEMO NA NOVI ID !
            
            ps.setInt(1, r.getIdRezervacija());
            ps.setDate(2, java.sql.Date.valueOf(r.getDatumPreuzimanja()));
            ps.setDate(3, java.sql.Date.valueOf(r.getDatumVracanja()));
            ps.setDouble(4, r.getIznosRezervacije());
            ps.setInt(5, r.getIdZaposleni());
            ps.setInt(6, r.getIdKlijent());
            

            ps.executeUpdate(); // INSERT INTO PROGNOZA VALUES (1, '2020-10-10','Super', 1)

            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private int vratiRezID() {
        String upit = "SELECT MAX(idRezervacije) FROM rezervacija";
        int maxID = 0;
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                maxID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ++maxID;
    }

    public boolean apdejtujIznosRezervacije(int idrezervacije) throws SQLException {
        
        String suma = "SELECT SUM(iznosStavke) FROM stavkarezervacije WHERE idrezervacije = ?";
        String naredba = "UPDATE rezervacija SET iznosRezervacije = ? WHERE idRezervacije = ?";
        

        try {
           
            PreparedStatement psSuma = Konekcija.getInstance().getConnection().prepareStatement(suma);
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);
            
            psSuma.setInt(1, idrezervacije);
            ResultSet rs = psSuma.executeQuery();

            double ukupno = 0;
            if (rs.next()) {
                ukupno = rs.getDouble(1); // Uzimamo izračunatu sumu
            }

            ps.setDouble(1, ukupno);
            ps.setInt(2, idrezervacije);

            ps.executeUpdate();

            Konekcija.getInstance().getConnection().commit();

            return true;
        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

//    private Rezervacija vratiRez(int idrezervacije) {
//        
//        String upit = "SELECT * FROM rezervacija WHERE idRezervacije = ?";
//
//        try {
//            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
//            ps.setInt(1, idrezervacije);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                
//                Rezervacija r = new Rezervacija(0, null, null, rs.getDouble("iznosRezervacije"),
//                        0, null, 0, null);
//                
//                return r;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return null;
//    }

    public boolean obrisiRezervaciju(int idr) {
        String obrisiRez = "DELETE FROM rezervacija WHERE idRezervacije = ?";
        String obrisiStavke = "DELETE FROM stavkarezervacije WHERE idRezervacije = ?";
        
        try {
            PreparedStatement psStavke = Konekcija.getInstance().getConnection().prepareStatement(obrisiStavke);
            PreparedStatement psRez = Konekcija.getInstance().getConnection().prepareStatement(obrisiRez);

            psStavke.setInt(1, idr);
            psStavke.executeUpdate();
            
            psRez.setInt(1, idr);
            psRez.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
           if (Konekcija.getInstance().getConnection() != null) {
            try {
                Konekcija.getInstance().getConnection().rollback();  //Ovaj rollback će sada imati efekat
                System.out.println("Rollback izvršen!");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
                }
            }
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        return false;
        }
    }

    public ArrayList<Rezervacija> pretraziRezervacije(LocalDate datum) {
        ArrayList<Rezervacija> lista = new ArrayList<>();
        String upit = "SELECT * FROM rezervacija WHERE datumPreuzimanja = ?";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setDate(1, java.sql.Date.valueOf(datum)); // Postavljamo parametar u upit
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("idRezervacije");
               
                LocalDate datumUzimanja = rs.getObject("datumPreuzimanja", LocalDate.class);
                LocalDate datumVracanja = rs.getObject("datumVracanja", LocalDate.class);
                
                Klijent klijent = vratiKlijenta(rs.getInt("idKlijent"));
                
                ArrayList<StavkaRezervacije> listaStavki = vratiStavkeRezervacije(id);

                Rezervacija rez = new Rezervacija(id, datumUzimanja, datumVracanja, rs.getDouble("iznosRezervacije"),
                        rs.getInt("idKlijent"), klijent, rs.getInt("idZaposleni"), listaStavki);
                
                lista.add(rez);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public ArrayList<StavkaRezervacije> vratiStavkeRez(int rezID) {
        ArrayList<StavkaRezervacije> lista;
        lista=vratiStavkeRezervacije(rezID);
        return lista;
    }

    public boolean obrisiStavku(int rb) {
        
        String obrisiStavke = "DELETE FROM stavkarezervacije WHERE rb = ?";
        
        try {
            
            PreparedStatement psStavke = Konekcija.getInstance().getConnection().prepareStatement(obrisiStavke);
            

            psStavke.setInt(1, rb);
            psStavke.executeUpdate();
            
           
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
           if (Konekcija.getInstance().getConnection() != null) {
            try {
                Konekcija.getInstance().getConnection().rollback();  //Ovaj rollback će sada imati efekat
                System.out.println("Rollback izvršen!");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
                }
            }
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        return false;
        }
    }


    
}

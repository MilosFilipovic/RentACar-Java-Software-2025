/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.rezervacija;

import baza.DBBrokerNew;
import java.util.ArrayList;
import klase.AbstractDomainObject;
import klase.Rezervacija;
import klase.Zaposleni;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOPretraziRezID extends AbstractSO{
    private ArrayList<Rezervacija> rezLista = new ArrayList<>();
    private Rezervacija r = new Rezervacija();

    public ArrayList<Rezervacija> getRezLista() {
        return rezLista;
    }

    public Rezervacija getR() {
        return r;
    }

    
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        if (ado==null || !(ado instanceof Rezervacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase rezrvacija!");
        }
        
    }
    

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        
        AbstractDomainObject object;
        object=DBBrokerNew.getInstance().selectObject(ado);
        r=(Rezervacija) object;
        
        
//        AbstractDomainObject ado=(AbstractDomainObject) obj;
//        List<AbstractDomainObject> lista=DBBrokerNew.getInstance().selectList(ado);
//        mesta=(ArrayList<Mesto>)(ArrayList<?>)lista;
    }
}

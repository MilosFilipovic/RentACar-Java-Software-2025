/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.rezervacija;

import baza.DBBrokerNew;
import java.util.ArrayList;
import java.util.List;
import klase.AbstractDomainObject;
import klase.Rezervacija;
import klase.Zaposleni;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOPretraziRezPoVozilu extends AbstractSO{
    private ArrayList<Rezervacija> reze;

    public ArrayList<Rezervacija> getReze() {
        return reze;
    }

    
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        if (ado==null || !(ado instanceof Rezervacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase rez!");
        }
        
    }
    

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        List<AbstractDomainObject> lista=DBBrokerNew.getInstance().selectList(ado);
        reze=(ArrayList<Rezervacija>)(ArrayList<?>)lista;
    }
}

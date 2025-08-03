/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.klijent;

import baza.DBBrokerNew;
import klase.AbstractDomainObject;
import klase.Klijent;
import klase.Zaposleni;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOKreirajKlijenta extends AbstractSO{
    private int id;
     
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        
        if (ado==null || !(ado instanceof Klijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Klijent!");
        }
        

    
    }

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        
        id=DBBrokerNew.getInstance().insert(ado);
        
    }

    public int getId() {
        return id;
    }
}

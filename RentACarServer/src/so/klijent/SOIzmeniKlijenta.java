/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.klijent;

import baza.DBBrokerNew;
import klase.AbstractDomainObject;
import klase.Klijent;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOIzmeniKlijenta extends AbstractSO{
    private int affectedRows;

    public int getAffectedRows() {
        return affectedRows;
    }
    
   
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        if (!(ado instanceof Klijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Klijent!");
        }
        
        
        
    }

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        
        affectedRows=DBBrokerNew.getInstance().update(ado);
        
        
    }
}

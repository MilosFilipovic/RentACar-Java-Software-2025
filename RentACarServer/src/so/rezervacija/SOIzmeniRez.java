/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.rezervacija;

import baza.DBBrokerNew;
import klase.AbstractDomainObject;
import klase.Rezervacija;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOIzmeniRez extends AbstractSO{
    private int affectedRows;

    public int getAffectedRows() {
        return affectedRows;
    }
    
   
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        if (!(ado instanceof Rezervacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase rezervacija!");
        }
        

        
    }

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        
        affectedRows=DBBrokerNew.getInstance().update(ado);
        
        
    }
}

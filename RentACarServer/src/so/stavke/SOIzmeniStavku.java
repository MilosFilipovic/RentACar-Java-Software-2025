/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavke;

import baza.DBBrokerNew;
import klase.AbstractDomainObject;
import klase.StavkaRezervacije;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOIzmeniStavku extends AbstractSO{
     private int affectedRows;

    public int getAffectedRows() {
        return affectedRows;
    }
    
   
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        if (!(ado instanceof StavkaRezervacije)) {
            throw new Exception("Prosledjeni objekat nije instanca klase stavka!");
        }
        

        
    }

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        
        affectedRows=DBBrokerNew.getInstance().update(ado);
        
        
    }
}

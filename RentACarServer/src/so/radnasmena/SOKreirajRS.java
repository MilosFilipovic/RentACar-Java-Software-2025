/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.radnasmena;

import baza.DBBrokerNew;
import klase.AbstractDomainObject;
import klase.RadnaSmena;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOKreirajRS extends AbstractSO{
    private int id;
     
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        
        if (ado==null || !(ado instanceof RadnaSmena)) {
            throw new Exception("Prosledjeni objekat nije instanca klase RS!");
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

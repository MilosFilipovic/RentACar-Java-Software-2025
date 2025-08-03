/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavke;

import baza.DBBrokerNew;
import java.util.ArrayList;
import java.util.List;
import klase.AbstractDomainObject;
import klase.Rezervacija;
import klase.StavkaRezervacije;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOGetListStavke extends AbstractSO{
    private ArrayList<StavkaRezervacije> sr;

    public ArrayList<StavkaRezervacije> getSr() {
        return sr;
    }

    
    
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        if (ado==null || !(ado instanceof StavkaRezervacije)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkeRezervacija!");
        }
        
    }
    

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        List<AbstractDomainObject> lista=DBBrokerNew.getInstance().selectList(ado);
        sr=(ArrayList<StavkaRezervacije>)(ArrayList<?>)lista;
    }
}

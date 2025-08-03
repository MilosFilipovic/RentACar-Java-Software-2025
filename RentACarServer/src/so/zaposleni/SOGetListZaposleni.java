/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.zaposleni;

import baza.DBBrokerNew;
import java.util.ArrayList;
import java.util.List;
import klase.AbstractDomainObject;
import klase.Mesto;
import klase.Zaposleni;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOGetListZaposleni extends AbstractSO{
    private ArrayList<Zaposleni> zaposleni;

    public ArrayList<Zaposleni> getZaposleni() {
        return zaposleni;
    }
    
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        if (ado==null || !(ado instanceof Zaposleni)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Zaposleni!");
        }
        
    }
    

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        List<AbstractDomainObject> lista=DBBrokerNew.getInstance().selectList(ado);
        zaposleni=(ArrayList<Zaposleni>)(ArrayList<?>)lista;
    }
}

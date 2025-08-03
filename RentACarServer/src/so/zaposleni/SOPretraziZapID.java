/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.zaposleni;

import baza.DBBrokerNew;
import java.util.ArrayList;
import klase.AbstractDomainObject;
import klase.Klijent;
import klase.Zaposleni;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOPretraziZapID extends AbstractSO{
    private ArrayList<Zaposleni> zapLista = new ArrayList<>();
    private Zaposleni z = new Zaposleni();

    public ArrayList<Zaposleni> getZapLista() {
        return zapLista;
    }

    public Zaposleni getZ() {
        return z;
    }

    
    
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        if (ado==null || !(ado instanceof Zaposleni)) {
            throw new Exception("Prosledjeni objekat nije instanca klase zaposleni!");
        }
        
    }
    

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        
        AbstractDomainObject object;
        object=DBBrokerNew.getInstance().selectObject(ado);
        z=(Zaposleni) object;
        
        
//        AbstractDomainObject ado=(AbstractDomainObject) obj;
//        List<AbstractDomainObject> lista=DBBrokerNew.getInstance().selectList(ado);
//        mesta=(ArrayList<Mesto>)(ArrayList<?>)lista;
    }
}

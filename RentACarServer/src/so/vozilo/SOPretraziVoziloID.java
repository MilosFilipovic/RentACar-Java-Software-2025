/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.vozilo;

import baza.DBBrokerNew;
import java.util.ArrayList;
import klase.AbstractDomainObject;
import klase.Klijent;
import klase.Vozilo;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOPretraziVoziloID extends AbstractSO{
    private ArrayList<Vozilo> vozilo = new ArrayList<>();
    private Vozilo v = new Vozilo();

    public ArrayList<Vozilo> getVozilo() {
        return vozilo;
    }

    public Vozilo getV() {
        return v;
    }

    
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        if (ado==null || !(ado instanceof Vozilo)) {
            throw new Exception("Prosledjeni objekat nije instanca klase vozilo!");
        }
        
    }
    

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        
        AbstractDomainObject object;
        object=DBBrokerNew.getInstance().selectObject(ado);
        v=(Vozilo) object;
        
        
//        AbstractDomainObject ado=(AbstractDomainObject) obj;
//        List<AbstractDomainObject> lista=DBBrokerNew.getInstance().selectList(ado);
//        mesta=(ArrayList<Mesto>)(ArrayList<?>)lista;
    }
}

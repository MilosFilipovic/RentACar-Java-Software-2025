/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.vozilo;

import baza.DBBrokerNew;
import java.util.ArrayList;
import java.util.List;
import klase.AbstractDomainObject;
import klase.Vozilo;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOPretraziVozilaNaziv extends AbstractSO{
    private ArrayList<Vozilo> vozila;

    public ArrayList<Vozilo> getVozila() {
        return vozila;
    }
    
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        if (ado==null || !(ado instanceof Vozilo)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Vozilo!");
        }
        
    }
    

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        List<AbstractDomainObject> lista=DBBrokerNew.getInstance().selectList(ado);
        vozila=(ArrayList<Vozilo>)(ArrayList<?>)lista;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import klase.Rezervacija;
import klase.StavkaRezervacije;

/**
 *
 * @author Miloš
 */
public class TabelaRezervacija extends AbstractTableModel{
    
    ArrayList<Rezervacija> listaRez = new ArrayList<>();
    String[] kolone = {"ID", "Date", "Client", "Price", "Employee"};
    
    

    @Override
    public int getRowCount() {
        return (listaRez != null) ? listaRez.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rezervacija r = listaRez.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return r.getIdRezervacija();
            case 1:
                return r.getDatumPreuzimanja();
                
            case 2:
                
                return r.getIdKlijent();
            case 3:
                return r.getIznosRezervacije();
                
            case 4:
                return r.getIdZaposleni();
                
            default:
                return "return!";
        }
    }

    public void setRez(ArrayList<Rezervacija> rez) {
        

        this.listaRez = rez;
        fireTableDataChanged(); // Obaveštava JTable da osveži prikaz
    }

    public void obrisiRez(int row) {
        listaRez.remove(row);
        fireTableDataChanged();
    }

    public void setPretragaRez(ArrayList<Rezervacija> pretrazeneRez) {
        this.listaRez = pretrazeneRez;
        fireTableDataChanged();
        
    }
    
}

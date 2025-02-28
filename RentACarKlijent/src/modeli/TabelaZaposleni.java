/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import klase.Zaposleni;

public class TabelaZaposleni extends AbstractTableModel {

    ArrayList<Zaposleni> listaZaposlenih;
    String[] kolone = {"ID", "Name", "Surname", "Username"};
    
    
    

    public TabelaZaposleni() {
        listaZaposlenih = new ArrayList<>();
        
    }

    @Override
    public int getRowCount() {
        return listaZaposlenih.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return kolone[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zaposleni z = listaZaposlenih.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return z.getIdZaposleni();
            case 1:
                return z.getIme();
            case 2:
                return z.getPrezime();
            case 3:
                return z.getKorisnickoIme();
                

            default:
                return "return!";
        }
    }
    
     public void setZaposleni(ArrayList<Zaposleni> zaposleni) {
        this.listaZaposlenih = zaposleni;
        fireTableDataChanged(); // Obaveštava JTable da osveži prikaz
    }

    public void obrisiZaposlenog(int row) {
        listaZaposlenih.remove(row);
        fireTableDataChanged();
    }

    public void setPretragaZaposleni(ArrayList<Zaposleni> pretrazeniZ) {
        this.listaZaposlenih = pretrazeniZ;
        fireTableDataChanged();
    }

}

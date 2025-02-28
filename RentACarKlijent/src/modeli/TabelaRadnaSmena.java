/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import klase.RadnaSmena;

/**
 *
 * @author Miloš
 */
public class TabelaRadnaSmena extends AbstractTableModel{

    ArrayList<RadnaSmena> listaRS;
    String[] kolone = {"ID", "Start", "End"};
    
    
    

    public TabelaRadnaSmena() {
        listaRS = new ArrayList<>();
        
    }

    @Override
    public int getRowCount() {
        return listaRS.size();
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
        RadnaSmena rs = listaRS.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return rs.getIdSmena();
            case 1:
                return rs.getPocetakSmene();
            case 2:
                return rs.getKrajSmene();
            
                

            default:
                return "return!";
        }
    }
    
//     public void setZaposleni(ArrayList<Zaposleni> zaposleni) {
//        this.listaZaposlenih = zaposleni;
//        fireTableDataChanged(); // Obaveštava JTable da osveži prikaz
//    }
//
//    public void obrisiPrognozu(int row) {
//        listaZaposlenih.remove(row);
//        fireTableDataChanged();
//    }

    public void setRadnaSmena(ArrayList<RadnaSmena> rs) {
        this.listaRS = rs;
        fireTableDataChanged(); // Obaveštava JTable da osveži prikaz
    }

    public void obrisiSmenu(int row) {
        listaRS.remove(row);
        fireTableDataChanged();
    }

    public void setPretragaRS(ArrayList<RadnaSmena> pretrazeneRS) {
        this.listaRS = pretrazeneRS;
        fireTableDataChanged();
    }
    
    
    
}

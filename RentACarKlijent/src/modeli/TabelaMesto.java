/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;


import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import klase.Mesto;

/**
 *
 * @author Miloš
 */
public class TabelaMesto extends AbstractTableModel{

    ArrayList<Mesto> listaMesta;
    String[] kolone = {"City", "Adress", "ID"};
    
    public TabelaMesto(){
        listaMesta=new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return listaMesta.size();
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
        Mesto m = listaMesta.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return m.getNazivMesta();
            case 1:
                return m.getAdresa();
            case 2:
                return m.getIdMesta();
            

            default:
                return "return!";
        }
    }

    public void setMesta(ArrayList<Mesto> mesta) {
        this.listaMesta = mesta;
        fireTableDataChanged(); // Obaveštava JTable da osveži prikaz
    }

    public void obrisiSmenu(int row) {
        listaMesta.remove(row);
        fireTableDataChanged();
    }

    public void setPretragaZaposleni(ArrayList<Mesto> pretrazenoMesto) {
        this.listaMesta = pretrazenoMesto;
        fireTableDataChanged();
    }
    
    
    
}

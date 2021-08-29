package Modelos;

import Clases.Cargo;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Modelo_cargo extends AbstractTableModel {

    private List<Cargo> Lista_Cargo;

    public Modelo_cargo(List<Cargo> Lista_Cargo) {
        this.Lista_Cargo = Lista_Cargo;
    }

    @Override
    public int getRowCount() {
        return Lista_Cargo.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cargo obj = Lista_Cargo.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return obj.getCodigo();
            case 1:
                return obj.getNombre();

        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "CODIGO";
            case 1:
                return "NOMBRE";
        }
        return "";
    }

}

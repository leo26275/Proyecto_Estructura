package Modelos;

import Clases.Cargo;
import Clases.Categoria;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Modelo_categoria extends AbstractTableModel {

    private List<Categoria> Lista_Categoria;

    public Modelo_categoria(List<Categoria> Lista_Categoria) {
        this.Lista_Categoria = Lista_Categoria;
    }

    @Override
    public int getRowCount() {
        return Lista_Categoria.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Categoria obj = Lista_Categoria.get(rowIndex);
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

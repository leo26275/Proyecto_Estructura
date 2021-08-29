
package Modelos;

import Clases.Producto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Modelo_Producto extends AbstractTableModel {
    private List<Producto> Lista_producto;

    public Modelo_Producto(List<Producto> Lista_producto) {
        this.Lista_producto = Lista_producto;
    }

    @Override
    public int getRowCount() {
        return Lista_producto.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto obj = Lista_producto.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return obj.getCodigo();
            case 1:
                return obj.getNombre();
            case 2:
                return obj.getIdcategoria().getNombre();
            case 3:
                return obj.getDescriccion();

        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Codigo";
            case 1:
                return "Nombre";
            case 2:
                return "Nombre Categoria";
            case 3:
                return "Descripcion";

        }
        return "";
    }
}

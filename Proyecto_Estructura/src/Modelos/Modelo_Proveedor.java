
package Modelos;

import Clases.Proveedor;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Modelo_Proveedor extends AbstractTableModel{
    private List<Proveedor> Lista_proveedor;

    public Modelo_Proveedor(List<Proveedor> Lista_proveedor) {
        this.Lista_proveedor = Lista_proveedor;
    }
      @Override
    public int getRowCount() {
        return Lista_proveedor.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proveedor obj = Lista_proveedor.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return obj.getNombre();
            case 1:
                return obj.getApellido();
            case 2:
                return obj.getEmpresa();
            case 3:
                return obj.getNitempresa();
            case 4:
                return obj.getDireccion();
            case 5:
                return obj.getTelefono();

        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nombre";
            case 1:
                return "Apellido";
            case 2:
                return "Empresa";
            case 3:
                return "NIT Empresa";
            case 4:
                return "Dirreccion";
            case 5:
                return "Telefono";

        }
        return "";
    }
}

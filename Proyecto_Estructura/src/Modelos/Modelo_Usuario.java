
package Modelos;

import Clases.Usuario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Modelo_Usuario extends AbstractTableModel {
     private List<Usuario> Lista_Usuario;

    public Modelo_Usuario(List<Usuario> Lista_Usuario) {
        this.Lista_Usuario = Lista_Usuario;
    }

    @Override
    public int getRowCount() {
        return Lista_Usuario.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario obj = Lista_Usuario.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return obj.getNombre();
            case 1:
                return "**************";
            case 2:
                return obj.getTipo();
            case 3:
                return obj.getId_empleado().getNombre();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Usuario";
            case 1:
                return "Contraseña";
            case 2:
                return "Tipo de cuenta";
            case 3:
                return "Nombre de Empleado";

        }
        return "";
    }
}

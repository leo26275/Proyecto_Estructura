
package Modelos;

import Clases.Referencia;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Modelo_Referencia extends AbstractTableModel{
    private List<Referencia> Lista_Referencia;

    public Modelo_Referencia(List<Referencia> Lista_Referencia) {
        this.Lista_Referencia = Lista_Referencia;
    }
     @Override
    public int getRowCount() {
        return Lista_Referencia.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Referencia obj = Lista_Referencia.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return obj.getNombre();
            case 1:
                return obj.getApellido();
            case 2:
                return obj.getTelefono();
            case 3:
                return obj.getIdcliente().getNombre();

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
                return "Telefono";
            case 3:
                return "Nombre Cliente";


        }
        return "";
    }
    
}

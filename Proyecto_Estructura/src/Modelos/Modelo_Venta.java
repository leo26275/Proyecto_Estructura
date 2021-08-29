package Modelos;

import Clases.Venta;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Modelo_Venta extends AbstractTableModel {

    private List<Venta> Lista_venta;

    public Modelo_Venta(List<Venta> Lista_venta) {
        this.Lista_venta = Lista_venta;
    }

    @Override
    public int getRowCount() {
        return Lista_venta.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta obj = Lista_venta.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return obj.getNumerofactura();
            case 1:
                return obj.getIdempleado().getNombre();
            case 2:
                return obj.getIdcliente().getNombre();
            case 3:
                return obj.getFecha();
            case 4:
                return obj.getTipo();

        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Codigo";
            case 1:
                return "Nombre Empleado";
            case 2:
                return "Nombre Cliente";
            case 3:
                return "Fecha Compra";
            case 4:
                return "Tipo Pago";
        }
        return "";
    }
}

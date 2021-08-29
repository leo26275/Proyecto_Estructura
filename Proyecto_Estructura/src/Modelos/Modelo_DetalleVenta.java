package Modelos;

import Clases.Detalle_venta;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Modelo_DetalleVenta extends AbstractTableModel {

    private List<Detalle_venta> Lista_detalleventa;

    public Modelo_DetalleVenta(List<Detalle_venta> Lista_detalleventa) {
        this.Lista_detalleventa = Lista_detalleventa;
    }

    @Override
    public int getRowCount() {
        return Lista_detalleventa.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Detalle_venta obj = Lista_detalleventa.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return obj.getId_datos_venta().getNumerofactura();
            case 1:
                return obj.getId_producto().getNombre();
            case 2:
                return obj.getCantidad();
            case 3:
                return obj.getCliente().getNombre();
            case 4:
                return obj.getTipo();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Numero de Factura";
            case 1:
                return "Nombre de Producto";
            case 2:
                return "Cantidad";
            case 3:
                return "Nombre de Cliente";
            case 4:
                return "Tipo de Venta";

        }
        return "";
    }
}

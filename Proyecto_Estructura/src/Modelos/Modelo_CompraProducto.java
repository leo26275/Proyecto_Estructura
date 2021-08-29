package Modelos;

import Clases.CompraProducto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Modelo_CompraProducto extends AbstractTableModel {

    private List<CompraProducto> Lista_compraproducto;

    public Modelo_CompraProducto(List<CompraProducto> Lista_compraproducto) {
        this.Lista_compraproducto = Lista_compraproducto;
    }

    @Override
    public int getRowCount() {
        return Lista_compraproducto.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CompraProducto obj = Lista_compraproducto.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return obj.getCantidad();
            case 1:
                return obj.getPrecio();
            case 2:
                return obj.getFechaadquisiscion();
            case 3:
                return obj.getPrecio() + (obj.getPrecio() * (obj.getPorcentajeganancia() / 100));
            case 4:
                return obj.getProveedor().getNombre();
            case 5:
                return obj.getProducto().getNombre();

        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Cantidad";
            case 1:
                return "Precio";
            case 2:
                return "Fecha Adquisicion";
            case 3:
                return "Precio venta";
            case 4:
                return "Nombre proveedor";
            case 5:
                return "Nombre de Producto";

        }
        return "";
    }
}

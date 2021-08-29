package Modelos;

import Clases.Producto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Modelo_Inventario extends AbstractTableModel {

    private List<Producto> Lista_producto;

    public Modelo_Inventario(List<Producto> Lista_producto) {
        this.Lista_producto = Lista_producto;
    }

    @Override
    public int getRowCount() {
        return Lista_producto.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
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
            case 4:
                return obj.getCompraproducto().getCantidad();
            case 5:
                return obj.getCompraproducto().getFechaadquisiscion();
            case 6:
                return  obj.getCompraproducto().getPrecio() + (obj.getCompraproducto().getPrecio() * (obj.getCompraproducto().getPorcentajeganancia()/100));

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
            case 4:
                return "Cantidad";
            case 5:
                return "Fecha Adquisicion";
            case 6:
                return "Precio de compra";

        }
        return "";
    }
}

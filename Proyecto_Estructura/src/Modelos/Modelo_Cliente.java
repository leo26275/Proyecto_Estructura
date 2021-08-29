package Modelos;

import Clases.Cargo;
import Clases.Cliente;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Modelo_Cliente extends AbstractTableModel {

    private List<Cliente> Lista_cliente;

    public Modelo_Cliente(List<Cliente> Lista_Cargo) {
        this.Lista_cliente = Lista_Cargo;
    }

    @Override
    public int getRowCount() {
        return Lista_cliente.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente obj = Lista_cliente.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return obj.getCodigo();
            case 1:
                return obj.getNombre();
            case 2:
                return obj.getApellido();
            case 3:
                return obj.getDireccion();
            case 4:
                return obj.getTelefono();
            case 5:
                return obj.getDui();

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
                return "Apellido";
            case 3:
                return "Direccion";
            case 4:
                return "Telefono";
            case 5:
                return "DUI";

        }
        return "";
    }

}

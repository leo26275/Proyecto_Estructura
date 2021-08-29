package Modelos;

import Clases.Cargo;
import Clases.Cliente;
import Clases.Empleado;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Modelo_Empleado extends AbstractTableModel {

    private List<Empleado> Lista_Empleado;

    public Modelo_Empleado(List<Empleado> Lista_Cargo) {
        this.Lista_Empleado = Lista_Cargo;
    }

    @Override
    public int getRowCount() {
        return Lista_Empleado.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Empleado obj = Lista_Empleado.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return obj.getCodigo();
            case 1:
                return obj.getNombre();
            case 2:
                return obj.getApellido();
            case 3:
                return obj.getDui();
            case 4:
                return obj.getNit();
            case 5:
                return obj.getTelefono();
            case 6:
                return obj.getCorreo();
            case 7:
                return obj.getDireccion();
            case 8:
                return obj.getSalario();
            case 9:
                return obj.getId_cargo().getNombre();

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
                return "Dui";
            case 4:
                return "Nit";
            case 5:
                return "Telefono";
            case 6:
                return "Correo";
            case 7:
                return "Direccion";
            case 8:
                return "Salario";
            case 9:
                return "Cargo";

        }
        return "";
    }

}

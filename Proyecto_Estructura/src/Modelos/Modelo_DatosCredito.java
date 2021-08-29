package Modelos;

import Clases.Datos_credito;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Modelo_DatosCredito extends AbstractTableModel {

    private List<Datos_credito> Lista_datoscreditos;

    public Modelo_DatosCredito(List<Datos_credito> Lista_datoscreditos) {
        this.Lista_datoscreditos = Lista_datoscreditos;
    }

    @Override
    public int getRowCount() {
        return Lista_datoscreditos.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Datos_credito obj = Lista_datoscreditos.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return obj.getFecha_cance();
            case 1:
                return obj.getTiempo();
            case 2:
                return obj.getCuotas_totales();
            case 3:
                return obj.getCuotas_pagadas();
            case 4:
                return obj.getProducto().getCodigo();
            case 5:
                return obj.getProducto().getNombre();
            case 6: 
                return obj.getDetalleventa().getTipo();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Fecha de canselacion";
            case 1:
                return "Tiempo en numero de meses";
            case 2:
                return "Cuotas Totales";
            case 3:
                return "Cuotas Pagadas";
            case 4:
                return "Codigo de Producto";
            case 5:
                return "Nombre de Producto";
            case 6:
                return "Tipo de accion";

        }
        return "";
    }
}

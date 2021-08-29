
package Modelos;

import Clases.Pago;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Modelo_Pago extends AbstractTableModel{
    private List<Pago> Lista_Pago;

    public Modelo_Pago(List<Pago> Lista_Pago) {
        this.Lista_Pago = Lista_Pago;
    }

    @Override
    public int getRowCount() {
        return Lista_Pago.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pago obj = Lista_Pago.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return obj.getFechapago();
            case 1:
                return obj.getDatoscredito().getCuotas_totales();
            case 2:
                return obj.getDatoscredito().getCuotas_pagadas();

        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {

            case 0:
                return "Fecha pago";
            case 1:
                return "Pagos totales";
            case 2:
                return "Pagos pagados";


        }
        return "";
    }
}

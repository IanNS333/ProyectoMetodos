package mx.itesm.ianneumann.proyectometodos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import java.util.ArrayList;

/**
 * Created by Ian Neumann on 20/04/2017.
 */

public abstract class MetodoSistemaEcuaciones extends Metodo {

    protected EcuacionesSizeDialog ecuacionesSizeDialog;
    protected TableLayout tableLayout;
    protected int table_id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        View view = super.onCreateView(inflater, container, SavedInstanceState);
        ecuacionesSizeDialog = new EcuacionesSizeDialog(getContext());
        ecuacionesSizeDialog.show();
        ecuacionesSizeDialog.setAceptarListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableLayout = Matrix.crearTablaVacía(tableLayout,ecuacionesSizeDialog.getEcuaciones(),ecuacionesSizeDialog.getEcuaciones()+1);
                ecuacionesSizeDialog.dismiss();
            }
        });

        tableLayout = (TableLayout) view.findViewById(table_id);
        Matrix.crearTablaVacía(tableLayout,1,2);
        return view;
    }
}

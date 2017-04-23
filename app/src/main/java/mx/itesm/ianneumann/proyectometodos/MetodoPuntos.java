package mx.itesm.ianneumann.proyectometodos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

/**
 * Created by Ian Neumann on 22/04/2017.
 */

public abstract class MetodoPuntos extends Metodo {

    TableLayout tableLayout;
    PuntosSizeDialog puntosSizeDialog;

    int puntos;

    int table_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        View view = super.onCreateView(inflater, container, SavedInstanceState);
        puntosSizeDialog = new PuntosSizeDialog(getContext());
        puntosSizeDialog.show();
        puntosSizeDialog.setAceptarListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableLayout = Matrix.crearTablaVacía(tableLayout,puntosSizeDialog.getPuntos(),2);
                puntosSizeDialog.dismiss();
            }
        });
        puntos = puntosSizeDialog.getPuntos();

        tableLayout = (TableLayout) view.findViewById(table_id);
        Matrix.crearTablaVacía(tableLayout,1,2);
        return view;
    }
}

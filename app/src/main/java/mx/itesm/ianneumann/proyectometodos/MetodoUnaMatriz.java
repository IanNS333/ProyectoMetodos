package mx.itesm.ianneumann.proyectometodos;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Ian Neumann on 20/04/2017.
 */

public abstract class MetodoUnaMatriz extends Metodo {

    protected MatrizSizeDialog matrizSizeDialog;
    protected TableLayout tableLayout;
    protected int table_id;

    private boolean initiated = false;

    private int alto;
    private int ancho;

    private ArrayList<ArrayList<Double>> datos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        View view = super.onCreateView(inflater, container, SavedInstanceState);
        matrizSizeDialog = new MatrizSizeDialog(getContext());
        matrizSizeDialog.show();
        matrizSizeDialog.setAceptarListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableLayout = Matrix.crearTablaVacía(tableLayout,matrizSizeDialog.getAlto(),matrizSizeDialog.getAncho());
                matrizSizeDialog.dismiss();
            }
        });
        alto = matrizSizeDialog.getAlto();
        ancho =matrizSizeDialog.getAncho();

        tableLayout = (TableLayout) view.findViewById(table_id);
        Matrix.crearTablaVacía(tableLayout,1,1);
        return view;
    }
}

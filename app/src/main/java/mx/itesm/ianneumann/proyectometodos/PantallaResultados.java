package mx.itesm.ianneumann.proyectometodos;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ian Neumann on 22/04/2017.
 */

public class PantallaResultados extends Fragment {
    private ArrayList<View> resultado;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_resultados,container,false);

        TableLayout table = (TableLayout)view.findViewById(R.id.resultados_table);
        table = obtenerTable(table,resultado);
        return view;
    }

    private TableLayout obtenerTable(TableLayout tableLayout, ArrayList<View> resultado) {
        for(View v:resultado){
            tableLayout.addView(v);
        }
        return tableLayout;
    }

    public void setResultado(ArrayList<View> resultado) {
        this.resultado = resultado;
    }
}

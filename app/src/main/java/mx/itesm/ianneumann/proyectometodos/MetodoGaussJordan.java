package mx.itesm.ianneumann.proyectometodos;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ian Neumann on 20/04/2017.
 */

public class MetodoGaussJordan extends MetodoUnaMatriz {

    ArrayList<View> instrucciones;

    public MetodoGaussJordan(){
        content_layout = R.layout.content_gauss_jordan;
        table_id = R.id.gauss_jordan_table;
        execute_id = R.id.gauss_jordan_aceptar;
        instrucciones = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle SavedInstanceState) {
        View view = super.onCreateView(inflater,container,SavedInstanceState);
        return view;
    }

    @Override
    protected ArrayList<View> ejecutar() {
        Matrix m = new Matrix(tableLayout);
        TextView texto;
        Double pivote,coeficiente;
        ArrayList<Double> temporal;

        texto = crearInstruccion();
        texto.setText("Dada la siguiente matriz:");
        instrucciones.add(texto);
        instrucciones.add(m.crearTablaResultado(new TableLayout(getContext())));
        for(int i = 0; i < m.getData().size();i++){
            texto = crearInstruccion();

            pivote = m.getData().get(i).get(i);

            texto.setText("Agarramos el " + (i==0?"primer":"siguiente") + " pivote (" + String.format("%.3f",pivote) + ") y multiplicamos el renglon por la inversa del pivote (1/"
                    + String.format("%.3f",pivote) + " = " + String.format("%.3f",1/pivote) + ") para que el pivote se vuelva 1.");
            instrucciones.add(texto);

            m.multiplyRow(i,1/m.getData().get(i).get(i));
            instrucciones.add(m.crearTablaResultado(new TableLayout(getContext())));

            texto = crearInstruccion();

            if(i < m.getData().size()-1) {
                texto.setText("Por cada uno de los siguiente renglones multiplicames el renglon actual (" + (i+1)
                        + ") por el negativo del numero en la columna del pivote y sumamos los renglones, dejando el resultado en el renglon " +
                        "que no contiene el pivote actual");
                instrucciones.add(texto);
                for (int j = i + 1; j < m.getData().size();j++){
                    coeficiente = m.getData().get(j).get(i);
                    texto = crearInstruccion();
                    texto.setText("Agarramos el numero en la columna del pivote del renglon " + (j+1) + " ("+ String.format("%.3f",coeficiente) +")"
                            + ", lo volvemos negativo ("+ String.format("%.3f",-coeficiente) +"), multiplicamos el renglon " + (i+1) + " por dicho numero ("
                            +String.format("%.3f",-coeficiente) +")  y se lo sumamos al renglon " + (j+1) + " para que asi quede en 0.");
                    instrucciones.add(texto);
                    temporal = new ArrayList<>(m.getRow(i));
                    Matrix.multiply(temporal,-coeficiente);
                    m.addToRow(j,temporal);
                    instrucciones.add(m.crearTablaResultado(new TableLayout(getContext())));
                }
            }
        }
        texto = crearInstruccion();
        texto.setText("Dandonos como resultado: ");
        instrucciones.add(texto);
        instrucciones.add(m.crearTablaResultado(new TableLayout(getContext())));
        return instrucciones;
    }



}

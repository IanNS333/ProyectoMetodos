package mx.itesm.ianneumann.proyectometodos;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ian Neumann on 20/04/2017.
 */

public class MetodoGaussSiedel extends MetodoSistemaEcuaciones {

    ArrayList<View> instrucciones;

    EditText Porcentaje;

    public MetodoGaussSiedel(){
        content_layout = R.layout.content_gauss_siedel;
        table_id = R.id.gauss_siedel_table;
        execute_id = R.id.gauss_siedel_aceptar;
        instrucciones = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle SavedInstanceState) {
        View view = super.onCreateView(inflater,container,SavedInstanceState);

        Porcentaje = (EditText)view.findViewById(R.id.gauss_siedel_porcentaje);
        return view;
    }

    @Override
    protected ArrayList<View> ejecutar() {

        Matrix m = new Matrix(tableLayout);

        TextView texto = crearInstruccion();
        texto.setText("Dado el siguiente sistema de ecuaciones: ");
        instrucciones.add(texto);
        instrucciones.add(m.crearTablaResultado(new TableLayout(getContext())));

        double error = (100-Double.parseDouble(Porcentaje.getText().toString()))/100;

        ArrayList<Double> resultados = m.getColumn(m.getData().size());
        ArrayList<Double> anteriores = new ArrayList<>();
        for(int i = 0; i < resultados.size();i++){
            anteriores.add(2*resultados.get(i));
        }
        double suma;
        while(promedioErrores(resultados,anteriores) >= error){
            anteriores = new ArrayList<>(resultados);
            for(int i = 0; i< resultados.size();i++){
                suma = 0;
                for(int j = 0; j < resultados.size();j++){
                    if(i!=j){
                        suma += m.getData().get(i).get(j)*resultados.get(j);
                    }
                }
                resultados.set(i,(m.getData().get(i).get(m.getData().size()) - suma)/ m.getData().get(i).get(i));
            }
        }
        ArrayList<ArrayList<Double>> r = new ArrayList<>();
        r.add(resultados);
        Matrix res = new Matrix(r);
        res.transpose();
        ArrayList<String> headers = new ArrayList<>();
        for(int i = 0; i < res.getData().size();i++){
            headers.add("x"+toSubscript(i+1)+" = ");
        }
        texto = crearInstruccion();
        texto.setText("Los valores para las variables son los siguientes: ");
        instrucciones.add(texto);
        instrucciones.add(res.crearTablaResultadoRowHeaders(new TableLayout(getContext()),headers));
        return instrucciones;
    }

    private double promedioErrores(ArrayList<Double> actuales,ArrayList<Double> anteriores){
        double suma = 0;
        for(int i = 0; i < actuales.size();i++){
            suma += Math.abs((actuales.get(i)-anteriores.get(i))/actuales.get(i));
        }
        return suma/actuales.size();
    }

    private String subscript(int number){
        String res = "";
        while(number >=10){
            res += toSubscript(number%10);
            number/=10;
        }
        return res;
    }

    private String toSubscript(int number){
        if(number >= 10){
            return subscript(number);
        }
        switch (number){
            case (0):
                return "\u2080";
            case (1):
                return "\u2081";
            case (2):
                return "\u2082";
            case (3):
                return "\u2083";
            case (4):
                return "\u2084";
            case (5):
                return "\u2085";
            case (6):
                return "\u2086";
            case (7):
                return "\u2087";
            case (8):
                return "\u2088";
            case (9):
                return "\u2089";
            default:
                return "";
        }
    }

}

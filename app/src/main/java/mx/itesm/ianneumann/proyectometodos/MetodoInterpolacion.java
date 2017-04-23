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

public class MetodoInterpolacion extends MetodoPuntos {

    ArrayList<View> instrucciones;

    public MetodoInterpolacion(){
        content_layout = R.layout.content_interpolacion;
        table_id = R.id.interpolacion_table;
        execute_id = R.id.interpolacion_aceptar;
        instrucciones = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle SavedInstanceState) {
        View view = super.onCreateView(inflater,container,SavedInstanceState);
        return view;
    }

    @Override
    protected ArrayList<View> ejecutar() {
        TextView textView = crearInstruccion();
        textView.setText("Dados los siguientes puntos");
        instrucciones.add(textView);
        Matrix puntos = new Matrix(tableLayout);
        instrucciones.add(puntos.crearTablaResultado(new TableLayout(getContext())));


        ArrayList<ArrayList<Double>> matrizA = new ArrayList<>();
        for(int i = 0; i < puntos.getData().size();i++){
            matrizA.add(new ArrayList<Double>());
            for(int j = 0; j < puntos.getData().size();j++){
                matrizA.get(i).add(j==0?1:matrizA.get(i).get(j-1)*puntos.getData().get(i).get(0));
            }
        }

        ArrayList<ArrayList<Double>> matrizB = new ArrayList<>();
        for(int i = 0; i< puntos.getData().size();i++){
            matrizB.add(new ArrayList<Double>());
            matrizB.get(i).add(puntos.getData().get(i).get(1));
        }

        Matrix a = new Matrix(matrizA);
        Matrix b = new Matrix(matrizB);
        a.inverse();
        a.multiply(b);

        textView = crearInstruccion();
        textView.setText("La función que se ajusta a todos los datos es la siguiente:");
        instrucciones.add(textView);

        String funcion ="f(x) = ";
        for(int i = puntos.getData().size()-1; i > 0;i--){
            funcion += (a.getData().get(i).get(0)>=0?"+":"")+ String.format("%.3f",a.getData().get(i).get(0)) + "x"+(i!=1?superscript(i):"") + " ";
        }
        funcion += (a.getData().get(0).get(0)>=0?"+":"") + String.format("%.3f",a.getData().get(0).get(0));
        textView = crearInstruccion();
        textView.setText(funcion);
        instrucciones.add(textView);
        return instrucciones;
    }


    private String superscript(int number){
        String res = "";
        while(number>=10){
            res += toSuperscript(number%10);
            number/=10;
        }
        res += toSuperscript(number);
        return res;
    }

    private String toSuperscript(int i) {
        if(i >= 10){
            return superscript(i);
        }
        switch (i){
            case (0):
                return "\u2070";
            case (1):
                return "\u00b9";
            case (2):
                return "\u00b2";
            case (3):
                return "\u00b3";
            case (4):
                return "\u2074";
            case (5):
                return "\u2075";
            case (6):
                return "\u2076";
            case (7):
                return "\u2077";
            case (8):
                return "\u2078";
            case (9):
                return "\u2079";
            default:
                return "";
        }
    }

}

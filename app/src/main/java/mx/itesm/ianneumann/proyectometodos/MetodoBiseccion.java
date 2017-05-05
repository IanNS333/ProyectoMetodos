package mx.itesm.ianneumann.proyectometodos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import java.util.ArrayList;

/**
 Created by gerry on 5/3/17.
 */

public class MetodoBiseccion extends MetodoFunciones {
    private int aFieldId;
    private int bFieldId;
    EditText aFieldTxt;
    EditText bFieldTxt;

    public MetodoBiseccion(){
        content_layout = R.layout.content_bisection;
        execute_id = R.id.bisection_aceptar;
        functionFieldId = R.id.bisection_function;

        aFieldId = R.id.bisection_a;
        bFieldId = R.id.bisection_b;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle SavedInstanceState) {
        View view = super.onCreateView(inflater,container,SavedInstanceState);

        functionFieldTxt = (EditText)view.findViewById(functionFieldId);
        aFieldTxt = (EditText)view.findViewById(aFieldId);
        bFieldTxt = (EditText)view.findViewById(bFieldId);

        return view;
    }

    @Override
    protected ArrayList<View> ejecutar() {
        ArrayList<View> toRet = new ArrayList<View>();
        toRet.add(
                crearInstruccion(
                        String.format("%.4f",
                                bisect(
                                functionFieldTxt.getText().toString(),
                                aFieldTxt.getText().toString(),
                                bFieldTxt.getText().toString()
                                )
                        )
                )
        );
        return toRet;
    }

    private double bisect(String f, String pa, String pb){
        double a = Double.parseDouble(pa);
        double b = Double.parseDouble(pb);
        double m = (b + a) / 2.0;
        double e = evaluate(f, m);

        while(e != 0.0  && !(e > -0.0001 && e < 0.0001)){
            if(e < 0) 	a = m;
            else 		b = m;

            m = (a + b) / 2.0;
            e = evaluate(f, m);
        }
        return m;
    }

}

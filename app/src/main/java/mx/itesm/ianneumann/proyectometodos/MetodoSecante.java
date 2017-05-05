package mx.itesm.ianneumann.proyectometodos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by gerry on 5/5/17.
 */

public class MetodoSecante extends MetodoFunciones{
    private int guess1FieldId;
    private int guess2FieldId;
    private int presicionFieldId;

    EditText guess1FieldTxt;
    EditText guess2FieldTxt;
    EditText secantPrecisionFieldTxt;

    public MetodoSecante(){
        content_layout = R.layout.content_secante;
        execute_id = R.id.secant_aceptar;
        functionFieldId = R.id.secant_function;

        guess1FieldId = R.id.secant_guess1;
        guess2FieldId = R.id.secant_guess2;
        presicionFieldId = R.id.secant_presicion;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        View view = super.onCreateView(inflater,container,SavedInstanceState);

        functionFieldTxt = (EditText)view.findViewById(functionFieldId);
        guess1FieldTxt = (EditText)view.findViewById(guess1FieldId);
        guess2FieldTxt = (EditText)view.findViewById(guess2FieldId);
        secantPrecisionFieldTxt  = (EditText)view.findViewById(presicionFieldId);

        return view;
    }

    @Override
    protected ArrayList<View> ejecutar() {
        ArrayList<View> toRet = new ArrayList<View>();
        toRet.add(
                crearInstruccion(
                        String.format("%.4f",
                                secant(
                                        functionFieldTxt.getText().toString(),
                                        guess1FieldTxt.getText().toString(),
                                        guess2FieldTxt.getText().toString(),
                                        secantPrecisionFieldTxt.getText().toString()
                                )
                        )
                )
        );
        return toRet;
    }

    private double secant(String f, String guess_a, String guess_b, String pres){
        double xn_2 = Double.parseDouble(guess_a);
        double xn_1 = Double.parseDouble(guess_b);
        double xprev;
        double xn = ( (xn_2 * evaluate(f, xn_1)) - (xn_1 * evaluate(f, xn_2)) ) / ( evaluate(f, xn_1) - evaluate(f, xn_2) );

        double precision = Double.parseDouble(pres);
        double e = Double.POSITIVE_INFINITY;

        while( e > precision ){
            xprev = xn;
            xn = ( (xn_2 * evaluate(f, xn_1)) - (xn_1 * evaluate(f, xn_2)) ) / ( evaluate(f, xn_1) - evaluate(f, xn_2) );
            e = (xn - xprev)/xn;
        }
        return xn;
    }
}

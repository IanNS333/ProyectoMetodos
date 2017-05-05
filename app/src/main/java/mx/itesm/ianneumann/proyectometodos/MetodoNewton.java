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

public class MetodoNewton extends MetodoFunciones {
    private int dfdxFieldId;
    private int guessFieldId;
    private int presicionFieldId;

    EditText dfdxFieldTxt;
    EditText guessFieldTxt;
    EditText presicionFieldTxt;

    public MetodoNewton(){
        content_layout = R.layout.content_newton;
        execute_id = R.id.newton_aceptar;
        functionFieldId = R.id.newton_function;

        dfdxFieldId = R.id.newton_dfdx;
        guessFieldId = R.id.newton_guess;
        presicionFieldId = R.id.newton_presicion;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        View view = super.onCreateView(inflater,container,SavedInstanceState);

        functionFieldTxt = (EditText)view.findViewById(functionFieldId);
        dfdxFieldTxt = (EditText)view.findViewById(dfdxFieldId);
        guessFieldTxt = (EditText)view.findViewById(guessFieldId);
        presicionFieldTxt = (EditText)view.findViewById(presicionFieldId);
        return view;
    }

    @Override
    protected ArrayList<View> ejecutar() {
        ArrayList<View> toRet = new ArrayList<View>();
        toRet.add(
                crearInstruccion(
                        String.format("%.4f",
                                newt(
                                        functionFieldTxt.getText().toString(),
                                        dfdxFieldTxt.getText().toString(),
                                        guessFieldTxt.getText().toString(),
                                        presicionFieldTxt.getText().toString()
                                )
                        )
                )
        );
        return toRet;
    }

    private double newt(String f, String dfdx, String guess, String pres){
        double xn = Double.parseDouble(guess);
        double precision = Double.parseDouble(pres);
        double prevx, e = Double.POSITIVE_INFINITY;

        while( e > precision ){
            prevx = xn;
            xn = xn - ( evaluate(f, xn)/evaluate(dfdx, xn) );
            e = (xn - prevx)/xn;
        }
        return xn;
    }
}

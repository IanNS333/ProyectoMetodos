package mx.itesm.ianneumann.proyectometodos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * Created by Ian Neumann on 20/04/2017.
 */

public class MetodoGaussJordan extends MetodoUnaMatriz {

    public MetodoGaussJordan(){
        content_layout = R.layout.content_gauss_jordan;
        table_id = R.id.gauss_jordan_table;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle SavedInstanceState) {

        View view = super.onCreateView(inflater,container,SavedInstanceState);
        return view;
    }


}

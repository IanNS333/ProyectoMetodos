package mx.itesm.ianneumann.proyectometodos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ian Neumann on 20/04/2017.
 */

public class MetodoPrueba extends MetodoUnaMatriz {

    public MetodoPrueba(){
        content_layout = R.layout.content_prueba;
        table_id = R.id.prueba_table;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        return super.onCreateView(inflater, container, SavedInstanceState);
    }
}

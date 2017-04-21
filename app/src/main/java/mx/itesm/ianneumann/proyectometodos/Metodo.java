package mx.itesm.ianneumann.proyectometodos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ian Neumann on 20/04/2017.
 */

public abstract class Metodo extends Fragment {

    protected int content_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        View view = inflater.inflate(content_layout,container,false);
        return view;
    }


}
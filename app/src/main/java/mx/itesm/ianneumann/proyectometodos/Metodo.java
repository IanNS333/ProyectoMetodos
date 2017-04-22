package mx.itesm.ianneumann.proyectometodos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Ian Neumann on 20/04/2017.
 */

public abstract class Metodo extends Fragment {

    protected int content_layout;
    protected int execute_id;
    protected int metodo_aceptar;
    protected Button execute;

    protected MenuMetodos menu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        View view = inflater.inflate(content_layout,container,false);

        execute = (Button)view.findViewById(metodo_aceptar);
        execute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.mostrarResultados(ejecutar());
            }
        });

        return view;
    }

    public void setMenu(MenuMetodos menu){
        this.menu = menu;
    }

    protected abstract ArrayList<View> ejecutar();

}
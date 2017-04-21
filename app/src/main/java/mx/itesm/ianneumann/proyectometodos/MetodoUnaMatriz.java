package mx.itesm.ianneumann.proyectometodos;

import android.os.Bundle;
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

public abstract class MetodoUnaMatriz extends Metodo {

    protected MatrizSizeDialog matrizSizeDialog;
    protected TableLayout tableLayout;
    protected int table_id;

    private int alto;
    private int ancho;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        View view = super.onCreateView(inflater, container, SavedInstanceState);
        matrizSizeDialog = new MatrizSizeDialog(getContext());
        matrizSizeDialog.show();
        matrizSizeDialog.setAceptarListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearTabla();
                matrizSizeDialog.dismiss();
            }
        });
        alto = matrizSizeDialog.getAlto();
        ancho =matrizSizeDialog.getAncho();

        tableLayout = (TableLayout) view.findViewById(table_id);
        addData();
        return view;
    }
    private void crearTabla() {
        tableLayout = (TableLayout) getActivity().findViewById(table_id);
        tableLayout.removeAllViews();
        alto = matrizSizeDialog.getAlto();
        ancho =matrizSizeDialog.getAncho();
        addData();
    }

    private void addData(){
        for(int i = 0; i < alto;i++) {
            tableLayout.addView(addRow());
        }
    }

    private TableRow addRow(){
        TableRow tr;
        tr = new TableRow(getContext());
        tr.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        for (int i = 0; i < ancho;i++){
            tr.addView(addCell());
        }
        return tr;
    }

    private EditText addCell(){
        EditText text = new EditText(getContext());
        text.setInputType(InputType.TYPE_CLASS_NUMBER);
        text.setGravity(Gravity.CENTER);
        text.setText("0");
        text.setWidth(200);
        return text;
    }
}

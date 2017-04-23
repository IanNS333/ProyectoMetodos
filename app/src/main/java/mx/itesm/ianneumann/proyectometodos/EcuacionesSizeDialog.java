package mx.itesm.ianneumann.proyectometodos;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ian Neumann on 20/04/2017.
 */

public class EcuacionesSizeDialog extends Dialog implements View.OnClickListener{

    private EditText Ecuaciones;
    private Button Aceptar;
    private Button Cancelar;

    public EcuacionesSizeDialog(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.exuaciones_size_dialog);
        Ecuaciones = (EditText) findViewById(R.id.dialog_ecuaciones_size);
        Aceptar = (Button) findViewById(R.id.dialog_ecuaciones_aceptar);
        Cancelar = (Button) findViewById(R.id.dialog_ecuaciones_cancelar);
        Aceptar.setOnClickListener(this);
        Cancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_matrix_aceptar:
                dismiss();
                break;
            case R.id.dialog_matrix_cancelar:
                dismiss();
                break;
            default:
                break;
        }
    }

    public int getEcuaciones() {
        return Integer.parseInt(Ecuaciones.getText().toString());
    }

    public void setAceptarListener(View.OnClickListener listener){
        Aceptar.setOnClickListener(listener);
    }

    public void setCancelarListener(View.OnClickListener listener){
        Cancelar.setOnClickListener(listener);
    }
}

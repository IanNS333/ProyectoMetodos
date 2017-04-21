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

public class MatrizSizeDialog extends Dialog implements View.OnClickListener{

    private EditText Alto;
    private EditText Ancho;
    private Button Aceptar;
    private Button Cancelar;

    public MatrizSizeDialog(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.matrix_size_dialog);
        Alto = (EditText) findViewById(R.id.dialog_alto);
        Ancho = (EditText) findViewById(R.id.dialog_ancho);
        Aceptar = (Button) findViewById(R.id.dialog_aceptar);
        Cancelar = (Button) findViewById(R.id.dialog_cancelar);
        Aceptar.setOnClickListener(this);
        Cancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_aceptar:
                dismiss();
                break;
            case R.id.dialog_cancelar:
                dismiss();
                break;
            default:
                break;
        }
    }

    public int getAlto(){
        return Integer.parseInt(Alto.getText().toString());
    }
    public int getAncho(){
        return Integer.parseInt(Ancho.getText().toString());
    }

    public void setAceptarListener(View.OnClickListener listener){
        Aceptar.setOnClickListener(listener);
    }

    public void setCancelarListener(View.OnClickListener listener){
        Cancelar.setOnClickListener(listener);
    }
}

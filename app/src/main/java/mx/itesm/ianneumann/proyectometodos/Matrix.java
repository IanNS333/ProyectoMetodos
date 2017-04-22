package mx.itesm.ianneumann.proyectometodos;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.text.InputType;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Ian Neumann on 21/04/2017.
 */

public class Matrix {

    ArrayList<ArrayList<Double>> datos;

    public Matrix(TableLayout table){

        TableRow tr;
        datos = new ArrayList<ArrayList<Double>>(table.getChildCount());
        for(int i = 0; i < table.getChildCount();i++) {
            tr = (TableRow) table.getChildAt(i);
            datos.add(new ArrayList<Double>(tr.getChildCount()));
            for (int j = 0; j < tr.getChildCount(); j++) {
                datos.get(i).add(j,Double.parseDouble(((EditText) tr.getChildAt(j)).getText().toString()));
            }
        }
    }

    public ArrayList<ArrayList<Double>> getData(){
        return datos;
    }

    public ArrayList<Double> getRow(int i){
        return datos.get(i);
    }

    public ArrayList<Double> getColumn(int i){
        ArrayList<Double> resultado = new ArrayList<>();
        for(int j = 0; j < datos.size();i++){
            resultado.add(datos.get(i).get(j));
        }
        return resultado;
    }

    public void multiplyRow(int indice, Double value){
        for(int j = 0; j < datos.get(indice).size();j++){
            datos.get(indice).set(j,datos.get(indice).get(j)*value);
        }
    }

    public static ArrayList<Double> multiply(ArrayList<Double> datos, Double value){
        for(int j = 0; j < datos.size();j++){
            datos.set(j,datos.get(j)*value);
        }
        return datos;
    }

    public void addRow(int resultIndex,int rowIndex){
        Double a,b;
        for(int j = 0; j < datos.get(resultIndex).size() ;j++){
            a = datos.get(resultIndex).get(j);
            b = datos.get(rowIndex).get(j);
            datos.get(resultIndex).set(j,a+b);
        }
    }

    public void addRow(int indice,ArrayList<Double> list){
        Double a,b;
        for(int j = 0; j < datos.get(indice).size() ;j++){
            a = datos.get(indice).get(j);
            b = list.get(j);
            datos.get(indice).set(j,a+b);
        }
    }

    public void subRow(int resultIndex,int rowIndex){
        Double a,b;
        for(int j = 0; j < datos.get(resultIndex).size() ;j++){
            a = datos.get(resultIndex).get(j);
            b = datos.get(rowIndex).get(j);
            datos.get(resultIndex).set(j,a-b);
        }
    }

    public TableLayout crearTablaResultado(TableLayout tableLayout){
        tableLayout.removeAllViews();
        return addData(tableLayout,datos,false);
    }

    public static TableLayout crearTablaResultado(Matrix matriz,TableLayout tableLayout, Context context){
        tableLayout.removeAllViews();
        return addData(tableLayout,matriz.getData(),false);
    }

    public static TableLayout crearTabla(Matrix matriz,TableLayout tableLayout, Context context){
        tableLayout.removeAllViews();
        return addData(tableLayout,matriz.getData(),true);
    }

    private static TableLayout addData(TableLayout tableLayout,ArrayList<ArrayList<Double>> matriz,boolean editable) {
        for(int i = 0; i< matriz.size();i++){
            tableLayout.addView(addRow(new ArrayList<Object>(matriz.get(i)),tableLayout.getContext(),editable));
        }
        return tableLayout;
    }

    private static TableRow addRow(ArrayList<Object> data, Context context,boolean editable){
        TableRow tr;
        tr = new TableRow(context);
        tr.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        for (int i = 0; i < data.size();i++){
            if(editable){
                tr.addView(addEditableCell(data.get(i).toString(),context));
            }
            else {
                tr.addView(addUneditableCell(String.format("%.3f",data.get(i)),context));
            }
        }
        return tr;
    }

    private static EditText addEditableCell(String data, Context context){
        EditText text = new EditText(context);
        text.setInputType(InputType.TYPE_CLASS_NUMBER);
        text.setGravity(Gravity.CENTER);
        text.setText(data);
        text.setWidth(200);
        return text;
    }

    private static TextView addUneditableCell(String data, Context context){
        TextView text = new TextView(context);
        text.setText(data);
        text.setGravity(Gravity.CENTER);
        text.setPadding(40,40,40,40);
        return text;
    }

    public static TableLayout crearTablaVacía(TableLayout tableLayout,int alto, int ancho){
        tableLayout.removeAllViews();
        return addData(tableLayout ,alto,ancho);
    }

    private static TableLayout addData(TableLayout tableLayout ,int alto, int ancho){
        for(int i = 0; i < alto;i++) {
            tableLayout.addView(addRow(tableLayout.getContext(),ancho));
        }
        return tableLayout;
    }



    private static TableRow addRow(Context context, int ancho){
        TableRow tr;
        tr = new TableRow(context);
        tr.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        for (int i = 0; i < ancho;i++){
            tr.addView(addCell(context));
        }
        return tr;
    }

    private static EditText addCell(Context context){
        EditText text = new EditText(context);
        text.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_SIGNED|InputType.TYPE_NUMBER_FLAG_DECIMAL);
        text.setGravity(Gravity.CENTER);
        text.setText("0");
        text.setWidth(200);
        return text;
    }

    @Override
    public String toString() {
        String res = "";
        for(int i = 0; i < datos.size();i++){
            for(int j = 0; j < datos.get(i).size();j++){
                res += String.format("%.3f ",datos.get(i).get(j));
            }
            res += "\n";
        }
        return res;
    }
}

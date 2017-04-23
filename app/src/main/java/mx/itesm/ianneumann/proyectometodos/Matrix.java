package mx.itesm.ianneumann.proyectometodos;

import android.content.Context;
import android.text.InputType;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.NavigableMap;

/**
 * Created by Ian Neumann on 21/04/2017.
 */

public class Matrix {

    ArrayList<ArrayList<Double>> datos;

    public Matrix(TableLayout table){

        TableRow tr;
        datos = new ArrayList<>(table.getChildCount());
        for(int i = 0; i < table.getChildCount();i++) {
            tr = (TableRow) table.getChildAt(i);
            datos.add(new ArrayList<Double>(tr.getChildCount()));
            for (int j = 0; j < tr.getChildCount(); j++) {
                datos.get(i).add(j,Double.parseDouble(((EditText) tr.getChildAt(j)).getText().toString()));
            }
        }
    }

    public Matrix(Matrix matrix){
        datos = new ArrayList<>();
        for(ArrayList<Double> d : matrix.getData()) {
            datos.add(new ArrayList<>(d));
        }
    }

    public Matrix(ArrayList<ArrayList<Double>> datos){
        this.datos = new ArrayList<>(datos);
    }

    public ArrayList<ArrayList<Double>> getData(){
        return datos;
    }

    public ArrayList<Double> getRow(int i){
        return datos.get(i);
    }

    public ArrayList<Double> getColumn(int i){
        ArrayList<Double> resultado = new ArrayList<>();
        for(int j = 0; j < datos.size();j++){
            resultado.add(datos.get(j).get(i));
        }
        return resultado;
    }

    public void setColumn(int i,ArrayList<Double> list){

        for(int j = 0; j < datos.size();j++){
            datos.get(j).set(i,list.get(j));
        }
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

    public void addToRow(int resultIndex, int rowIndex){
        Double a,b;
        for(int j = 0; j < datos.get(resultIndex).size() ;j++){
            a = datos.get(resultIndex).get(j);
            b = datos.get(rowIndex).get(j);
            datos.get(resultIndex).set(j,a+b);
        }
    }

    public void addToRow(int indice, ArrayList<Double> list){
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
    public TableLayout crearTablaResultadoRowHeaders(TableLayout tableLayout,ArrayList<String> headers){
        tableLayout.removeAllViews();
        return addDataRowHeaders(tableLayout,datos,false,headers);
    }

    private static TableLayout addDataRowHeaders(TableLayout tableLayout,ArrayList<ArrayList<Double>> matriz,boolean editable,ArrayList<String> headers) {
        for(int i = 0; i< matriz.size();i++){
            tableLayout.addView(addRowWithHeader(new ArrayList<Object>(matriz.get(i)),tableLayout.getContext(),editable, headers.get(i)));
        }
        return tableLayout;
    }

    public void inverse(){
        for(int i = 0; i < datos.size();i++){
            for(int j = 0; j < datos.size();j++){
                datos.get(i).add(i==j?1.0:0.0);
            }
        }
        GaussJordan();
        ArrayList<ArrayList<Double>> d =  new ArrayList<>();
        for(int i = 0; i < datos.size();i++){
            d.add(new ArrayList<Double>());
            for(int j = 0; j < datos.size();j++){
                d.get(i).add(datos.get(i).get(j+datos.size()));
            }
        }
        datos = new Matrix(d).getData();
    }

    public void multiply(Matrix B){
        int p,q,r;
        p = getData().size();
        q = getData().get(0).size();
        r = B.getData().get(0).size();

        if(q != B.getData().size()){
            throw new ArithmeticException("No se pueden multiplicar matrices que no coincidan");
        }
        ArrayList<ArrayList<Double>> resultado = new ArrayList<>();
        Double suma;
        for(int i = 0; i < p;i++){
            resultado.add(new ArrayList<Double>());
            for(int j = 0; j<r;j++){
                suma = 0.0;
                for(int k = 0; k < q;k++){
                    suma += datos.get(i).get(k)*B.getData().get(k).get(j);
                }
                resultado.get(i).add(suma);
            }
        }
        datos = resultado;
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

    private static TableRow addRow(ArrayList<Object> data, Context context, boolean editable){
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

    private static TableRow addRowWithHeader(ArrayList<Object> data, Context context, boolean editable,String header){
        TableRow tr;
        tr = new TableRow(context);
        tr.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        tr.addView(addUneditableCell(header,context));
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

    public void GaussJordan(){
        Double coeficiente;
        ArrayList<Double> temporal;

        for(int i = 0; i < datos.size();i++){

            multiplyRow(i,1/datos.get(i).get(i));

            for (int j = 0; j < datos.size();j++){
                if(i==j){
                    continue;
                }
                coeficiente = datos.get(j).get(i);
                temporal = new ArrayList<>(getRow(i));
                Matrix.multiply(temporal,-coeficiente);
                addToRow(j,temporal);
            }
        }
    }

    public static Matrix GaussJordan(Matrix m){
        Double coeficiente;
        ArrayList<Double> temporal;

        for(int i = 0; i < m.getData().size();i++){

            m.multiplyRow(i,1/m.getData().get(i).get(i));

            for (int j = 0; j < m.getData().size() && j!= i;j++){
                coeficiente = m.getData().get(j).get(i);
                temporal = new ArrayList<>(m.getRow(i));
                Matrix.multiply(temporal,-coeficiente);
                m.addToRow(j,temporal);
            }
        }
        return m;
    }

    public static Matrix inverse(Matrix m){
        for(int i = 0; i < m.getData().size();i++){
            for(int j = 0; j < m.getData().get(i).size();j++){
                m.getData().get(i).add(i==j?1.0:0.0);
            }
        }
        m.GaussJordan();
        ArrayList<ArrayList<Double>> d =  new ArrayList<>();
        for(int i = 0; i < m.getData().size();i++){
            d.add(new ArrayList<Double>());
            for(int j = 0; j < m.getData().size();j++){
                d.get(i).add(m.getData().get(i).get(j+m.getData().size()));
            }
        }
        m = new Matrix(d);
        return m;
    }

    public void transpose() {
        ArrayList<ArrayList<Double>> nuevaMatriz = new ArrayList<>();
        for(int i = 0; i < datos.get(0).size();i++){
            nuevaMatriz.add(new ArrayList<Double>());
            for(int j = 0; j < datos.size();j++){
                nuevaMatriz.get(i).add(0.0);
            }
        }
        Double temp;
        for(int i = 0; i < datos.size();i++){
            for(int j = 0; j < datos.get(i).size();j++){
                nuevaMatriz.get(j).set(i,datos.get(i).get(j));
            }
        }
        datos = nuevaMatriz;
    }

    public void swapRows(int i, int j){
        ArrayList<Double> temp;
        temp = datos.get(i);
        datos.set(i,datos.get(j));
        datos.set(j,temp);
    }
}

package com.example.al.calculadora;

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class Calcu extends AppCompatActivity {
    private TextView _pantalla;
    private String display = "";
    private String opActual = "";
    private boolean resuelto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcu);
        _pantalla = (TextView)findViewById(R.id.textView);
        _pantalla.setText(display);
    }

    private void updateScreen(){
       _pantalla.setText(display);
    }

    protected void onClickNum(View v){
        if(resuelto){
            clear();
            updateScreen();
            resuelto = false;
        }
        Button b = (Button) v;
        display += b.getText();
        updateScreen();
    }

    protected void onClickOp(View v){
        Button b = (Button) v;
        display += b.getText();
        opActual = b.getText().toString();
        updateScreen();
    }

    private void clear(){
        display="";
        opActual= "";
        resuelto = false;
    }

    protected void onClickCA(View v){
        clear();
        updateScreen();
    }

    private double operar(String a, String b, String op){
        resuelto = true;
        switch (op){
            case "+":return Double.valueOf(a) + Double.valueOf(b);
            case "−":return Double.valueOf(a) - Double.valueOf(b);
            case "×":return Double.valueOf(a) * Double.valueOf(b);
            case "÷":try{
                return Double.valueOf(a) / Double.valueOf(b);
            }catch(Exception e){
                resuelto = false;
                Log.d("Calc", e.getMessage());
            }
            case "%":return Double.valueOf(a) % Double.valueOf(b);
            default: return -1;
        }
    }

    protected void onClickIgual(View v){
        String[] operacion = display.split(Pattern.quote(opActual));
        if(operacion.length < 2) return;

        Double _resultado = operar(operacion[0], operacion[1], opActual);
        _pantalla.setText(display + "\n" + String.valueOf(_resultado));

    }
}

package com.upiita.movimientodeimagenes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VistaGraficos grafico = new VistaGraficos(this);
        setContentView(grafico);
    }

    class VistaGraficos extends View {
        float x1 = 300, y1 = 300;
        float x2 = 600, y2 = 300;
        float x3 = 300, y3 = 600;
        float x4 = 600, y4 = 600; // Nuevo círculo
        String texto = "";
        int circuloActivo = -1; // -1 significa ningún círculo activo

        public VistaGraficos(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint pintura = new Paint();
            pintura.setColor(Color.LTGRAY);
            canvas.drawPaint(pintura);

            // Dibujar círculos
            pintura.setColor(Color.RED);
            canvas.drawCircle(x1, y1, 60, pintura);
            pintura.setColor(Color.BLUE);
            canvas.drawCircle(x2, y2, 120, pintura);
            pintura.setColor(Color.GREEN);
            canvas.drawCircle(x3, y3, 30, pintura);
            pintura.setColor(Color.BLACK);
            canvas.drawCircle(x4, y4, 90, pintura); // Nuevo círculo negro

            // Dibujar texto
            pintura.setColor(Color.BLACK);
            pintura.setTextSize(80);
            canvas.drawText("x1= " + x1 + "  y1= " + y1, 100, 100, pintura);
            canvas.drawText("x2= " + x2 + "  y2= " + y2, 100, 180, pintura);
            canvas.drawText("x3= " + x3 + "  y3= " + y3, 100, 260, pintura);
            canvas.drawText("x4= " + x4 + "  y4= " + y4, 100, 340, pintura); // Coordenadas del nuevo círculo
            canvas.drawText("Accion= " + texto, 100, 420, pintura);
        }

        @Override
        public boolean onTouchEvent(MotionEvent evento) {
            float x = evento.getX();
            float y = evento.getY();

            if (evento.getAction() == MotionEvent.ACTION_DOWN) {
                texto = "Action Down";
                if (distancia(x, y, x1, y1) <= 60) {
                    circuloActivo = 1;
                } else if (distancia(x, y, x2, y2) <= 120) {
                    circuloActivo = 2;
                } else if (distancia(x, y, x3, y3) <= 30) {
                    circuloActivo = 3;
                } else if (distancia(x, y, x4, y4) <= 90) {
                    circuloActivo = 4; // Nuevo círculo
                } else {
                    circuloActivo = -1;
                }
            } else if (evento.getAction() == MotionEvent.ACTION_UP) {
                texto = "Action Up";
                circuloActivo = -1;
            } else if (evento.getAction() == MotionEvent.ACTION_MOVE) {
                texto = "Action Move";
                if (circuloActivo == 1) {
                    x1 = x;
                    y1 = y;
                } else if (circuloActivo == 2) {
                    x2 = x;
                    y2 = y;
                } else if (circuloActivo == 3) {
                    x3 = x;
                    y3 = y;
                } else if (circuloActivo == 4) {
                    x4 = x;
                    y4 = y; // Mover el nuevo círculo
                }
            }
            invalidate();
            return true;
        }

        private float distancia(float x1, float y1, float x2, float y2) {
            return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        }
    }
}
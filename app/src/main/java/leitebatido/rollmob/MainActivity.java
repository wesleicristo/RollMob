package leitebatido.rollmob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button botao;
    private ProgressBar pbTempo;
    private TextView txvResultado;

    Tempo tempo = new Tempo();
    Thread thTempo = new Thread(tempo);

    private class Dado {
        private Dado(int lado){nLados = lado; Rolar();}
        int nLados;
        int valor;
        private void Rolar(){
            Random gerador = new Random();
            valor = gerador.nextInt(nLados)+1;
        }
        private String Resultado(){
            return String.valueOf(valor);
        }
    }

    private class Tempo implements Runnable{
        int cont = 0;
        @Override
        public void run() {
            while(!isFinishing()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (cont > 100){
                    cont = 0;
                } else {
                    cont++;
                }
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pbTempo.setProgress(cont);
                }
            });
        }
        public void setCont(int cont){
            this.cont = cont;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pbTempo = (ProgressBar)findViewById(R.id.progressBar);
        txvResultado = (TextView)findViewById(R.id.txtvresultado);
        thTempo.start();
    }

    public void rolar(View view){
        botao = (Button)view;
        Dado dado = new Dado(Integer.parseInt(botao.getText().toString().substring(1)));
        txvResultado.setText(dado.Resultado());
    }
}

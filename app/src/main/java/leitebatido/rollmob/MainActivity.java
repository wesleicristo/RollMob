package leitebatido.rollmob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    ProgressBar barra = (ProgressBar)findViewById(R.id.progressBar);
    int tempo;
    contador cont = new contador();

    public class contador extends Thread{
        @Override
        public void run() {
            while(true) {
                if (tempo >= 100) {
                    tempo = 0;
                } else {
                    tempo++;
                }
                barra.setProgress(tempo);
            }
        }
    }

    public class dado {
        public dado(int lado){nlados = lado;}
        int nlados;
        private int rolar(){
            Random roll = new Random();
            return roll.nextInt(nlados)+1;
        }
    }

    dado d4 = new dado(4);
    dado d6 = new dado(6);
    dado d8 = new dado(8);
    dado d10 = new dado(10);
    dado d12 = new dado(12);
    dado d20 = new dado(20);

    public void rolar(View view){
        cont.start();
        Button dado = (Button)view;
        switch (Integer.parseInt(dado.getText().toString().substring(1))){
            case 4: {
                resultado(d4.rolar());
                break;
            }
            case 6: {
                resultado(d6.rolar());
                break;
            }
            case 8: {
                resultado(d8.rolar());
                break;
            }
            case 10: {
                resultado(d10.rolar());
                break;
            }
            case 12: {
                resultado(d12.rolar());
                break;
            }
            case 20: {
                resultado(d20.rolar());
                break;
            }
        }
    }

    private void resultado(int valor){
        TextView txt = (TextView)findViewById(R.id.txtvresultado);
        txt.setText(String.valueOf(valor));
    }

}

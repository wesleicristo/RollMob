package leitebatido.rollmob;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView testetexto;

    private Button botao;
    private ProgressBar pbTempo;
    private TextView txvResultado;
    private TextView txvDetalhe;
    private int cont=0;

    private boolean limpo = true;

    private int resultado;
    private String detalhe;

    Tempo tempo = new Tempo();
    Thread thTempo = new Thread(tempo);

    Dado d4 = new Dado(4);
    Dado d6 = new Dado(6);
    Dado d8 = new Dado(8);
    Dado d10 = new Dado(10);
    Dado d12 = new Dado(12);
    Dado d20 = new Dado(20);

    private Dado[] dados = {d4,d6,d8,d10,d12,d20};

    private class Dado {
        private Dado(int lado){nLados = lado;}
        int nLados;
        int valor;
        int nDados;
        private void Rolar(){
            Random gerador = new Random();
            valor = gerador.nextInt(nLados)+1;
            nDados++;
        }
        private int getValor(){
            return valor;
        }
        private int getNDados(){
            return nDados;
        }
        private void limpaDados(){
            nDados = 0;
        }
    }

    private class Tempo implements Runnable{
        @Override
        public void run() {
            while(true){
                cont--;
                if (cont < 0){
                    cont = 0;
                } else {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pbTempo.setProgress(cont);
                            limpo=false;
                        }
                    });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (cont <= 0 && !limpo){
                            limparBotoes();
                            for (int i = 0;i < dados.length; i++){
                                dados[i].limpaDados();
                            }
                            resultado = 0;
                            txvResultado.setText(R.string.resultado);
                            txvDetalhe.setText(R.string.detalhe);
                            limpo=true;
                        }
                    }
                });
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pbTempo = (ProgressBar)findViewById(R.id.progressBar);
        txvResultado = (TextView)findViewById(R.id.txtvresultado);
        txvDetalhe = (TextView)findViewById(R.id.txtvdetalhe);
        thTempo.start();
        testetexto = (TextView)findViewById(R.id.textView);
    }

    private void limparBotoes(){
        Button d4 = (Button)findViewById(R.id.btnd4);
        Button d6 = (Button)findViewById(R.id.btnd6);
        Button d8 = (Button)findViewById(R.id.btnd8);
        Button d10 = (Button)findViewById(R.id.btnd10);
        Button d12 = (Button)findViewById(R.id.btnd12);
        Button d20 = (Button)findViewById(R.id.btnd20);
        d4.setText(R.string.d4);
        d6.setText(R.string.d6);
        d8.setText(R.string.d8);
        d10.setText(R.string.d10);
        d12.setText(R.string.d12);
        d20.setText(R.string.d20);
    }

    public void rolar(View view){
        botao = (Button)view;
        if (limpo)detalhe = "D: ";
        switch (botao.getId()){
            case R.id.btnd4:{
                d4.Rolar();
                resultado += d4.getValor();
                txvResultado.setText(String.valueOf(resultado));
                if (!limpo)detalhe = detalhe + ",";
                detalhe = detalhe + d4.getValor();
                botao.setText(d4.getNDados()+"D4");
                break;
            }
            case R.id.btnd6: {
                d6.Rolar();
                resultado += d6.getValor();
                txvResultado.setText(String.valueOf(resultado));
                if (!limpo)detalhe = detalhe + ",";
                detalhe = detalhe + d6.getValor();
                botao.setText(d6.getNDados()+"D6");
                break;
            }
            case R.id.btnd8: {
                d8.Rolar();
                resultado += d8.getValor();
                txvResultado.setText(String.valueOf(resultado));
                if (!limpo)detalhe = detalhe + ",";
                detalhe = detalhe + d8.getValor();
                botao.setText(d8.getNDados()+"D8");
                break;
            }
            case R.id.btnd10: {
                d10.Rolar();
                resultado += d10.getValor();
                txvResultado.setText(String.valueOf(resultado));
                if (!limpo)detalhe = detalhe + ",";
                detalhe = detalhe + d10.getValor();
                botao.setText(d10.getNDados()+"D10");
                break;
            }
            case R.id.btnd12: {
                d12.Rolar();
                resultado += d12.getValor();
                txvResultado.setText(String.valueOf(resultado));
                if (!limpo)detalhe = detalhe + ",";
                detalhe = detalhe + d12.getValor();
                botao.setText(d12.getNDados()+"D12");
                break;
            }
            case R.id.btnd20: {
                d20.Rolar();
                resultado += d20.getValor();
                txvResultado.setText(String.valueOf(resultado));
                if (!limpo)detalhe = detalhe + ",";
                detalhe = detalhe + d20.getValor();
                botao.setText(d20.getNDados()+"D20");
                break;
            }
            default:{
                break;
            }
        }
        txvDetalhe.setText(detalhe);
        cont = 100;
    }
}

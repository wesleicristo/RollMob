package leitebatido.rollmob;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView botao;
    private ProgressBar pbTempo;
    private TextView txvResultado;
    private TextView txvDetalhe;
    private int cont=0;

    private Color corFundo;

    private RelativeLayout screenView;

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
        private int getnLados(){
            return nLados;
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
                            if(cont <= 90){
                                screenView.setBackgroundColor(getResources().getColor(R.color.bGcolor));
                            }
                        }
                    });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (cont <= 0 && !limpo){
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
        pbTempo = (ProgressBar)findViewById(R.id.pbtempo);
        txvResultado = (TextView)findViewById(R.id.txtvresultado);
        txvDetalhe = (TextView)findViewById(R.id.txtvdetalhe);
        thTempo.start();
        screenView = (RelativeLayout)findViewById(R.id.view);

        AdView adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    public void rolar(View view){
        botao = (ImageView)view;
        if (limpo)detalhe = "D: ";
        switch (botao.getId()){
            case R.id.iD4:{
                d4.Rolar();
                resultado += d4.getValor();
                txvResultado.setText(String.valueOf(resultado));
                if (!limpo)detalhe = detalhe + ",";
                if (d4.getValor() == d4.getnLados()){
                    screenView.setBackgroundColor(getResources().getColor(R.color.ACcrit));
                } else if (d4.getValor() == 1){
                    screenView.setBackgroundColor(getResources().getColor(R.color.FLcrit));
                } else {
                    screenView.setBackgroundColor(getResources().getColor(R.color.bGcolor));
                }
                detalhe = detalhe + d4.getValor();
                break;
            }
            case R.id.iD6: {
                d6.Rolar();
                resultado += d6.getValor();
                txvResultado.setText(String.valueOf(resultado));
                if (!limpo)detalhe = detalhe + ",";
                if (d6.getValor() == d6.getnLados()){
                    screenView.setBackgroundColor(getResources().getColor(R.color.ACcrit));
                } else if (d6.getValor() == 1){
                    screenView.setBackgroundColor(getResources().getColor(R.color.FLcrit));
                } else {
                    screenView.setBackgroundColor(getResources().getColor(R.color.bGcolor));
                }
                detalhe = detalhe + d6.getValor();
                break;
            }
            case R.id.iD8: {
                d8.Rolar();
                resultado += d8.getValor();
                txvResultado.setText(String.valueOf(resultado));
                if (!limpo)detalhe = detalhe + ",";
                if (d8.getValor() == d8.getnLados()){
                    screenView.setBackgroundColor(getResources().getColor(R.color.ACcrit));
                } else if (d8.getValor() == 1){
                    screenView.setBackgroundColor(getResources().getColor(R.color.FLcrit));
                } else {
                    screenView.setBackgroundColor(getResources().getColor(R.color.bGcolor));
                }
                detalhe = detalhe + d8.getValor();
                break;
            }
            case R.id.iD10: {
                d10.Rolar();
                resultado += d10.getValor();
                txvResultado.setText(String.valueOf(resultado));
                if (!limpo)detalhe = detalhe + ",";
                if (d10.getValor() == d10.getnLados()){
                    screenView.setBackgroundColor(getResources().getColor(R.color.ACcrit));
                } else if (d10.getValor() == 1){
                    screenView.setBackgroundColor(getResources().getColor(R.color.FLcrit));
                } else {
                    screenView.setBackgroundColor(getResources().getColor(R.color.bGcolor));
                }
                detalhe = detalhe + d10.getValor();
                break;
            }
            case R.id.iD12: {
                d12.Rolar();
                resultado += d12.getValor();
                txvResultado.setText(String.valueOf(resultado));
                if (!limpo)detalhe = detalhe + ",";
                if (d12.getValor() == d12.getnLados()){
                    screenView.setBackgroundColor(getResources().getColor(R.color.ACcrit));
                } else if (d12.getValor() == 1){
                    screenView.setBackgroundColor(getResources().getColor(R.color.FLcrit));
                } else {
                    screenView.setBackgroundColor(getResources().getColor(R.color.bGcolor));
                }
                detalhe = detalhe + d12.getValor();
                break;
            }
            case R.id.iD20: {
                d20.Rolar();
                resultado += d20.getValor();
                txvResultado.setText(String.valueOf(resultado));
                if (!limpo)detalhe = detalhe + ",";
                if (d20.getValor() == d20.getnLados()){
                    screenView.setBackgroundColor(getResources().getColor(R.color.ACcrit));
                } else if (d20.getValor() == 1){
                    screenView.setBackgroundColor(getResources().getColor(R.color.FLcrit));
                } else {
                    screenView.setBackgroundColor(getResources().getColor(R.color.bGcolor));
                }
                detalhe = detalhe + d20.getValor();
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

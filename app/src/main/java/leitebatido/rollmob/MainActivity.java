package leitebatido.rollmob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void rolar(View view){
        Button d = (Button)view;

        int dado = Integer.parseInt(d.getText().toString().substring(2));

        /*

        Random random = new Random();
        int valor = random.nextInt(dado)+1;

        String resultado = "Res: ";
        resultado.concat(String.valueOf(valor));

*/

        TextView txtres = (TextView)findViewById(R.id.txtvresultado);
        txtres.setText(dado);

    }

}

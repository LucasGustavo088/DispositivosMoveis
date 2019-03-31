package br.com.lucas.ccp3anmca;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import br.com.lucas.meuprimeiroappccp3anmca.R;

public class MainActivity extends AppCompatActivity {

    private EditText mensagemEditText;
    public static final String CHAVE_MSG =  "br.usjt.msg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Inflando a view
        mensagemEditText = findViewById(R.id.mensagemEditText);
    }

    public void enviarMensagem (View view){
        String mensagem = mensagemEditText.getEditableText().toString();
        Intent intent = new Intent (this, br.com.lucas.meuprimeiroappccp3anmca.ExibeMensagemActivity.class);
        intent.putExtra(CHAVE_MSG, mensagem);
        startActivity(intent);
    }
}

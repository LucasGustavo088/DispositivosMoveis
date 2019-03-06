package br.com.lucas.meuprimeiroappccp3anmca;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

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
        Intent intent = new Intent (this, ExibeMensagemActivity.class);
        intent.putExtra(CHAVE_MSG, mensagem);
        startActivity(intent);
    }
}

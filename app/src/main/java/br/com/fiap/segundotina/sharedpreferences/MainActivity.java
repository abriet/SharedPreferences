package br.com.fiap.segundotina.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtUsuario;
    EditText edtSenha;
    CheckBox chkSalvarSenha;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        chkSalvarSenha = (CheckBox) findViewById(R.id.chkSalvarSenha);

        //Inicializa o SharedPreferences no modo privado
        sp = getPreferences(MODE_PRIVATE);
        //Modo privado só a aplicação pode ver este dado
        edtUsuario.setText(sp.getString("usuario",""));
        edtSenha.setText(sp.getString("senha",""));
    }

    public void login(View v){
        String usuario = edtUsuario.getText().toString().trim().toUpperCase();
        String senha = edtSenha.getText().toString().trim();

        if (usuario.equals("FIAP") && senha.equals("123")){
            SharedPreferences.Editor e = sp.edit();
            if (chkSalvarSenha.isChecked()){
                e.putString("usuario",usuario);
                e.putString("senha",senha);
            }else{
                e.remove("usuario");
                e.remove("senha");
            }

            e.commit();

            //Logica para abrir outra activity



//          Abrindo outra activity
            Intent it = new Intent(this, DadosActivity.class);
            startActivity(it);
            return;
        }

        Toast.makeText(this, R.string.usuario_incorreto, Toast.LENGTH_SHORT).show();
    }

}

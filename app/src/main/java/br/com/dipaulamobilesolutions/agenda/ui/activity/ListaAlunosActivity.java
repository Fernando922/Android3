package br.com.dipaulamobilesolutions.agenda.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.dipaulamobilesolutions.agenda.R;
import br.com.dipaulamobilesolutions.agenda.dao.AlunoDAO;
import br.com.dipaulamobilesolutions.agenda.model.activity.Aluno;
import br.com.dipaulamobilesolutions.agenda.ui.ListaAlunosView;
import br.com.dipaulamobilesolutions.agenda.ui.adapter.ListaAlunosAdapter;

import static br.com.dipaulamobilesolutions.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    private FloatingActionButton fabAdicionaAluno;
    private final ListaAlunosView listaAlunosView = new ListaAlunosView(this);
    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        fabAdicionaAluno = findViewById(R.id.fabAdicionaAluno);
        configuraFabNovoAluno();
        configuraLista();
    }

    //adiciona menus de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        //se nao colocar if e else todos os itens do menu vao ter o mesmo funcionamento
        if (item.getItemId() == R.id.menu_remove) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            listaAlunosView.confirmaRemocao(menuInfo);
        }


        return super.onContextItemSelected(item);

    }


    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizaLista();
    }

    private void configuraFabNovoAluno() {
        fabAdicionaAluno.setOnClickListener(v -> abreformularioModoInsereAluno());
    }

    private void abreformularioModoInsereAluno() {
        Intent intent = new Intent(this, FormularioAlunoActivity.class);
        startActivity(intent);
    }


    private void configuraLista() {
        ListView lvAlunos = findViewById(R.id.lvAlunos);
        listaAlunosView.configuraAdapter(lvAlunos);
        configuraListenerDeCliquePorItem(lvAlunos);
        registerForContextMenu(lvAlunos);  //registra no menu de contextoo!
    }


    private void configuraListenerDeCliquePorItem(ListView lvAlunos) {
        lvAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);
                abreFormularioModoEditaAluno(alunoEscolhido);
            }
        });


    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        intent.putExtra(CHAVE_ALUNO, aluno);
        startActivity(intent);
    }


}


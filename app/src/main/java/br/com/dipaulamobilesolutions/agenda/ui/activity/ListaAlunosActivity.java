package br.com.dipaulamobilesolutions.agenda.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
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

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.dipaulamobilesolutions.agenda.R;
import br.com.dipaulamobilesolutions.agenda.dao.AlunoDAO;
import br.com.dipaulamobilesolutions.agenda.model.activity.Aluno;
import br.com.dipaulamobilesolutions.agenda.ui.adapter.ListaAlunosAdapter;

import static br.com.dipaulamobilesolutions.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    private FloatingActionButton fabAdicionaAluno;
    private ListaAlunosAdapter adapter;
    private final AlunoDAO dao = new AlunoDAO();


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
            confirmaRemocao(menuInfo);
        }


        return super.onContextItemSelected(item);

    }

    private void confirmaRemocao(AdapterView.AdapterContextMenuInfo menuInfo) {
        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);

        //Padrão builder (construir)
        new AlertDialog
                .Builder(this)
                .setTitle("Remover aluno")
                .setMessage("tem certeza que deseja remover este aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removerAlunoDaLista(alunoEscolhido);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.atualiza(dao.todos());
    }

    private void configuraFabNovoAluno() {
        fabAdicionaAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreformularioModoInsereAluno();

            }
        });
    }

    private void abreformularioModoInsereAluno() {
        Intent intent = new Intent(this, FormularioAlunoActivity.class);
        startActivity(intent);
    }


    private void configuraLista() {
        ListView lvAlunos = findViewById(R.id.lvAlunos);
        configuraAdapter(lvAlunos);
        configuraListenerDeCliquePorItem(lvAlunos);
        registerForContextMenu(lvAlunos);  //registra no menu de contextoo!
    }


    private void removerAlunoDaLista(Aluno alunoEscolhido) {
        dao.remove(alunoEscolhido);
        adapter.remove(alunoEscolhido);  //remove da lista recarregando a pagina
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

    private void configuraAdapter(ListView lvAlunos) {
        //dataset lista interna de items de um adapter
        adapter = new ListaAlunosAdapter(this);
        lvAlunos.setAdapter(adapter);
    }
}


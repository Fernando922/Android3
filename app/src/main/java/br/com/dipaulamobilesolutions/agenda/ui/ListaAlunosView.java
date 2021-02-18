package br.com.dipaulamobilesolutions.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.dipaulamobilesolutions.agenda.dao.AlunoDAO;
import br.com.dipaulamobilesolutions.agenda.model.activity.Aluno;
import br.com.dipaulamobilesolutions.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {


    private Context context;
    private final AlunoDAO dao = new AlunoDAO();
    private ListaAlunosAdapter adapter;

    public ListaAlunosView(Context context){
        this.context = context;

    }

    public void confirmaRemocao(MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AdapterView.AdapterContextMenuInfo menuInfo =
                                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                        remove(alunoEscolhido);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }



    public void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ListaAlunosAdapter(context);
        listaDeAlunos.setAdapter(adapter);
    }

    private void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    public void atualizaLista() {
        adapter.atualiza(dao.todos());
    }

}

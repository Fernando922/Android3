package br.com.dipaulamobilesolutions.agenda;

import android.app.Application;

import br.com.dipaulamobilesolutions.agenda.dao.AlunoDAO;
import br.com.dipaulamobilesolutions.agenda.model.activity.Aluno;


//para dados estaticos é necessário manter na camada da apĺicação que é o estado global!
public class AgendaApplication extends Application {

    private final AlunoDAO dao = new AlunoDAO();


    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosExemplo();
    }

    private void criaAlunosExemplo() {
        dao.salva(new Aluno("Fernando", "16994153565", "454545@sdss"));
        dao.salva(new Aluno("Fernando", "16994153565", "454545@sdss"));
        dao.salva(new Aluno("Fernando", "16994153565", "454545@sdss"));
    }
}

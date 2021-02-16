package br.com.dipaulamobilesolutions.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.dipaulamobilesolutions.agenda.model.activity.Aluno;

public class AlunoDAO {



    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;


    public void salva(Aluno alunoCriado) {
        alunoCriado.setId(contadorDeIds);
        alunos.add(alunoCriado);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public void edita(Aluno aluno){

        //encontra o aluno
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);
        //se o aluno encontrado for diferente de null
        if(alunoEncontrado != null){
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    private Aluno buscaAlunoPeloId(Aluno aluno) {
        for(Aluno a: alunos){
            if(a.getId() == aluno.getId()){
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos); //imutabilidade
    }

    public void remove(Aluno aluno) {
        Aluno alunoDevolvido = buscaAlunoPeloId(aluno);
        if(alunoDevolvido != null){
            alunos.remove(alunoDevolvido);
        }
    }
}

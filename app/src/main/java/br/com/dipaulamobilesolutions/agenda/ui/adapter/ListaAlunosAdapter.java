package br.com.dipaulamobilesolutions.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.dipaulamobilesolutions.agenda.R;
import br.com.dipaulamobilesolutions.agenda.model.activity.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View viewCriada = LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, viewGroup, false);
        return viewCriada;
    }

    public void clear() {
        alunos.clear();
    }


    public void addAll(List<Aluno> alunos) {
        this.alunos.addAll(alunos);
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
    }
}

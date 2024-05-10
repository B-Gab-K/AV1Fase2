package com.example.av1fase2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.av1fase2.Aluno;
import com.example.av1fase2.R;
import java.util.List;

public class AlunoAdapter extends ArrayAdapter<Aluno> {

    private Context context;
    private List<Aluno> alunos;

    public AlunoAdapter(Context context, List<Aluno> alunos) {
        super(context, 0, alunos);
        this.context = context;
        this.alunos = alunos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item_aluno, parent, false);
        }

        Aluno aluno = getItem(position);

        TextView nomeTextView = itemView.findViewById(R.id.listNome);
        TextView emailTextView = itemView.findViewById(R.id.listEmail);
        TextView telefoneTextView = itemView.findViewById(R.id.listTelefone);
        TextView campusTextView = itemView.findViewById(R.id.listCampus);
        TextView generoTextView = itemView.findViewById(R.id.listGenero);

        assert aluno != null;
        nomeTextView.setText(aluno.getNome());
        emailTextView.setText(aluno.getEmail());
        telefoneTextView.setText(aluno.getTelefone());
        campusTextView.setText(aluno.getCampus());
        generoTextView.setText(aluno.getGenero());

        return itemView;
    }
}
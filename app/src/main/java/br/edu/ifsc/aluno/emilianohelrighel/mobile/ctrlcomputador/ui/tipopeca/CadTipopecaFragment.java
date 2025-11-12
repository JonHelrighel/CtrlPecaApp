package br.edu.ifsc.aluno.emilianohelrighel.mobile.ctrlcomputador.ui.tipopeca;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import br.edu.ifsc.aluno.emilianohelrighel.mobile.ctrlcomputador.R;
import br.edu.ifsc.aluno.emilianohelrighel.mobile.ctrlcomputador.model.Peca;

public class CadTipopecaFragment extends Fragment {

    private static final String TAG = "CadTipopecaFragment";

    // Declaração das views do XML
    private EditText etNmPeca;
    private EditText etDeMarca;
    private EditText etVlPotencia;
    private EditText etDeModelo;
    private EditText etVlCapacidade;
    private EditText etVlPolegadas;
    private EditText etVlDpi;
    private Spinner spIdTipoPeca;
    private Button btnSalvar;

    public CadTipopecaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate chamado");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView chamado");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cad_tipopeca, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated chamado");

        // Associando as views do XML aos objetos Java
        etNmPeca = view.findViewById(R.id.etNmPeca);
        etDeMarca = view.findViewById(R.id.etDeMarca);
        etVlPotencia = view.findViewById(R.id.etVlPotencia);
        etDeModelo = view.findViewById(R.id.etDeModelo);
        etVlCapacidade = view.findViewById(R.id.etVlCapacidade);
        etVlPolegadas = view.findViewById(R.id.etVlPolegadas);
        etVlDpi = view.findViewById(R.id.etVlDpi);
        spIdTipoPeca = view.findViewById(R.id.spIdTipoPeca);
        btnSalvar = view.findViewById(R.id.btnSalvar);

        // Configurando o listener para o botão Salvar
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarPeca();
            }
        });
    }

    private void salvarPeca() {
        try {
            // Criando um novo objeto Peca
            Peca peca = new Peca();

            // Definindo os valores dos EditTexts e Spinner
            peca.setNmPeca(etNmPeca.getText().toString().trim());
            peca.setDeMarca(etDeMarca.getText().toString().trim());
            peca.setVlPotencia(etVlPotencia.getText().toString().trim());
            peca.setDeModelo(etDeModelo.getText().toString().trim());
            peca.setVlCapacidade(etVlCapacidade.getText().toString().trim());
            peca.setVlPolegadas(etVlPolegadas.getText().toString().trim());
            peca.setVlDpi(etVlDpi.getText().toString().trim());

            // Para idTipoPeca, assumindo que o Spinner usa posições como IDs (ex.: posição 0 = ID 1, ajuste se necessário)
            int idTipoPeca = spIdTipoPeca.getSelectedItemPosition() + 1; // +1 se IDs começam em 1
            peca.setIdTipoPeca(idTipoPeca);

            // Aqui você pode salvar no banco, enviar para API, etc. Por enquanto, só logamos o JSON
            Log.d(TAG, "Peca salva: " + peca.toJsonObject().toString());

            // Mostrando uma mensagem de sucesso
            Toast.makeText(getContext(), "Peça salva com sucesso!", Toast.LENGTH_SHORT).show();

            // Limpar os campos após salvar (opcional)
            limparCampos();

        } catch (IllegalArgumentException e) {
            // Tratando erros de validação (ex.: nome curto)
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Outros erros
            Log.e(TAG, "Erro ao salvar peça", e);
            Toast.makeText(getContext(), "Erro ao salvar peça", Toast.LENGTH_SHORT).show();
        }
    }

    private void limparCampos() {
        etNmPeca.setText("");
        etDeMarca.setText("");
        etVlPotencia.setText("");
        etDeModelo.setText("");
        etVlCapacidade.setText("");
        etVlPolegadas.setText("");
        etVlDpi.setText("");
        spIdTipoPeca.setSelection(0);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart chamado");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume chamado");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause chamado");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop chamado");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView chamado");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy chamado");
    }
}
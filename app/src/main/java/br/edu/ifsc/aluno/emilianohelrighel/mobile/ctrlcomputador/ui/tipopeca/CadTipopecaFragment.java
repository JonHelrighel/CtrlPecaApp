package br.edu.ifsc.aluno.emilianohelrighel.mobile.ctrlcomputador.ui.tipopeca;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.edu.ifsc.aluno.emilianohelrighel.mobile.ctrlcomputador.R;
import br.edu.ifsc.aluno.emilianohelrighel.mobile.ctrlcomputador.model.Peca;
import br.edu.ifsc.aluno.emilianohelrighel.mobile.ctrlcomputador.model.TipoPeca;

public class CadTipopecaFragment extends Fragment implements View.OnClickListener {

    private EditText etNmPeca;
    private EditText etDeMarca;
    private EditText etVlPotencia;
    private EditText etDeModelo;
    private EditText etVlCapacidade;
    private EditText etVlPolegadas;
    private EditText etVlDpi;
    private Button btnSalvar;
    private Spinner spIdTipoPeca;
    private RequestQueue requestQueue;

    private View view;

    public CadTipopecaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_cad_tipopeca, container, false);

        //spIdipoPEca
        this.spIdTipoPeca = (Spinner) view.findViewById(R.id.spIdTipoPeca);
        //EditText
        this.etNmPeca = (EditText) view.findViewById(R.id.etNmPeca);
        this.etDeMarca = (EditText) view.findViewById(R.id.etDeMarca);
        this.etVlPotencia = (EditText) view.findViewById(R.id.etVlPotencia);
        this.etDeModelo = (EditText) view.findViewById(R.id.etDeModelo);
        this.etVlCapacidade = (EditText) view.findViewById(R.id.etVlCapacidade);
        this.etVlPolegadas = (EditText) view.findViewById(R.id.etVlPolegadas);
        this.etVlDpi = (EditText) view.findViewById(R.id.etVlDpi);

        //Button - ADICIONE ESTA LINHA
        this.btnSalvar = (Button) view.findViewById(R.id.btnSalvar);
        //definindo o listener do botão
        this.btnSalvar.setOnClickListener(this);

        //instanciando a fila de requests - caso o objeto seja o view
        this.requestQueue = Volley.newRequestQueue(view.getContext());
        //inicializando a fila de requests do SO
        this.requestQueue.start();

        try {
            this.consultaTiposPeca();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        //return default
        return this.view;
    }

private void consultaTiposPeca() throws JSONException {
    //requisição para o Rest Server
    JsonArrayRequest jsonArrayReq = null;
    try {
       jsonArrayReq = new JsonArrayRequest(
            Request.Method.POST,
            "http://10.0.2.2/tp/conTipoPeca.php",  // Ajuste a URL para o seu PHP
            new JSONArray("[{}]"),
            response -> {
                try {
                    //se a consulta não veio vazia
                    if (response != null && response.length() > 0) {
                        //array list para receber a resposta
                        ArrayList<TipoPeca> listaTipos = new ArrayList<>();
                        //preenchendo ArrayList com JSONArray recebido
                        for (int pos = 0; pos < response.length(); pos++) {  // Corrigido: < length()
                            JSONObject jo = response.getJSONObject(pos);
                            TipoPeca tipoPeca = new TipoPeca();
                            tipoPeca.setIdTipoPeca(jo.getInt("idTipoPeca"));
                            tipoPeca.setNmTipoPeca(jo.getString("nmTipoPeca"));
                            listaTipos.add(tipoPeca);  // Corrigido: add sem posição
                        }
                        //Criando um adapter para o spinner
                        ArrayAdapter<TipoPeca> adapter = new ArrayAdapter<>(
                                requireContext(),
                                android.R.layout.simple_spinner_item,
                                listaTipos);
                        //colocando o adapter no spinner
                        this.spIdTipoPeca.setAdapter(adapter);
                    } else {
                        Snackbar mensagem = Snackbar.make(view,
                                "A consulta não retornou nenhum registro!",
                                Snackbar.LENGTH_LONG);
                        mensagem.show();
                    }
                } catch (JSONException e) {
                    Snackbar mensagem = Snackbar.make(view,
                            "Ops!Problema com o arquivo JSON: " + e,
                            Snackbar.LENGTH_LONG);
                    mensagem.show();
                }
            },
            error -> {
                //mostrar mensagem que veio do servidor
                Snackbar mensagem = Snackbar.make(view,
                        "Ops! Houve um problema ao realizar a consulta: " +
                                error.toString(), Snackbar.LENGTH_LONG);
                mensagem.show();
            }
    );
    } catch (JSONException e) {throw new RuntimeException(e);}
    //colocando nova request para fila de execução
    requestQueue.add(jsonArrayReq);
}
    private void cadastrarPeca(Peca peca) throws JSONException {
        //requisição para o Rest Server
        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(
                Request.Method.POST,
                "http://10.0.2.2/tp/cadComputador.php",
                peca.toJsonObject(),
                response -> {
                    try {
                        //se a consulta não veio vazia
                        if (response != null) {
                            Context context = requireContext();
                            if (response.getBoolean("success")) {
                                //limpar campos da tela
                                this.etNmPeca.setText("");
                                this.etDeMarca.setText("");
                                this.etVlPotencia.setText("");
                                this.etDeModelo.setText("");
                                this.etVlCapacidade.setText("");
                                this.etVlPolegadas.setText("");
                                this.etVlDpi.setText("");
                                //primeiro item do spinner
                                this.spIdTipoPeca.setSelection(0);
                            }
                            //mostrando a mensagem que veio do JSON
                            Toast toast = Toast.makeText(
                                    view.getContext(),
                                    response.getString("message"),
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            //mostrar mensagem do response == null
                            Snackbar mensagem = Snackbar.make(
                                    view,
                                    "A consulta não retornou nada!",
                                    Snackbar.LENGTH_LONG);
                            mensagem.show();
                        }
                    } catch (Exception e) {
                        //mostrar mensagem da exception
                        Snackbar mensagem = Snackbar.make(
                                view,
                                "Ops!Problema com o arquivo JSON: " + e,
                                Snackbar.LENGTH_LONG);
                        mensagem.show();
                    }
                },
                error -> {
                    //mostrar mensagem que veio do servidor
                    Snackbar mensagem = Snackbar.make(
                            view,
                            "Ops! Houve um problema: " + error.toString(),
                            Snackbar.LENGTH_LONG);
                    mensagem.show();
                }
        );
        //colocando nova request para fila de execução
        requestQueue.add(jsonObjectReq);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSalvar) {
            try {
                // Instanciando objeto de negócio
                Peca peca = new Peca();

                // Populando objeto com dados da tela
                peca.setNmPeca(this.etNmPeca.getText().toString());
                peca.setDeMarca(this.etDeMarca.getText().toString());
                peca.setDeModelo(this.etDeModelo.getText().toString());
                peca.setVlPotencia(this.etVlPotencia.getText().toString());
                peca.setVlCapacidade(this.etVlCapacidade.getText().toString());
                peca.setVlPolegadas(this.etVlPolegadas.getText().toString());
                peca.setVlDpi(this.etVlDpi.getText().toString());

                // Objeto do item selecionado do Spinner
                int posTipo = spIdTipoPeca.getSelectedItemPosition();
                TipoPeca tipoSelecionado = (TipoPeca) spIdTipoPeca.getItemAtPosition(posTipo);
                peca.setIdTipoPeca(tipoSelecionado.getIdTipoPeca());

                // Mensagem de sucesso
                Context context = view.getContext();
                CharSequence text = "Configuração salva com sucesso!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            } catch (Exception e) {
                // Tratamento de erro
                Snackbar mensagem = Snackbar.make(
                        this.view,
                        "Erro ao salvar: " + e.getMessage(),
                        Snackbar.LENGTH_LONG
                );
                mensagem.show();
            }
        }
    }

}

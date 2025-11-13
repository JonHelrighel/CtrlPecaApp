package br.edu.ifsc.aluno.emilianohelrighel.mobile.ctrlcomputador.ui.tipopeca;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.edu.ifsc.aluno.emilianohelrighel.mobile.ctrlcomputador.R;
import br.edu.ifsc.aluno.emilianohelrighel.mobile.ctrlcomputador.model.TipoPeca;

public class CadTipopecaFragment extends Fragment {

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

        //spinner
        this.spIdTipoPeca = (Spinner) view.findViewById(R.id.spIdTipoPeca);
        //instanciando a fila de requests - caso o objeto seja o view
        this.requestQueue = Volley.newRequestQueue(view.getContext());
        //inicializando a fila de requests do SO
        this.requestQueue.start();

        //return default
        return this.view;
    }


private void consultaTiposPeca() throws JSONException {
    //requisição para o Rest Server
    JsonArrayRequest jsonArrayReq = new JsonArrayRequest(
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
                        adapter.setDropDownViewResource(
                                android.R.layout.simple_spinner_dropdown_item);
                        //colocando o adapter no spinner
                        spIdTipoPeca.setAdapter(adapter);
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
    //colocando nova request para fila de execução
    requestQueue.add(jsonArrayReq);
}
// Classe auxiliar para os tipos de peça (adaptada de Perfil)

}

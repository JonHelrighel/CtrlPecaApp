package br.edu.ifsc.aluno.emilianohelrighel.mobile.ctrlcomputador.ui.tipopeca;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import br.edu.ifsc.aluno.emilianohelrighel.mobile.ctrlcomputador.databinding.FragmentContipopecaBinding;
import br.edu.ifsc.aluno.emilianohelrighel.mobile.ctrlcomputador.model.Peca;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Peca}.
 * TODO: Replace the implementation with code for your data type.
 */
public class tipopecaRecyclerViewAdapter extends RecyclerView.Adapter<tipopecaRecyclerViewAdapter.ViewHolder> {

    private final List<Peca> mValues;

    public tipopecaRecyclerViewAdapter(List<Peca> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentContipopecaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getNmPeca());
        String info = mValues.get(position).getDeMarca() + " | " + mValues.get(position).getDeModelo();
        holder.mContentView.setText(info);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Peca mItem;

        public ViewHolder(FragmentContipopecaBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
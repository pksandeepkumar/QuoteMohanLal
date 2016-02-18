package texus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.texus.mohanlalquotes.R;

import java.util.ArrayList;

import texus.datamodel.QuoteText;

/**
 * Created by sandeep on 17/2/16.
 */
public class QuoteTextAdapter extends RecyclerView.Adapter<QuoteTextAdapter.ViewHolder> {

    private ArrayList<QuoteText> mDataset;
    private Context mContext;

    public QuoteTextAdapter(Context context, ArrayList<QuoteText> datasets) {
        mDataset = datasets;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RippleView   rplAuthor;
        public ImageView    imAvatar;
        public TextView     tvAUthorName;
        public ImageButton  imShare;
        public TextView     tvQuote;

        public ViewHolder(View v) {
            super(v);
            rplAuthor       = (RippleView)  v.findViewById(R.id.rplAuthor);
            imAvatar        = (ImageView)   v.findViewById(R.id.imAvatar);
            tvAUthorName    = (TextView)    v.findViewById(R.id.tvAUthorName);
            imShare         = (ImageButton) v.findViewById(R.id.imShare);
            tvQuote         = (TextView)    v.findViewById(R.id.tvQuote);
        }
    }

//    public void add(int position, String item) {
//        mDataset.add(position, item);
//        notifyItemInserted(position);
//    }

    public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public QuoteTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element_quote_text, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final QuoteText quoteText = mDataset.get(position);
        holder.tvAUthorName.setText( "" + quoteText.author );
        holder.tvQuote.setText( "" + quoteText.Quote );
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}

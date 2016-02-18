package texus.task;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.texus.mohanlalquotes.AppConstance;

import java.util.ArrayList;

import texus.adapter.QuoteTextAdapter;
import texus.datamodel.QuoteIndex;
import texus.datamodel.QuoteText;
import texus.db.Databases;
import texus.network.NetworkService;
import texus.preferances.SavedPreferance;
import texus.utility.Utility;

public class LoadQuoteTextTask extends  AsyncTask<Void, Void, String>
					implements AppConstance {
    Context context;
    RecyclerView recyclerView;
    QuoteTextAdapter adapter;
    int version = 0;

    public LoadQuoteTextTask(Context context, RecyclerView recyclerView) {
    	this.context = context;
        this.recyclerView = recyclerView;
        version = SavedPreferance.getVersionQuote(context);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(Void... params) {
		try {
            String responseData = "";
            String responseIndex = "";
            if(version == 0) {
                responseData = Utility.readFromAssets("quote1.xml",context);
                version = 1;
                parseAndInsertdata(responseData);
            } else {
				responseIndex = NetworkService.Get(INDEX_URL_QUOTE);
				ArrayList<QuoteIndex> indexes = QuoteIndex.parse(responseData);
				for(QuoteIndex index: indexes) {
					if(index.version <= version) continue;
					responseData = NetworkService.Get(BASE_URL + index.filename);
                    parseAndInsertdata(responseData);
				}
                
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
		//Populate list
		Databases db = new Databases(context);
		ArrayList<QuoteText> quoteTexts = QuoteText.getAllObject(db);
        recyclerView.setHasFixedSize(false);
        // specify an adapter (see also next example)
        adapter = new QuoteTextAdapter(context, quoteTexts);
        recyclerView.setAdapter(adapter);
		db.close();
	}

	private void parseAndInsertdata(String responseData) {
		Databases db = new Databases(context);
		ArrayList<QuoteText>  quoteTexts = QuoteText.parse(responseData);
		for( QuoteText quoteText: quoteTexts ) {
			QuoteText.inseartOrUpdateOperation(db, quoteText);
		}
		db.close();
		//Version sett by Mac
        SavedPreferance.setVersionQuote(context, version);
        publishProgress();

	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
	}

}

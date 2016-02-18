package texus.task;

import android.content.Context;
import android.os.AsyncTask;

import com.texus.mohanlalquotes.AppConstance;

public class SyncImages extends  AsyncTask<Void, Void, String>
					implements AppConstance {
	
    Context context;
    String message;
    
    public SyncImages(Context context) {
    	this.context = context;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(Void... params) {
		
		try {
			
//		SyncIssueHdrStatus task = new SyncIssueHdrStatus(context);
//		task.doTask0();
		
//		message = task.response;
            
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
//		BaseActivity.shareMessageTask(context, message);
	}

}

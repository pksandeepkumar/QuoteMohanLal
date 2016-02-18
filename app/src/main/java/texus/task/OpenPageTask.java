package texus.task;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.texus.mohanlalquotes.AppConstance;

public class OpenPageTask extends  AsyncTask<Void, Void, String>
					implements AppConstance {

    Context context;
    Class cls;

    public OpenPageTask(Context context, Class cls) {
    	this.context = context;
        this.cls = cls;

	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(Void... params) {
		
		try {
            Thread.sleep(TIME_TO_WAIT_IN_MS);

		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
	}

}

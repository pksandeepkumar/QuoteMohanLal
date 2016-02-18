package texus.network;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Sandeep Kumar
 * 
 */
public class NetworkService {

	public static String Get(String URLStr) throws Exception {

		Log.e("NetworkService", "Get Link:" + URLStr);
		String resultStr = "";
		BufferedReader in = null;
		try {

			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(URLStr);
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			resultStr = sb.toString();
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
		Log.e("NetworkService", "Response" + resultStr);
		return resultStr;
	}


}
package texus.datamodel;

/**
 * Created by sandeep on 17/2/16.
 */

import org.json.JSONObject;

import java.util.Iterator;

/**
 * image_url will be hold image url
 * if null this link has no image
 */
public class WikiImageResponse {
    public String image_url = null;
    public int width = 0;
    public int height = 0;

    public  WikiImageResponse getParse( String resp) {
        WikiImageResponse response = null;
        try {
            JSONObject object = new JSONObject(resp);
            response = new WikiImageResponse();
            JSONObject jsnPages = object.getJSONObject("pages");
            Iterator keys = jsnPages.keys();
            while(keys.hasNext()) {
                // loop to get the dynamic key
                String currentDynamicKey = (String)keys.next();
                // get the value of the dynamic key
                JSONObject jsnPageID = jsnPages.getJSONObject(currentDynamicKey);
                if(jsnPageID != null) {
                    JSONObject jsnThumb = null;
                    try {
                        jsnThumb = jsnPageID.getJSONObject("thumbnail");
                    } catch ( Exception e) { e.printStackTrace();}
                    if(jsnThumb != null) {
                        response.image_url = jsnThumb.getString("source");
                        response.width = jsnThumb.getInt("width");
                        response.height = jsnThumb.getInt("height");
                    }
                }
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}

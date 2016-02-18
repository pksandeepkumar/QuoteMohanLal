package texus.preferances;



import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.texus.mohanlalquotes.AppConstance;


public class SavedPreferance implements AppConstance{

	public static final int IMAGE_VERSION_DEFAULT = 0;
	private static final String IS_QUOTE_LOADED = "IS_QUOTE_LOADED";
	private static final String VERSION_QUOTE = "version_quote";
    private static final String VERSION_QUOTE_IMAGE = "version_quote_image";
    private static final String VERSION_FILM = "version_film";


    public static int getVersionFilm(Context context) {
        return getPreferance(context).getInt(VERSION_FILM, 0);
    }

    public static  void setVersionFilm(Context context, int version) {
        Editor edit = getPreferance(context).edit();
        edit.putInt(VERSION_FILM, version);
        edit.commit();
    }


    public static int getVersionQuoteImage(Context context) {
        return getPreferance(context).getInt(VERSION_QUOTE_IMAGE, 0);
    }

    public static  void setVersionQuoteImage(Context context, int version) {
        Editor edit = getPreferance(context).edit();
        edit.putInt(VERSION_QUOTE_IMAGE, version);
        edit.commit();
    }

    public static int getVersionQuote(Context context) {
        return getPreferance(context).getInt(VERSION_QUOTE, 0);
    }

    public static  void setVersionQuote(Context context, int version) {
        Editor edit = getPreferance(context).edit();
        edit.putInt(VERSION_QUOTE, version);
        edit.commit();
    }


	public static boolean getIsQuoteLoaded(Context context) {
		return getPreferance(context).getBoolean(IS_QUOTE_LOADED, false);
	}

	public static  void setIsQuoteLoaded(Context context,boolean status) {
		Editor edit = getPreferance(context).edit();
		edit.putBoolean(IS_QUOTE_LOADED, status);
		edit.commit();
	}

	private static SharedPreferences getPreferance(Context context) {
		SharedPreferences preferences = android.preference.PreferenceManager
				.getDefaultSharedPreferences(context);
		return preferences;
	}
	
	
}

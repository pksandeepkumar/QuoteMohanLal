/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package texus.datamodel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import texus.db.Databases;
import texus.utility.LOG;
import texus.utility.Utility;
import texus.xml.XMLElement;
import texus.xml.XMLTree;

/**
 * @author Sandeep Kumar P K <br>
 *         <a
 *         href="mailto:pksandeepkumar@gmail.com">pksandeepkumar@gmail.com</a>
 */
public class FilmData extends BaseDataModel{

	public static final String TABLE_NAME = "TableImage";
	
	
	public static final String ID = "id";
	public static final String FILM_NAME = "FilmName";
	public static final String DIRECTOR = "Director";
	public static final String WIKI_LINK = "WikiLink";
    public static final String YEAR = "YearOfRelease";
    public static final String ROLE = "Role";

	/** PK */
	public int id;
    public int year;
	public String film;
	public String director;
	public String role;
    public String wikiLink;


	public static final String CREATE_TABE_QUERY = "CREATE TABLE  " + TABLE_NAME 
			+ " ( " + "_id" + " INTEGER  PRIMARY KEY AUTOINCREMENT, " 
			+ ID + " INTEGER , "
            + YEAR + " INTEGER, "
			+ FILM_NAME + " TEXT, "
			+ DIRECTOR + " TEXT, "
            + ROLE + " TEXT, "
			+ WIKI_LINK + " TEXT);";

// <film  id="1"
// Year="1980"
// Film="Manjil Virinja Pookkal"
// Director="Fazil"
// Role="Narendran"
// WikiLink="https://en.wikipedia.org/wiki/Manjil_Virinja_Pookkal" />

	public static ArrayList<FilmData> parse(String xmlString) {
		ArrayList<FilmData> images = new ArrayList<FilmData>();
		if(xmlString ==  null) return images;
		XMLTree tree = new XMLTree();
		tree.Load(xmlString, false);
		XMLElement rootElement = tree.RootElement;
		if(rootElement == null) return images;
		for( XMLElement element : rootElement.Children) {
			if(element == null) continue;
			FilmData instance = new FilmData();
			instance.id = Utility.parseInt(element.GetAttribute("id"));
            instance.year = Utility.parseInt(element.GetAttribute("Year"));
			instance.film = element.GetAttribute("Film");
			instance.director = element.GetAttribute("Director");
			instance.role = element.GetAttribute("Role");
            instance.wikiLink = element.GetAttribute("WikiLink");
			images.add(instance);
		}
		return images;
	}



	
	public static FilmData getAnObjectFromCursor(Cursor c) {
		FilmData instance = null;
		if( c != null) {
			instance = new FilmData();
			instance.id = c.getInt(c.getColumnIndex(ID));
            instance.id = c.getInt(c.getColumnIndex(YEAR));
			instance.film = c.getString(c.getColumnIndex(FILM_NAME));
			instance.director = c.getString(c.getColumnIndex(DIRECTOR));
			instance.role = c.getString(c.getColumnIndex(ROLE));
            instance.wikiLink = c.getString(c.getColumnIndex(WIKI_LINK));
		} else {
			LOG.log("getAnObjectFromCursor:", "getAnObjectFromCursor Cursor is null");
		}
		return instance;
	}
	
	public static FilmData getAnObject(Databases db, FilmData object) {
		FilmData instance = null;
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "select * from " + TABLE_NAME + " WHERE " + ID + " = " + object.id + "";
		LOG.log("Query:", "Query:" + query);
		Cursor c = dbRead.rawQuery(query, null);
		if (c.moveToFirst()) {
			instance = getAnObjectFromCursor(c);
		}
		c.close();
		dbRead.close();
		return instance;
	}

	public static FilmData getAnObject(Databases db, int id) {
		FilmData instance = null;
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "select * from " + TABLE_NAME + " WHERE " + ID + " = " + id + "";
		LOG.log("Query:", "Query:" + query);
		Cursor c = dbRead.rawQuery(query, null);
		if (c.moveToFirst()) {
			instance = getAnObjectFromCursor(c);
		}
		c.close();
		dbRead.close();
		return instance;
	}


	public static ArrayList<FilmData> getAllObject(Databases db) {
		ArrayList<FilmData> collections = new ArrayList<FilmData>();
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "select * from " + TABLE_NAME ;
		LOG.log("Query:", "Query:" + query);
		Cursor c = dbRead.rawQuery(query, null);
		if(c.moveToFirst()){
			do {
				FilmData instance = getAnObjectFromCursor(c);
				collections.add(instance);
			} while (c.moveToNext());
		}

		c.close();
		dbRead.close();
		return collections;
	}

//    + ID + " INTEGER , "
//            + YEAR + " INTEGER, "
//            + FILM_NAME + " TEXT, "
//            + DIRECTOR + " TEXT, "
//            + ROLE + " TEXT, "
//            + WIKI_LINK

    public static boolean inseartOperation(Databases db, FilmData instance) {
		SQLiteDatabase sql = db.getWritableDatabase();
		String query = "";
		query = "insert into " + TABLE_NAME + " (" 
				+ ID + ","
				+ YEAR + ","
				+ FILM_NAME + ","
                + DIRECTOR + ","
                + ROLE + ","
				+ WIKI_LINK + " ) values ( "
				+ "" + instance.id + ","
				+ "" + instance.year + ","
				+ "'" + instance.film + "',"
                + "'" + instance.director + "',"
                + "'" + instance.role + "',"
				+ "'" + instance.wikiLink + "');";
		LOG.log("Query:", "Query:" + query);
		sql.execSQL(query);
		return true;
	}

	public static boolean updateOperation(Databases db, FilmData instance) {

		SQLiteDatabase sql = db.getWritableDatabase();
		String query = "";
		query = "update " + TABLE_NAME + " SET " 
				+ ID + " = " + instance.id + " , "
                + YEAR + " = " + instance.year + " , "
				+ FILM_NAME + " = '" + instance.film + "',"
				+ DIRECTOR + " = '" + instance.director + "',"
                + ROLE + " = '" + instance.role + "',"
				+ WIKI_LINK + " = '" + instance.role + "' WHERE " + ID + " = " + instance.id + "";
		LOG.log("Query:", "Query:" + query);
		sql.execSQL(query);
		sql.close();
		return true;
	}

	public static int getNextID(Databases db) {
		try {
			final String MY_QUERY = "SELECT MAX(_id) FROM " + TABLE_NAME;
			SQLiteDatabase dbRead = db.getReadableDatabase();
			Cursor cur = dbRead.rawQuery(MY_QUERY, null);
			cur.moveToFirst();
			int ID = cur.getInt(0) + 1;
			cur.close();
			return ID;
		} catch( Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static boolean deleteTable(Databases db) {
		try {
			SQLiteDatabase sql = db.getWritableDatabase();
			String query = "DELETE from " + TABLE_NAME;
			LOG.log("Query:", "Query:" + query);
			sql.execSQL(query);
			sql.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void inseartOrUpdateOperation(Databases db, FilmData object) {
		if(object == null) return;
		FilmData fetchedObject = getAnObject(db, object);
		if (fetchedObject == null) {
			inseartOperation(db, object);
		} else {
			updateOperation(db, object);
		}
	}
}

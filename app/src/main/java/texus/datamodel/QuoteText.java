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
public class QuoteText extends BaseDataModel{

	public static final String TABLE_NAME = "TableQuoteText";
	
	
	public static final String ID = "id";
	public static final String IMAGE_URL = "Quote";
	public static final String AUTHOR = "Author";
	public static final String WIKILINK = "Wikilink";

	/** PK */
	public int id;
	public String Quote;
	public String author;
	public String WikiLink;


	public static final String CREATE_TABE_QUERY = "CREATE TABLE  " + TABLE_NAME 
			+ " ( " + "_id" + " INTEGER  PRIMARY KEY AUTOINCREMENT, " 
			+ ID + " INTEGER , "
			+ IMAGE_URL + " TEXT, "
			+ AUTHOR + " TEXT, "
			+ WIKILINK + " TEXT);";

//	<Quote id="1" Quote="The finest actor in the country"
// Author="Amitabh Bachchan"
// WikiLink="http://en.wikipedia.org/wiki/Amitabh_Bachchan"/>

	public static ArrayList<QuoteText> parse(String xmlString) {
		ArrayList<QuoteText> images = new ArrayList<QuoteText>();
		if(xmlString ==  null) return images;
		XMLTree tree = new XMLTree();
		tree.Load(xmlString, false);
		XMLElement rootElement = tree.RootElement;
		if(rootElement == null) return images;
		for( XMLElement element : rootElement.Children) {
			if(element == null) continue;
			QuoteText image = new QuoteText();
			image.id = Utility.parseInt(element.GetAttribute("id"));
			image.Quote = element.GetAttribute("Quote");
			image.author = element.GetAttribute("Author");
			image.WikiLink = element.GetAttribute("WikiLink");
			images.add(image);
		}

		return images;
	}



	
	public static QuoteText getAnObjectFromCursor(Cursor c) {
		QuoteText instance = null;
		if( c != null) {
			instance = new QuoteText();
			instance.id = c.getInt(c.getColumnIndex(ID));
			instance.Quote = c.getString(c.getColumnIndex(IMAGE_URL));
			instance.author = c.getString(c.getColumnIndex(AUTHOR));
			instance.WikiLink = c.getString(c.getColumnIndex(WIKILINK));
		} else {
			LOG.log("getAnObjectFromCursor:", "getAnObjectFromCursor Cursor is null");
		}
		return instance;
	}
	
	public static QuoteText getAnObject(Databases db, QuoteText object) {
		QuoteText instance = null;
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

	public static QuoteText getAnObject(Databases db, int id) {
		QuoteText instance = null;
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


	public static ArrayList<QuoteText> getAllObject(Databases db) {
		ArrayList<QuoteText> collections = new ArrayList<QuoteText>();
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "select * from " + TABLE_NAME ;
		LOG.log("Query:", "Query:" + query);
		Cursor c = dbRead.rawQuery(query, null);
		if(c.moveToFirst()){
			do {
				QuoteText instance = getAnObjectFromCursor(c);
				collections.add(instance);
			} while (c.moveToNext());
		}
		
		c.close();
		dbRead.close();
		return collections;
	}




	public static boolean inseartOperation(Databases db, QuoteText instance) {
		SQLiteDatabase sql = db.getWritableDatabase();
		String query = "";
		query = "insert into " + TABLE_NAME + " ("
				+ ID + ","
				+ IMAGE_URL + ","
				+ AUTHOR + ","
				+ WIKILINK + " ) values ( "
				+ "" + instance.id + ","
				+ "'" + instance.Quote.replaceAll("'","\'") + "',"
				+ "'" + instance.author + "',"
				+ "'" + instance.WikiLink + "');";

		LOG.log("Query:", "Query:" + query);
        try {
            sql.execSQL(query);
        } catch (Exception e) { e.printStackTrace();}
		return true;
	}

	public static boolean updateOperation(Databases db, QuoteText instance) {

		SQLiteDatabase sql = db.getWritableDatabase();

		String query = "";
		query = "update " + TABLE_NAME + " SET " 
				+ ID + " = " + instance.id + " , "
				+ IMAGE_URL + " = '" + instance.Quote + "',"
				+ AUTHOR + " = '" + instance.author + "',"
				+ WIKILINK + " = '" + instance.WikiLink + "' WHERE " + ID + " = " + instance.id + "";
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

	public static void inseartOrUpdateOperation(Databases db, QuoteText object) {
		if(object == null) return;
		QuoteText fetchedObject = getAnObject(db, object);
		if (fetchedObject == null) {
			inseartOperation(db, object);
		} else {
			updateOperation(db, object);
		}
	}
}

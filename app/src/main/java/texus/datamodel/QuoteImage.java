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
public class QuoteImage extends BaseDataModel{

	public static final String TABLE_NAME = "TableImage";
	
	
	public static final String ID = "id";
	public static final String IMAGE_URL = "ImageURL";
	public static final String AUTHOR = "Author";
	public static final String TAGS = "Tags";

	/** PK */
	public int id;
	public String imageUrl;
	public String author;
	public String tags;


	public static final String CREATE_TABE_QUERY = "CREATE TABLE  " + TABLE_NAME 
			+ " ( " + "_id" + " INTEGER  PRIMARY KEY AUTOINCREMENT, " 
			+ ID + " INTEGER , "
			+ IMAGE_URL + " TEXT, "
			+ AUTHOR + " TEXT, "
			+ TAGS + " TEXT);";

//    <Image id="1"
//    url="http://4.bp.blogspot.com/-OBlx8_bk9tU/Vpx6Xj_dYVI/AAAAAAAACXg/UeKDnC930zk/s1600/Image1451743274498.jpg"
//    auth="Sandra Thomas"
//    role=""/>

	public static ArrayList<QuoteImage> parse(String xmlString) {
		ArrayList<QuoteImage> images = new ArrayList<QuoteImage>();
		if(xmlString ==  null) return images;
		XMLTree tree = new XMLTree();
		tree.Load(xmlString, false);
		XMLElement rootElement = tree.RootElement;
		if(rootElement == null) return images;
		for( XMLElement element : rootElement.Children) {
			if(element == null) continue;
			QuoteImage image = new QuoteImage();
			image.id = Utility.parseInt(element.GetAttribute("id"));
			image.imageUrl = element.GetAttribute("url");
			image.author = element.GetAttribute("auth");
			image.tags = element.GetAttribute("role");
			images.add(image);
		}

		return images;
	}



	
	public static QuoteImage getAnObjectFromCursor(Cursor c) {
		QuoteImage instance = null;
		if( c != null) {
			instance = new QuoteImage();
			instance.id = c.getInt(c.getColumnIndex(ID));
			instance.imageUrl = c.getString(c.getColumnIndex(IMAGE_URL));
			instance.author = c.getString(c.getColumnIndex(AUTHOR));
			instance.tags = c.getString(c.getColumnIndex(TAGS));
		} else {
			LOG.log("getAnObjectFromCursor:", "getAnObjectFromCursor Cursor is null");
		}
		return instance;
	}
	
	public static QuoteImage getAnObject(Databases db, QuoteImage object) {
		QuoteImage instance = null;
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

	public static QuoteImage getAnObject(Databases db, int id) {
		QuoteImage instance = null;
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


	public static ArrayList<QuoteImage> getAllObject(Databases db) {
		ArrayList<QuoteImage> collections = new ArrayList<QuoteImage>();
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "select * from " + TABLE_NAME ;
		LOG.log("Query:", "Query:" + query);
		Cursor c = dbRead.rawQuery(query, null);
		if(c.moveToFirst()){
			do {
				QuoteImage instance = getAnObjectFromCursor(c);
				collections.add(instance);
			} while (c.moveToNext());
		}
		
		c.close();
		dbRead.close();
		return collections;
	}

	public static boolean inseartOperation(Databases db, QuoteImage instance) {
		SQLiteDatabase sql = db.getWritableDatabase();
		String query = "";
		query = "insert into " + TABLE_NAME + " (" 
				+ ID + ","
				+ IMAGE_URL + ","
				+ AUTHOR + ","
				+ TAGS + " ) values ( "
				+ "" + instance.id + ","
				+ "'" + instance.imageUrl + "',"
				+ "'" + instance.author + "',"
				+ "'" + instance.tags + "');";
		LOG.log("Query:", "Query:" + query);
		sql.execSQL(query);
		return true;
	}

	public static boolean updateOperation(Databases db, QuoteImage instance) {

		SQLiteDatabase sql = db.getWritableDatabase();
		String query = "";
		query = "update " + TABLE_NAME + " SET " 
				+ ID + " = " + instance.id + " , "
				+ IMAGE_URL + " = '" + instance.imageUrl + "',"
				+ AUTHOR + " = '" + instance.author + "',"
				+ TAGS + " = '" + instance.tags + "' WHERE " + ID + " = " + instance.id + "";
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

	public static void inseartOrUpdateOperation(Databases db, QuoteImage object) {
		if(object == null) return;
		QuoteImage fetchedObject = getAnObject(db, object);
		if (fetchedObject == null) {
			inseartOperation(db, object);
		} else {
			updateOperation(db, object);
		}
	}
}

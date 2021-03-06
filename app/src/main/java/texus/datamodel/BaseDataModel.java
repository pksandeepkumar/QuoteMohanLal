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

/**
 * @author Sandeep Kumar P K 
 * <br><a href="mailto:pksandeepkumar@gmail.com">pksandeepkumar@gmail.com</a>
 */
public class BaseDataModel extends JsonParserBase {
	
	/**
	 * Assign value to string if the string is null or null string or nothing
	 * @param string
	 * @param value
	 */
	public static void setValue( String string, String value) {
		if( string == null) {
			string = value;return;
		}
		if( string.length() == 0) {
			string = value;return;
		}
		if( string.equals("null")) {
			string = value;return;
		}
		return;
	}

}

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

import java.util.ArrayList;

import texus.utility.Utility;
import texus.xml.XMLElement;
import texus.xml.XMLTree;

/**
 * @author Sandeep Kumar P K <br>
 *         <a
 *         href="mailto:pksandeepkumar@gmail.com">pksandeepkumar@gmail.com</a>
 */
public class QuoteIndex extends BaseDataModel{

	public int version;
	public String filename;


//	<E vrsn="1" flNme="quote1.xml"/>
	public static ArrayList<QuoteIndex> parse(String xmlString) {
		ArrayList<QuoteIndex> indexes = new ArrayList<QuoteIndex>();
		if(xmlString ==  null) return indexes;
		XMLTree tree = new XMLTree();
		tree.Load(xmlString, false);
		XMLElement rootElement = tree.RootElement;
		if(rootElement == null) return indexes;
		for( XMLElement element : rootElement.Children) {
			if(element == null) continue;
			QuoteIndex index = new QuoteIndex();
			index.version = Utility.parseInt(element.GetAttribute("vrsn"));
			index.filename = element.GetAttribute("flNme");
			indexes.add(index);
		}

		return indexes;
	}


}

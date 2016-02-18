/**
 * 
 */
package texus.xml;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author agn
 *
 */
public class XMLTree 
{

	public XMLElement RootElement = null;
	
	private XMLElement currentElement = null;
	
	/**
	 * 
	 */
	public XMLTree()
	{
		// TODO Auto-generated constructor stub
	}
	
	public void Load(String XmlToLoad, boolean IsLoadFile)
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		if (null == factory)
		{
			return;
		}

		SAXParser saxParser = null;
	    try 
	    {
			saxParser = factory.newSAXParser();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == saxParser)
		{
			return;
		}
		
		
		DefaultHandler handler = new DefaultHandler()
		 {
			 
		     public void startElement(String uri, String localName,
		        String qName, Attributes attributes)
		        throws SAXException {

		    	 //XMLTreeTestActivity.MessageBox(qName);
		    	 XMLElement newElement = new XMLElement(qName);
		    	 //newElement.ElementAttributes = attributes;
		    	 if (null != attributes)
		    	 {
			    	 for (int i = 0; i < attributes.getLength(); i++) 
			    	 {
			    		 String keyName = attributes.getLocalName(i);
		    			 String attrValue = attributes.getValue(i);
			    		 if (null != keyName)
			    		 {
			    			 newElement.SetAttribute(keyName, attrValue);
			    		 }
					 }
		    		 
		    	 }
		    	 
		    	 
		    	 if (null != currentElement)
		    	 {
		    		 newElement.Parent = currentElement;
		    		 currentElement.Add(newElement);
		    	 }
		    	
		    	 currentElement = newElement;
		    	 
		    	 if (null == RootElement)
		    	 {
		    		 RootElement = currentElement;
		    	 }
		    	 
		 
		     }
		 
		     public void endElement(String uri, String localName,
		          String qName)
		          throws SAXException {
		 
		    	 	if (null != currentElement)
		    	 	{
		    	 		currentElement = currentElement.Parent;
		    	 	}
		 
		     }
		 
		     public void characters(char ch[], int start, int length)
		         throws SAXException {
		    	 //String nodeText = new String(ch, start, length);
	 
		        }
		 
		      };
	
		     
		      if (null != handler)
		      {
					try 
					{
						if (IsLoadFile)
						{
							InputStream obj_is =  new FileInputStream(XmlToLoad);
							if (null != obj_is)
							{
								saxParser.parse(obj_is, handler);
							}
						}
						else
						{
							ByteArrayInputStream bs = new ByteArrayInputStream(XmlToLoad.getBytes());
							saxParser.parse(bs, handler);
						}
						
					} catch (Exception e1) 
					{
						return ;
					}
		    	  
		      }
		
		
	}
	
	public void Clear()
	{
		Deleteelement(RootElement);
	}

	
	private static void Deleteelement(XMLElement currentElement)
	{
		if (null == currentElement)
		{
			return;
		}

		if (null != currentElement.Attributes)
		{
			currentElement.Attributes.clear();
		}

		if (null != currentElement.Children)
		{
			currentElement.Children.clear();
		}

		currentElement = null;
	}
	
}

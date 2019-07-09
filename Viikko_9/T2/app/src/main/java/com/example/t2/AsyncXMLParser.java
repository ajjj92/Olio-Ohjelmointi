package com.example.t2;

import android.os.AsyncTask;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


/**
 * 7/7/19
 * AtteJantunen
 */
public class AsyncXMLParser extends AsyncTask<Void, Void, Void> {

    private Document doc;
    private ArrayAdapter adapt;
    public AsyncXMLParser(ArrayAdapter adapt) {
        this.adapt = adapt;

    }

    @Override
    protected void onPreExecute() {
        System.out.println("ASYNC TASK START.");
    }
    @Override
    protected Void doInBackground(Void... params) {
        XmlParser();
        return null;
    }
    @Override
    protected void onPostExecute(Void v) {
        System.out.println("ASYNC TASK EXECUTED.");
        adapt.notifyDataSetChanged();

    }

    public String readDatatoString() throws IOException {
        URL url = new URL("https://www.finnkino.fi/xml/TheatreAreas/");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String line, allines="";
        while ((line = in.readLine()) != null) {
            allines += line+"\n";
        }
        in.close();
        return  allines;
    }
    public void XmlParser() {
        try {

            URL url = new URL("https://www.finnkino.fi/xml/TheatreAreas/");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbuilder = factory.newDocumentBuilder();

            String data = readDatatoString();
            doc = dbuilder.parse(new InputSource(new StringReader(data)));
            doc.getDocumentElement().normalize();
            NodeList xmllist = doc.getElementsByTagName("TheatreArea");

            for(int i=0; i<xmllist.getLength(); i++) {
                Theater teatteri = new Theater();
                NodeList childList = xmllist.item(i).getChildNodes();

                for (int j = 0; j<childList.getLength(); j++) {
                    Node childNode = childList.item(j);

                    if(("Name".equals(childNode.getNodeName())) && childNode.getNodeName()!=null) {
                        teatteri.setName(childList.item(j).getTextContent());
                    }
                    if("ID".equals(childNode.getNodeName()) && childNode.getNodeName()!=null) {
                        teatteri.setID(Integer.parseInt(childList.item(j).getTextContent()));
                    }
                }
                if (!teatteri.getName().equals("Valitse alue/teatteri") && !teatteri.getName().equals("Pääkaupunkiseutu")){
                    TheaterControl.getInstance().addToList(teatteri);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}

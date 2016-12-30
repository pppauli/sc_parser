package Model;

import Datatypes.Attribute;
import Datatypes.Item;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by paul on 29.12.16.
 */
public class Datamanager {
    private String path;
    private ArrayList<String> attributeNames;
    private List<String> lines;
    private ArrayList<Datatypes.Item> weapons;
    private IO.Reader input;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Datamanager(String path) {
        this.path = path;
    }
    public void setAttributeNames()
    {
        String inputPath;
        inputPath = path.concat("/input.csv");
        File inputfile = new File(inputPath);
        if(inputfile.isFile())
        {
            input = new IO.Reader(inputPath);
            try {
                attributeNames = input.getAttributeNames();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("No file found at : " + inputPath);
        }
    }

    public void setLines(){
        try {
            lines = input.getLines();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createItems()
    {
        weapons = new ArrayList<Item>();
        for(int i = 1; i < lines.size(); i++)
        {
            weapons.add(createItemFromLine(lines.get(i)));
        }
    }

    public Datatypes.Item createItemFromLine(String line)
    {
        String seperator = ",";
        String name;
        int j = 0;
        ArrayList<Datatypes.Attribute> attributes = new ArrayList<Datatypes.Attribute>();
        StringBuilder sb = new StringBuilder();
        Datatypes.Attribute attribute;
        for(int i = 0; i < line.length() && j < attributeNames.size()-1; i++)
        {
            if(line.charAt(i) == seperator.charAt(0))
            {
                String temp;
                temp = sb.toString();
                if(temp.isEmpty())
                {
                    //System.out.println(attributeNames.get(j) + " " + j + " " + " : " + "no value");
                    name = attributeNames.get(j).replace("(", "").replace(")", "").replace("/", "");
                    attribute = new Datatypes.Attribute(name, "no value");
                    attributes.add(attribute);
                }
                else
                {
                    //System.out.println(attributeNames.get(j) + " " + j + " " + " : " + temp);
                    name = attributeNames.get(j).replace("(", "").replace(")", "").replace("/", "");
                    attribute = new Datatypes.Attribute(name, sb.toString());
                    attributes.add(attribute);
                }
                sb = new StringBuilder();
                j++;
            }
            else
            {
                sb.append(line.charAt(i));
            }
        }
        return new Datatypes.Item(attributes.get(0).getValue(), attributes);
    }

    public void test()
    {
        for(Datatypes.Item weapon: weapons)
        {
            System.out.println("*************************************************************************************");
            System.out.println("Name: " + weapon.getName());
            for (Datatypes.Attribute attribute: weapon.getAttributes())
            {
                System.out.println(attribute.getName() + ": " + attribute.getValue());
            }
        }
    }

    public void writeWeaponsCsv()
    {
        IO.Writer output = new IO.Writer();
        path = System.getProperty("user.dir").concat("/output.xml");
        try {
            output.writeXml(weapons, path);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}

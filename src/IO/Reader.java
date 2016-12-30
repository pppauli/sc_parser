package IO;

import Datatypes.Attribute;
import com.sun.xml.internal.fastinfoset.util.CharArrayString;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by paul on 29.12.16.
 */
public class Reader {
    private String path;

    public Reader(String path) {
        this.path = path;
    }

    public ArrayList<String> getAttributeNames() throws FileNotFoundException {
        ArrayList<String> attributes = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        //Delete before release
        //System.out.println("read file");
        Scanner scanner = new Scanner(new File(path));
        String lineOne;
        String addToOne = scanner.next();
        sb.append(addToOne);
        while(scanner.hasNext())
        {
            addToOne = scanner.next();
            if(addToOne.contains("Sure"))
            {
                break;
            }
            else
            {
                sb.append(addToOne);
            }
        }
        lineOne = sb.toString();
        lineOne = lineOne.replace("\"", "");
        //Delete before release
        //System.out.println(lineOne);
        StringTokenizer strTok = new StringTokenizer(lineOne, ",");
        while(strTok.hasMoreTokens())
        {
            attributes.add(strTok.nextToken());
        }
        return attributes;
    }

    public ArrayList<String> getLines() throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<String>();
        String temp;
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(new File(path));
        ArrayList<String> pattern = new ArrayList<String>();
        pattern.add("(");
        pattern.add(")");
        pattern.add("/");
        pattern.add("\"");
        while(scanner.hasNext())
        {
            temp = scanner.next();
            if(temp.length() < 150)
            {
                sb.append(temp);
            }
            else
            {
                sb.append(temp);
                lines.add(sb.toString().replace("\"", ""));
                //Teastausgabe
                //System.out.println(sb.toString());
                sb = new StringBuilder();
            }
        }
        System.out.println("****************************************************************************************");
        return lines;
    }
}

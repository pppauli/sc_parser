package Datatypes;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by paul on 29.12.16.
 */
public class Item {
    final String name;
    final ArrayList<Attribute> attributes;

    public Item(String name, ArrayList<Attribute> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }
}

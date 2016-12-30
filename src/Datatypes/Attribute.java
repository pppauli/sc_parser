package Datatypes;

/**
 * Created by paul on 29.12.16.
 */
public class Attribute {
    final String name;
    final String value;

    public String getName() {
        return name;
    }

    public String getValue() {

        return value;
    }

    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }
}

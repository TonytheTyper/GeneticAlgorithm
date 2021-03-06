public class Item {
    private final String name;
    // A label for the item, such as “Jewelry” or “Kindle”

    private final double weight;
    // The weight of the item in pounds
    private final int value;
    // The value of the item rounded to the nearest dollar
    private boolean included;
    // Indicates whether the item should be taken or not

    public Item(String name, double weight, int value) {
        // Initializes the Item’s fields to the values that are passed in; the included
        // field is initialized to false
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.included = false;
    }

    public Item(Item other) {
        // Initializes this item’s fields to the be the same as the other item’s
        this.name = other.name;
        this.weight = other.weight;
        this.value = other.value;
        this.included = other.included;
    }

    public double getWeight() {
        return this.weight;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isIncluded() {
        // Getter for the item’s fields (you don’t need a getter for the name)
        return this.included;
    }

    public void setIncluded(boolean included) {
        // Setter for the item’s included field (you don’t need setters for the other
        // fields)
        this.included = included;
    }

    public String toString() {
        // Displays the item in the form <name> (<weight> lbs, $<value>)
        return name + (", " + weight + " lbs, " + "$" + value);
    }
}
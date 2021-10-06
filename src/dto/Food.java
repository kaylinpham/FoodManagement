package dto;

public class Food {
    private String ID;
    private String name;
    private double weight;
    private String type;
    private String place;
    private String expiredDate;

    public Food(String ID, String name, double weight, String type, String place, String expiredDate) {
        this.ID = ID;
        setName(name);
        setWeight(weight);
        setType(type);
        setPlace(place);
        setExpiredDate(expiredDate);
    }

    public Food(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isBlank()) throw new IllegalArgumentException("Name cannot be blank!");
        this.name = name.trim();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight <= 0) throw new IllegalArgumentException("Weight must be greater than 0!");
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.isBlank()) throw new IllegalArgumentException("You should set type of food!");
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        if (place.isBlank()) throw new IllegalArgumentException("You should specify where this food is!");
        this.place = place;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        if (expiredDate.isBlank()) throw new IllegalArgumentException("You should set expired date!");
        this.expiredDate = expiredDate;
    }

    @Override
    public String toString() {
        return ID + " | " + name + " | " + weight + " | " + type + " | " + place + " | " + expiredDate;
    }

    public void output() {
        System.out.printf("|%-10s|%-20s|%-13.1f|%-20s|%-20s|%10s|\n", ID, name, weight, type, place, expiredDate);
    }
}

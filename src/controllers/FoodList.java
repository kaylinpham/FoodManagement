package controllers;

import dto.Food;
import utilities.Validator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

public class FoodList extends LinkedList<Food> {
    private static final String DIR_PATH = "src/storage";

    public Food addFood() {
        String ID;
        while (true) {
            ID = Validator.getNoBlankString("ID: ");
            if (isDuplicatedID(ID)) System.out.println("This ID already exist!");
            else break;
        }

        String name = Validator.getNoBlankString("Name: ");
        double weight = Validator.getDouble("Weight(kg): ", 0, false);
        String type = Validator.getNoBlankString("Type: ");
        String place = Validator.getNoBlankString("Where it is placed: ");

        String expiredDate;
        while (true) {
            expiredDate = Validator.getDateString("Expired date (dd/MM/yyyy): ");
            if (Validator.isExpiredDate(expiredDate, "dd/MM/yyyy")) {
                System.out.println("This food is already expired!");
            } else break;
        }

        Food newFood = new Food(ID, name, weight, type, place, expiredDate);
        this.add(newFood);
        return newFood;
    }

    public void searchFoodsByName(String name) {
        boolean flag = false;

        for (Food food : this) {
            if (food.getName().toLowerCase().contains(name.toLowerCase())) {
                if (!flag) {
                    flag = true;
                    System.out.println("----------------------------------------------------------------------------------------------------");
                    System.out.println("|    ID    |        TÊN         |CÂN NẶNG (KG)|        LOẠI        |       VỊ TRÍ       |   HSD    |");
                }
                food.output();
            }
        }

        if (!flag) System.out.println("This food does not exist!");
        else
            System.out.println("----------------------------------------------------------------------------------------------------");
    }

    public Food removeFoodByID(String ID) {
        Food result = getFoodByID(ID);
        if (result == null) {
            System.out.println("Not found ID!");
            return null;
        } else {
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("|    ID    |        TÊN         |CÂN NẶNG (KG)|        LOẠI        |       VỊ TRÍ       |   HSD    |");
            result.output();
            System.out.println("----------------------------------------------------------------------------------------------------");
        }

        Menu confirm = new Menu();
        confirm.addOptions("Yes", "No");
        int choice = confirm.getChoice("Do you want to delete this food?");

        if (choice == 1) {
            this.remove(result);
            return result;
        } else return null;
    }

    public void printAllFoods() {
        if (this.size() == 0) System.out.println("There's no foods!");
        else {
            LinkedList<Food> cloned = new LinkedList<>(this);
            Collections.sort(cloned, new Comparator<Food>() {
                @Override
                public int compare(Food o1, Food o2) {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date1 = null, date2 = null;

                    try {
                        date1 = dateFormat.parse(o1.getExpiredDate());
                        date2 = dateFormat.parse(o2.getExpiredDate());
                        return date2.compareTo(date1);
                    } catch (ParseException e) {
                        System.out.println(e.getMessage());
                    }

                    return 0;
                }
            });

            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("|    ID    |        TÊN         |CÂN NẶNG (KG)|        LOẠI        |       VỊ TRÍ       |   HSD    |");
            for (Food food : cloned) food.output();
            System.out.println("----------------------------------------------------------------------------------------------------");
        }
    }

    public void saveToFile(String filename) {
        String filePath = DIR_PATH + "/" + filename + ".txt";

        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Food food : this) {
                bw.write(food.toString());
                bw.newLine();
            }
            System.out.println("Save to file successfully.");
            System.out.println("FILE PATH: " + filePath);

            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Save to file failed! " + e.getMessage());
        }
    }

    private Food getFoodByID(String ID) {
        for (Food food : this)
            if (food.getID().equalsIgnoreCase(ID)) return food;
        return null;
    }

    private boolean isDuplicatedID(String ID) {
        for (Food food : this) {
            if (food.getID().equalsIgnoreCase(ID)) return true;
        }
        return false;
    }
}

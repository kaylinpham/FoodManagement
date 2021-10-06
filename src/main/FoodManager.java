package main;

import controllers.FoodList;
import controllers.Menu;
import dto.Food;
import utilities.Validator;

public class FoodManager {
    public static void main(String[] args) {
        FoodList foods = new FoodList();
        boolean flag = true;

        Menu menu = new Menu();
        menu.addOptions(
                "Add a new food",
                "Search a food by name",
                "Remove the food by ID",
                "Print the food list",
                "Save food list to file",
                "Quit"
        );

        while (flag) {
            Menu subMenu = new Menu();
            subMenu.addOptions("Yes", "No");
            int choice = menu.getChoice("Welcome to Food Management - @ 2021 by <SE150249 - Phạm Hà Giang>");

            switch (choice) {
                case 1:
                    while (true) {
                        foods.addFood();
                        int subChoice = subMenu.getChoice("Do you want to continue adding another food?");
                        if (subChoice == 2) break;
                    }
                    break;
                case 2:
                    while (true) {
                        String foodName = Validator.getNoBlankString("Enter food's name to search: ");
                        foods.searchFoodsByName(foodName);
                        int subChoice = subMenu.getChoice("Do you want to continue searching another foods?");
                        if (subChoice == 2) break;
                    }
                    break;
                case 3:
                    while (true) {
                        String ID = Validator.getNoBlankString("Enter food's ID to remove: ");
                        Food removed = foods.removeFoodByID(ID);

                        if (removed == null) {
                            System.out.println("Remove failed!");
                            int subChoice = subMenu.getChoice("Do you want to continue removing another food?");
                            if (subChoice == 2) break;
                        } else {
                            System.out.println("Remove successfully.");
                            break;
                        }
                    }
                    break;
                case 4:
                    foods.printAllFoods();
                    break;
                case 5:
                    if (foods.size() == 0) {
                        System.out.println("Save FAILED. Empty list.");
                    } else {
                        String fileName = Validator.getNoBlankString("Enter file name to save: ");
                        foods.saveToFile(fileName);
                    }
                    break;
                case 6:
                    System.out.println("Goodbye <3");
                    flag = false;
            }
        }
    }
}

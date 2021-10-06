package controllers;

import utilities.Validator;

import java.util.ArrayList;

public class Menu<E> extends ArrayList {
    public int getChoice(String message) {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println(message);

        if (this.size() == 0) {
            System.out.println("No choices");
            return -1;
        } else System.out.println("Select the options below:");

        for (int i = 0; i < this.size(); i++)
            System.out.println((i + 1) + " - " + this.get(i));
        return Validator.getInteger("Choose: ", 1, this.size());
    }

    public void addOptions(String... options) {
        for (String option : options) this.add(option);
    }
}

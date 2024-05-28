package main;

import controller.MenuController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new MenuController(scanner);
        scanner.close();
    }
}
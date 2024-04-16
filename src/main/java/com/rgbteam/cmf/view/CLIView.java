/*
 * Copyright (c) 2024. RGBTeam
 */

 
package com.rgbteam.cmf.view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.rgbteam.cmf.GeneralFlowController;
import com.rgbteam.cmf.chemistry.Element;

import javafx.util.Pair;

public class CLIView {
    public static void main(String[] args) {
        GeneralFlowController controller = new GeneralFlowController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello User!");

        label: while (true) {
            System.out.println("""

                    Enter:
                    1 -- get element info by its number (87, 43, etc.)
                    2 -- get element info by its short name ('H', 'As', etc.)
                    3 -- get element info by its full name ('Sodium', 'Neon', etc.)
                    4 -- count mass of a compound (M[Na Cl] = 58.44 g/mol, M[Fe S O 4] = 151.901 g/mol, etc.)
                    5 -- count oxidation state
                    0 -- exit the program""");

            String choice = scanner.nextLine();

            switch (choice) {
                case "0":
                    System.out.println("Exiting...");
                    break label;
                case "1": {
                    System.out.print("Enter element's number: ");
                    String inputNumber = scanner.nextLine();
                    try {
                        int elementNumber = Integer.parseInt(inputNumber);
                        Element element = controller.retrieveElementByNumber(elementNumber);
                        if (element != null)
                            System.out.println(element);
                        else
                            System.out.println("Element not found.");

                    } catch (NumberFormatException e) {
                        System.err.println("Invalid input.");
                    }
                    break;
                }
                case "2": {
                    System.out.print("Enter short designation: ");
                    String inputDesignation = scanner.nextLine();
                    Element element = controller.retrieveElementByShortName(inputDesignation);
                    if (element != null)
                        System.out.println(element);
                    else
                        System.err.println("Element not found.");
                    break;
                }
                case "3": {
                    System.out.print("Enter full name: ");
                    String inputFullName = scanner.nextLine();
                    Element element = controller.retrieveElementByFullName(inputFullName);
                    if (element != null)
                        System.out.println(element);
                    else
                        System.err.println("Element not found.");

                    break;
                }
                case "4":
                    System.out.print(
                            "Enter compound (Separate elements, numbers and parenthesis using '-' or '_' or ' '): ");
                    String unparsed = scanner.nextLine();
                    try {
                        double mass = controller.calculateCompoundsAtomicMass(unparsed);
                        System.out.println("The mass of the [" + unparsed + "] is: " + String.format("%.4f", mass));
                    } catch (Exception e) {
                        System.err.println("Invalid input or element not found: " + e.getCause());
                    }
                    break;
                    case "5":
                        System.out.println(
                            "Enter compound (Separate elements, numbers and parenthesis using '-' or '_' or ' '): ");
                        String unparsed1 = scanner.nextLine();
                        try {
                            Map<String, Integer> map = controller.determineCompoundsOxidationState(unparsed1);
                            StringBuilder stringBuilder = new StringBuilder();
                            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                                stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                            }
                            System.out.println(stringBuilder.toString());
                        } catch (Exception e) {
                            System.err.println("Invalid input or element not found: " + e.getCause());
                        }
                        break;
                        
                default:
                    System.err.println("No such command available");
                    break;
            }
        }
        scanner.close();
    }
}

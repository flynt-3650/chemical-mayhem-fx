/*
 * Copyright (c) 2024. RGBTeam
 */


package com.rgbteam.cmf.chemistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compound {
    private final Element[] parsedCompound;
 
        // Constructor for the Compound class that takes a string representation of a compound
        public Compound(String rawCompound) {
            // Initialize an empty list to store the elements of the compound
            List<Element> elements = new ArrayList<>();
    
            // Define a regular expression pattern to match the elements and their counts in the compound
            Pattern pattern = Pattern.compile("([A-Z][a-z]*)(\\d*)|(\\()|(\\))|(\\d*)");
            // Create a matcher object to match the pattern in the compound string
            Matcher matcher = pattern.matcher(rawCompound);
    
            // Initialize a list to store the sub-elements in a group (for compounds with parentheses)
            List<Element> subElements = new ArrayList<>();
            // Initialize a counter to keep track of the number of times a group should be repeated
            int count = 1;
    
            // Loop through the compound string and match the pattern
            while (matcher.find()) {
                // Extract the element symbol and count from the match
                String elementSymbol = matcher.group(1);
                String elementCountStr = matcher.group(2);
                // Extract the group (if any) from the match
                String group = matcher.group();
                String groupCountStr = matcher.group(5);
    
                // If the match is an element symbol
                if (elementSymbol != null) {
                    // Get the element from the periodic table
                    Element element = PeriodicTable.getElementByShortName(elementSymbol);
                    // Parse the element count (default to 1 if not provided)
                    int elementCount = (elementCountStr.isEmpty()) ? 1 : Integer.parseInt(elementCountStr);
    
                    // Add the element to the sub-elements list
                    for (int i = 0; i < count * elementCount; i++) {
                        subElements.add(element);
                    }
    
                    // Reset the group count
                    count = 1;
                // If the match is an opening parenthesis
                } else if (group.equals("(")) {
                    // If there are sub-elements, add them to the elements list and clear the sub-elements list
                    if (!subElements.isEmpty()) {
                        elements.addAll(subElements);
                        subElements.clear();
                    }
                // If the match is a closing parenthesis
                } else if (group.equals(")")) {
                    // Parse the group count (default to 1 if not provided)
                    int subCount = 1;
                    if (groupCountStr != null) {
                        subCount = Integer.parseInt(groupCountStr);
                    }
                    // Add the sub-elements to the elements list
                    for (int i = 0; i < subCount; i++) {
                        elements.addAll(subElements);
                    }
                    // Clear the sub-elements list
                    subElements.clear();
                }
            }
    
            // Add any remaining sub-elements to the elements list
            elements.addAll(subElements);
            // Convert the elements list to an array
            parsedCompound = elements.toArray(new Element[0]);
    
            // Validate the compound
            try {
                validateCompound();
            } catch (InvalidCompoundException e) {
                // If the compound is invalid, throw a runtime exception
                throw new RuntimeException("Invalid compound: " + rawCompound, e);
            }
    
            // Print the elements in the compound
            for (Element item : parsedCompound) {
                System.out.println(item);
            }
        }
    
    
    
    private void validateCompound() throws InvalidCompoundException {
        int sumOxidationStates = 0;
    
        // Loop through all elements in the compound
        for (Element token : parsedCompound) {
            Element element = token;
    
            if (element != null) {
                // Get all possible oxidation states for the current element
                int[] oxidationStates = element.getOxidationStates();
    
                // If the element has only one oxidation state, simply add it to the sum
                if (oxidationStates.length == 1) {
                    sumOxidationStates += oxidationStates[0];
                } else {
                    // If the element has multiple oxidation states, try all possible options
                    boolean validOxidationStateFound = false;
    
                    // Loop through all possible oxidation states
                    for (int oxidationState : oxidationStates) {
                        // Add the current oxidation state to the sum
                        sumOxidationStates += oxidationState;
    
                        // If after adding all elements the sum of oxidation states is zero,
                        // then the compound is valid
                        if (sumOxidationStates == 0) {
                            validOxidationStateFound = true;
                            break;
                        }
    
                        // If after adding the current element the sum of oxidation states is not zero,
                        // cancel the addition of the current oxidation state and try the next one
                        sumOxidationStates -= oxidationState;
                    }
    
                    // If we couldn't find a valid oxidation state for the current element,
                    // throw an exception
                    if (!validOxidationStateFound) {
                        throw new InvalidCompoundException("Invalid compound");
                    }
                }
            }
        }
    }
    
    


    // private static boolean isInteger(String token) {
    //     try {
    //         Integer.parseInt(token);
    //         return true;
    //     } catch (NumberFormatException e) {
    //         return false;
    //     }
    // }
    // private static boolean isElement(String token) {
    //     return PeriodicTable.getElementByShortName(token) != null;
    // }

    // public double



    public double calculateAtomicMass() {
        double totalMass = 0.0;
        for (Element element : parsedCompound) {
            totalMass += element.getAtomicMass();
        }
        return totalMass;
    }


    @Override
    public String toString() {
        return Arrays.toString(parsedCompound);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(parsedCompound);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Compound other = (Compound) obj;
        return Arrays.equals(parsedCompound, other.parsedCompound);
    }
}

 
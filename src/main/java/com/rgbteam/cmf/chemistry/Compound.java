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
 
    public Compound(String rawCompound) {
        List<Element> elements = new ArrayList<>();
        Pattern pattern = Pattern.compile("([A-Z][a-z]*)(\\d*)|(\\()|(\\))|(\\d+)");
        Matcher matcher = pattern.matcher(rawCompound);
    
        List<Element> subElements = new ArrayList<>();
        int count = 1;
    
        while (matcher.find()) {
            String elementSymbol = matcher.group(1);
            String elementCountStr = matcher.group(2);
            String group = matcher.group();
            String groupCountStr = matcher.group(5);
    
            if (elementSymbol != null) {
                Element element = PeriodicTable.getElementByShortName(elementSymbol);
                int elementCount = (elementCountStr.isEmpty()) ? 1 : Integer.parseInt(elementCountStr);
    
                for (int i = 0; i < count * elementCount; i++) {
                    subElements.add(element);
                }
    
                count = 1;
            } else if (group.equals("(")) {
                if (!subElements.isEmpty()) {
                    elements.addAll(subElements);
                    subElements.clear();
                }
            } else if (groupCountStr != null) {
                int subCount = 1;
                if (groupCountStr != null) {
                    subCount = Integer.parseInt(groupCountStr);
                }
                for (int i = 0; i < subCount; i++) {
                    elements.addAll(subElements);
                }
                subElements.clear();
            }
        }
    
        elements.addAll(subElements);
        parsedCompound = elements.toArray(new Element[0]);

        try {
            validateCompound();
        } catch (InvalidCompoundException e) {
            throw new RuntimeException("Invalid compound: " + rawCompound, e);
        }

        for (Element item : parsedCompound) {
            System.out.println(item.getShortName());
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

 
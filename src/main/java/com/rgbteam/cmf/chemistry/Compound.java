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
        Pattern pattern = Pattern.compile("([A-Z][a-z]*)(\\d*)|(\\()|(\\))|(\\d*)");
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
            } else if (group.equals(")")) {
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
        
        for (Element element : parsedCompound) {
            System.out.println(element);
        }
    }
    
    
    
    
    


    // private void validateCompound() throws InvalidCompoundException {
    //     int sumOxidationStates = 0;
 
    //     for (String token : parsedCompound) {
    //         Element element = PeriodicTable.getElementByShortName(token);
 
    //         if (element != null) {
    //             int[] oxidationStates = element.getOxidationStates();
    //             if (oxidationStates.length == 1) {
    //                 sumOxidationStates += oxidationStates[0];
    //             } else {
    //                 boolean validOxidationStateFound = false;
 
    //                 for (int oxidationState : oxidationStates) {
    //                     System.out.println("OXID STATE: " + oxidationState);
    //                     if (sumOxidationStates + oxidationState == 0) {
    //                         validOxidationStateFound = true;
    //                         sumOxidationStates += oxidationState;
    //                         break;
    //                     }
    //                 }
    //                 if (!validOxidationStateFound) {
    //                     throw new InvalidCompoundException("Invalid compound");
    //                 }
    //             }
    //         }
    //     }
    // }


    private static boolean isInteger(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private static boolean isElement(String token) {
        return PeriodicTable.getElementByShortName(token) != null;
    }

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

 
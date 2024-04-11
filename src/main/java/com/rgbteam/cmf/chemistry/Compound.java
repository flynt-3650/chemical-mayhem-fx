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

            } else if (group.equals("(")) {
                if (!subElements.isEmpty()) {
                    elements.addAll(subElements);
                    subElements.clear();
                }
            } else if (groupCountStr != null) {
                int subCount = 1;
                subCount = Integer.parseInt(groupCountStr);
                for (int i = 0; i < subCount; i++) {
                    elements.addAll(subElements);
                }
                subElements.clear();
            }
        }
    
        elements.addAll(subElements);
        parsedCompound = elements.toArray(new Element[0]);

        if (!isValidCompound()) {
            StringBuilder compoundSymbols = new StringBuilder();

            for (var e : parsedCompound) {
                compoundSymbols.append(e.getShortName());
            }
            System.err.println("INVALID COMPOUND: " + compoundSymbols);

            throw new InvalidCompoundException();
        }
    }
    
    private boolean isValidCompound() {
        List<Integer> allOxidStates = new ArrayList<>();

        for (Element element : parsedCompound) { // in this 'for' loop I add all oxid states that elements may possess
            int[] currentElementOxidStates = element.getOxidationStates();
            for (Integer i : currentElementOxidStates) {
                allOxidStates.add(i);
            }
        }

        for (int i = 0; i < allOxidStates.size(); i++) { // if any of sums = 0 we count such compound as valid
            for (int j = i + 1; j < allOxidStates.size(); j++) {
                int sum = allOxidStates.get(i) + allOxidStates.get(j);
                if (sum == 0) {
                    return true;
                }
            }
        }

        return false;
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

    @Override
    public String toString() {
        return "Compound [parsedCompound=" + Arrays.toString(parsedCompound) + "]";
    }
}

 
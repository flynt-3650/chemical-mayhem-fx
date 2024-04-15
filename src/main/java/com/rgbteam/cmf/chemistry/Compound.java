/*
 * Copyright (c) 2024. RGBTeam
 */


package com.rgbteam.cmf.chemistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    // checks if compound can exist by verifying that sum of elements' oxid. states can be equal 0
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

    public double calculateAtomicMass() {
        double totalMass = 0.0;

        for (Element element : parsedCompound) {
            totalMass += element.getAtomicMass();
        }
        return totalMass;
    }

    public Map<String, Integer> determineTheOxidationState() {
        Map<String, Integer> finalOxidationStates = new HashMap<>();
        Map<String, List<Integer>> oxidationStates = new HashMap<>();
        List<Map<String, Integer>> allOxidationStates = new ArrayList<>();
        List<Integer> currentElementOxidStates;

        for (Element element : parsedCompound) {
            currentElementOxidStates = new ArrayList<>();
            for (int item : element.getOxidationStates()) {
                currentElementOxidStates.add(item);
            }
            oxidationStates.put(element.getShortName(), currentElementOxidStates);
        }

        // recursive method for finding all possible combinations of valences
        findOxidationStates(oxidationStates, new ArrayList<>(oxidationStates.keySet()), 0, allOxidationStates);

        // save only those valences that add up to 0
        for (Map<String, Integer> state : allOxidationStates) {
            int sum = 0;
            for (Integer value : state.values()) {
                sum += value;
            }
            if (sum == 0) {
                finalOxidationStates = state;
                break;
            }
        }

        return finalOxidationStates;
    }

    private void findOxidationStates(Map<String, List<Integer>> oxidationStates, List<String> elements, int sum, List<Map<String, Integer>> allOxidationStates) {
        if (elements.isEmpty()) {
            if (sum == 0) {
                Map<String, Integer> newState = new HashMap<>();
                for (String element : oxidationStates.keySet()) {
                    newState.put(element, oxidationStates.get(element).get(0));
                }
                allOxidationStates.add(newState);
            }
            return;
        }

        String element = elements.get(0);
        List<Integer> oxidationStateValues = oxidationStates.get(element);
        for (int i = 0; i < oxidationStateValues.size(); i++) {
            int oxidationStateValue = oxidationStateValues.get(i);
            oxidationStates.get(element).set(0, oxidationStateValue);
            List<String> newElements = new ArrayList<>(elements);
            newElements.remove(0);
            findOxidationStates(oxidationStates, newElements, sum + oxidationStateValue, allOxidationStates);
        }

        // reset valences for the next recursive call
        oxidationStates.get(element).set(0, 0);
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

 
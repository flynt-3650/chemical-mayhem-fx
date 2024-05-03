/*
 * Copyright (c) 2024. RGBTeam
 */


package com.rgbteam.cmf.chemistry;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.util.Pair;

public class Compound {

    private final Element[] compound;

    public Compound(String rawCompound) {
        List<Element> elements = parseCompound(rawCompound);
        compound = elements.toArray(new Element[0]);

        if (!isValidCompound()) {
            StringBuilder compoundSymbols = new StringBuilder();

            for (var e : compound) {
                compoundSymbols.append(e.getShortName());
            }

            throw new InvalidCompoundException("INVALID COMPOUND: " + compoundSymbols);
        }
    }

    private List<Element> parseCompound(String rawCompound) {
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
        return elements;
    }


    // checks if compound can exist by verifying that sum of elements' oxid. states can be equal 0
    private boolean isValidCompound() {
        List<Integer> allOxidStates = new ArrayList<>();

        for (Element element : compound) { // in this 'for' loop I add all oxid states that elements may possess
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

        for (Element element : compound) {
            totalMass += element.getAtomicMass();
        }
        return totalMass;
    }

//    //here we form a Cartesian set of lists
//    public List<List<Integer>> cartesianProduct(List<List<Integer>> lists) {
//        List<List<Integer>> result = new ArrayList<>();
//        if (lists.size() == 0) {
//            result.add(new ArrayList<>());
//            return result;
//        }
//        List<Integer> firstList = lists.get(0); //bring first list
//        List<List<Integer>> remainingLists = cartesianProduct(lists.subList(1, lists.size())); //call recursively for the remaining lists
//        for (int i : firstList) {
//            for (List<Integer> list : remainingLists) {
//                ArrayList<Integer> newList = new ArrayList<>();
//                //form Cartesian set
//                newList.add(i);
//                newList.addAll(list);
//                result.add(newList);
//            }
//        }
//        return result;
//    }

    public Map<Element, int[]> getElementsOxidStates() {

        Map<Element, int[]> compoundOxidStates = new HashMap<>();
        for (Element el : compound) {
            compoundOxidStates.put(el, el.getOxidationStates());
        }
        return compoundOxidStates;
    }


    public Pair<String, String> compoundClassDeterminant() {
        List<String> groupsOfElements = new ArrayList<>();
        for (Element el : compound) {

           groupsOfElements.add(el.getGroupElement());
        }
        
        // Проверяем, содержит ли список группы Nonmetals и Carbon
        boolean containsCarbon = groupsOfElements.contains("Nonmetals") && groupsOfElements.contains("Carbon");
        boolean containsMetal = groupsOfElements.stream().anyMatch(group -> group.equals("Alkali metals") 
        || group.equals("Alkaline earth metals") || group.equals("Transition metals") 
        || group.equals("Post-transition metals") || group.equals("Lanthanoids") || group.equals("Actinoids"));
        boolean containsOxygen = groupsOfElements.contains("Nonmetals") && groupsOfElements.contains("Oxygen");
        boolean containsHalogen = groupsOfElements.contains("Halogen");
    
        if (containsCarbon && containsOxygen && containsHalogen) {
            return new Pair<>("Carboxylic acids", "Organic");
        } else if (containsCarbon && containsOxygen) {
            return new Pair<>("Alcohols", "Organic");
        } else if (containsCarbon && containsHalogen) {
            return new Pair<>("Halogenoalkanes", "Organic");
        } else if (containsCarbon) {
            return new Pair<>("Hydrocarbons", "Organic");
        } else if (containsMetal && containsOxygen) {
            return new Pair<>("Oxides", "Inorganic");
        } else if (containsMetal && containsHalogen) {
            return new Pair<>("Salts", "Inorganic");
        } else if (containsMetal) {
            return new Pair<>("Bases", "Inorganic");
        } else if (containsOxygen && containsHalogen) {
            return new Pair<>("Acids", "Inorganic");
        } else if (containsOxygen) {
            return new Pair<>("Oxides", "Inorganic");
        } else if (containsHalogen) {
            return new Pair<>("Halogens", "Inorganic");
        } else {
            return new Pair<>("Unknown", "Unknown");
        }
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(compound);
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
        return Arrays.equals(compound, other.compound);
    }

    @Override
    public String toString() {
        return "Compound [parsedCompound=" + Arrays.toString(compound) + "]";
    }
}

 
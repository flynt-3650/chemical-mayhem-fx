/*
 * Copyright (c) 2024. RGBTeam
 */


package com.rgbteam.cmf.chemistry;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Compound {
    private final Element[] parsedCompound;
    
    public Compound(String rawCompound) {
        Stack<Element> elementStack = new Stack<>();
        Stack<Element> elementsBrackets = new Stack<>();
        Stack<Element> compoundHelper = new Stack<>();
        Stack<Element> resultCompound = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(rawCompound, "()", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (isElement(token)) {
                Element element = PeriodicTable.getElementByShortName(token);
                elementStack.push(element);
            } else if (token.equals("(")) {
                while (tokenizer.hasMoreTokens() && !token.equals(")")) {
                    Element elementBrackets = PeriodicTable.getElementByShortName(token);
                    elementsBrackets.push(elementBrackets);
                }
            } else if(token.equals(")")) {
                
                if (tokenizer.hasMoreTokens() && isInteger(tokenizer.nextToken())) {
                    int multiplier = Integer.parseInt(tokenizer.nextToken());
                    for (int i = 0; i < multiplier; i++) {
                        for (Element element : elementsBrackets) {
                            compoundHelper.push(element);
                        }
                    }
                    for (int i = 0; i < multiplier; i++) {
                        for (Element element : compoundHelper) {
                            elementStack.push(element);
                        }
                    }
                }

            }
        }
        while (!elementStack.isEmpty()){
            resultCompound.push(elementStack.pop());
        }
        parsedCompound = resultCompound.toArray(new Element[0]);

    }
   
    


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

//    private void validateCompound() throws InvalidCompoundException {
//        int sumOxidationStates = 0;
//
//        for (String token : parsedCompound) {
//            Element element = PeriodicTable.getElementByShortName(token);
//
//            if (element != null) {
//                int[] oxidationStates = element.getOxidationStates();
//                if (oxidationStates.length == 1) {
//                    sumOxidationStates += oxidationStates[0];
//                } else {
//                    boolean validOxidationStateFound = false;
//
//                    for (int oxidationState : oxidationStates) {
//                        System.out.println("OXID STATE: " + oxidationState);
//                        if (sumOxidationStates + oxidationState == 0) {
//                            validOxidationStateFound = true;
//                            sumOxidationStates += oxidationState;
//                            break;
//                        }
//                    }
//                    if (!validOxidationStateFound) {
//                        throw new InvalidCompoundException("Invalid compound");
//                    }
//                }
//            }
//        }
//    }

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

 
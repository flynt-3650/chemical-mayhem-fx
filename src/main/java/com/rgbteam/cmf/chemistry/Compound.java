/*
 * Copyright (c) 2024. RGBTeam
 */

 
 package com.rgbteam.cmf.chemistry;

 import java.util.Arrays;
 import java.util.Stack;
 import java.util.StringTokenizer;
 
 public class Compound {
     private final String[] parsedCompound;
 
     public Compound(String rawCompound) throws InvalidCompoundException {
         StringTokenizer tokenizer = new StringTokenizer(rawCompound, "-_ ");
 
         int tokenCount = tokenizer.countTokens();
         this.parsedCompound = new String[tokenCount];
 
         for (int i = 0; i < tokenCount; i++) {
             this.parsedCompound[i] = tokenizer.nextToken();
         }
         validateCompound();
     }
 
     private void validateCompound() throws InvalidCompoundException {
        int sumOxidationStates = 0;
        for (String token : parsedCompound) {
            Element element = PeriodicTable.getElementByShortName(token);
            if (element != null) {
                int[] oxidationStates = element.getOxidationStates();
                if (oxidationStates.length == 1) {
                    sumOxidationStates += oxidationStates[0];
                } else {
                    boolean validOxidationStateFound = false;
                    for (int oxidationState : oxidationStates) {
                        if (sumOxidationStates + oxidationState == 0) {
                            validOxidationStateFound = true;
                            sumOxidationStates += oxidationState;
                            break;
                        }
                    }
                    if (!validOxidationStateFound) {
                        throw new InvalidCompoundException("Invalid compound");
                    }
                }
            }
        }
    }
    
 
     
 
     public double calculateAtomicMass() {
         // Create a stack to keep track of atomic masses and counts
         Stack<Double> stack = new Stack<>();
 
         // Iterate through each token in the parsedCompound array
         for (String token : parsedCompound) {
             // Check if the token is an element
 
             Element element = PeriodicTable.getElementByShortName(token);
 
             if (element != null) {
                 // Retrieve the atomic mass of the element from the PeriodicTable
                 stack.push(element.getAtomicMass());
             }
             // Check for an opening parenthesis "("
             else if (token.equals("(")) {
                 // Push a placeholder 0.0 onto the stack to mark the start of a subexpression
                 stack.push(0.0);
             }
             // Check for a closing parenthesis ")"
             else if (token.equals(")")) {
                 double sum = 0.0;
                 // Calculate the sum of atomic masses within the parentheses
                 while (!stack.isEmpty() && stack.peek() != 0.0) {
                     sum += stack.pop();
                 }
                 // Pop the open parenthesis (0.0) from the stack
                 if (!stack.isEmpty() && stack.peek() == 0.0) {
                     stack.pop();
                 }
                 int count = 1;
                 // Check if there's a numeric coefficient preceding the closing parenthesis
                 if (!stack.isEmpty() && isInteger(String.valueOf(stack.peek()))) {
                     count = stack.pop().intValue();
                 }
                 // Push the result of (sum * count) onto the stack
                 stack.push(sum * count);
             }
             // Check if the token is a numeric coefficient
             else if (isInteger(token)) {
                 int count = Integer.parseInt(token);
                 // Multiply the top element on the stack (atomic mass) by the coefficient
                 if (!stack.isEmpty() && stack.peek() != 0.0) {
                     double mass = stack.pop();
                     stack.push(mass * count);
                 }
             } else {
                 return 0.0;
             }
         }
 
         double totalMass = 0.0;
         // Sum up the remaining values in the stack to obtain the total atomic mass
         while (!stack.isEmpty()) {
             totalMass += stack.pop();
         }
 
         // Format the total mass as a string with three decimal places
         return totalMass;
     }
 
     // public double 
 
     private static boolean isElement(String token) {
         return PeriodicTable.getElementByShortName(token) != null;
     }
 
     private static boolean isInteger(String token) {
         try {
             Integer.parseInt(token);
             return true;
         } catch (NumberFormatException e) {
             return false;
         }
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
 
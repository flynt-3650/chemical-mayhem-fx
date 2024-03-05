/*
 * Copyright (c) 2024. RGBTeam
 */


package com.rgbteam.cmf.chemistry;

public class Element {
    private final String fullName;
    private final String shortName;
    private final int atomicNumber;
    private final String groupElement;
    private final double atomicMass;
    private final double molarMass;
    private final int protonAmount;
    private final int neutronAmount;
    private final int electronAmount;

    public Element(String shortName, String fullName, int number, String groupElement, double atomicMass) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.atomicNumber = number;
        this.protonAmount = number;
        this.electronAmount = number;
        this.groupElement = groupElement;
        this.atomicMass = atomicMass;
        this.molarMass = atomicMass / 10;
        this.neutronAmount = (int) atomicMass - number;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public int getNumber() {
        return atomicNumber;
    }

    public String getGroupElement() {
        return groupElement;
    }

    public double getAtomicMass() {
        return atomicMass;
    }

    public double getMolarMass() {
        return molarMass;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public int getProtonAmount() {
        return protonAmount;
    }

    public int getNeutronAmount() {
        return neutronAmount;
    }

    public int getElectronAmount() {
        return electronAmount;
    }

    @Override
    public String toString() {
        return "Element is " + fullName + " (" + shortName + ")\n" +
                "Atomic Number: " + atomicNumber + "\n" +
                "Element group: " + groupElement + "\n" +
                "Atomic Mass: " + String.format("%.4f", atomicMass) + " amu\n" +
                "Molar Mass: " + String.format("%.4f", molarMass) + " g/mol\n" +
                "Protons: " + protonAmount + "\n" +
                "Neutrons: " + neutronAmount + "\n" +
                "Electrons: " + electronAmount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
        result = prime * result + atomicNumber;
        result = prime * result + ((groupElement == null) ? 0 : groupElement.hashCode());
        long temp;
        temp = Double.doubleToLongBits(atomicMass);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(molarMass);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + protonAmount;
        result = prime * result + neutronAmount;
        result = prime * result + electronAmount;
        return result;
    }

    @Override
    public boolean equals(Object obj) { // it does the job, believe me
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Element other = (Element) obj;

        if (fullName == null && other.fullName != null) {
            return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        if (shortName == null && other.shortName != null) {
            return false;
        } else if (!shortName.equals(other.shortName))
            return false;
        if (atomicNumber != other.atomicNumber)
            return false;
        if (groupElement != null && other.groupElement != null) {
            return false;
        } else if (!groupElement.equals(other.groupElement)) {
            return false;
        }
        if (Double.doubleToLongBits(atomicMass) != Double.doubleToLongBits(other.atomicMass))
            return false;
        if (Double.doubleToLongBits(molarMass) != Double.doubleToLongBits(other.molarMass))
            return false;
        if (protonAmount != other.protonAmount)
            return false;
        if (neutronAmount != other.neutronAmount)
            return false;
        return electronAmount == other.electronAmount;
    }
}
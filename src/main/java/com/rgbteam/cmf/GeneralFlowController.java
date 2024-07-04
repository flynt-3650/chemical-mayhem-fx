/*
 * Copyright (c) 2024. RGBTeam
 */


package com.rgbteam.cmf;

import com.rgbteam.cmf.chemistry.Compound;
import com.rgbteam.cmf.chemistry.Element;
import com.rgbteam.cmf.chemistry.ElementDoesNotExistException;
import com.rgbteam.cmf.chemistry.InvalidCompoundException;
import com.rgbteam.cmf.chemistry.PeriodicTable;

import java.util.Map;

public class GeneralFlowController {
    public Element retrieveElementByNumber(int number) throws ElementDoesNotExistException {
        return PeriodicTable.getElementByNumber(number);
    }


    public Element retrieveElementByShortName(String shortName) throws ElementDoesNotExistException {
        return PeriodicTable.getElementByShortName(shortName);
    }


    public Element retrieveElementByFullName(String fullName) throws ElementDoesNotExistException {
        return PeriodicTable.getElementByFullName(fullName);
    }


    public Double calculateCompoundsAtomicMass(String rawCompound) throws InvalidCompoundException {
        Compound compound = new Compound(rawCompound);
        return compound.calculateAtomicMass();
    }


    public Map<Element, int[]> findCompoundsOxidationStates(String rawCompound) throws InvalidCompoundException {
        Compound compound = new Compound(rawCompound);
        return compound.getElementsOxidStates();
    }


    public String retrieveClassOfCompound(String rawCompound) throws InvalidCompoundException {
        Compound compound = new Compound(rawCompound);
        return compound.determineCompoundClass();
    }


    public String retrieveElementGroup(int number) throws ElementDoesNotExistException {
        return PeriodicTable.getElementGroup(number);
    }
}

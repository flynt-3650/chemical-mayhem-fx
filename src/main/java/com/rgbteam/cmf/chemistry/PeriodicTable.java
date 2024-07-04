/*
 * Copyright (c) 2024. RGBTeam
 */


package com.rgbteam.cmf.chemistry;

public class PeriodicTable {
    private static final Element[] TABLE = {
            new Element("H", "Hydrogen", 1, "nonmetal", 1.008, new int[]{1}, new int[]{+1, -1}),
            new Element("He", "Helium", 2, "noble gases", 4.0026, new int[]{0}, new int[]{0}),
            new Element("Li", "Lithium", 3, "alkali metals", 6.94, new int[]{1}, new int[]{+1}),
            new Element("Be", "Beryllium", 4, "alkaline earth metals", 9.0122, new int[]{2}, new int[]{+1, +2}),
            new Element("B", "Boron", 5, "metalloids", 10.81, new int[]{3}, new int[]{-3, -1, +1, +2, +3}),
            new Element("C", "Carbon", 6, "nonmetal", 12.011, new int[]{4}, new int[]{-4, 0, +2, +4}),
            new Element("N", "Nitrogen", 7, "nonmetal", 14.007, new int[]{3}, new int[]{-3, -2, -1, +1, +2, +3, +4, +5}),
            new Element("O", "Oxygen", 8, "nonmetal", 15.999, new int[]{2}, new int[]{-2, -1, 0, +1, +2}),
            new Element("F", "Fluorine", 9, "halogen", 18.998, new int[]{1}, new int[]{-1}),
            new Element("Ne", "Neon", 10, "noble gases", 20.180, new int[]{0}, new int[]{0}),
            new Element("Na", "Sodium", 11, "alkali metals", 22.990, new int[]{1}, new int[]{+1}),
            new Element("Mg", "Magnesium", 12, "alkaline earth metals", 24.305, new int[]{2}, new int[]{+2}),
            new Element("Al", "Aluminum", 13, "post-transition metals", 26.982, new int[]{3}, new int[]{+3}),
            new Element("Si", "Silicon", 14, "metalloids", 28.085, new int[]{4}, new int[]{+4}),
            new Element("P", "Phosphorus", 15, "nonmetal", 30.974, new int[]{3}, new int[]{3, 0, +3, +5}),
            new Element("S", "Sulfur", 16, "nonmetal", 32.06, new int[]{2}, new int[]{-2, 0, +4, +6}),
            new Element("Cl", "Chlorine", 17, "halogen", 35.45, new int[]{1}, new int[]{-1, 0, +1, +3, +5, +7, +2, +4}),
            new Element("Ar", "Argon", 18, "noble gases", 39.948, new int[]{0}, new int[]{0}),
            new Element("K", "Potassium", 19, "alkali metals", 39.098, new int[]{1}, new int[]{+1}),
            new Element("Ca", "Calcium", 20, "alkaline earth metals", 40.078, new int[]{2}, new int[]{+2}),
            new Element("Sc", "Scandium", 21, "transition metals", 44.956, new int[]{3}, new int[]{+3}),
            new Element("Ti", "Titanium", 22, "transition metals", 47.867, new int[]{4}, new int[]{+2, +3, +4}),
            new Element("V", "Vanadium", 23, "transition metals", 50.942, new int[]{5}, new int[]{+2, +3, +4, +5}),
            new Element("Cr", "Chromium", 24, "transition metals", 51.996, new int[]{6}, new int[]{+2, +3, +6}),
            new Element("Mn", "Manganese", 25, "transition metals", 54.938, new int[]{7}, new int[]{+2, +3, +4, +6, +7}),
            new Element("Fe", "Iron", 26, "transition metals", 55.845, new int[]{8}, new int[]{+2, +3, +4, +6}),
            new Element("Co", "Cobalt", 27, "transition metals", 58.933, new int[]{9}, new int[]{+2, +3, +4}),
            new Element("Ni", "Nickel", 28, "transition metals", 58.693, new int[]{10}, new int[]{+2, +1, +3, +4}),
            new Element("Cu", "Copper", 29, "transition metals", 63.546, new int[]{1}, new int[]{+1, +2, +3}),
            new Element("Zn", "Zinc", 30, "transition metals", 65.38, new int[]{2}, new int[]{+2}),
            new Element("Ga", "Gallium", 31, "post-transition metals", 69.723, new int[]{3}, new int[]{+3, +2}),
            new Element("Ge", "Germanium", 32, "metalloids", 72.630, new int[]{4}, new int[]{-4, +2, +4}),
            new Element("As", "Arsenic", 33, "metalloids", 74.922, new int[]{3}, new int[]{-3, +3, +5, +2}),
            new Element("Se", "Selenium", 34, "nonmetal", 78.971, new int[]{2}, new int[]{-2, +4, +6, +2}),
            new Element("Br", "Bromine", 35, "halogen", 79.904, new int[]{1}, new int[]{-1, +1, +5, +3, +4}),
            new Element("Kr", "Krypton", 36, "noble gases", 83.798, new int[]{0}, new int[]{0}),
            new Element("Rb", "Rubidium", 37, "alkali metals", 85.468, new int[]{1}, new int[]{+1}),
            new Element("Sr", "Strontium", 38, "alkaline earth metals", 87.62, new int[]{2}, new int[]{+2}),
            new Element("Y", "Yttrium", 39, "transition metals", 88.906, new int[]{3}, new int[]{+3}),
            new Element("Zr", "Zirconium", 40, "transition metals", 91.224, new int[]{4}, new int[]{+4, +2, +3}),
            new Element("Nb", "Niobium", 41, "transition metals", 92.906, new int[]{5}, new int[]{+3, +5, +2, +4}),
            new Element("Mo", "Molybdenum", 42, "transition metals", 95.95, new int[]{6}, new int[]{+3, +6, +2, +3, +5}),
            new Element("Tc", "Technetium", 43, "transition metals", 98.0, new int[]{7}, new int[]{+6}),
            new Element("Ru", "Ruthenium", 44, "transition metals", 101.07, new int[]{8}, new int[]{+3, +4, +8, +2, +6, +7}),
            new Element("Rh", "Rhodium", 45, "transition metals", 102.91, new int[]{9}, new int[]{+4, +2, +3, +6}),
            new Element("Pd", "Palladium", 46, "transition metals", 106.42, new int[]{10}, new int[]{+2, +4, +6}),
            new Element("Ag", "Silver", 47, "transition metals", 107.87, new int[]{1}, new int[]{+1, +2, +3}),
            new Element("Cd", "Cadmium", 48, "transition metals", 112.41, new int[]{2}, new int[]{+2, +1}),
            new Element("In", "Indium", 49, "post-transition metals", 114.82, new int[]{3}, new int[]{+3, +1, +2}),
            new Element("Sn", "Tin", 50, "post-transition metals", 118.71, new int[]{4}, new int[]{+2, +4}),
            new Element("Sb", "Antimony", 51, "metalloids", 121.76, new int[]{3}, new int[]{-3, +3, +5, +4}),
            new Element("Te", "Tellurium", 52, "nonmetal", 127.60, new int[]{2}, new int[]{-2, +4, +6}),
            new Element("I", "Iodine", 53, "halogen", 126.904, new int[]{1}, new int[]{-1, +1, +5, +7, +3, +4}),
            new Element("Xe", "Xenon", 54, "noble gases", 131.294, new int[]{0}, new int[]{0}),
            new Element("Cs", "Cesium", 55, "alkali metals", 132.91, new int[]{1}, new int[]{+1}),
            new Element("Ba", "Barium", 56, "alkaline earth metals", 137.33, new int[]{2}, new int[]{+2}),
            new Element("La", "Lanthanum", 57, "lanthanoids", 138.91, new int[]{3}, new int[]{+3}),
            new Element("Ce", "Cerium", 58, "lanthanoids", 140.12, new int[]{4}, new int[]{+4, +3}),
            new Element("Pr", "Praseodymium", 59, "lanthanoids", 140.91, new int[]{5}, new int[]{+3}),
            new Element("Nd", "Neodymium", 60, "lanthanoids", 144.24, new int[]{6}, new int[]{+3, +4}),
            new Element("Pm", "Promethium", 61, "lanthanoids", 145.0, new int[]{7}, new int[]{+3}),
            new Element("Sm", "Samarium", 62, "lanthanoids", 150.36, new int[]{8}, new int[]{+3, +2}),
            new Element("Eu", "Europium", 63, "lanthanoids", 151.96, new int[]{9}, new int[]{+3, +2}),
            new Element("Gd", "Gadolinium", 64, "lanthanoids", 157.25, new int[]{10}, new int[]{+3}),
            new Element("Tb", "Terbium", 65, "lanthanoids", 158.93, new int[]{11}, new int[]{+3, +4}),
            new Element("Dy", "Dysprosium", 66, "lanthanoids", 162.50, new int[]{12}, new int[]{+3}),
            new Element("Ho", "Holmium", 67, "lanthanoids", 164.93, new int[]{13}, new int[]{+3}),
            new Element("Er", "Erbium", 68, "lanthanoids", 167.26, new int[]{14}, new int[]{+3}),
            new Element("Tm", "Thulium", 69, "lanthanoids", 168.93, new int[]{15}, new int[]{+3, +2}),
            new Element("Ib", "Ytterbium", 70, "lanthanoids", 173.05, new int[]{16}, new int[]{+3, +2}),
            new Element("Lu", "Lutetium", 71, "lanthanoids", 174.97, new int[]{17}, new int[]{+3}),
            new Element("Hf", "Hafnium", 72, "transition metals", 178.49, new int[]{4}, new int[]{+4}),
            new Element("Ta", "Tantalum", 73, "transition metals", 180.9479, new int[]{5}, new int[]{+5, +4, +3}),
            new Element("W", "Tungsten", 74, "transition metals", 183.84, new int[]{6}, new int[]{+6, +5, +4, +3, +2}),
            new Element("Re", "Rhenium", 75, "transition metals", 186.207, new int[]{7}, new int[]{+2, +4, +6, +7, -1, +1, +3, +5}),
            new Element("Os", "Osmium", 76, "transition metals", 190.2, new int[]{8}, new int[]{+3, +4, +6, +8, +2}),
            new Element("Ir", "Iridium", 77, "transition metals", 192.22, new int[]{9}, new int[]{+3, +4, +6, +1, +2}),
            new Element("Pt", "Platinum", 78, "transition metals", 195.08, new int[]{10}, new int[]{+2, +4, +6, +1, +3}),
            new Element("Au", "Gold", 79, "transition metals", 196.96657, new int[]{1}, new int[]{+1, +3, +2}),
            new Element("Hg", "Mercury", 80, "transition metals", 200.59, new int[]{2}, new int[]{+1, +2}),
            new Element("Tl", "Thallium", 81, "post-transition metals", 204.383, new int[]{3}, new int[]{+1, +3, +2}),
            new Element("Pb", "Lead", 82, "post-transition metals", 207.0, new int[]{4}, new int[]{+2, +4}),
            new Element("Bi", "Bismuth", 83, "post-transition metals", 208.98040, new int[]{5}, new int[]{+3, +3, +2, +4, +5}), // here
            new Element("Po", "Polonium", 84, "metalloids", 208.98243, new int[]{6}, new int[]{+2, +4, -2, +6}),
            new Element("At", "Astatine", 85, "halogen", 209.98715, new int[]{1}, new int[]{+1, -1, +3, +5, +7}),
            new Element("Rn", "Radon", 86, "noble gases", 222.01758, new int[]{0}, new int[]{0}),
            new Element("Fr", "Francium", 87, "alkali metals", 223.01973, new int[]{1}, new int[]{+1}),
            new Element("Ra", "Radium", 88, "alkaline earth metals", 226.02541, new int[]{2}, new int[]{+2}),
            new Element("Ac", "Actinium", 89, "actinoids", 227.02775, new int[]{3}, new int[]{+3}),
            new Element("Th", "Thorium", 90, "actinoids", 232.038, new int[]{4}, new int[]{+4, +3}),
            new Element("Pa", "Protactinium", 91, "actinoids", 231.03588, new int[]{5}, new int[]{+5, +4, +3}),
            new Element("U", "Uranium", 92, "actinoids", 238.0289, new int[]{6}, new int[]{+6, +5, +4, +3}),
            new Element("Np", "Neptunium", 93, "actinoids", 237.048172, new int[]{7}, new int[]{+7, +6, +5, +4, +3}),
            new Element("Pu", "Plutonium", 94, "actinoids", 244.06420, new int[]{8}, new int[]{+8, +6, +5, +4, +3}),
            new Element("Am", "Americium", 95, "actinoids", 243.061380, new int[]{9}, new int[]{+9, +6, +5, +4, +3}),
            new Element("Cm", "Curium", 96, "actinoids", 247.07035, new int[]{10}, new int[]{+10, +8, +6, +5, +4, +3}),
            new Element("Bk", "Berkelium", 97, "actinoids", 247.07031, new int[]{11}, new int[]{+11, +8, +6, +5, +4, +3}),
            new Element("Cf", "Californium", 98, "actinoids", 251.07959, new int[]{12}, new int[]{+12, +8, +6, +5, +4, +3}),
            new Element("Es", "Einsteinium", 99, "actinoids", 252.0830, new int[]{13}, new int[]{+13, +8, +6, +5, +4, +3}),
            new Element("Fm", "Fermium", 100, "actinoids", 257.09511, new int[]{14}, new int[]{+14, +8, +6, +5, +4, +3}),
            new Element("Md", "Mendelevium", 101, "actinoids", 258.09843, new int[]{15}, new int[]{+15, +8, +6, +5, +4, +3}),
            new Element("No", "Nobelium", 102, "actinoids", 259.10100, new int[]{16}, new int[]{+16, +8, +6, +5, +4, +3}),
            new Element("Lr", "Lawrencium", 103, "actinoids", 266.120, new int[]{17}, new int[]{+17, +8, +6, +5, +4, +3}),
            new Element("Rf", "Rutherfordium", 104, "transition metals", 267.122, new int[]{4}, new int[]{+4, +3, +2}),
            new Element("Db", "Dubnium", 105, "transition metals", 268.126, new int[]{5}, new int[]{+5, +4, +3, +2}),
            new Element("Sg", "Seaborgium", 106, "transition metals", 269.128, new int[]{6}, new int[]{+6, +5, +4, +3, +2}),
            new Element("Bh", "Bohrium", 107, "transition metals", 270.133, new int[]{7}, new int[]{+7, +6, +5, +4, +3, +2}),
            new Element("Hs", "Hassium", 108, "transition metals", 269.1336, new int[]{8}, new int[]{+8, +6, +5, +4, +3, +2}),
            new Element("Mt", "Meitnerium", 109, "transition metals", 277.154, new int[]{9}, new int[]{+9, +6, +5, +4, +3, +2}),
            new Element("Ds", "Darmstadtium", 110, "transition metals", 282.166, new int[]{10}, new int[]{+10, +8, +6, +5, +4, +3, +2}),
            new Element("Rg", "Roentgenium", 111, "transition metals", 282.169, new int[]{11}, new int[]{+11, +8, +6, +5, +4, +3, +2}),
            new Element("Cn", "Copernicium", 112, "transition metals", 286.179, new int[]{12}, new int[]{+12, +8, +6, +5, +4, +3, +2}),
            new Element("Nh", "Nihonium", 113, "post-transition metals", 286.182, new int[]{13}, new int[]{+13, +8, +6, +5, +4, +3, +2}),
            new Element("Fl", "Flerovium", 114, "post-transition metals", 290.192, new int[]{14}, new int[]{+14, +8, +6, +5, +4, +3, +2}),
            new Element("Mc", "Moscovium", 115, "post-transition metals", 290.196, new int[]{15}, new int[]{+15, +8, +6, +5, +4, +3, +2}),
            new Element("Lv", "Livermorium", 116, "post-transition metals", 293.205, new int[]{16}, new int[]{+16, +8, +6, +5, +4, +3, +2}),
            new Element("Ts", "Tennessine", 117, "halogen", 294.211, new int[]{17}, new int[]{+17, +8, +6, +5, +4, +3, +2}),
            new Element("Og", "Oganesson", 118, "noble gases", 295.216, new int[]{0}, new int[]{0})
    };


    public static Element getElementByNumber(int queryNumber) throws ElementDoesNotExistException {
        if (queryNumber >= 1 && queryNumber <= TABLE.length)
            return TABLE[queryNumber - 1];

        throw new ElementDoesNotExistException("Element with number " + queryNumber + " doesn't exist.");
    }


    public static Element getElementByShortName(String queryShortName) throws ElementDoesNotExistException {
        for (Element item : TABLE)
            if (item.getShortName().equalsIgnoreCase(queryShortName))
                return item;

        throw new ElementDoesNotExistException("Element with short name " + queryShortName + " doesn't exist.");
    }


    public static Element getElementByFullName(String queryFullName) throws ElementDoesNotExistException {
        for (Element item : TABLE)
            if (item.getFullName().equalsIgnoreCase(queryFullName))
                return item;

        throw new ElementDoesNotExistException("Element with full name " + queryFullName + " doesn't exist.");
    }


    public static String getElementGroup(int queryNumber) throws ElementDoesNotExistException {
        return getElementByNumber(queryNumber).getElementGroup();
    }
}

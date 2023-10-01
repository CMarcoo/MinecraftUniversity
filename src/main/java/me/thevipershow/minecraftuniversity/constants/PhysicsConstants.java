package me.thevipershow.minecraftuniversity.constants;

import lombok.Getter;
import me.thevipershow.minecraftuniversity.gui.GUIUtilities;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Constants that belong to the world of Physics.
 */
@Getter
public enum PhysicsConstants implements ItemConversible {
    LIGHT_SPEED(                                "c",        "Speed of light in vacuum",                         299792458,              "1E8",      "m⋅s^−1"),
    PLANCK_CONSTANT(                            "h",        "Planck constant",                                  6.62607015,             "1E-34",    "J⋅Hz^−1"),
    REDUCED_PLANCK_CONSTANT(                    "ℏ",        "Reduced Planck constant",                          1.054571817,            "1E-34",    "J⋅s"),
    VACUUM_MAGNETIC_PERMEABILITY(               "μ0",       "Vacuum magnetic permeability",                     1.25663706212,          "1E-6",     "N⋅A^−2"),
    VACUUM_ELECTRIC_PERMITTIVITY(               "ε0",       "Vacuum electric permittivity",                     8.854187817,            "1E-12",    "F/m"),
    BOLTZMANN_CONSTANT(                         "k",        "Boltzmann constant",                               1.380649,               "1E-23",    "J/K"),
    GRAVITATIONAL_CONSTANT(                     "G",        "Gravitational constant",                           6.67430,                "1E-11",    "m^3/(kg·s^2)"),
    COULOMB_CONSTANT(                           "ke",       "Coulomb constant",                                 8.9875517923,           "1E9",      "N·m^2/C^2"),
    COSMOLOGICAL_CONSTANT(                      "Λ",        "Cosmological constant",                            1.089,                  "1E-52",    "m^-2"),
    STEFAN_BOLTZMANN_CONSTANT(                  "σ",        "Stefan–Boltzmann constant",                        5.670374419,            "1E-8",     "W/(m^2·K^4)"),
    FIRST_RADIATION_CONSTANT(                   "c1",       "First radiation constant",                         3.74177179,             "1E-16",    "W·m^2"),
    FIRST_RADIATION_CONSTANT_SPECTRAL_RADIANCE( "c1L",      "First radiation constant for spectral radiance",   1.191042972,            "1E-16",    "W·m^2·sr^-1"),
    SECOND_RADIATION_CONSTANT(                  "c2",       "Second radiation constant",                        0.01438777,             "1E-2",     "m·K"),
    WIEN_WAVELENGTH_DISPLACEMENT_CONSTANT(      "bwien",    "Wien wavelength displacement law constant",        2.897771955,            "1E-3",     "m·K"),
    WIEN_FREQUENCY_DISPLACEMENT_CONSTANT(       "bwien'",   "Wien frequency displacement law constant",         5.878925757,            "1E10",     "Hz·K"),
    WIEN_ENTROPY_DISPLACEMENT_CONSTANT(         "b_ΔS",     "Wien entropy displacement law constant",           3.002916077,            "1E-3",     "m·K"),
    ELEMENTARY_CHARGE(                          "e",        "Elementary charge",                                1.602176634,            "1E-19",    "C"),
    CONDUCTANCE_QUANTUM(                        "G0",       "Conductance quantum",                              7.748091729,            "1E-5",     "S"),
    INVERSE_CONDUCTANCE_QUANTUM(                "invG0",    "Inverse conductance quantum",                      12906.40372,            "1",        "kΩ"),
    VON_KLITZING_CONSTANT(                      "RK",       "Von Klitzing constant",                            25812.807455,           "1",        "Ω"),
    JOSEPHSON_CONSTANT(                         "KJ",       "Josephson constant",                               483597.8484,            "1E9",      "Hz/V"),
    MAGNETIC_FLUX_QUANTUM(                      "Phi0",     "Magnetic flux quantum",                            2.067833848,            "1E-15",    "Wb"),
    FINE_STRUCTURE_CONSTANT(                    "α",        "Fine-structure constant",                          0.0072973525693,        "1E-3",     ""),
    INVERSE_FINE_STRUCTURE_CONSTANT(            "σ",        "Inverse fine-structure constant",                  137.035999084,          "1",        ""),
    ELECTRON_MASS(                              "me",       "Electron mass",                                    9.10938356,             "1E-31",    "kg"),
    PROTON_MASS(                                "mp",       "Proton mass",                                      1.672621898,            "1E-27",    "kg"),
    NEUTRON_MASS(                               "mn",       "Neutron mass",                                     1.674927471,            "1E-27",    "kg"),
    MUON_MASS(                                  "mmu",      "Muon mass",                                        1.883531594,            "1E-28",    "kg"),
    TAU_MASS(                                   "mtau",     "Tau mass",                                         3.1675463,              "1E-27",    "kg"),
    TOP_QUARK_MASS(                             "mt",       "Top quark mass",                                   3.0784,                 "1E-27",    "kg"),
    PROTON_TO_ELECTRON_MASS_RATIO(              "mp_me",    "Proton-to-electron mass ratio",                    1836.15267343,          "1E3",      ""),
    W_TO_Z_MASS_RATIO(                          "mW_mZ",    "W-to-Z mass ratio",                                0.88153,                "1",        ""),
    WEAK_MIXING_ANGLE(                          "sin2th",   "Weak mixing angle",                                0.22290,                "1",        ""),
    ELECTRON_G_FACTOR(                          "ge",       "Electron g-factor",                                2.00231930436182,       "1",        ""),
    MUON_G_FACTOR(                              "gmu",      "Muon g-factor",                                    2.00233183620,          "1",        ""),
    PROTON_G_FACTOR(                            "gp",       "Proton g-factor",                                  5.585694702,            "1",        ""),
    QUANTUM_OF_CIRCULATION(                     "h/2me",    "Quantum of circulation",                           3.6369475516,           "1E-4",     "m^2/s"),
    BOHR_MAGNETON(                              "muB",      "Bohr magneton",                                    9.274009994,            "1E-24",    "J/T"),
    NUCLEAR_MAGNETON(                           "muN",      "Nuclear magneton",                                 5.0507837461,           "1E-27",    "J/T"),
    CLASSICAL_ELECTRON_RADIUS(                  "re",       "Classical electron radius",                        2.8179403227,           "1E-15",    "m"),
    THOMSON_CROSS_SECTION(                      "sigmae",   "Thomson cross section",                            6.6524587158,           "1E-29",    "m^2"),
    BOHR_RADIUS(                                "a0",       "Bohr radius",                                      5.2917721067,           "1E-11",    "m"),
    HARTREE_ENERGY(                             "Eh",       "Hartree energy",                                   4.3597447222071,        "1E-19",    "J"),
    RYDBERG_CONSTANT(                           "Ry",       "Rydberg constant",                                 1.0973731568539,        "1",        "m^-1"),
    FERMI_COUPLING_CONSTANT(                    "GF",       "Fermi coupling constant",                          1.1663787,              "1E-5",     "GeV^-2"),
    AVOGADRO_CONSTANT(                          "NA",       "Avogadro constant",                                6.02214076,             "1E23",     "mol^-1"),
    MOLAR_GAS_CONSTANT(                         "R",        "Molar gas constant",                               8.31446261815324,       "1",        "J·mol^-1·K^-1"),
    FARADAY_CONSTANT(                           "F",        "Faraday constant",                                 96485.3321233100184,    "1",        "C·mol^-1"),
    MOLAR_PLANCK_CONSTANT(                      "NAh",      "Molar Planck constant",                            3.9903127128934314,     "1E-10",    "J·s·mol^-1"),
    ATOMIC_MASS_CARBON_12(                      "m(12C)",   "Atomic mass of carbon-12",                         1.99264687992,          "1E-26",    "kg"),
    MOLAR_MASS_CARBON_12(                       "MC12",     "Molar mass of carbon-12",                          1.99264687992,          "1E-3",     "kg/mol"),
    ATOMIC_MASS_CONSTANT(                       "mu",       "Atomic mass constant",                             1.66053906660,          "1E-27",    "kg"),
    MOLAR_MASS_CONSTANT(                        "Mu",       "Molar mass constant",                              1.66053906660,          "1E-3",     "kg/mol"),
    MOLAR_VOLUME_SILICON(                       "VmSi",     "Molar volume of silicon",                          12.0588349,             "1E-5",     "m^3/mol"),
    HYPERFINE_TRANSITION_FREQUENCY_CESIUM(      "DnuCs",    "Hyperfine transition frequency of 133Cs",          9.192631770,            "1",        "Hz");


    private final String symbol, name, measureUnit, scientificNotation;
    private final double value;

    /**
     * Main constructor for PhysicsConstants class
     * @param symbol Scientific symbol of the constant. Possibly with original symbol, when possible.
     * @param name The name of the constant.
     * @param value The value in decimal notation for the constant.
     * @param scientificNotation The scientific notation value for the constant.
     * @param measureUnit Unit of measure as a String. Exponents should be expressed with ^, fractions may use ^-1 for single values only.
     */
    PhysicsConstants(String symbol, String name, double value, String scientificNotation, String measureUnit) {
        this.symbol = symbol;
        this.name = name;
        this.measureUnit = measureUnit;
        this.scientificNotation = scientificNotation;
        this.value = value;
    }

    /**
     * Generate an Item from this class.
     *
     * @return The new Item.
     */
    @Override
    public ItemStack convertToItem() {
        return GUIUtilities.createCustomItem(Material.END_CRYSTAL, 1, "§6" + getName(),
                "§7Symbol§r: §e" + getSymbol(),
                "§7Value§r: §e" + getValue() + " " + getScientificNotation(),
                "§7Unit§r: §e" + getMeasureUnit());
    }
}

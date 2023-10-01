package me.thevipershow.minecraftuniversity.constants;

import lombok.Getter;
import me.thevipershow.minecraftuniversity.gui.GUIUtilities;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Constants that belong to the world of Mathematics.
 */
@Getter
public enum MathematicsConstants implements ItemConversible {
    ZERO(                                   "0",                "Zero",                                     0),
    ONE(                                    "1",                "One",                                      1),
    PI(                                     "π",                "Pi",                                               Math.PI),
    E(                                      "e",                "Euler's Number",                                   Math.E),
    GOLDEN_RATIO(                           "φ",                "Golden Ratio",                             (1 + Math.sqrt(5)) / 2),
    SQRT_TWO(                               "√2",               "Square Root of 2",                                 Math.sqrt(2)),
    SQRT_THREE(                             "√3",               "Square Root of 3",                                 Math.sqrt(3)),
    SQRT_FIVE(                              "√5",               "Square Root of 5",                                 Math.sqrt(5)),
    DE_BRUIJN_NEWMAN_CONSTANT(              "M1",               "de Bruijn–Newman constant",                0.26149721284764278375542683860869585),
    MEISSEL_MERTENS_CONSTANT(               "beta",             "Meissel–Mertens constant",                 0.28016949902386913303),
    GAUSS_KUZMIN_WIRSING_CONSTANT(          "lambda",           "Gauss–Kuzmin–Wirsing constant",            0.30366300289873265859744812190155623),
    HAFNER_SARNAK_MCCURLEY_CONSTANT(        "sigma",            "Hafner–Sarnak–McCurley constant",          0.35323637185499598454351655043268201),
    LANDAU_CONSTANT(                        "L",                "Landau's constant",                        0.5),
    OMEGA_CONSTANT(                         "Omega",            "Omega constant",                           0.567143290409783872999966866221035554),
    GOLOMB_DICKMAN_CONSTANT(                "lambda/mu",        "Golomb–Dickman constant",                  0.62432998854355087099293638310083724),
    CAHEN_CONSTANT(                         "Cahen",            "Cahen's constant",                         0.6434105462),
    TWIN_PRIME_CONSTANT(                    "C2",               "Twin prime constant",                      0.660161815846869573927811211001455577),
    LAPLACE_LIMIT(                          "Laplace limit",    "Laplace limit",                            0.662743193491815809747209710925290),
    EMBREE_TREFETHEN_CONSTANT(              "beta*",            "Embree–Trefethen constant",                0.70258),
    LANDAU_RAMANUJAN_CONSTANT(              "K",                "Landau–Ramanujan constant",                0.76422365358922066299069873125009232),
    BRUNS_CONSTANT_FOR_PRIME_QUADRUPLETS(   "B4",               "Brun's constant for prime quadruplets",    0.870588),
    CATALANS_CONSTANT(                      "G",                "Catalan's constant",                       0.915965594177219015460351493238411),
    LEGENDRES_CONSTANT(                     "B'L",              "Legendre's constant",                      1),
    VISWANATHS_CONSTANT(                    "K",                "Viswanath's constant",                     1.13198824),
    APERYS_CONSTANT(                        "zeta(3)",          "Apéry's constant",                         1.20205690315959428539973816151144999),
    CONWAYS_CONSTANT(                       "lambda",           "Conway's constant",                        1.30357726903429639125709911215255189),
    MILLS_CONSTANT(                         "theta",            "Mills' constant",                          1.3063778838069046861449260260571),
    PLASTIC_CONSTANT(                       "rho",              "Plastic constant",                         1.32471795724474602596090885447809734),
    RAMANUJAN_SOLNDER_CONSTANT(             "mu",               "Ramanujan–Soldner constant",               1.45136923348838105028396848589202744),
    BACKHOUSES_CONSTANT(                    "Backhouse",        "Backhouse's constant",                     1.4560749485826896713995953511654356),
    PORTERS_CONSTANT(                       "Porter",           "Porter's constant",                        1.467078794),
    LIEBS_SQUARE_ICE_CONSTANT(              "Lieb",             "Lieb's Square Ice constant",               1.5396007178),
    ERDOS_BORWEIN_CONSTANT(                 "EB",               "Erdős–Borwein constant",                   1.60669515241529176378330152319092458),
    FEIGENBAUM_CONSTANT(                    "alpha",            "Feigenbaum constant",                      2.50290787509589282228390287321821578),
    SIERPINSKIS_CONSTANT(                   "K",                "Sierpiński's constant",                    2.58498175957925321706589358738317116),
    KHINCHINS_CONSTANT(                     "Khinchin",         "Khinchin's constant",                      2.68545200106530644530971483548179569),
    FRANSEN_ROBINSON_CONSTANT(              "F",                "Fransén–Robinson constant",                2.80777024202851936522150118655777293),
    LEVYS_CONSTANT(                         "Levy",             "Lévy's constant",                          3.27582291872181159787681824538413861159),
    RECIPROCAL_FIBONACCI_CONSTANT(          "psi",              "Reciprocal Fibonacci constant",            3.3598856624317755317201130291892717),
    FEIGENBAUM_CONSTANT_CHAOS(              "delta",            "Feigenbaum constant (Chaos)",              4.66920160910299067185320382046620161);

    private final String symbol, name;
    private final double value;

    /**
     * Main constructor for the MathematicsConstants class.
     * @param symbol Scientific symbol of the constant. Possibly with original symbol, when possible.
     * @param name The name of the constant.
     * @param value The value in decimal notation for the constant.
     */
    MathematicsConstants(String symbol, String name, double value) {
        this.symbol = symbol;
        this.name = name;
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
                "§7Value§r: §e" + getValue());
    }
}

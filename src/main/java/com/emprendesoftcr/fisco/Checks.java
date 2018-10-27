package com.emprendesoftcr.fisco;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.regex.Pattern;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public class Checks {

    public static Iterable checkMaxSize(Iterable values, int maxLength) {
        checkArgument((Iterables.size(values) <= maxLength), "La lista EXCEDE la longitud máxima.");
        return values;
    }

    public static Iterable checkMaxSize(Iterable values, int maxLength, String argName) {
        checkArgument((Iterables.size(values) <= maxLength),
                argName + " la lista EXCEDE la longitud máxima.");
        return values;
    }

    public static Iterable checkEmpty(Iterable values, boolean nullAllowed) {
        checkArgument(((nullAllowed && values == null) || !Iterables.isEmpty(values)), "La lista DEBE contener VALORES.");
        return values;
    }

    public static Iterable checkEmpty(Iterable values, boolean nullAllowed, String argName) {
        checkArgument(((nullAllowed && values == null) || !Iterables.isEmpty(values)),
                argName + " la lista DEBE contener VALORES.");
        return values;
    }

    public static Iterable checkEmpty(Iterable values, Object message, boolean nullAllowed) {
        checkArgument(((nullAllowed && values == null) || !Iterables.isEmpty(values)), message);
        return values;
    }

    public static void checkArgument(boolean expression, Object message) {
        Preconditions.checkArgument(expression, message);
    }

    public static <T> T checkNotNull(T value, Object message, boolean check) {
        return !check ? value : Preconditions.checkNotNull(value, message);
    }

    public static <T> T checkNotNull(T value, boolean check) {
        return !check ? value : Preconditions.checkNotNull(value);
    }

    public static <T> T checkNotNull(T value) {
        return Preconditions.checkNotNull(value);
    }

    public static <T> T checkNotNull(T value, String argName) {
        return Preconditions.checkNotNull(value, argName + " NO DEBE ser NULL");
    }

    public static String checkOnlyNumbers(String value, boolean nullAllowed) {
        checkArgument((nullAllowed && value == null) || value.matches("[0-9]+"),
                "CONTIENE valores no NUMÉRICOS.");
        return value;
    }

    public static String checkOnlyNumbers(String value, boolean nullAllowed, String argName) {
        checkArgument((nullAllowed && value == null) || value.matches("[0-9]+"),
                argName + " CONTIENE valores no NUMÉRICOS.");
        return value;
    }

    public static boolean sizeAllowed(String value, int size) {
        return value.length() <= size;
    }

    public static boolean valueAllowed(Integer value, Integer maxValue) {
        return value <= maxValue;
    }

    public static boolean valueAllowedMin(Integer value, Integer minValue) {
        return value >= minValue;
    }

    public static boolean valueAllowed(Double value, Double maxValue) {
        return value <= maxValue;
    }

    public static boolean valueAllowed(Long value, Long maxValue) {
        return value <= maxValue;
    }

    public static String[] splitDouble(Double value) {
        return value.toString().split(Pattern.quote("."));
    }

    public static boolean decimalsAllowed(Double value, int maxDecimals) {
        BigDecimal d = BigDecimal.valueOf(value);
        BigDecimal result = d.subtract(d.setScale(0, RoundingMode.FLOOR)).movePointRight(d.scale());
        return result.toPlainString().length() <= maxDecimals;
    }

    public static boolean isEmpty(String value) {
        return value.isEmpty();
    }

    public static String checkString(String value) {
        checkNotNull(value, "String NO DEBE ser NULL.", true);
        checkArgument(!isEmpty(value), "NO DEBE ser EMPTY.");
        return value;
    }

    public static String checkString(String value, String argName) {
        checkNotNull(value, argName + " NO DEBE ser NULL.", true);
        checkArgument(!isEmpty(value),  argName + " NO DEBE ser EMPTY.");
        return value;
    }

    public static String checkStrictString(String value, int size) {
        checkNotNull(value, "String NO DEBE ser NULL.", true);
        checkArgument(!isEmpty(value), "NO DEBE ser EMPTY.");
        checkArgument((value.length() == size), "NO CORRESPONDE al tamaño permitido");
        return value;
    }

    public static String checkStrictString(String value, int size, String argName) {
        checkNotNull(value, argName + " NO DEBE ser NULL.", true);
        checkArgument(!isEmpty(value), argName + " NO DEBE ser EMPTY.");
        checkArgument((value.length() == size), argName + " NO CORRESPONDE al tamaño permitido");
        return value;
    }

    public static String checkStrictString(String value, int size, boolean nullAllowed) {
        checkNotNull(value, "String NO DEBE ser NULL.", !nullAllowed);
        checkArgument(!isEmpty(value), "NO DEBE ser EMPTY.");
        checkArgument((value.length() == size), "NO CORRESPONDE al tamaño permitido");
        return value;
    }

    public static String checkStrictString(String value, int size, boolean nullAllowed, String argName) {
        checkNotNull(value, argName + " NO DEBE ser NULL.", !nullAllowed);
        checkArgument(!isEmpty(value), argName + " NO DEBE ser EMPTY.");
        checkArgument((value.length() == size), argName + " NO CORRESPONDE al tamaño permitido");
        return value;
    }

    public static String checkString(String value, int size) {
        checkNotNull(value, "String NO DEBE ser NULL.", true);
        checkArgument(!isEmpty(value), "NO DEBE ser EMPTY.");
        checkArgument(sizeAllowed(value, size), "EXCEDE el tamaño permitido");
        return value;
    }

    public static String checkString(String value, int size, String argName) {
        checkNotNull(value, argName + " NO DEBE ser NULL.", true);
        checkArgument(!isEmpty(value), argName + " NO DEBE ser EMPTY.");
        checkArgument(sizeAllowed(value, size), argName + " EXCEDE el tamaño permitido");
        return value;
    }

    public static String checkString(String value, int size, boolean nullAllowed) {
        checkNotNull(value, "String NO DEBE ser NULL.", !nullAllowed);
        checkArgument(((nullAllowed && value == null) || !isEmpty(value)), "NO DEBE ser EMPTY.");
        checkArgument(((nullAllowed && value == null) || sizeAllowed(value, size)), "EXCEDE el tamaño permitido");
        return value;
    }

    public static String checkString(String value, int size, boolean nullAllowed, String argName) {
        checkNotNull(value, argName + " NO DEBE ser NULL.", !nullAllowed);
        checkArgument(((nullAllowed && value == null) || !isEmpty(value)), argName + " NO DEBE ser EMPTY.");
        checkArgument(((nullAllowed && value == null) || sizeAllowed(value, size)), argName + " EXCEDE el tamaño permitido");
        return value;
    }

    public static String checkStringSize(String value, int size) {
        checkArgument((value == null || sizeAllowed(value, size)), "EXCEDE el tamaño permitido");
        return value;
    }

    public static String checkStringSize(String value, int size, String argName) {
        checkArgument((value == null || sizeAllowed(value, size)), argName + " EXCEDE el tamaño permitido");
        return value;
    }

    public static String checkStringSize(String value, int size, boolean check) {
        checkArgument((!check || value == null || sizeAllowed(value, size)), "EXCEDE el tamaño permitido");
        return value;
    }

    public static String checkStringSize(String value, int size, boolean check, String argName) {
        checkArgument((!check || value == null || sizeAllowed(value, size)), argName + " EXCEDE el tamaño permitido");
        return value;
    }

    public static Integer checkInteger(Integer value, Integer minValue, Integer maxValue, boolean nullAllowed) {
        if (nullAllowed && value == null) {
            return value;
        } else {
            checkNotNull(value, "Integer NO DEBE ser NULL.", true);
            checkArgument(valueAllowedMin(value, minValue), "MENOR al valor permitido");
            checkArgument(valueAllowed(value, maxValue), "EXCEDE el valor permitido");
            return value;
        }
    }

    public static Integer checkInteger(Integer value, Integer minValue, Integer maxValue, boolean nullAllowed, String argName) {
        if (nullAllowed && value == null) {
            return value;
        } else {
            checkNotNull(value, argName + " Integer NO DEBE ser NULL.", true);
            checkArgument(valueAllowedMin(value, minValue), argName + " MENOR al valor permitido");
            checkArgument(valueAllowed(value, maxValue), argName + " EXCEDE el valor permitido");
            return value;
        }
    }

    public static Integer checkInteger(Integer value, Integer maxValue, boolean nullAllowed) {
        if (nullAllowed && value == null) {
            return value;
        } else {
            checkNotNull(value, "Integer NO DEBE ser NULL.", true);
            checkArgument(valueAllowed(value, maxValue), "EXCEDE el valor permitido");
            return value;
        }
    }

    public static Integer checkInteger(Integer value, Integer maxValue, boolean nullAllowed, String argName) {
        if (nullAllowed && value == null) {
            return value;
        } else {
            checkNotNull(value, argName + " Integer NO DEBE ser NULL.", true);
            checkArgument(valueAllowed(value, maxValue), argName + " EXCEDE el valor permitido");
            return value;
        }
    }

    public static Double checkDouble(Double value, Double maxValue, boolean nullAllowed) {
        if (nullAllowed && value == null) {
            return value;
        } else {
            checkNotNull(value, "Double NO DEBE ser NULL.", true);
            checkArgument(valueAllowed(value, maxValue), "EXCEDE el valor permitido");
            return value;
        }
    }

    public static Double checkDouble(Double value, Double maxValue, boolean nullAllowed, String argName) {
        if (nullAllowed && value == null) {
            return value;
        } else {
            checkNotNull(value, argName + " Double NO DEBE ser NULL.", true);
            checkArgument(valueAllowed(value, maxValue), argName + " EXCEDE el valor permitido");
            return value;
        }
    }

    public static Double checkDouble(Double value, Double maxValue, int decimals, boolean round, boolean nullAllowed) {
        if (nullAllowed && value == null) {
            return value;
        } else {
            checkNotNull(value, "Double NO DEBE ser NULL.", true);
            checkArgument(valueAllowed(value, maxValue), "EXCEDE el valor permitido");
            if (!round) {
                checkArgument(decimalsAllowed(value, decimals), "EXCEDE el valor máximo de decimales");
            } else if (!decimalsAllowed(value, decimals)) {
                double roundValue = Double.valueOf("1" + Strings.repeat("0", decimals) + ".0");
                value = Math.round(value * roundValue) / roundValue;
            }
            return value;
        }
    }

    public static Double checkDouble(Double value, Double maxValue, int decimals, boolean round,
                                     boolean nullAllowed, String argName) {
        if (nullAllowed && value == null) {
            return value;
        } else {
            checkNotNull(value,  argName + " Double NO DEBE ser NULL.", true);
            checkArgument(valueAllowed(value, maxValue), argName + " EXCEDE el valor permitido");
            if (!round) {
                checkArgument(decimalsAllowed(value, decimals), argName + " EXCEDE el valor máximo de decimales");
            } else if (!decimalsAllowed(value, decimals)) {
                double roundValue = Double.valueOf("1" + Strings.repeat("0", decimals) + ".0");
                value = Math.round(value * roundValue) / roundValue;
            }
            return value;
        }
    }

    public static Double checkDouble(Double value, int totalNumbers, int decimals, boolean round, boolean nullAllowed) {
        if (nullAllowed && value == null) {
            return value;
        } else {
            checkNotNull(value, "Double NO DEBE ser NULL.", true);
            String[] strValues = splitDouble(value);
            checkArgument(valueAllowed(strValues[0].length(), totalNumbers), "EXCEDE el valor permitido");
            if (!round) {
                checkArgument(decimalsAllowed(value, decimals), "EXCEDE el valor máximo de decimales");
            } else if (!decimalsAllowed(value, decimals)) {
                double roundValue = Double.valueOf("1" + Strings.repeat("0", decimals) + ".0");
                value = Math.round(value * roundValue) / roundValue;
            }
            return value;
        }
    }

    public static Double checkDouble(Double value, int totalNumbers, int decimals, boolean round,
                                     boolean nullAllowed, String argName) {
        if (nullAllowed && value == null) {
            return value;
        } else {
            checkNotNull(value, argName + " Double NO DEBE ser NULL.", true);
            String[] strValues = splitDouble(value);
            checkArgument(valueAllowed(strValues[0].length(), totalNumbers), argName + " EXCEDE el valor permitido");
            if (!round) {
                checkArgument(decimalsAllowed(value, decimals), argName + " EXCEDE el valor máximo de decimales");
            } else if (!decimalsAllowed(value, decimals)) {
                double roundValue = Double.valueOf("1" + Strings.repeat("0", decimals) + ".0");
                value = Math.round(value * roundValue) / roundValue;
            }
            return value;
        }
    }

    public static Long checkLong(Long value, Long maxValue, boolean nullAllowed) {
        if (nullAllowed && value == null) {
            return value;
        } else {
            checkNotNull(value, "Long NO DEBE ser NULL.", true);
            checkArgument(valueAllowed(value, maxValue), "EXCEDE el valor permitido");
            return value;
        }
    }

    public static Long checkLong(Long value, Long maxValue, boolean nullAllowed, String argName) {
        if (nullAllowed && value == null) {
            return value;
        } else {
            checkNotNull(value, argName + " Long NO DEBE ser NULL.", true);
            checkArgument(valueAllowed(value, maxValue),  argName + " EXCEDE el valor permitido");
            return value;
        }
    }

    public static void checkMaxInt(Integer value, Integer maxValue, boolean nullAllowed) {
        checkArgument((nullAllowed ? value == null || value <= maxValue : value <= maxValue), "EXCEDE el valor permitido");
    }

    public static void checkMaxInt(Integer value, Integer maxValue, boolean nullAllowed, String argName) {
        checkArgument((nullAllowed ? value == null || value <= maxValue : value <= maxValue),
                argName + " EXCEDE el valor permitido");
    }

    public static void checkMaxLong(Long value, Long maxValue, boolean nullAllowed) {
        checkArgument((nullAllowed ? value == null || value <= maxValue : value <= maxValue), "EXCEDE el valor permitido");
    }

    public static void checkMaxLong(Long value, Long maxValue, boolean nullAllowed, String argName) {
        checkArgument((nullAllowed ? value == null || value <= maxValue : value <= maxValue),
                argName + " EXCEDE el valor permitido");
    }

    public static void checkEnum(Map enumValues, String key) {
        checkArgument(enumValues.containsKey(key), "NO es un ENUM permitido");
    }

    public static void checkEnum(Map enumValues, String key, String argName) {
        checkArgument(enumValues.containsKey(key), argName + " NO es un ENUM permitido");
    }

    public static void checkEnum(Map enumValues, String key, boolean nullAllowed) {
        checkArgument(((nullAllowed && key == null) || enumValues.containsKey(key)), "NO es un ENUM permitido");
    }

    public static void checkEnum(Map enumValues, String key, boolean nullAllowed, String argName) {
        checkArgument(((nullAllowed && key == null) || enumValues.containsKey(key)), argName + " NO es un ENUM permitido");
    }

  

    public static void checkRegex(String value, String regex, boolean nullAllowed, String argName) {
        checkArgument(((nullAllowed && value == null) || value.matches(regex)),
                argName + " NO cumple con el patrón definido");
    }
}

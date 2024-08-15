package com.panfleto.panfleto.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class FormatKey {
    public static String NormalizeKey(String str) {

        str = str.replaceAll(" ", "");
        String normalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalizedString).replaceAll("").toLowerCase();
    }
}

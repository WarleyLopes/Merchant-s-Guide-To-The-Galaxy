// @author WarleyLopes
package com.merchGalaxyGuide.Controller;

import java.io.*;
import java.util.*;

public class Communication {

    RomanNumerals romanNumerals = new RomanNumerals();
    Map<String, String> galaticRomanMap = new HashMap<>();
    Map<String, Double> metalValueMap = new HashMap<>();

    public void ReadFile(String archiveName) throws FileNotFoundException, IOException {
        FileInputStream stream = new FileInputStream(archiveName+".txt");
        InputStreamReader reader = new InputStreamReader(stream);
        try (BufferedReader br = new BufferedReader(reader)) {
            String line = br.readLine();
            while (line != null) {
                if (romanNumerals.romanMap.containsKey(line.charAt(line.lastIndexOf(" ") + 1)) && line.length() == line.lastIndexOf(" ") + 2) {
                    galaticRomanMap.put(line.substring(0, line.indexOf(" ")).toLowerCase(), line.substring(line.lastIndexOf(" ") + 1, line.length()).toUpperCase());
                } else if (line.toUpperCase().endsWith(" CREDITS") && !(line.toUpperCase().startsWith("HOW MANY") || line.toUpperCase().startsWith("HOW MUCH"))) {
                    String credits = "";
                    for (Character c : line.toCharArray()) {
                        if (Character.isDigit(c)) {
                            credits += c;
                        }
                    }
                    String firstPartOfString = line.toUpperCase().substring(0, line.toUpperCase().indexOf(" IS "));
                    String metal = firstPartOfString.substring(firstPartOfString.lastIndexOf(" ") + 1, firstPartOfString.length());
                    String quantities[] = firstPartOfString.toLowerCase().substring(0, firstPartOfString.indexOf(metal)).split("\\s+");
                    Set<String> keys = galaticRomanMap.keySet();
                    String romanMetalQuantity = "";
                    for (String s : quantities) {
                        if (keys.contains(s)) {
                            romanMetalQuantity += galaticRomanMap.get(s);
                        }
                    }
                    int metalQuantity = romanNumerals.RomanToArabic(romanMetalQuantity.replaceAll("\\s+", ""));
                    metalValueMap.put(metal, Double.parseDouble(credits) / metalQuantity);
                } else if (line.toUpperCase().startsWith("HOW MUCH IS")) {
                    String quantities2[] = line.toLowerCase().substring(line.indexOf(" is ") + 4, line.indexOf("?") - 1).split("\\s+");
                    Set<String> keys2 = galaticRomanMap.keySet();
                    String romanQuantity2 = "";
                    for (String s : quantities2) {
                        if (keys2.contains(s)) {
                            romanQuantity2 += galaticRomanMap.get(s);
                        }
                    }
                    int galaticNumeral = romanNumerals.RomanToArabic(romanQuantity2);
                    System.out.println(line.toLowerCase().substring(line.indexOf(" is ") + 4, line.indexOf("?") - 1).concat(" is " + galaticNumeral));
                } else if (line.toUpperCase().startsWith("HOW MANY CREDITS IS ")) {
                    String galaticMetalsQuestion = line.toLowerCase().replace("how many credits is ", "");
                    galaticMetalsQuestion = galaticMetalsQuestion.replace(" ?", "");
                    String quantities3[] = galaticMetalsQuestion.substring(0, galaticMetalsQuestion.lastIndexOf(" ")).split("\\s+");
                    Set<String> keys3 = galaticRomanMap.keySet();
                    String romanQuantity3 = "";
                    for (String s : quantities3) {
                        if (keys3.contains(s)) {
                            romanQuantity3 += galaticRomanMap.get(s);
                        }
                    }
                    int metalQuantityQuestion = romanNumerals.RomanToArabic(romanQuantity3);
                    double questionedMetalValue = metalValueMap.get(galaticMetalsQuestion.toUpperCase().substring(galaticMetalsQuestion.lastIndexOf(" ") + 1, galaticMetalsQuestion.length()));
                    galaticMetalsQuestion = galaticMetalsQuestion.replace(String.valueOf(galaticMetalsQuestion.charAt(galaticMetalsQuestion.lastIndexOf(" ") + 1)), String.valueOf(galaticMetalsQuestion.charAt(galaticMetalsQuestion.lastIndexOf(" ") + 1)).toUpperCase());
                    galaticMetalsQuestion += " is " + questionedMetalValue * metalQuantityQuestion + " Credits";
                    System.out.println(galaticMetalsQuestion);
                } else {
                    System.out.println("I have no idea what you are talking about");
                }
                line = br.readLine();
            }
        }
    }
}

// @author WarleyLopes
package com.merchGalaxyGuide.Controller;

import java.util.HashMap;
import java.util.Map;

public class RomanNumerals {

    Map<Character, Integer> romanMap = new HashMap<>();
    Map<Integer, Character> arabicMap = new HashMap<>();
    private int comparable = 0;

    public RomanNumerals() {
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        arabicMap.put(1, 'I');
        arabicMap.put(5, 'V');
        arabicMap.put(10, 'X');
        arabicMap.put(50, 'L');
        arabicMap.put(100, 'C');
        arabicMap.put(500, 'D');
        arabicMap.put(1000, 'M');
    }

    public int RomanToArabic(String romanNumeral) {
        romanNumeral = romanNumeral.toUpperCase();
        if (romanNumeral.length() == 1 && !romanNumeral.equals("") && romanMap.containsKey(romanNumeral.charAt(0))) {
            return romanMap.get(romanNumeral.charAt(0));
        } else if (romanNumeral.length() > 1) {
            int finalArabicNumber = 0;
            for (int i = 0; i < romanNumeral.length(); i++) {
                if (romanMap.containsKey(romanNumeral.charAt(0))) {
                    if (romanMap.get(romanNumeral.charAt(i)) >= 1000 && romanMap.get(romanNumeral.charAt(i)) < 4000) {
                        finalArabicNumber = finalArabicNumber + 1000;
                        if (romanNumeral.length() > 1 && i != 0 && romanNumeral.charAt(i - 1) == 'C') {
                            finalArabicNumber -= 100;
                        }
                    }
                    if (romanNumeral.length() > romanNumeral.lastIndexOf(romanNumeral.charAt(i))) {
                        if (romanMap.get(romanNumeral.charAt(i)) == 100 && romanNumeral.indexOf(i) != -1 && romanMap.get(romanNumeral.charAt(i - 1)) != 10) {
                            if (romanMap.get(romanNumeral.charAt(i + 1)) == 1000) {
                                finalArabicNumber = finalArabicNumber + 900;
                            }
                        }
                    }
                    if (romanMap.get(romanNumeral.charAt(i)) >= 500 && romanMap.get(romanNumeral.charAt(i)) < 900) {
                        finalArabicNumber = finalArabicNumber + 500;
                        if (romanNumeral.length() > 1 && i != 0 && romanNumeral.charAt(i - 1) == 'C') {
                            finalArabicNumber -= 200;
                        }
                    }
                    if (romanNumeral.length() > romanNumeral.lastIndexOf(romanNumeral.charAt(i))) {
                        if (romanMap.get(romanNumeral.charAt(i)) >= 100 && romanMap.get(romanNumeral.charAt(i)) < 400) {
                            finalArabicNumber = finalArabicNumber + 100;//100,200,300
                            if (romanNumeral.length() > i + 1 && romanMap.get(romanNumeral.charAt(i + 1)) == 1000) {
                                finalArabicNumber = finalArabicNumber - 100;
                            } else if (romanNumeral.length() >= i + 1 && romanNumeral.indexOf(i) != -1 && romanMap.get(romanNumeral.charAt(i - 1)) == 10) {
                                finalArabicNumber -= 20;
                            } else if (romanNumeral.length() >= i + 1 && romanMap.get(romanNumeral.charAt(i)) == 100 && romanNumeral.indexOf("C") != 0 && romanMap.get(romanNumeral.charAt(i - 1)) != 500 && romanMap.get(romanNumeral.charAt(i - 1)) != 100) {
                                finalArabicNumber -= 20;
                            } else if (romanNumeral.length() > 1 && i != 0 && romanNumeral.charAt(i - 1) == 'X') {
                                finalArabicNumber -= 20;
                            }
                        }
                    }
                    if (romanMap.get(romanNumeral.charAt(i)) >= 50 && romanMap.get(romanNumeral.charAt(i)) < 100) {
                        finalArabicNumber = finalArabicNumber + 50;
                        if (romanNumeral.length() > romanNumeral.lastIndexOf(romanNumeral.charAt(i)) && i != 0) {
                            if (romanNumeral.charAt(i - 1) == 'X') {
                                finalArabicNumber -= 20;
                            }
                        }
                    }
                    if (romanMap.get(romanNumeral.charAt(i)) >= 10 && romanMap.get(romanNumeral.charAt(i)) < 40) {
                        finalArabicNumber = finalArabicNumber + 10;
                        if (romanNumeral.length() > romanNumeral.lastIndexOf(romanNumeral.charAt(i)) && i != 0) {
                            if (romanNumeral.charAt(i - 1) == 'I') {
                                finalArabicNumber -= 2;
                            }
                        }
                    }
                    if (romanMap.get(romanNumeral.charAt(i)) >= 5 && romanMap.get(romanNumeral.charAt(i)) < 10) {
                        finalArabicNumber = finalArabicNumber + 5;
                        if (romanNumeral.length() > romanNumeral.lastIndexOf(romanNumeral.charAt(i)) && i != 0) {
                            if (romanNumeral.charAt(i - 1) == 'I') {
                                finalArabicNumber = finalArabicNumber - 2;
                            }
                        }
                    }
                    if (romanMap.get(romanNumeral.charAt(i)) >= 1 && romanMap.get(romanNumeral.charAt(i)) < 4) {
                        finalArabicNumber = finalArabicNumber + 1;//2,3
                    }
                } else {
                    finalArabicNumber = -1;
                    return finalArabicNumber;
                }
            }
            return finalArabicNumber;
        }
        return -1;
    }

    public String ArabicToRoman(int arabic) {
        comparable = arabic;
        StringBuilder roman = new StringBuilder();
        if (comparable > 0 && comparable < 4000) {
            do {
                if (comparable >= 1000) {
                    roman.insert(0, arabicMap.get(1000));
                    comparable = comparable - 1000;
                }
                if (comparable >= 900 && comparable < 1000) {
                    roman.insert(roman.length(), arabicMap.get(100));
                    roman.insert(roman.length(), arabicMap.get(1000));
                    comparable = comparable - 900;
                }
                if (comparable >= 500 && comparable < 900) {
                    roman.insert(roman.length(), arabicMap.get(500));
                    comparable = comparable - 500;
                }
                if (comparable >= 400 && comparable < 500) {
                    roman.insert(roman.length(), arabicMap.get(100));
                    roman.insert(roman.length(), arabicMap.get(500));
                    comparable = comparable - 400;
                }
                if (comparable >= 100 && comparable < 400) {
                    roman.insert(roman.length(), arabicMap.get(100));
                    comparable = comparable - 100;
                }
                if (comparable >= 90 && comparable < 100) {
                    roman.insert(roman.length(), arabicMap.get(10));
                    roman.insert(roman.length(), arabicMap.get(100));
                    comparable = comparable - 90;
                }
                if (comparable >= 50 && comparable < 90) {
                    roman.insert(roman.length(), arabicMap.get(50));
                    comparable = comparable - 50;
                }
                if (comparable >= 40 && comparable < 50) {
                    roman.insert(roman.length(), arabicMap.get(10));
                    roman.insert(roman.length(), arabicMap.get(50));
                    comparable = comparable - 40;
                }
                if (comparable >= 10 && comparable < 40) {
                    roman.insert(roman.length(), arabicMap.get(10));
                    comparable = comparable - 10;
                }
                if (comparable == 9) {
                    roman.insert(roman.length(), arabicMap.get(1));
                    roman.insert(roman.length(), arabicMap.get(10));
                    comparable = comparable - 9;
                }
                if (comparable >= 5 && comparable < 10) {
                    roman.insert(roman.length(), arabicMap.get(5));
                    comparable = comparable - 5;
                }
                if (comparable >= 4 && comparable < 10) {
                    roman.insert(roman.length(), arabicMap.get(1));
                    roman.insert(roman.length(), arabicMap.get(5));
                    comparable = comparable - 4;
                }
                if (comparable >= 1 && comparable < 4) {
                    roman.insert(roman.length(), arabicMap.get(1));
                    comparable = comparable - 1;
                }
            } while (comparable > 0);
            comparable = 0;
            return roman.toString();
        } else {
            comparable = 0;
            return "I have no idea what you are talking about";
        }
    }
}

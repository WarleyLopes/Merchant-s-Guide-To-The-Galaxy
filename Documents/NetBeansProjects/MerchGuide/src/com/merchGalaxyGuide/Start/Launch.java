// @author WarleyLopes
package com.merchGalaxyGuide.Start;

import com.merchGalaxyGuide.Controller.Communication;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Launch {

    public static void main(String[] args) {
        System.out.println("Hello Galaxy Merchant! Welcome to your translation program of Galactic Languages!\n");
        System.out.println("Type only the name of the txt documment which has the galatic language you want to translate!");
        System.out.println("PS: The name of the archive is case SenSiTiVe!\n");
        System.out.print("File name: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = null;
        try {
            fileName = br.readLine();
            System.out.println();
            Communication communication = new Communication();
            communication.ReadFile(fileName);
        } catch (IOException ex) {
            System.err.println("The .txt archive named as "+fileName+" was not found in the program folder!");
            System.err.println("Error: "+ex);
            System.exit(1);
        }
    }

}

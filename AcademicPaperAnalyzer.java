package Q3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AcademicPaperAnalyzer {
    
	public static String readPaperText() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Which paper do you want to analysize?");
		int choice=scanner.nextInt();
        StringBuilder paperText = new StringBuilder();
        String savePath="/Users/dingyiling/eclipse-workspace/Ding_HW2/src/Q3/";
        int [] path= {291,309, 668, 953, 990, 1136, 1206, 1942, 2091, 2714, 2966, 3329, 3441, 3453, 3477, 3853, 4694, 4727, 64, 621, 656, 3531};
        boolean found = false;

        for (int i = 0; i < path.length; i++) {
            if (path[i] == choice) {
                found = true;
                break; 
            }
        }
        
        if(found=true) {
	        try (BufferedReader reader = new BufferedReader(new FileReader(savePath+choice))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                paperText.append(line).append("\n");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	    }
        return paperText.toString();
        
	}

    public static void searchTopWords() {
        String paperText = readPaperText();
        String[] words = paperText.split("\\s+");

        // Count word occurrences
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            word = word.toLowerCase().replaceAll("[^a-z]", "");
            if (word.length() > 0) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }

        // Sort by frequency in descending order
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordCount.entrySet());
        sortedList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Display the top 10 words
        System.out.println("Top 10 most common words:");
        for (int i = 0; i < 10 && i < sortedList.size(); i++) {
            System.out.println(sortedList.get(i).getKey() + ": " + sortedList.get(i).getValue());
        }
    }

    public static void checkWordExistence(String word) {
        String paperText = readPaperText();
        word = word.toLowerCase().trim();
        boolean exists = paperText.toLowerCase().contains(word);

        if (exists) {
            System.out.println("The word '" + word + "' exists in the paper.");
        } else {
            System.out.println("The word '" + word + "' does not exist in the paper.");
        }
    }

    public static void checkPaperSections() {
        String paperText = readPaperText();

        // Use regular expressions to search for paper sections
        Pattern abstractPattern = Pattern.compile("(?i)\\babstract\\b");
        Pattern introductionPattern = Pattern.compile("(?i)\\bintroduction\\b");
        Pattern conclusionPattern = Pattern.compile("(?i)\\bconclusion\\b");
        Pattern assumptionPattern = Pattern.compile("(?i)\\bassumption\\b");

        Matcher abstractMatcher = abstractPattern.matcher(paperText);
        Matcher introductionMatcher = introductionPattern.matcher(paperText);
        Matcher conclusionMatcher = conclusionPattern.matcher(paperText);
        Matcher assumptionMatcher = assumptionPattern.matcher(paperText);

        System.out.println("Sections found in the paper:");

        if (abstractMatcher.find()) {
            System.out.println(" - Abstract");
        }
        if (introductionMatcher.find()) {
            System.out.println(" - Introduction");
        }
        if (conclusionMatcher.find()) {
            System.out.println(" - Conclusion");
        }
        if (assumptionMatcher.find()) {
            System.out.println(" - Assumption");
        }
    }

    public static void listPaperTitlesWithWord(String wordToSearch) {

        Map<String, String> paperDatabase = new HashMap<>();
        paperDatabase.put("Paper 953: Vartiation of Reslistance wilth Composition in the β-Phase of the H—Pd System at 298 K", "Introduction ... Derivation of resistance-loading function ...Discussion ... Acknowledgements ... References: ...");
        paperDatabase.put("Paper 291: Investigation of the Nickel-Hydrogen Anomalous Heat Effect", "Introduction...Sample Preparation ...Experimental Design...4. Principal Experiment Trial...Discussion and Conclusion ...References...");
        paperDatabase.put("Paper 309: Calorimetry of Energy-Efficient Glow Discharge – Apparatus Design and Calibration", "Introduction ... Content ...");
        paperDatabase.put("Paper 668: ENERGETIC CHARGED PARTICLES FROM DEUTERIUM METAL SYSTEMS", "Abstract: ... Content ...");
        paperDatabase.put("Paper 990: Intensification Of Low Energy Nuclear Reactions Using Superwave Excitation", "Abstract: ... Content ...");
        paperDatabase.put("Paper 1136: Hydrogen triggered exothermal reaction in uranium metal", "Abstract: ... Content ...");
        paperDatabase.put("Paper 1206: Abnormal excess heat observed during Mizuno-type experiments", "Abstract: ... Content ...");
        paperDatabase.put("Paper 1942: SPORADIC OBSERVATION OF THE FLEISCHMANN- PONS HEAT EFFECT", "Abstract: ... Content ...");
        paperDatabase.put("Paper 2091: Theory of Low-Energy Deuterium Fusion in Micro/Nano-Scale Metal Grains and Particles", "Abstract: ... Content ...");
        paperDatabase.put("Paper 2714: ISOTHERMAL FLOW CALORIMETRIC INVESTIGATIONS OF THE D/Pd SYSTEM", "Abstract: ... Content ...");
        paperDatabase.put("Paper 3329: THEY WILL ONLY LAUGH AT YOU: Cold Fusion", "Abstract: ... Content ...");
        paperDatabase.put("Paper 2966: Generation of Heat and Products During Plasma Electrolysis", "Abstract: ... Content ...");
        paperDatabase.put("Paper 3441: TRITIUM GENERATION DURING ELECTROLYSIS EXPERIMENT", "Abstract: ... Content ...");
        paperDatabase.put("Paper 3453: SEARCH FOR NUCLEAR FUSION IN GAS PHASE DEUTERIDING OF TITANIUM METAL", "Abstract: ... Content ...");
        paperDatabase.put("Paper 3477: Some Experiments on the Decrease of the Radioactivity of Tritium Sorbed by Titanium", "Abstract: ... Content ...");
        paperDatabase.put("Paper 3853: NEW ELECTROLYTIC PROCEDURE FOR THE OBTAINMENT OF VERY HIGH HlPd LOADING RATIOS. PRELIMINARY ATTEMPTS FOR ITS APPLICATION TO THE D-Pd SYSTEM", "Abstract: ... Content ...");
        paperDatabase.put("Paper 4694: ELECTROCHEMICAL EFFECTS ON THE RESISTANCE MEASUREMENTS OF PD|H ELECTRODE", "Abstract: ... Content ...");
        paperDatabase.put("Paper 4727: BETHE'S CALCULATION FOR SOLAR ENERGY AND SELECTIVE RESONANT TUNNELING", "Abstract: ... Content ...");
        paperDatabase.put("Paper 64: ELECTROLYSIS OF D2O WITH A PALLADIUM CATHODE COMPARED WITH ELECTROLYSIS OF H20O WITH A PLATINUM ELECTRODE: PROCEDURE AND EXPERIMENTAL DETAILS.", "Abstract: ... Content ...");
        paperDatabase.put("Paper 621: OVERVIEW OF H-NI SYSTEMS: OLD EXPERIMENTS AND NEW SETUP", "Abstract: ... Content ...");
        paperDatabase.put("Paper 656: CALORIMETRIC MEASUREMENTS DURING Pd-Ni THIN FILM– CATHODES ELECTROLYSIS IN Li2SO4/H2O SOLUTION", "Abstract: ... Content ...");
        paperDatabase.put("Paper 3531: TRITIUM GENERATION FROM THE INTERACTION OF A GLOW DISCHARGE PLASMA WITH METALS AND WITH A MAGNETIC FIELD", "Abstract: ... Content ...");

        System.out.println("Papers associated with the word '" + wordToSearch + "':");

        for (Map.Entry<String, String> entry : paperDatabase.entrySet()) {
            if (entry.getKey().toLowerCase().contains(wordToSearch.toLowerCase()) ||
                    entry.getValue().toLowerCase().contains(wordToSearch.toLowerCase())) {
                System.out.println(entry.getKey());
            }
        }
    }
}

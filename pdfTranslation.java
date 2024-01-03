package Q3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class pdfTranslation {
	
	private static String formatTextStartingFromSection(String pdfText, String sectionTitleToStartFrom) {
        String[] lines = pdfText.split("\n");
        StringBuilder formattedText = new StringBuilder();
        Pattern title1 = Pattern.compile("^(\\d+)\\.\\s+\\w+.*");
        Pattern title2 = Pattern.compile("^[0-9]+\\s[A-Za-z].*");
//        Pattern title3 = Pattern.compile("^[A-Z\\s]*$");
        Pattern title4 = Pattern.compile("^[A-Za-z]*$");
        Pattern title5 = Pattern.compile("^\\d$");
        Pattern rf = Pattern.compile("references", Pattern.CASE_INSENSITIVE);
        
        for (String line : lines) {
        	Matcher title1matcher = title1.matcher(line);
//        	Matcher title2matcher = title2.matcher(line);
//        	Matcher title3matcher = title3.matcher(line);
        	Matcher title4matcher = title4.matcher(line);
        	Matcher title5matcher = title5.matcher(line);
        	Matcher rfmatcher = rf.matcher(line);
        	if (line.contains("\t")||
        			title1matcher.find()||title4matcher.find()||title5matcher.find()||
        			rfmatcher.find()) {
        		formattedText.append("\n");
        		formattedText.append(line);
        		formattedText.append("\n\n");
        	}
        	else {
        		formattedText.append(line);
            	formattedText.append("\n");
        	}
        	
        }
		return formattedText.toString();

//        StringBuilder formattedText = new StringBuilder();
//        boolean foundStartSection = false;
//        
//        Pattern title = Pattern.compile("^(\\d+)\\.\\s+\\w+.*");
//        Pattern content = Pattern.compile("^(\\d+)\\. .*?\\.\\s*$");
//        Pattern rf = Pattern.compile("reference", Pattern.CASE_INSENSITIVE);
//        Pattern end = Pattern.compile("\\. $");
//        for (String line : lines) {
//        	
//        	Matcher refermatcher = rf.matcher(line);
//        	Matcher endmatcher = end.matcher(line);
//        	Matcher contentmatcher = end.matcher(line);
//        		
//        	if (titlematcher.find() && foundStartSection == false) {
//        		formattedText.append(line);
//                formattedText.append("\n\n");
//        	}
//        	
//        	if (titlematcher.find()) {
//                formattedText.append(line);
//                formattedText.append("\n\n");
//                foundStartSection = true;
//            }
//        	
//        	if (refermatcher.find() && foundStartSection == true) {
//             	
//             	formattedText.append(line);
//                formattedText.append("\n");
//            }
//        	
//        
//           
//            
//            else {
//            	formattedText.append(line);
//            	formattedText.append("\n");
//            }
//        }

    }
	
	public static void main(String[] args) {
		int [] easyName= {291,309, 668, 953, 990, 1136, 1206, 1942, 2091, 2714, 2966,3329, 3441, 3453, 3477, 3853, 4694, 4727};
		int []hardName= {64, 621, 656, 3531};
		String file1="/Users/dingyiling/Desktop/PDFData/Easy to Process/";
		String file2="/Users/dingyiling/Desktop/PDFData/Challenging to Process/";
		String savePath="/Users/dingyiling/eclipse-workspace/Ding_HW2/src/Q3/";
		for (int i:easyName) {
	        try (PDDocument document1 = PDDocument.load(new File(file1+i+".pdf"))) {
	            PDFTextStripper textStripper = new PDFTextStripper();
	            String pdfText = textStripper.getText(document1);
	            String sectionTitleToStartFrom = "1. Introduction";
	            String formattedText = formatTextStartingFromSection(pdfText, sectionTitleToStartFrom);
	            textStripper.setParagraphStart("/t");
	            textStripper.setSortByPosition(true);
	            
	            document1.close();
	            BufferedWriter BW = new BufferedWriter(new FileWriter(savePath + i));
	            BufferedReader BR = new BufferedReader(new FileReader(savePath + i));
	    		
		        BW.write(formattedText);
		    	BR.close();
		    	BW.close();
		        System.out.println(i+" Done");
	        }
        catch (IOException e) {
            e.printStackTrace();
        	}
		}	
		for (int i:hardName) {
	        try (PDDocument document1 = PDDocument.load(new File(file2+i+".pdf"))) {
	            PDFTextStripper textStripper = new PDFTextStripper();
	            String pdfText = textStripper.getText(document1);
	            String sectionTitleToStartFrom = "1. Introduction";
	            String formattedText = formatTextStartingFromSection(pdfText, sectionTitleToStartFrom);
	            textStripper.setParagraphStart("/t");
	            textStripper.setSortByPosition(true);
	            
	            document1.close();
	            BufferedWriter BW = new BufferedWriter(new FileWriter(savePath + i));
	            BufferedReader BR = new BufferedReader(new FileReader(savePath + i));
	    		
		        BW.write(formattedText);
		    	BR.close();
		    	BW.close();
		        System.out.println(i+" Done");
	        }
        catch (IOException e) {
            e.printStackTrace();
        	}	
	}

        while (true) {
            System.out.println("Academic Paper Analyzer Menu:");
            System.out.println("1. Search for the top 10 most common words");
            System.out.println("2. Check if a word exists in the paper");
            System.out.println("3. Check for paper sections");
            System.out.println("4. List paper titles associated with a word");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    AcademicPaperAnalyzer.searchTopWords();
                    break;
                case 2:
                    System.out.print("Enter the word to check: ");
                    String wordToCheck = scanner.nextLine();
                    AcademicPaperAnalyzer.checkWordExistence(wordToCheck);
                    break;
                case 3:
                	AcademicPaperAnalyzer.checkPaperSections();
                    break;
                case 4:
                    System.out.print("Enter the word to search for in paper titles: ");
                    String wordToSearch = scanner.nextLine();
                    AcademicPaperAnalyzer.listPaperTitlesWithWord(wordToSearch);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}

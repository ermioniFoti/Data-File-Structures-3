package project3_2020030081;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		final int NUM_OF_SEARCHES = 100;
		Scanner sc = new Scanner(System.in);
		

		BPlusTree<String, PositionInFile> tree1 = new BPlusTree<>();
		

		System.out.println("----------------------Menu--------------------------");
		System.out.println("1)Insert the words from a file in the Data Structure");
		System.out.println("2)Print the information of a certain word");
		System.out.println("3)Search 100 existing words ");
		System.out.println("4)Exit");
		String option = sc.next();

		while (!option.equals("4")) {

			if (option.equals("1")) {

				System.out.println("Enter the name of the file");
				String fileName = sc.next();
				InputStream is;
				TokenIterator ti;
				try {
					is = new FileInputStream(fileName);
					ti = new TokenIterator(is);

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
				
				while (ti.hasNext()) {
					
					Token t = ti.next();
					String key = t.token;

					PositionInFile p = new PositionInFile(fileName, t.position);
					tree1.insert(key, p);

				}
				
				
				
				

			}
			
			else if(option.equals("2")) {
				
				SearchStats.setNodeAccessCount(0);
				SearchStats.setCompareCount(0);
				System.out.println("Enter the word:");
				String key2 = sc.next();
				List<PositionInFile> p = tree1.search(key2);
				System.out.println(p);
				System.out.println("The Node Access Count is: "+SearchStats.getNodeAccessCount());
				System.out.println("The Number Of Comparisons is: "+SearchStats.getCompareCount());
				
			}
			
			else if (option.equals("3")) {

				int[] order = { 10, 20 };

				for (int i = 0; i < order.length; i++) {
					BTreeInnerNode.setInnerOrder(order[i]);
					BTreeLeafNode.setLeafOrder(order[i]);

					BPlusTree<String, PositionInFile> tree2 = new BPlusTree<>();

					long sumOfNodeAccessCount = 0;
					long sumOfCompareCount = 0;

					String fileName1 = ".\\data\\1.txt";
					String fileName2 = ".\\data\\2.txt";
					InputStream is1, is2;
					TokenIterator ti1, ti2;

					try {
						is1 = new FileInputStream(fileName1);
						ti1 = new TokenIterator(is1);
						is2 = new FileInputStream(fileName2);
						ti2 = new TokenIterator(is2);

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}

					while (ti1.hasNext()) {

						Token t = ti1.next();
						String key = t.token;

						PositionInFile p = new PositionInFile(fileName1, t.position);
						tree2.insert(key, p);

					}

					while (ti2.hasNext()) {

						Token t = ti2.next();
						String key = t.token;

						PositionInFile p = new PositionInFile(fileName2, t.position);
						tree2.insert(key, p);

					}

					List<String> keys1 = readWordsFromFile(fileName1);
					List<String> randomKeys1 = getRandomWords(keys1, NUM_OF_SEARCHES / 2);

					List<String> keys2 = readWordsFromFile(fileName2);
					List<String> randomKeys2 = getRandomWords(keys2, NUM_OF_SEARCHES / 2);

					SearchStats.setNodeAccessCount(0);
					SearchStats.setCompareCount(0);

					for (int j = 0; j < NUM_OF_SEARCHES / 2; j++) {

						List<PositionInFile> p = tree2.search(randomKeys1.get(j));
						sumOfCompareCount += SearchStats.getCompareCount();
						sumOfNodeAccessCount += SearchStats.getNodeAccessCount();
						SearchStats.setNodeAccessCount(0);
						SearchStats.setCompareCount(0);
					}

					for (int k = 0; k < NUM_OF_SEARCHES / 2; k++) {

						List<PositionInFile> p = tree2.search(randomKeys2.get(k));
						sumOfCompareCount += SearchStats.getCompareCount();
						sumOfNodeAccessCount += SearchStats.getNodeAccessCount();
						SearchStats.setNodeAccessCount(0);
						SearchStats.setCompareCount(0);

					}
					
					System.out.println("Order of the Tree:"+order[i]);

					double avgCompareCount = sumOfCompareCount / NUM_OF_SEARCHES;
					double avgNodeAccessCount = sumOfNodeAccessCount / NUM_OF_SEARCHES;

					System.out.println("Average CompareCount: " + avgCompareCount);
					System.out.println("Average Node Access Count: " + avgNodeAccessCount);
					
				}
			}

			else {
				System.out.println("EXIT");
				return;
			}

			System.out.println("\n\n----------------------Menu--------------------------");
			System.out.println("1)Insert the words from a file in the Data Structure");
			System.out.println("2)Print the information of a certain word");
			System.out.println("3)Searching 100 words that exist");
			System.out.println("4)Exit");
			option = sc.next();

		}

	}

    public static List<String> readWordsFromFile(String fileName) {
        List<String> words = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNext()) {
                String word = scanner.next();
                words.add(word);
            }
            
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return words;
    }

    public static List<String> getRandomWords(List<String> words, int count) {
        List<String> randomWords = new ArrayList<>();
        Random random = new Random();
        
        for (int i = 0; i < count; i++) {
            int randomIndex = random.nextInt(words.size());
            String randomWord = words.get(randomIndex);
            randomWords.add(randomWord);
        }
        
        return randomWords;
    }
}



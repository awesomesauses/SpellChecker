import java.awt.Dimension;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.*;

import org.jsoup.nodes.Document;

public class PrintingWords extends JScrollPane {	
	String text;
	HashMap<String, Integer> counter;
	JPanel Panel;
	ArrayList<Entry<String, Integer>> sorted;
	Scanner Scan;
	
	public PrintingWords(Document doc){
		counter = new HashMap<String, Integer>();
		text = doc.body().text();
		countWords();
		sorted = SortedByValues(counter);
		Panel = new JPanel(new GridLayout(counter.size(), 2));
		for(Entry<String, Integer> entry: sorted){
			JLabel wrd = new JLabel(entry.getKey());
			Panel.add(wrd);
		}
		setViewportView(Panel);
		setPreferredSize(new Dimension(350,350));
	}

	private ArrayList<Entry<String, Integer>> SortedByValues(HashMap<String, Integer> map) {
		ArrayList<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(map.entrySet());
		Comparator<Entry<String, Integer>> byMapValues = new Comparator<Entry<String, Integer>>(){
			public int compare(Entry<String, Integer>left, Entry<String, Integer> right){
				return right.getValue().compareTo(left.getValue());
			}
		};
		Collections.sort(list, byMapValues);
		return list;
	}

	private void countWords() {
		System.out.println("Misspelled words:");
		StringTokenizer st = new StringTokenizer(text);
		while(st.hasMoreTokens()){
			int i = 0;
			String word = st.nextToken();
			word = word.toLowerCase();
			
			try {
				Scan = new Scanner(new File("ListOfWords.txt"));
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			while(Scan.hasNext()){
				String Spelled = Scan.next();
				//System.out.println(word);
				if(word.equals(Spelled)){
					i = 1;
					break;
				}
				i = 2;
			}
			
			if(i == 2)
				System.out.println(word);
			
			int count = counter.containsKey(word) ? counter.get(word) : 0;
			counter.put(word, count + 1);
		}
	}
}


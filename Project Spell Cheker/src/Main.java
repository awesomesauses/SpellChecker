import java.io.*;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main extends JTabbedPane {
	Document doc = null;
	
    public Main() throws FileNotFoundException {
		try{
			doc = Jsoup.connect("https://google.com").get();
		} 	
		catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	addTab("Most to least occuring", new PrintingWords(doc));
    }
    
    public static void main(String[] args) throws FileNotFoundException{
    	JFrame frame = new JFrame("Spell Check");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	Main construct = new Main();
    	frame.add(construct);
    	frame.setVisible(true);
    	frame.setSize(400, 400);
    }
}




import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;


public class RandomWord {

	private static RandomWord rw;
	
	private final Random random;
	private final ArrayList<String> listOfWords;
	
	private RandomWord() throws IOException {
		random = new Random();
		listOfWords = new ArrayList<String>(80000);
		
		//File f = new File(Config.DICTIONARY_LOCATION);
		URL url = new URL(Config.DICTIONARY_LOCATION);
		InputStream in = url.openStream();
		//FileReader fr = new FileReader(in);
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader read = new BufferedReader(isr);
		try {
			String s;
			while ( (s = read.readLine()) != null) {
				listOfWords.add(s);
			}
		} finally {
			read.close(); //automatically calls close on underlying stream
		}
	}
	
	public static RandomWord getInstance() {
		try {
			if (rw == null)
				rw = new RandomWord();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rw;
	}
	
	public String getWord() {
		int index = random.nextInt(listOfWords.size());
		return listOfWords.get(index);
		
	}
}

package data;

import java.io.PrintWriter;

public class FileWriter {
	public void writeBill(String filename, String billInfo) {
		try {
			PrintWriter file = new PrintWriter(filename, "UTF-8");
			file.write(billInfo);
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}


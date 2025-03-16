package util;

import java.io.*;
import java.util.*;

public class FileIOReader {
	public static List<String[]> ReadFromCSV(String path) throws Exception {

		List<String[]> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String rows;
			while ((rows = br.readLine()) != null) {
				String[] values = rows.split("(,)(?=(?:[^\"]|\"[^\"]*\")*$)");
				records.add(values);
			}
		} catch (IOException e) {
			throw new Exception("FileReadError path: " + path);
		}
		
		return records;
	}
}

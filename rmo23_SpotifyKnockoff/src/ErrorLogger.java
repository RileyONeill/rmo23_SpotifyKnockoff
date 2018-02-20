import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorLogger {
	public void log(String errorMessage) throws IOException {
		//save the following information to errorLog.txt
		//Date, Time, errorMessage \n
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		
		String file = "errorLog.txt";
		
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("Date and Time: " + currentDate + ", " + errorMessage + "\n\n");
		bw.close();
	}

}

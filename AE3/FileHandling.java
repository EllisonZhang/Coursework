/* use this class to read the file content and assign the values to BondTrade objects.
*/
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileHandling {
	public String title;
//	private FileReader readFile;
	private Scanner s;
	private String content= "";
// store all the BondTrade objects.	
    static ArrayList<BondTrade> trade = new ArrayList<BondTrade>();
    private ArrayList<String> data = new ArrayList<String>();
    private int position = 0;
    
//update the data in constructor
	public FileHandling () {
		getFileContent ();
		assignData();
	}
	
	public String getContent() {
		return content;
	}
	public ArrayList<BondTrade> getTrade () {
		return trade;
	}
	
	public void  getFileContent () {
		JFileChooser fc = new JFileChooser(); // how to specify address
//		String curDir = System.getProperty("user.dir");
		fc.setCurrentDirectory(null);
		int returnVal = fc.showOpenDialog(new JFrame());
		try {			
			s = new Scanner (fc.getSelectedFile());
			while(s.hasNextLine()) {
				if(position>0) { 
					data.add(s.nextLine());     // store the data like {7.266,1044,227} in "data" list
					trade.add(new BondTrade());	// the number of objects in trade list is same as data								
				}else {
					title= s.nextLine();
				}
				position++;
			  }      
			}catch (FileNotFoundException e) {
				System.out.println("wrong address!! file not found.");
			} finally{
//				try {
//					readFile.close();
//				} catch (IOException e) {
//					
//			    }		
			}			
	}  
// assign the YIELD, DAYS and Amount data to the objects in ArrayList<BondTrade>
	public void assignData() {
		for (int i=0; i< data.size();i++) {
			trade.get(i).setYeild(Double.parseDouble(data.get(i).split(",")[0]));
			trade.get(i).setDays(Integer.parseInt(data.get(i).split(",")[1]));
			trade.get(i).setAmount(Integer.parseInt(data.get(i).split(",")[2]));
		}	
	}
   
}

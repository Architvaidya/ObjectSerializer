package genericCheckpointing.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;



public class FileProcessor {
	public FileInputStream fileInputStream;
	public FileWriter fileOutPutStream;
	public BufferedReader bufferedReader;
	public BufferedWriter bufferedWriter;
	public String inputFileName;
	public String outputFileName;
	/**
	 * Parameterized Constructor
	 * @param fileInputStream
	 * @param file
	 * @throws FileNotFoundException
	 */
	public FileProcessor(String inputFileNameIn, String outputFileNameIn) throws FileNotFoundException{
			//File outputFile = new File("Output.txt");
			//fileInputStream = new FileInputStream(file);
			//fileOutPutStream = new FileOutputStream(outputFile);
			//this.bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			//bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutPutStream));
			fileInputStream = null;
			fileOutPutStream = null;
			bufferedReader = null;
			bufferedWriter = null;
			inputFileName = inputFileNameIn;
			outputFileName = outputFileNameIn;
	}
	public void open(String mode) throws FileNotFoundException{
		//File outputFile = new File("Output.txt");
			
			
			try {
					if(mode.equals("serdeser")){	//File is empty
						fileOutPutStream = new FileWriter(outputFileName);
						bufferedWriter = new BufferedWriter(fileOutPutStream);
						fileInputStream = new FileInputStream(inputFileName);
						this.bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
					}
					else if(mode.equals("deser")){
						fileInputStream = new FileInputStream(inputFileName);
						this.bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
					}
					return;
				
			} catch (FileNotFoundException e) {
					if(mode.equals("deser")){
						System.out.println("0 number of objects created because file does not exist");
					}
					else{
					System.err.println("Some problem in the file");
					
					}
					System.exit(0);
			}catch(Exception e){
				System.err.println("Some problem in the file");
				System.exit(0);
			}
			
			//System.out.println("Opened the file succesfully");
		
		
}
	
	/**
	 * 
	 * @return fileInputStream
	 */
	
	public FileInputStream getFileInputStream() {
		return fileInputStream;
	}
	/**
	 * 
	 * @param fileInputStream
	 */
	public void setFileInputStream(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	/**
	 * 
	 * @return BufferedReader
	 */
	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}
	/**
	 * 
	 * @param bufferedReader
	 */
	public void setBufferedReader(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}
	/**
	 * 
	 * @return each line as a String
	 */
	public String readFromLine(){
		String line = null;
		String tempLine;
		
		try {
			tempLine = bufferedReader.readLine();
			
			if(tempLine!=null){
				line = tempLine.trim();
			}

			
			
		} catch (IOException e) {
			//e.printStackTrace();
			System.err.println("I/O error");
			System.exit(0);
		}
		return line;
	}
	
	public void writeToFile(String line){
		try {
			bufferedWriter.write(line);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (Exception e) {
			System.err.println("Empty file required");
			System.exit(0);
		}
	}

	public void close(){
		try {
			if(bufferedWriter!=null){
				bufferedWriter.close();
				fileOutPutStream.close();
			}
			bufferedReader.close();
			
			fileInputStream.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}


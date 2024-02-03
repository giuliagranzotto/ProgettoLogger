package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;

import main.MainLogger;

public class MyLogger {
	private BufferedWriter writer;
	private String filePath;
	private String level;
	private String handler;
	
	public MyLogger()
	{
		Properties properties = new Properties();
		InputStream inputStream = MainLogger.class.getClassLoader().getResourceAsStream("config.properties");
		
		try
		{
			properties.load(inputStream);
			level = properties.getProperty("logger.level");
			filePath = properties.getProperty("logger.file");
			handler = properties.getProperty("logger.handler");
			
			File logFile;
			FileWriter fileWriter;
			writer = null;
			
			if(!handler.equals("CONSOLE"))
			{
				logFile = new File(filePath);
	        	fileWriter = new FileWriter(logFile, true);
	        	writer = new BufferedWriter(fileWriter);
			}
		}catch(IOException e)
		{
			System.out.println("Errore caricamento proprieta' " + e.getMessage());
		}
	}
	
	public MyLogger(BufferedWriter writer, String filePath, String level, String handler) {
		this.writer = writer;
		this.filePath = filePath;
		this.level = level;
		this.handler = handler;
	}
	
	public void error(String message)
	{
		stampa("ERROR", message);
	}
	
	public void warning(String message)
	{
		if(!level.equals("error"))
		{
			stampa("WARNING", message);
		}
	}
	
	public void info(String message)
	{
		if(level.equals("info"))
		{
			stampa("INFO", message);
		}
	}
	
	private void stampa(String tipo, String message)
	{
		Calendar calendario = Calendar.getInstance();
		if(handler.equals("FILE"))
		{
			handlerFile(tipo, message, calendario);
		}
		if(handler.equals("CONSOLE"))
		{
			handlerConsole(tipo, message, calendario);
		}
		if(handler.equals("ALL"))
		{
			handlerFile(tipo, message, calendario);
			handlerConsole(tipo, message, calendario);
		}
	}
	
	private void handlerFile(String tipo, String message, Calendar calendario)
	{
		int giorno = calendario.get(Calendar.DAY_OF_MONTH);
		int mese = calendario.get(Calendar.MONTH) + 1;
		int anno = calendario.get(Calendar.YEAR);
		int ore = calendario.get(Calendar.HOUR); // il Calendar.HOUR funziona in 12 ore, come le conteggiano gli americani (AM e PM), quindi aggiungo 12
		int minuti = calendario.get(Calendar.MINUTE);
		
		if(calendario.get(Calendar.AM) == 0)
		{
			ore += 12;
		}
		
		File logFile;
    	FileWriter fileWriter;
		try {
			logFile = new File(filePath);
			fileWriter = new FileWriter(logFile, true);
			writer = new BufferedWriter(fileWriter);
			String minutiString = String.valueOf(minuti);
			if(minuti < 10)
			{
				minutiString = ("0" + minuti);
			}
			
			if (ore == 24) 
			{
				ore = 0;
			}
			
			String stringaFile = ("[" + giorno + "-" + mese + "-" + anno + " " + ore + ":" + minutiString + "] - " + tipo + " - " + message);
			
			writer.write(stringaFile);
			writer.newLine();
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore sull'input: " + e.getMessage());
		}
	}
	
	private void handlerConsole(String tipo, String message, Calendar calendario)
	{
		int giorno = calendario.get(Calendar.DAY_OF_MONTH);
		int mese = calendario.get(Calendar.MONTH) + 1;
		int anno = calendario.get(Calendar.YEAR);
		int ore = calendario.get(Calendar.HOUR); // il Calendar.HOUR funziona in 12 ore, come le conteggiano gli americani (AM e PM), quindi aggiungo 12
		int minuti = calendario.get(Calendar.MINUTE);
		
		if(calendario.get(Calendar.AM) == 0)
		{
			ore += 12;
		}
		
		String minutiString = String.valueOf(minuti);
		if(minuti < 10)
		{
			minutiString = ("0" + minuti);
		}
		
		if (ore == 24) 
		{
			ore = 0;
		}
		System.out.println("");
		System.out.println("[" + giorno + "-" + mese + "-" + anno + " " + ore + ":" + minutiString + "] - " + tipo + " - " + message);
		System.out.println("");
	}
	
}

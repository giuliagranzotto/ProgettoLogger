package main;

import java.util.Scanner;

import model.MyLogger;

public class MainLogger {

	public static void main(String[] args) {
			
		MyLogger log = new MyLogger();
		int scelta = 0;
		Scanner scanner = new Scanner(System.in);
		
		do
		{
			System.out.println("|-----------------------------|");
			System.out.println("| 1. Genera un log.error      |");
			System.out.println("| 2. Genera un log.warning    |");
			System.out.println("| 3. Genera un log.info       |");
			System.out.println("| 0. Esci                     |");
			System.out.println("|-----------------------------|");
			System.out.print("| Scegli l'operazione: ");
			scelta = scanner.nextInt();
			System.out.println("|-----------------------------|");
			
			switch(scelta)
			{
			case 1:
				log.error("Logger ERROR");
				break;
				
			case 2:
				log.warning("Logger WARNING");
				break;
				
			case 3:
				log.info("Logger INFO");
				break;
			
			case 0:
				scanner.close();
				System.out.println("|   Uscita dal programma...   |");
				System.out.println("|-----------------------------|");
				break;
			}
			
		}while(scelta != 0);
	}

}

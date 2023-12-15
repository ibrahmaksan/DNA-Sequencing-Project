package Biyoloji;

import java.util.Scanner;

public class DNA {
	
	Scanner scanner = new Scanner(System.in);
		
	int dnaLength;
	
	char[] dnaOlustur() {
		
		System.out.println("DNA uzunlu�unu giriniz: ");
		dnaLength = scanner.nextInt();
	
		char[] DNA = new char[dnaLength];
	
		System.out.println("DNA rastgele mi olu�sun ?  (Evetse 1; hay�rsa0 tu�una bas�n�z)");
		int in = scanner.nextInt();
		scanner.nextLine();
	
		if( !((in == 1) || (in == 0))){
		
			System.out.println("Hatal� bir tu�lama yapt�n�z. Program� yeniden ba�lat�n�z.");
			System.exit(0);
		}
	
		else if(in == 1) {
			int say�;
			for(int i = 0; i< dnaLength;i++) {
			
				say� = (int) (Math.random()*4);
				if(say� == 0) {
				DNA[i] = 'A';
				}
				else if(say� == 1) {
				DNA[i] = 'T';
				}
			
				else if(say� == 2) {
					DNA[i] = 'C';
				}
			
				else {
					DNA[i] = 'G';
			}
		}
	}
	
		else if(in == 0) {
		
			System.out.println("DNA'y� giriniz: ");
		
			String dna = scanner.nextLine();
		
			for(int i = 0; i<dna.length(); i++) {
			
				DNA[i] = dna.charAt(i);
			}
		}
			System.out.print("DNA: --> ");
			for(int i = 0; i<DNA.length;i++) {
			
				System.out.print(DNA[i]);
			}
			System.out.println();
		
			return DNA;
	}
}

package Biyoloji;

import java.util.Random;
import java.util.Scanner;

public class Sequence {
	
	Scanner scanner = new Scanner(System.in);
	char [] DNA = null;
	int N;
	int L;
	
	public Sequence(char [] DNA,int N, int L) {
		
		this.DNA = DNA;
		this.N = N;
		this.L = L;
	}
	
	char[][] sequences(){
		
		char[][] sekanslar = new char[this.N][this.L]; // Sekanslar�n kaydedilece�i dizi
		
		Random rand = new Random();
				
		for(int i = 0; i<this.N; i++) {
			
			int firstPos = rand.nextInt(DNA.length - this.L - 1); // Sekans�n ba�lang�� noktas�. Buradan itibaren sekans uzunlu�u kadar okuma yapaca��m ve 
			// bir sekans �retmi�i olaca��m.
			
			for(int j = 0; j<this.L; j++) {
				
				sekanslar[i][j] = DNA[firstPos++];
			}
			
		}
		
		System.out.println("Sekanslar : ");
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<L ; j++) {
				
				System.out.print(sekanslar[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("");
		
		return sekanslar;
	}
}

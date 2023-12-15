package Biyoloji;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Odev {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		DNA dna = new DNA(); 
				
		char[] DNA = dna.dnaOlustur();
		
		
		// Sekanslar� ve Complementleri olu�turma k�sm�: 
		
		System.out.println("Ka� sekans istiyorsunuz : "); 
		int N = scanner.nextInt();
		
		System.out.println("Sekans uzunlu�unu giriniz: ");
		int L = scanner.nextInt(); scanner.nextLine();
		
		Sequence sekans = new Sequence(DNA,N,L); char[][] sekanslar = sekans.sequences(); // Sekanslar� olu�turma.
		
		char[][]complements = complement(sekanslar,N,L); // Complementleri olu�turma.
		
		
		
		
 		// Indel, e�le�meme, e�le�me puanlar�n�n hepsi pozitif girilecek.
		System.out.println("E�le�me skorlar�n� bulma ad�m�na ula��ld�. L�tfen indel match ve dismatch ' ler i�in atamak istedi�iniz puanlar� veriniz: "
				+ "\nAtanacak t�m puanlar pozitif olarak girilecektir.");
		
		System.out.println("Indel puan�n� giriniz: ");
		int indel = scanner.nextInt(); scanner.nextLine();
		
		System.out.println("E�le�meme durumu i�in puan�n� giriniz: ");
		int mismatch = scanner.nextInt(); scanner.nextLine();
		
		System.out.println("E�le�me durumu i�in puan� giriniz: ");
		int match = scanner.nextInt(); scanner.nextLine();
		
		
		// E�le�me skoru bulma
        int[][] SkorMatrisi = new int[N][N]; // Her bir sekans�n kar��la�t�r�l�p skorlar�n�n kaydedildi�i dizi.
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	
                if (i == j) {
                	
                    SkorMatrisi[i][j] = 0;
                    
                } else if(i<j) {
                    SkorMatrisi[i][j] = bestoverLap(L, indel, match, mismatch, sekanslar[i], sekanslar[j]);
                }
                else {
                    SkorMatrisi[i][j] = bestoverLap(L, indel, match, mismatch, sekanslar[i], complements[j]);
                }
            }
        }
        
        System.out.println("Skor matrisi olu�turuluyor. Skor matrisinde kabul edilen en k���k de�eri giriniz: ");
        int T = scanner.nextInt();scanner.nextLine();
        
        PrintWriter dosya = null; // Yazd�rma i�lemini PrintWriter ile yapt�m.
        
		try {
			dosya = new PrintWriter("C:\\Users\\ibrahim\\Desktop\\odev.txt");
			dosya.print("   ");
			for(int i =0;i<N;i++) {
				dosya.printf("  %2d",i+1);
			}
			dosya.printf("\n   -");
			for(int i =0;i<N;i++) {
				dosya.printf("----");
			}
			dosya.printf("\n");
			for(int i =0;i<N;i++) {
				
				dosya.printf("%-2d|",i+1);
				
				for(int j=0;j<N;j++) {
					if(SkorMatrisi[i][j]>T) {
						dosya.printf("  %2d",SkorMatrisi[i][j]);
					}
					else if(i == j) {
						dosya.printf("   * ");
					}
					else {
						dosya.printf("    "); // T den k���k de�erlerde matrisin o indisi bo� olacak.
					}
					}
				dosya.printf("\n\n");
			}
			
			System.out.println("Skor matrisi yazd�r�ld�.");
			
		} catch (FileNotFoundException ex) {
			
			System.out.println("Dosya bulunamad�.");
			
		} finally {
			
			dosya.close(); // ��lemin sonunda dosya kapatmak i�in yazd���m kod.
			
		}
		
		
		
		
		/* System.out.println("Konselsus olmas� i�in minimum ka� baz�n �rt��mesi gerkti�ini giriniz : ");
		
		int min = scanner.nextInt();
		
		int m = 0;
		
		for(int i = 0; i<N;i++) {
			
			for(int j = 0; j<N;j++) {
				
				if(SkorMatrisi[i][j]>=min) {
					 // minden by�k skorlar� bulup kar��la�t�rma yap�lacak. 8.3 teki �rnek i�in.
				}
			}
		}*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	static char[][] complement(char[][] sekanslar,int N,int L){
		
		
		char [][] complements = new char[N][L]; // Complementleri atamak i�in kulland���m dizi
		
		for(int i = 0; i<N; i++) {
			
			int temp = 0;
			
			for(int j = L-1; j>=0; j--) { 
				
				if(sekanslar[i][j] == 'A') {
					
					complements[i][temp++] = 'T';
				}
				
				else if(sekanslar[i][j] == 'T') {
					
					complements[i][temp++] = 'A';
				}
				
				else if(sekanslar[i][j] == 'C') {
					
					complements[i][temp++] = 'G';
				}
				
				else if(sekanslar[i][j] == 'G') {
					
					complements[i][temp++] = 'C';
				}
			}
		}
		
		System.out.println("COMPLEMENTLER: \n");
		
		for(int i = 0; i<N;i++) {
			
			for(int j = 0; j<L;j++) {
				
				System.out.print(complements[i][j]);
			}
			System.out.println();
		}
		return complements;
	}
	
	

	static int bestoverLap(int L, int indel, int match, int mismatch, char[] sekanslar, char[] complementler) {

    int[][] matris = new int[L + 1][L + 1]; 

    for (int i = 1; i < L + 1; i++) {
        for (int j = 1; j < L + 1; j++) {
        	
            int matchmismatch; // match ya da mismatch i�in puan atayaca��m de�i�ken.
            
            if (sekanslar[i - 1] == complementler[j - 1]) { // E�le�me durumu 
            	
                matchmismatch = match;
            } else {
                matchmismatch = - mismatch;
            }

            matris[i][j] = Math.max(Math.max(matris[i - 1][j] - indel, matris[i][j - 1] - indel), matris[i - 1][j - 1] + matchmismatch);

        }
    }

    int bestOverlap = matris[1][L]; // En iyi skorumuz ba�lang�� olarak bu olsun. Ayn� sat�r ve s�tunlardaki en b�y�k skoru bulmaya �al��aca��z. En iyi
    // overlap skorumuz o olmu� olacak.
    
    for (int k = 2,t =1; k < L + 1; k++,t++) {
        if (matris[k][L] > bestOverlap) {
            bestOverlap = matris[k][L];
        }
        
        if(matris[L][t] > bestOverlap) {
        	bestOverlap = matris[L][t];
        }
    }
    
    return bestOverlap;

}
}
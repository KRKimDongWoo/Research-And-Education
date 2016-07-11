package GeneticAlgorithm;

import java.util.Arrays;
import java.util.Random;



public class MainClass {


public static int[] answer = {-1, -1, -1, -1};
static final int POOLSIZE = 50;
static final int SELECTED_POOL = 15;
static final int ELITE = 0;
static final boolean MUTATE_TRUE = false;

public static void main(String[] args){
	
	
	int[] numbers = {-1, -1, -1, -1};
	int[] totalScore = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	Gene[] genePool = new Gene[POOLSIZE];
	Gene[] tempGenePool = new Gene[POOLSIZE];
	int generation = 0;
	
	for(int w = 0; w < 1000; w++){
		for(int i = 0; i < 4; i++){
			int j = 1;
			while(true){
				answer[i] = (int) (Math.random() * 10);
				j = 0;
				for(int k = 0; k < i; k++){
					if(answer[k] == answer[i]) j = 1;
				}
				if(j == 0) break;		
			}
		}//generate answer
			
		for(int l = 0; l < POOLSIZE; l++){
			for(int i = 0; i < 4; i++){
				numbers[i] = -1;
			}
			for(int i = 0; i < 4; i++){
				int j = 1;
				while(true){
					numbers[i] = (int) (Math.random() * 10);
					j = 0;
					for(int k = 0; k < i; k++){
						if(numbers[k] == numbers[i]) j = 1;
					}
					if(j == 0) break;
				}
			}
			genePool[l] = new Gene(numbers[0], numbers[1], numbers[2], numbers[3]);
			genePool[l].getScore();
		}//generate first genes
		
		Arrays.sort(genePool); // sort gene by score (Strike prior)
		generation = 1;
		while(genePool[0].score != 40 && generation < 10000){
		//for(int z = 0; z < 2; z++){
			int rand1 = 0;
			int rand2 = 0;
			for(int y = 0; y < POOLSIZE; y++) tempGenePool[y] = genePool[y]; //CHANGE TO 
			for(int i = ELITE; i < POOLSIZE; i++){
				rand1 = (int) (Math.random() * SELECTED_POOL);
				rand2 = (int) (Math.random() * SELECTED_POOL);
				genePool[i] = Crossover(tempGenePool[rand1], tempGenePool[rand2]); //crossover
				Disorder(genePool[i]); //check if gene dead
				if(MUTATE_TRUE && ((int) (Math.random() * 100) < 1))	Mutate(genePool[i]); //mutate
				genePool[i].getScore(); //get gene score
			}
			Arrays.sort(genePool);
			generation += 1;
		}
		
		if(generation >= 10000) totalScore[0] += 1;
		else if(10 <= generation && generation < 10000) totalScore[10] += 1;
		else totalScore[generation] += 1;
		
	}
	
	System.out.print("Failed : ");
	System.out.println(totalScore[0]);
	for(int i = 1; i < 10; i++){
		System.out.print(i);
		System.out.print(" generation : ");
		System.out.println(totalScore[i]);
	}
	System.out.print("10 or more : ");
	System.out.println(totalScore[10]);
}

	public static Gene Crossover(Gene g1, Gene g2){
		Gene g3 = new Gene(0, 0, 0, 0);
		for(int i = 0; i < 4; i++){
		if((int) (Math.random() * 2) < 1)	g3.chromosome[i] = g1.chromosome[i];
		else g3.chromosome[i] = g2.chromosome[i];
		}
		return g3;
	}//crossover of g1 and g2
	
	public static Gene Mutate(Gene g1){
		int rand1 = (int) (Math.random() *4);
		int rand2 = (int) (Math.random() *4);
		int temp = g1.chromosome[rand1];
		g1.chromosome[rand1] = g1.chromosome[rand2];
		g1.chromosome[rand2] = temp;
		return g1;
	}//mutate of g1
	
	public static Gene Disorder(Gene g1){
		for(int i = 0; i < 4; i++) {
			for(int j = i ; j < 4; j++) {
				if(g1.chromosome[i] == g1.chromosome[j]){
					g1 = new Gene(-1, -1, -1, -1);
				}
			}
		}
		return g1;
	}//judge if gene has disorder
	
}

package GeneticAlgorithm;

public class Gene implements Comparable{
	int[] chromosome = new int[4];
	int score;

	
	public Gene(int a, int b, int c, int d) {
		chromosome[0] = a;
		chromosome[1] = b;
		chromosome[2] = c; 
		chromosome[3] = d;
	}
	
	public void getScore() {
		int strike = 0;
		int ball = 0;
		for (int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if (chromosome[i] == MainClass.answer[j]) {
					if(i == j) strike += 1;
					else ball += 1;
				}
			}
		}
		score = strike * 10 + ball;
	}

	public int compareTo(Object obj) {
		Gene other = (Gene) obj;
		if(score > other.score) return -1;
		else if(score < other.score) return 1;
		else return 0;
	}

}

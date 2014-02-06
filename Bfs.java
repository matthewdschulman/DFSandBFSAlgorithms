import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bfs {	
	static void bfs(int[][] adjacencyMatrix, String fileName) throws IOException {
		int numOfUsers = VertexManipulation.numOfUsers(fileName);
		Scanner scanner = new Scanner( System.in );
        System.out.println("At which user would you like to start? Please enter an integer between 0 and "+(numOfUsers));
        int firstNode = Integer.parseInt(scanner.nextLine());
		Queue<Integer> queueCur = new LinkedList<Integer>();
		Queue<Integer> queueNext = new LinkedList<Integer>();
		Queue<Integer> alreadyVisited = new LinkedList<Integer>();
		queueNext.add(firstNode);
		alreadyVisited.add(firstNode);
		int waveCounter = 0;
		int nodeCounter = 0;
		while (!queueNext.isEmpty()) {
			System.out.println("Wave # "+waveCounter+" has length "+queueNext.size()+" | elements : "+queueNext);
			nodeCounter+=queueNext.size();
			waveCounter++;
			queueCur.addAll(queueNext);
			queueNext.clear();
			while (!queueCur.isEmpty()) {
				int curNodeToSearch = queueCur.remove();
				for (int i = (0); i <= numOfUsers; i++) {
					if (adjacencyMatrix[curNodeToSearch][i] == 1) {
						if (!alreadyVisited.contains(i)){
							queueNext.add(i);
							alreadyVisited.add(i);
						}						
					}
				}				
			}
		}	
		System.out.println("The BFS reached a total of "+nodeCounter+" nodes.");		          
	}		
}
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Bfs {	
	@SuppressWarnings("rawtypes")
	static void bfs(int[][] adjacencyMatrix, LinkedList[] adjacencyLines, 
			boolean trueIfMatrix, String fileName, int numOfUsers, int firstNode) throws IOException {
		Queue<Integer> queueCur = new LinkedList<Integer>();
		Queue<Integer> queueNext = new LinkedList<Integer>();
		Queue<Integer> alreadyVisited = new LinkedList<Integer>();
		queueNext.add(firstNode);
		alreadyVisited.add(firstNode);
		int waveCounter = 0;
		int nodeCounter = 0;
		while (!queueNext.isEmpty()) {
			System.out.println("Wave # "+waveCounter+" has length "+queueNext.size()+
					" | elements : "+queueNext);
			nodeCounter+=queueNext.size();
			waveCounter++;
			queueCur.addAll(queueNext);
			queueNext.clear();
			while (!queueCur.isEmpty()) {
				int curNodeToSearch = queueCur.remove();
				for (int i = (0); i <= numOfUsers; i++) {
					if (contains(curNodeToSearch, i, trueIfMatrix, adjacencyMatrix, adjacencyLines)) {
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

	@SuppressWarnings("rawtypes")
	private static boolean contains(int curNodeToSearch, int i, boolean trueIfMatrix, 
			int[][] adjacencyMatrix, LinkedList[] adjacencyLines) {
		if (trueIfMatrix) {
			if (adjacencyMatrix[curNodeToSearch][i] == 1) return true;
			else return false;
		}
		else {
			if (adjacencyLines[curNodeToSearch].contains(i)) return true;
			else return false;
		}
	}
}
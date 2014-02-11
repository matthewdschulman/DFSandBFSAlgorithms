/**
 * Bfs --- class to conduct the BFS for nodes loaded into
 * both an adjacency matrix and an adjacency list.
 * @author    Matt Schulman
 */

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Bfs {	
	/**
	   * conducts a BFS
	   * @param an adjacency matrix (2d array), the adjacencyList (an array of linked lists),
	   * a boolean describing if the search should be for a matrix or for a list, a String
	   * representing the file name, an int representing the number of users, and an int
	   * representing the first node
	   * @exception IOException
	   * @return none
	   */
	@SuppressWarnings("rawtypes")
	static void bfs(int[][] adjacencyMatrix, LinkedList[] adjacencyLines, 
			boolean trueIfMatrix, String fileName, int numOfUsers, int firstNode) throws IOException {
		//create queues to track current node to visit, next node(s) to visit, and the collection
		//of nodes that have already been visited
		Queue<Integer> queueCur = new LinkedList<Integer>();
		Queue<Integer> queueNext = new LinkedList<Integer>();
		Queue<Integer> alreadyVisited = new LinkedList<Integer>();
		
		//add the first node to the queue of nodes to visit next and mark this node as already visited
		queueNext.add(firstNode);
		alreadyVisited.add(firstNode);
		int waveCounter = 0;
		int nodeCounter = 0;
		
		//while there are still lines to visit in the text file
		while (!queueNext.isEmpty()) {
			System.out.println("Wave # "+waveCounter+" has length "+queueNext.size()+
					" | elements : "+queueNext);
			nodeCounter+=queueNext.size();
			waveCounter++;
			queueCur.addAll(queueNext);
			queueNext.clear();
			
			//while there are nodes in the current wave to visit
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

	/**
	   * function to determine if there is an edge between the current node and the 
	   * second node of interest
	   * @param an int corresponding with the current node to search, an int corresponding
	   * with the second node of interest, a boolean corresponding with if the search
	   * should be with a matrix or with a list, the adjacency matrix (a 2d int array), and
	   * the adjacency list (an array of linked lists).
	   * @exception none
	   * @return a boolean corresponding with if an edge exists between the two
	   * nodes of interest
	   */
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
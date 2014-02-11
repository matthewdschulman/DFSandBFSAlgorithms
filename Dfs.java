import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Dfs {	
	@SuppressWarnings("rawtypes")
	public static void dfs(int[][] adjacencyMatrix, LinkedList[] adjacencyLines, 
			boolean trueIfMatrix, String fileName, int numOfUsers, int firstNode) throws IOException {
        System.out.print("DFS Order : "+firstNode);
        Queue<Integer> alreadyVisited = new LinkedList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(firstNode);
		alreadyVisited.add(firstNode);
		int nodeCounter = 1;
        while(!stack.isEmpty()) {
        	int curVertex = stack.peek();
        	int childVertex = getUnvisitedChildVertex(curVertex, numOfUsers, 
        			adjacencyMatrix, adjacencyLines, alreadyVisited, trueIfMatrix);
        	if (childVertex != -1) {
        		System.out.print(" -> "+childVertex);
        		alreadyVisited.add(childVertex);
        		nodeCounter++;
        		stack.push(childVertex);
        	}
        	else {
        		stack.pop();
        	}
        }
        System.out.println();
        System.out.println("The DFS reached a total of "+nodeCounter+" nodes.");
	}
	
	private static int getUnvisitedChildVertex(int curVertex, int numOfUsers, 
			int[][] adjacencyMatrix, @SuppressWarnings("rawtypes") LinkedList[] adjacencyLines, Queue<Integer> alreadyVisited, boolean trueIfMatrix) {
    	//if (trueIfMatrix) {
		for (int i = 0; i <= numOfUsers; i++) {    		
			if (trueIfMatrix) {
				if (adjacencyMatrix[curVertex][i] == 1 && !alreadyVisited.contains(i)) {
					return i;
				}
			}
			else {
				if (adjacencyLines[curVertex].contains(i) && !alreadyVisited.contains(i)) {
					return i;
				}
			}		
		}
	return -1;
	}
}


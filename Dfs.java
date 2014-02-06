import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class Dfs {	
	public static void dfs(int[][] adjacencyMatrix, String fileName) throws IOException {
		int numOfUsers = VertexManipulation.numOfUsers(fileName);
		Scanner scanner = new Scanner( System.in );
        System.out.println("At which user would you like to start? Please enter an integer between 0 and "+(numOfUsers));
        int firstNode = Integer.parseInt(scanner.nextLine());
        System.out.print("DFS Order : "+firstNode);
        Queue<Integer> alreadyVisited = new LinkedList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(firstNode);
		alreadyVisited.add(firstNode);
		int nodeCounter = 1;
        while(!stack.isEmpty()) {
        	int curVertex = stack.peek();
        	int childVertex = VertexManipulation.getUnvisitedChildVertex(curVertex, numOfUsers, adjacencyMatrix, alreadyVisited);
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
	
    

}

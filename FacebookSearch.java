import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class FacebookSearch {
	public static void main(String[] args) throws IOException {   
		Scanner scanner = new Scanner( System.in );
		String fileName = "";
		System.out.println("Which file would you like to search?");
		fileName = scanner.nextLine();
		fileName = "/Users/Matt/Documents/Penn_2013-2014/Spring/NETS-150/HW1-FacebookNodes/src/"+fileName;
		int[][] adjacencyMatrix = uploadFile(fileName);
		System.out.println("Would you like to do a DFS or BFS? Please enter either 'BFS' or 'DFS'");
        String searchType = scanner.nextLine();
        if (searchType.equals("BFS")) {
        	bfs(adjacencyMatrix, fileName);
        }	
        if (searchType.equals("DFS")) {
        	dfs(adjacencyMatrix, fileName);
        }
	}		

	private static void bfs(int[][] adjacencyMatrix, String fileName) throws IOException {
		int numOfUsers = numOfUsers(fileName);
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
		newSearchOption();           
	}
	
	private static void dfs(int[][] adjacencyMatrix, String fileName) throws IOException {
		int numOfUsers = numOfUsers(fileName);
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
        	int childVertex = getUnvisitedChildVertex(curVertex, numOfUsers, adjacencyMatrix, alreadyVisited);
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
        newSearchOption();
	}       
	
    private static int getUnvisitedChildVertex(int curVertex, int numOfUsers, int[][] adjacencyMatrix, Queue<Integer> alreadyVisited) {
    	for (int i = 0; i <= numOfUsers; i++) {    		
			if (adjacencyMatrix[curVertex][i] == 1 && !alreadyVisited.contains(i)) {
				return i;
			}
		}
		return -1;
	}

	private static void newSearchOption() throws IOException {		
		Scanner scanner = new Scanner( System.in );
		System.out.println("Would you like to do a new search? Please enter Yes or No");
        String reset = scanner.nextLine();
        if (reset.equals("Yes")) {
        	main(null);
        }
        else {
        	System.out.println("Goodbye!");
        	System.exit(0);
        }     		
	}

	private static int[][] uploadFile(String fileName) throws IOException {		
		int numOfUsers = numOfUsers(fileName) + 1;
		System.out.println("numOfUsers = "+numOfUsers);
		int[][] adjacencyMatrix = new int[numOfUsers+1][numOfUsers+1];
		@SuppressWarnings("resource")
		BufferedReader bufRead2 = new BufferedReader(new FileReader(fileName));
		while (bufRead2.ready()) {
			String currentLine = bufRead2.readLine();
			int firstUser = Integer.parseInt(currentLine.substring(0, currentLine.indexOf(" ")));
			int secondUser = Integer.parseInt(currentLine.substring(currentLine.lastIndexOf(" ") + 1));
			adjacencyMatrix[firstUser][secondUser] = 1;
			adjacencyMatrix[secondUser][firstUser] = 1;
		}
		return adjacencyMatrix;
	}

	private static int numOfUsers(String fileName) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader bufRead = new BufferedReader(new FileReader(fileName));
		String curLine = "";
		while (bufRead.ready()) {
			curLine = bufRead.readLine();
		}
		int numOfUsers = Integer.parseInt(curLine.substring(curLine.lastIndexOf(" ") + 1));
		return numOfUsers;
	}	
}

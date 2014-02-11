import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;


public class FacebookSearch {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException {   
		Scanner scanner = new Scanner( System.in );
		String fileName = "";
		System.out.println("Which file would you like to search?");
		fileName = scanner.nextLine();
		fileName = "/Users/Matt/Documents/Penn_2013-2014/Spring/NETS-150/HW1-FacebookNodes/src/"+fileName;
		int numOfUsers = VertexManipulation.numOfUsers(fileName) + 1;
		System.out.println("Would you like to do a DFS or BFS? Please enter either 'BFS' or 'DFS'");
        String searchType = scanner.nextLine();
        System.out.println("At which user would you like to start? Please enter an integer between 0 and "+(numOfUsers));
        int firstNode = Integer.parseInt(scanner.nextLine());
		int[][] adjacencyMatrix = VertexManipulation.uploadFileMatrix(fileName, numOfUsers);
		LinkedList[] adjacencyLines = VertexManipulation.uploadFileList(fileName, numOfUsers);
        if (searchType.equals("BFS")) {
        	System.out.println("*****Adjacency Matrix BFS*****");
        	long beforeMatrix = System.currentTimeMillis();
        	//third boolean var is true if a Matrix search and false if a List search
        	Bfs.bfs(adjacencyMatrix, adjacencyLines, true, fileName, numOfUsers, firstNode);
        	System.out.println("The Adjacency Matrix BFS took " + (System.currentTimeMillis()-beforeMatrix) 
        			+ " miliseconds.");
        	System.out.println("*****Adjacency List BFS*****");
        	long beforeList = System.currentTimeMillis();
        	//third boolean var is true if a Matrix search and false if a List search
        	Bfs.bfs(adjacencyMatrix, adjacencyLines, false, fileName, numOfUsers, firstNode);
        	System.out.println("The Adjacency List BFS took " + (System.currentTimeMillis()-beforeList) 
        			+ " miliseconds.");
        	System.out.println("*****End of Seearch*****");
        	VertexManipulation.newSearchOption();
        }	
        if (searchType.equals("DFS")) {
        	System.out.println("*****Adjacency Matrix DFS*****");
        	long beforeMatrix = System.currentTimeMillis();
        	Dfs.dfs(adjacencyMatrix, adjacencyLines, true, fileName, numOfUsers, firstNode);
        	System.out.println("The Adjacency Matrix DFS took "+ (System.currentTimeMillis()-beforeMatrix) 
        			+ " miliseconds.");
        	System.out.println("*****Adjacency List DFS*****");
        	long beforeList = System.currentTimeMillis();
        	Dfs.dfs(adjacencyMatrix, adjacencyLines, false, fileName, numOfUsers, firstNode);
        	System.out.println("The Adjacency Matrix DFS took "+ (System.currentTimeMillis()-beforeList) 
        			+ " miliseconds.");
        	System.out.println("*****End of Seearch*****");
        	VertexManipulation.newSearchOption(); 
        }
	}
}
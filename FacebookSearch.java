/**
 * FacebookSearch --- program to conduct DFS and BFS for nodes loaded into
 * both an adjacency matrix and an adjacency list.
 * @author    Matt Schulman
 */

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class FacebookSearch {
	/**
	   * conducts the BFS and DFS searches
	   * @param args
	   * @exception IOException
	   * @return none
	   */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException { 
		
		//IO commands to prompt user
		Scanner scanner = new Scanner( System.in );
		String fileName = "";
		System.out.println("Which file would you like to search?");
		fileName = scanner.nextLine();
		fileName = "/Users/Matt/Documents/Penn_2013-2014/Spring/NETS-150/HW1-FacebookNodes/src/"+fileName;
		
		//determine number of users assuming that users are listed in the txt file from least to greatest
		int numOfUsers = VertexManipulation.numOfUsers(fileName) + 1;
		System.out.println("Would you like to do a DFS or BFS? Please enter either 'BFS' or 'DFS'");
        String searchType = scanner.nextLine();
        System.out.println("At which user would you like to start? Please enter an integer between 0 and "
        		+(numOfUsers));
        int firstNode = Integer.parseInt(scanner.nextLine());
        
        //create an adjacency matrix
		int[][] adjacencyMatrix = VertexManipulation.uploadFileMatrix(fileName, numOfUsers);
		
		//create an adjacency list
		LinkedList[] adjacencyLines = VertexManipulation.uploadFileList(fileName, numOfUsers);
		
		//conduct a BFS for both the matrix and the list
        if (searchType.equals("BFS")) {
        	
        	//conduct a matrix bfs
        	System.out.println("*****Adjacency Matrix BFS*****");
        	long beforeMatrix = System.currentTimeMillis();
        	//third boolean var is true if a Matrix search and false if a List search
        	Bfs.bfs(adjacencyMatrix, adjacencyLines, true, fileName, numOfUsers, firstNode);
        	System.out.println("The Adjacency Matrix BFS took " + (System.currentTimeMillis()-beforeMatrix) 
        			+ " miliseconds.");
        	
        	//conduct a list bfs
        	System.out.println("*****Adjacency List BFS*****");
        	long beforeList = System.currentTimeMillis();
        	//third boolean var is true if a Matrix search and false if a List search
        	Bfs.bfs(adjacencyMatrix, adjacencyLines, false, fileName, numOfUsers, firstNode);
        	System.out.println("The Adjacency List BFS took " + (System.currentTimeMillis()-beforeList) 
        			+ " miliseconds.");
        	System.out.println("*****End of Seearch*****");
        	
        	//prompt user to do a new search if he/she desires
        	VertexManipulation.newSearchOption();
        }	
        
        if (searchType.equals("DFS")) {
        	
        	//conduct a matrix dfs
        	System.out.println("*****Adjacency Matrix DFS*****");
        	long beforeMatrix = System.currentTimeMillis();
        	Dfs.dfs(adjacencyMatrix, adjacencyLines, true, fileName, numOfUsers, firstNode);
        	System.out.println("The Adjacency Matrix DFS took "+ (System.currentTimeMillis()-beforeMatrix) 
        			+ " miliseconds.");
        	
        	//conduct a list dfs
        	System.out.println("*****Adjacency List DFS*****");
        	long beforeList = System.currentTimeMillis();
        	Dfs.dfs(adjacencyMatrix, adjacencyLines, false, fileName, numOfUsers, firstNode);
        	System.out.println("The Adjacency Matrix DFS took "+ (System.currentTimeMillis()-beforeList) 
        			+ " miliseconds.");
        	System.out.println("*****End of Seearch*****");
        	
        	//prompt user to do a new search if he/she desires
        	VertexManipulation.newSearchOption(); 
        }
	}
}
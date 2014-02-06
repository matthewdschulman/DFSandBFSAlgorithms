import java.io.IOException;
import java.util.Scanner;


public class FacebookSearch {
	public static void main(String[] args) throws IOException {   
		Scanner scanner = new Scanner( System.in );
		String fileName = "";
		System.out.println("Which file would you like to search?");
		fileName = scanner.nextLine();
		fileName = "/Users/Matt/Documents/Penn_2013-2014/Spring/NETS-150/HW1-FacebookNodes/src/"+fileName;
		int[][] adjacencyMatrix = VertexManipulation.uploadFile(fileName);
		System.out.println("Would you like to do a DFS or BFS? Please enter either 'BFS' or 'DFS'");
        String searchType = scanner.nextLine();
        if (searchType.equals("BFS")) {
        	Bfs.bfs(adjacencyMatrix, fileName);
        	VertexManipulation.newSearchOption(); 
        }	
        if (searchType.equals("DFS")) {
        	Dfs.dfs(adjacencyMatrix, fileName);
        	VertexManipulation.newSearchOption(); 
        }
	}
}
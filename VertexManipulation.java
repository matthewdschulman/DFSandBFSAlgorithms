import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;


public class VertexManipulation {
	
	public static void newSearchOption() throws IOException {		
		Scanner scanner = new Scanner( System.in );
		System.out.println("Would you like to do a new search? Please enter Yes or No");
        String reset = scanner.nextLine();
        if (reset.equals("Yes")) {
        	FacebookSearch.main(null);
        }
        else {
        	System.out.println("Goodbye!");
        	System.exit(0);
        }     		
	}

	public static int numOfUsers(String fileName) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader bufRead = new BufferedReader(new FileReader(fileName));
		String curLine = "";
		while (bufRead.ready()) {
			curLine = bufRead.readLine();
		}
		int numOfUsers = Integer.parseInt(curLine.substring(curLine.lastIndexOf(" ") + 1));
		return numOfUsers;
	}

	public static int[][] uploadFileMatrix(String fileName, int numOfUsers) throws IOException {
		long timeInitial = System.currentTimeMillis();
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
		System.out.println("It took " + (System.currentTimeMillis()-timeInitial)+ " miliseconds to"
				+ " upload the adjacency matrix.");
		return adjacencyMatrix;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static LinkedList[] uploadFileList(String fileName, int numOfUsers) throws IOException {
		long timeInitial = System.currentTimeMillis();
		//int[][] adjacencyList = new int[numOfUsers+1][numOfUsers+1];
		
		//create an array of lists. each list represents a row of the adjacency list
		LinkedList[] adjacencyLines = new LinkedList[numOfUsers];
		//set the array with empty linked lists in each row, each of which has a head value equal to its line #
		for (int i = 0; i < numOfUsers; i++) {
			LinkedList<Integer> curLine = new LinkedList<Integer>();
			curLine.add(i);
			adjacencyLines[i] = curLine;			
		}
		@SuppressWarnings("resource")
		BufferedReader bufRead2 = new BufferedReader(new FileReader(fileName));
		while (bufRead2.ready()) {
			String currentLine = bufRead2.readLine();
			int firstUser = Integer.parseInt(currentLine.substring(0, currentLine.indexOf(" ")));
			int secondUser = Integer.parseInt(currentLine.substring(currentLine.lastIndexOf(" ") + 1));
			adjacencyLines[firstUser].add(secondUser);
			adjacencyLines[secondUser].add(firstUser);
		}	
		System.out.println("It took " + (System.currentTimeMillis()-timeInitial)+ " miliseconds to"
				+ " upload the adjacency list.");
		return adjacencyLines;
	}
}


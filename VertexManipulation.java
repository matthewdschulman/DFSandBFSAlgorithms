import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;
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

	public static int[][] uploadFileMatrix(String fileName) throws IOException {		
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
	
	public static int getUnvisitedChildVertex(int curVertex, int numOfUsers, int[][] adjacencyMatrix, Queue<Integer> alreadyVisited) {
    	for (int i = 0; i <= numOfUsers; i++) {    		
			if (adjacencyMatrix[curVertex][i] == 1 && !alreadyVisited.contains(i)) {
				return i;
			}
		}
		return -1;
	}
}


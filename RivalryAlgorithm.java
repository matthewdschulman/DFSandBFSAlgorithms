import java.util.Scanner;

public class RivalryAlgorithm {

    public static void main(String[] args) {    	
    	//ask the user for the actors and rivalries and put them into arrays
    	String[] actorListArr = getListFromUser("Please type the list of all the actors separated by commas (i.e. A,B,C..etc.) : ");
    	String[] rivalryListArr = getListFromUser("Please type the list of all the relationships separated by &s and commas (i.e. 'A & B,C & D' means that A rivals B and C rivals D) : ");   	 	
    	System.out.println(actorListArr[1]);
    	System.out.println(rivalryListArr[1]);
    }

	private static String[] getListFromUser(String IOtext) {
		Scanner scanner = new Scanner( System.in );
        System.out.println(IOtext);
        String curList = scanner.nextLine();  
        curList = curList.replaceAll("&", ",");
        curList.toLowerCase();
        curList.trim();
        
    	//set the actors and rivalries into arrays
    	String[] actorListArr = curList.split(",");
    	for (int i = 0; i < actorListArr.length; i++) {
    		actorListArr[i] = actorListArr[i].trim();
    	}
    	return actorListArr;
	}
}
import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class SolutionDay1{
	
	public static void main(String[] args){
		
		String filePath = "src/main/resources/Day1/input.txt";
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		
		loadFileToLists(filePath, list1, list2);
		
		int totalDistanceBetweenLists =  getTotalDistanceBetweenLists(list1,list2);
		int similarityScore = getSimilarityScore(list1, list2);
		
		System.out.println("TotalDistanceBetweenLists is : " +  totalDistanceBetweenLists);
		System.out.println("TotalDistanceBetweenLists is : " +  similarityScore);
		
		
	}
	
	private static void loadFileToLists(String filePath, List<Integer> list1, List<Integer> list2){
		
        try {
            for (String line : Files.readAllLines(Paths.get(filePath))) {
				String[] temp = line.split("   ");
				list1.add(Integer.valueOf(temp[0]));
				list2.add(Integer.valueOf(temp[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private static int getTotalDistanceBetweenLists(List<Integer> list1, List<Integer> list2){
		
		int sum = 0;
		
		list1.sort(Comparator.naturalOrder());
		list2.sort(Comparator.naturalOrder());
		
		for(int i = 0; i<list1.size(); i++){
			sum += Math.abs(list1.get(i) - list2.get(i));
		}
		
		return sum;
		
	}
	
	private static int getSimilarityScore(List<Integer> list1, List<Integer> list2){
		
		int sum = 0;
		
		Map<Integer, Integer> occ = new HashMap<>();
		
		for(Integer i : list2) {
			occ.put(i, occ.getOrDefault(i, 0)+1);
		}
		
		for(Integer i : list1) {
			sum += i * occ.getOrDefault(i, 0);
		}
		
		
		return sum;
	}
	
	
}
import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class SolutionDay2GPT{
	
	public static void main(String[] args){
		
		String filePath = "src/main/resources/Day2/input.txt";
		
		int countSafeReports = getCountSafeReports(filePath, "isSafe");
		int countAlmostSafeReports = getCountSafeReports(filePath, "isAlmostSafe");
		
		System.out.println("Total number of safe reports is : " +  countSafeReports);
		System.out.println("Total number of almost safe reports is : " +  countAlmostSafeReports);
		
	}
	
	private static int getCountSafeReports(String filePath, String methodName){
		
		
		int count = 0;
		
        try {
            for (String line : Files.readAllLines(Paths.get(filePath))) {
				String[] temp = line.split(" ");
				List<Integer> report = new ArrayList<>();
				for(String s : temp){
					report.add(Integer.valueOf(s));
				}
				
				if(methodName.equals("isSafe")) {
					count = isSafe(report) ? count+1 : count;
				}
				else if (methodName.equals("isAlmostSafe")){
					count = isAlmostSafe(report) ? count+1 : count;
				}
				
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return count;
	}
	
	
	
	private static boolean isSafe(List<Integer> report){
		
		int n = report.size();
		int s = 0;
		
		
		while(s<n-1){
			int diff = Math.abs(report.get(s)-report.get(s+1));
			boolean canBeIncreasing = report.get(0)<=report.get(n-1);
			if((canBeIncreasing && report.get(s)>report.get(s+1)) || (!canBeIncreasing && report.get(s)<report.get(s+1)) || diff<1 || diff>3) 
				return false;
			s++;
		}

		
		return true;
	}
	
	private static boolean isAlmostSafe(List<Integer> report) {
	    int n = report.size();
	    
	    // First, check if the report is already safe (O(n))
	    if (isSafe(report)) {
	        return true;  // No need to remove any levels
	    }

	    // Try to find the "bad" level that can be removed
	    int badLevelCount = 0;  // To count the number of bad levels
	    int badLevelIndex = -1; // The index of the bad level (if we encounter one)

	    for (int i = 0; i < n - 1; i++) {
	        int diff = Math.abs(report.get(i) - report.get(i + 1));
	        boolean canBeIncreasing = report.get(0) <= report.get(n - 1);

	        // Check if the current pair is a "bad" level
	        if ((canBeIncreasing && report.get(i) > report.get(i + 1)) || 
	            (!canBeIncreasing && report.get(i) < report.get(i + 1)) || diff < 1 || diff > 3) {
	            
	            badLevelCount++;  // Increment bad level count
	            badLevelIndex = i; // Record the index of the bad level

	            // If more than one bad level is found, return false
	            if (badLevelCount > 1) {
	                return false;
	            }
	        }
	    }

	    
	    // If one bad level was found, try removing it and check if the report is safe
	    List<Integer> modifiedReport = new ArrayList<>(report);
	    modifiedReport.remove(badLevelIndex);  // Remove the bad level

	    // Check if the modified report is safe
	    return isSafe(modifiedReport);
	}

	
	
}
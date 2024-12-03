import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class SolutionDay2{
	
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
	
private static boolean isAlmostSafe(List<Integer> report){
		
		int n = report.size();
		int s = 0, e = n-1;
		int badLevels = 0;
		
		
		while(s<n-1){
			int diff = Math.abs(report.get(s)-report.get(s+1));
			boolean canBeIncreasing = report.get(0)<=report.get(n-1);
			if(badLevels > 0 && (canBeIncreasing && report.get(s)>report.get(s+1)) || (!canBeIncreasing && report.get(s)<report.get(s+1)) || diff<1 || diff>3) {
				System.out.println("CASE 1");
				return false;
			}
			
			if(diff<1 || (diff>3 && s==0)) badLevels++;
			if(diff>3 && s>0) return false;
				
			if((canBeIncreasing && report.get(s)>report.get(s+1))) {
				if(s>0) {
					System.out.println("CASE 2");
					diff = Math.abs(report.get(s-1)-report.get(s+1));
					if(diff<1 || diff>3) return false;
				}
				System.out.println("CASE 3");
				badLevels++;
			}
			else if(!canBeIncreasing && report.get(s)<report.get(s+1)) {
				if(s<n-2) {
					System.out.println("CASE 4");
					diff = Math.abs(report.get(s)-report.get(s+2));
					if(diff<1 || diff>3) return false;
				}
				System.out.println("CASE 5");
				badLevels++;
			}
			
			s++;
		}
		
		return true;
	}
	
	
}
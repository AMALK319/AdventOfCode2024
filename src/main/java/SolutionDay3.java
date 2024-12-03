import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class SolutionDay3{
	
	public static void main(String[] args){
		
		String filePath = "src/main/resources/Day3/input.txt";
		
		String memory = loadFileToString(filePath);
		String memory1 = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
		String memory2 = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
	
		int sumOfMulti = getSumOfMultiplications(memory);
		int sumOfEnabledMulti = getSumOfEnabledMultiplications(memory);
		
		System.out.println("Sum of multiplications is : " +  sumOfMulti);
		System.out.println("Sum of enabled multiplications is : " +  sumOfEnabledMulti);
		
		
	}
	
	private static String loadFileToString(String filePath){

		String memory = "";
		
        try {
        	memory = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        return memory;
	}
	
	private static int getSumOfMultiplications(String memory){
		
			int sum = 0, n = memory.length();

			for(int i = 0; i<n-4; i++) {
				
				
				if(memory.substring(i, i+4).equals("mul(")) {
					
					i += 4;
					
					int start = i;
				
					while(i<n && Character.isDigit(memory.charAt(i)) && i < start+3) 
						i++;
					int op1 = i > start ? Integer.parseInt(memory.substring(start, i)) : 0;
					
					if(i<n && memory.charAt(i) != ',') continue;

					start = ++i;
					
					while(i<n && Character.isDigit(memory.charAt(i)) && i < start+3) 
						i++;
					int op2 = i > start ? Integer.parseInt(memory.substring(start, i)) : 0;
									
					if(i<n && memory.charAt(i) != ')') continue;
					
					sum += op1*op2;
					
				}
			}
	
		return sum;
		
	}
	
	private static int getSumOfEnabledMultiplications(String memory){
		
		int sum = 0, n = memory.length();
		boolean enabled = true;

		for(int i = 0; i<n-4; i++) {
			
			if(enabled) {
				
				if(i<n-7 && memory.substring(i, i+7).equals("don't()")) {
					enabled = false;
					i += 6;
					continue;
				}
				if(memory.substring(i, i+4).equals("mul(")) {
					i += 4;
					
					int start = i;
				
					while(i<n && Character.isDigit(memory.charAt(i)) && i < start+3) 
						i++;
					int op1 = i > start ? Integer.parseInt(memory.substring(start, i)) : 0;
					
					if(i<n && memory.charAt(i) != ',') continue;

					start = ++i;
					
					while(i<n && Character.isDigit(memory.charAt(i)) && i < start+3) 
						i++;
					int op2 = i > start ? Integer.parseInt(memory.substring(start, i)) : 0;
									
					if(i<n && memory.charAt(i) != ')') continue;
					
					sum += op1*op2;
					
				}
			}
			else {
				
				if(memory.substring(i, i+4).equals("do()")) {
					enabled = true;
					i += 3;
				}
				
			}
			
			
		}

	return sum;
	
}
	
	
	
	
}
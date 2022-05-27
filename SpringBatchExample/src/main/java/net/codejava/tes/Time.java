package net.codejava.tes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Time {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println(Long.parseLong("1"));
		
		@SuppressWarnings("deprecation")
		String startDate="22-09-2021 10:10:30";
		Date endDate=new Date();
		
		 SimpleDateFormat sdf
         = new SimpleDateFormat(
             "dd-MM-yyyy HH:mm:ss");
		 Date start = sdf.parse(startDate);
		long differenceintime=start.getTime()-endDate.getTime();
		
		System.out.println(differenceintime);
		System.out.println( Math.abs((differenceintime
                   / (1000 * 60))
                  % 60));
		if(true)
		{
			if(false)
			{
				if(false)
				{
					System.out.println("yes");
				}
				else {
					System.out.println("No");
				}
			}
			else {
				System.out.println("if else if");
			}
		}
		else {
			if(false)
			{
				
			}
			else {
				
			
			System.out.println("Last");
			}
		}
		
		

	}

}

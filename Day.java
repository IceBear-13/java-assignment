public class Day implements Cloneable {
	
	private int year;
	private int month;
	private int day;
    private static final String MonthNames="JanFebMarAprMayJunJulAugSepOctNovDec";

	public void set(String sDay) //Set year,month,day based on a string like 01-Mar-2024
    {
        String[] sDayParts = sDay.split("-");
        this.day = Integer.parseInt(sDayParts[0]); //Apply Integer.parseInt for sDayParts[0];
        this.year = Integer.parseInt(sDayParts[2]);
        this.month = MonthNames.indexOf(sDayParts[1])/3+1;
    }


	//Constructor
	public Day(String sDay) {set(sDay);}
	
	
	public int compareTo(Day other){
		if (this.year != other.year) {
			return this.year - other.year;
		} else if (this.month != other.month) {
			return this.month - other.month;
		} else {
			return this.day - other.day;
		}
	}
	// check if a given year is a leap year
	static public boolean isLeapYear(int y) {
		if (y%400==0)
			return true;
		else if (y%100==0)
			return false;
		else if (y%4==0)
			return true;
		else
			return false;
	}

    
	
	// check if y,m,d valid
	static public boolean valid(int y, int m, int d) {
		if (m<1 || m>12 || d<1) return false;
		switch(m){
			case 1: case 3: case 5: case 7:
			case 8: case 10: case 12:
					 return d<=31; 
			case 4: case 6: case 9: case 11:
					 return d<=30; 
			case 2:
					 if (isLeapYear(y))
						 return d<=29; 
					 else
						 return d<=28; 
		}
		return false;
	}

	@Override
    public Day clone() {
		Day copy = null;
		try {
			copy = (Day) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return copy;
	}

	@Override
	public String toString()
    {
        return day+"-"+ MonthNames.substring((month-1)*3,month*3) + "-"+ year; // (month-1)*3,(month)*3
    } 

	public Day advance(int n) {
		day += n;
		while (!valid(year, month, day)) {
			if (day < 1) {
				month--;
				if (month == 0) {
					month = 12;
					year--;
				}
				day += daysInMonth(year, month);
			} else {
				if (day > daysInMonth(year, month)) {
					day -= daysInMonth(year, month);
					month++;
					if (month == 13) {
						month = 1;
						year++;
					}
				}
			}
		}
		return this;
	}

	private int daysInMonth(int year, int month) {
		switch (month) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				return 31;
			case 4: case 6: case 9: case 11:
				return 30;
			case 2:
				return isLeapYear(year) ? 29 : 28;
			default:
				throw new IllegalArgumentException("Invalid month: " + month);
		}
	}

	public static boolean isValidDateString(String sDay) {
		String[] sDayParts = sDay.split("-");
		if (sDayParts.length != 3) return false;
		
		try {
			int day = Integer.parseInt(sDayParts[0]);
			int year = Integer.parseInt(sDayParts[2]);
			int monthIndex = MonthNames.indexOf(sDayParts[1]);
			if (monthIndex == -1) return false;
			int month = monthIndex / 3 + 1;
			return valid(year, month, day);
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
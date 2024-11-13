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

	//Advance this day by n days
	public void advance(int n){
		day += n;
		while (!valid(year, month, day)){
			if (day<1){
				month--;
				if (month==0){
					month=12;
					year--;
				}
				day += 31;
				if (month==2){
					if (isLeapYear(year))
						day--;
					day -= 3;
				}
				if (month==4 || month==6 || month==9 || month==11)
					day--;
			}
			else {
				if (month==2){
					if (isLeapYear(year)){
						if (day>29){
							day -= 29;
							month++;
						}
					}
					else {
						if (day>28){
							day -= 28;
							month++;
						}
					}
				}
				else {
					if (day>30){
						day -= 30;
						month++;
					}
				}
				if (month==13){
					month = 1;
					year++;
				}
			}
		}
	}
}
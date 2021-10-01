package com.kyleokeeffe.lab2exercise3;

public class MethodOverloading {
	
	public static void main(String[] args) {
		System.out.printf("The following are output from three overloaded methods%n"
			+ "%s%n%s%n%s%n",returnNumber(3),returnNumber(4.34),returnNumber("five"));
	}
	
	public static String returnNumber(int number) {
		return String.format("The number is %d",number);
	}
	
	public static String returnNumber(double number) {
		return String.format("The number is %d",(int)Math.round(number));
	}
	
	public static String returnNumber(String number) {
		enum NumberStrings{ONE,TWO,THREE,FOUR,FIVE};
		String response = new String();
			try{
				response= String.format("The number is %d",NumberStrings.valueOf(number.toUpperCase()).ordinal()+1);
			}
			catch(Exception e) {
				response="The number is outside the range of this software.";
		}
			return response;
	}
}

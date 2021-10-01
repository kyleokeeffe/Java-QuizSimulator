package com.kyleokeeffe.lab2exercise2;

import java.util.*;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.lang.Math.*;

public class LottoDriver {
	private enum Status{CONTINUE,WON,LOST};
	public static void main(String[] args) {
		runGame();
	}
	
	public static void runGame() {
		Status gameStatus = Status.CONTINUE;
		int roundNumber=0;
		int userGuess = 0;
		boolean validInput=false;
		
		//validate input
		do {
			try {
				userGuess = Integer.parseInt(JOptionPane.showInputDialog(String.format("Welcome to the Lottery!%nPlease choose a number between 3 and 27:"),null));
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid entry. Please choose a number between 3 and 27.");
				validInput=false;
				continue;
			}
			validInput=true;
			
			if(userGuess>=2 && userGuess<=27)
				validInput=true;
			else {
				JOptionPane.showMessageDialog(null, "Invalid number choice. Your number must be between 3 and 27.");
				validInput=false;
			}
		}
		while(validInput==false);
	
		//run lotto
		do {
			roundNumber++;
			Lotto lottoRound = new Lotto();
			String roundPrintout = printLottoRound(lottoRound);
			int roundSum =0;
			for(int number:lottoRound.getLottoNumbers()) {roundSum+=number;};
			roundPrintout+=String.format("%n%s%n%10d%n%s%n%10d%n","Which means the number to guess for this round is:",roundSum,"Your guess: ",userGuess);
			Status roundResult = checkRoundResult(userGuess,roundSum);

			switch(roundResult) {
				case WON:
					gameStatus=Status.WON;
					roundPrintout += String.format("%nAmazing! You won!%nCongratulations!!");
					break;
				case LOST:
					if(roundNumber<=4) {
						roundPrintout+=String.format("%nSorry, you lost that round. Would you like to run the lotto again?");
						int reply = JOptionPane.showConfirmDialog(null,roundPrintout,null,JOptionPane.YES_NO_OPTION);
						if(reply == JOptionPane.YES_OPTION)
							gameStatus=Status.CONTINUE;
						else {
							roundPrintout = "No problem, better luck next time. Thanks for playing!";
							gameStatus=Status.LOST;
						}
					}
					else{
						roundPrintout += String.format("%nSorry, you lost that round.");
							gameStatus=Status.LOST;
					}
					break;
					default:
						roundPrintout=roundPrintout;
						break;
			}
			if(gameStatus!=Status.CONTINUE)
				JOptionPane.showMessageDialog(null, roundPrintout);
		}
		while(gameStatus == Status.CONTINUE && roundNumber<=5);
		
		String finalMessage;
		switch(gameStatus) {
			case WON:
				finalMessage = String.format("Congratulations on winning the lottery!%n");
				break;
			case LOST:
				finalMessage = String.format("How unfortunate - you didn't guess correctly this time!%n");
				break;
			default:
			finalMessage = null;
		}
		finalMessage+="Thanks for playing and don't forget to play again next time";
		JOptionPane.showMessageDialog(null, finalMessage);
		}
	
	public static String printLottoRound(Lotto lottoRound) {
		String output = String.format("The numbers drawn for this round are:%n");
		for(int number:lottoRound.getLottoNumbers()) {
			output+=String.format("%10d ",number);
		}
		return output;
	}
	
	public  void validateInput() {
	}
	
	public static Status checkRoundResult(int userGuess, int lottoRoundResult) {
		Status roundStatus;
		if(userGuess == lottoRoundResult)
			roundStatus = Status.WON;
		else
			roundStatus = Status.LOST;
		return roundStatus;
	}
}
	



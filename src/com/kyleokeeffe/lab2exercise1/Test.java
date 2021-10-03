package com.kyleokeeffe.lab2exercise1;

import java.security.SecureRandom;
import javax.swing.JOptionPane;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;

public class Test{
	private enum AnswerStatus{INCORRECT,CORRECT};
	private enum AnswerLetters{A,B,C,D};
	
	private Question[] questions;
	private ArrayList<Integer> questionsIndex;
	private ArrayList<Enum<?>> testResult;
	
	private class Question{
		private String question;
		private HashMap<String,Enum<?>> options;
		private ArrayList<Integer> optionsIndexArray;
		private ArrayList<String> questionOptionPrintOrder;
		private String questionPrinted;
		
		private Question(String question, HashMap<String,Enum<?>> options) {
			this.question=question;
			this.options = options;
		}
		
		private void setQuestion(String question) {
			this.question=question;
		}
		
		private String getQuestion() {
			return this.question;
		}
		
		private void setOptions (HashMap<String,Enum<?>> options) {
			this.options = options;
		}
		
		private HashMap<String,Enum<?>> getOptions(){
			return this.options;
		}
		
		private void setOptionsIndexArray(ArrayList<Integer> optionsIndexArray) {
			this.optionsIndexArray = optionsIndexArray;
		}
		
		private ArrayList<Integer> getOptionsIndexArray(){
			return this.optionsIndexArray;
		}
		
		private void setQuestionOptionPrintOrder(ArrayList<String> questionOptionPrintOrder){
			this.questionOptionPrintOrder = questionOptionPrintOrder;
		}
		
		private ArrayList<String> getQuestionOptionPrintOrder(){
			return this.questionOptionPrintOrder;
		}
		
		private void setQuestionPrinted(String questionPrinted) {
			this.questionPrinted = questionPrinted;
		}
		
		private void setQuestionPrinted(String questionPrinted, String appendString) {
				this.questionPrinted = questionPrinted.concat(appendString);
		}
		
		private String getQuestionPrinted() {
			return this.questionPrinted;
		}
	}
	
	public Test() {
		this.testResult=new ArrayList<Enum<?>>();
		this.questions= new Question[] {
				new Question(
						"What are the five phases of the Java execution cycle?",
						new HashMap<String,Enum<?>>(){
							private static final long serialVersionUID = 1L;
							{
								put("Edit, Compile, Load, Verify, Program",AnswerStatus.INCORRECT);
								put("Program, Compile, Load, Verify, Execute",AnswerStatus.INCORRECT);
								put("Program, Compile, Save, Verify, Execute",AnswerStatus.INCORRECT);
								put("Edit, Compile, Load, Verify, Execute",AnswerStatus.CORRECT);
							}
						}),
				new Question(
						"What is the purpose of the Verify stage in the Java execution cycle?",
						new HashMap<String,Enum<?>>(){
							private static final long serialVersionUID = 1L;
							{
								put("Validates bytecode and checks for security violations",AnswerStatus.CORRECT);
								put("Validates bytecode and check that program compiles",AnswerStatus.INCORRECT);
								put("Validates source code and checks for security violations",AnswerStatus.INCORRECT);
								put("Validates source code and checks that program compiles",AnswerStatus.INCORRECT);
							}
						}),
				new Question(
						"Why is it recommended to begin public get or set methods with the word 'Get' or 'Set' (ie. GetFirstName() or SetFirstName)?",
						new HashMap<String,Enum<?>>(){
							private static final long serialVersionUID = 1L;
							{
								put("Not starting the method name with 'Get' or 'Set' will result in a syntax error.",AnswerStatus.INCORRECT);
								put("Not starting the method name with 'Get' or 'Set' will result in a NotImplementedException.",AnswerStatus.INCORRECT);
								put("It is not recommended to start public get or set methods with the word 'Get' or 'Set' since a difference casing is preferred.",AnswerStatus.CORRECT);
								put("Starting the method name with 'Get' or 'Set' is recomended simply out of convention.",AnswerStatus.INCORRECT);
							}
						}),
				new Question(
						"Which of the following represents the format specifier for the type, 'int'?",
						new HashMap<String,Enum<?>>(){
							private static final long serialVersionUID = 1L;
							{
								put("%d",AnswerStatus.CORRECT);
								put("%i",AnswerStatus.INCORRECT);
								put("%I",AnswerStatus.INCORRECT);
								put("%D",AnswerStatus.INCORRECT);
							}
						}),
				new Question(
						"In order to use the method, Math.abs(), what import statement needs to be entered below the package name?",
						new HashMap<String,Enum<?>>(){
							private static final long serialVersionUID = 1L;
							{
								put("import java.Math.Abs",AnswerStatus.INCORRECT);
								put("import java.util.Math",AnswerStatus.INCORRECT);
								put("import java.Math.*",AnswerStatus.INCORRECT);
								put("Math Class Methods require no import statement",AnswerStatus.CORRECT);
							}
						})
				};
		this.questionsIndex = new ArrayList<Integer>();
		
		for(int qI = 0;qI<this.getQuestions().length;qI++)
			getQuestionsIndex().add(qI);
	}
	
	private void setQuestions(Question[] questions) {
		this.questions=questions;
	}
	
	private Question[] getQuestions() {
		return this.questions;
	}
	
	private void setQuestionsIndex(ArrayList<Integer> questionsIndex) {
		this.questionsIndex = questionsIndex;
	}
	
	private ArrayList<Integer> getQuestionsIndex() {
		return this.questionsIndex;
	}
		
	private void setTestResult(ArrayList<Enum<?>> testResult) {
		this.testResult = testResult;
	}
	
	private ArrayList<Enum<?>> getTestResult(){
		return this.testResult;
	}
	
	public Question simulateQuestion(int questionNumber) {
		SecureRandom randomizer = new SecureRandom();
		//get randomly chosen question
		int randomQuestionIndex = randomizer.nextInt(getQuestionsIndex().size());
		Question currentQuestion = this.getQuestions()[getQuestionsIndex().get(randomQuestionIndex)];
		
		//Create and fill arrays of index values representing each option in question: facilitating random printing of each option only once
		currentQuestion.setQuestionOptionPrintOrder(new ArrayList<String>());
		currentQuestion.setOptionsIndexArray(new ArrayList<Integer>());
		
		for(int oI = 0;oI<currentQuestion.getOptions().size();oI++)
			currentQuestion.getOptionsIndexArray().add(oI);
		//prompt user to answer current question
		currentQuestion.setQuestionPrinted(String.format("%d) %s%n",questionNumber,currentQuestion.getQuestion())); 
		
		for(int j = 0;j<currentQuestion.getOptions().size();j++) {
			//generate random number between 0 and size of remaining options index array
			int randomOptionIndex= randomizer.nextInt(currentQuestion.getOptionsIndexArray().size());
			//correlate random option choice from options index array to actual option HashMap object to generate string of random option key
			String thisOption= currentQuestion.getOptions().keySet().toArray()[currentQuestion.getOptionsIndexArray().get(randomOptionIndex)].toString();
			//add String representing random option to array for printing
			currentQuestion.getQuestionOptionPrintOrder().add(thisOption);
			
			currentQuestion.setQuestionPrinted(currentQuestion.getQuestionPrinted(),String.format("%s) %s%n", AnswerLetters.values()[j],thisOption.toString()));
			//remove index of randomly generated option from option index array to prevent repetition 
			currentQuestion.getOptionsIndexArray().remove(randomOptionIndex);
		}
		//remove index of randomly generate question from question index array to prevent repetition
		this.getQuestionsIndex().remove(randomQuestionIndex);
		return currentQuestion;
	}

	public void inputAnswer() {
		Scanner input = new Scanner(System.in);
		char inputtedAnswer;
		int questionNumber=1;
		boolean validInput=true;
		//loop through all the questions in the test
		for(Question question:this.getQuestions()) {
			Question currentQuestion=simulateQuestion(questionNumber++);
			//loop through the same question until valid input is given
				do{
					//save upper case of first character entered by user
					inputtedAnswer = JOptionPane.showInputDialog(currentQuestion.getQuestionPrinted()).toUpperCase().charAt(0);
					try {
						AnswerLetters.valueOf(Character.toString(inputtedAnswer));
						checkAnswer(currentQuestion,inputtedAnswer);
						//reinitizialize loop exit variable in case of continuing from catch block
						validInput=true;
					}
					catch(java.lang.IllegalArgumentException e) {
						JOptionPane.showMessageDialog(null, "Please enter a valid option");
						//change loop exit variable to repeat try block
						validInput=false;
						
					}
				}
				while(!validInput);
			}
		//determine score of test
			int resultsCorrect=Collections.frequency(getTestResult(), AnswerStatus.CORRECT);
			int resultsIncorrect=Collections.frequency(getTestResult(), AnswerStatus.INCORRECT);
			double score = (double)resultsCorrect/(double)(resultsCorrect+resultsIncorrect)*100;
		JOptionPane.showMessageDialog(null,String.format("You got %d out of %d correct, or %%%.0f",resultsCorrect,resultsCorrect+resultsIncorrect,score));
		}

	public void checkAnswer(Question currentQuestion, char answer) {
		//Corelate the selected printed option to the answer status of that option in the current question
		int inputtedAnswerIndex = AnswerLetters.valueOf(Character.toString(answer)).ordinal();
		String inputtedAnswer = currentQuestion.getQuestionOptionPrintOrder().get(inputtedAnswerIndex);
		AnswerStatus answerJudgement = (AnswerStatus)currentQuestion.getOptions().get(inputtedAnswer);
		//
		this.getTestResult().add(answerJudgement);
		generateMessage(answerJudgement);
	}
	
	public static void generateMessage(Enum<?> answerJudgement) {
		SecureRandom randomizer = new SecureRandom();
		String message= new String();
		//Define collections of messages
		String[] correctMessages= {
				"That's right! Nice one!",
				"You got it! Way to go!",
				"Correct! You're doing great!",
				"Very good! That's the right answer!"
		};
		String[] incorrectMessages= {
				"Not quite, but don't give up!!",
				"Sorry, that's not the right answer. Keep trying!",
				"Incorrect. Try once more.",
				"Not quite, but try again!"	
		};
		//determine bank of responses from which to choose random option
		switch(answerJudgement.ordinal()) {
			case 0:
				message = incorrectMessages[randomizer.nextInt(incorrectMessages.length)];
				break;
			case 1:
				message = correctMessages[randomizer.nextInt(correctMessages.length)];
				break;
			default:
				break;
		}
		JOptionPane.showMessageDialog(null,message);
	}
}

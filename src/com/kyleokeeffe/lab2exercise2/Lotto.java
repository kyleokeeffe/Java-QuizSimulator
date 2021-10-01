package com.kyleokeeffe.lab2exercise2;

import java.security.SecureRandom;

public class Lotto {
	private int[] lottoNumbers;
	public int[] getLottoNumbers() {
		return this.lottoNumbers;
	}
	
	public Lotto() {
		SecureRandom randomizer = new SecureRandom();
		int min=1;
		int max=9;
		int numberOfNumbers=3;
		
		this.lottoNumbers = new int[numberOfNumbers];
		for(int i = 0;i<lottoNumbers.length;i++) {
			this.lottoNumbers[i]=randomizer.nextInt((max - min) + 1) + min;
		}
	}
}

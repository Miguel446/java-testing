package br.com.az.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestService {

	public int sum(int x, int y) {
		return x + y;
	}

	public Object isNull() {
		return null;
	}

	public Object isNotNull() {
		return new Object();
	}

	public double ceiling(BigDecimal numerator, BigDecimal denominator) {
		return numerator.divide(denominator, RoundingMode.CEILING).doubleValue();
	}

	public double roundingEven(BigDecimal numerator, BigDecimal denominator) {
		return numerator.divide(denominator, RoundingMode.HALF_EVEN).doubleValue();
	}

	public boolean containsA(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == 'a' || word.charAt(i) == 'A') {
				return true;
			}
		}
		return false;
	}

	public int countA(String word) {
		int a = 0;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == 'a' || word.charAt(i) == 'A') {
				a++;
			}
		}
		return a;
	}

	public int[] createFbSequence() {
		int[] array = new int[8];
		for (int i = 0; i < array.length; i++) {
			switch (i) {
			case 0:
				array[i] = 0;
				break;
			case 1:
				array[i] = 1;
				break;
			default:
				array[i] = array[i - 1] + array[i - 2];
			}
		}
		return array;
	}

}

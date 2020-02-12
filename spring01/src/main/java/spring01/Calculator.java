package spring01;
import java.lang.*;

public class Calculator {

	public int setcal(char i, int a, int b) {
		int result = 0;
		switch(i) {
		case '+' : result = a+b; break;
		case '-' : result = a-b; break;
		case '%' : result = a%b; break;
		case '/' : result = a/b; break;
		case '*' : result = a*b; break;
		}
		return result;
	}
}

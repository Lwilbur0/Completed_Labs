import pkg.*;
import java.util.*;
import java.time.*;
import java.lang.*;

class main {
	public static void main(String args[]) {
		BaseClass yo = new BaseClass();
		String infix = "A+B*C";
		String postfix = yo.postfix(infix);
		System.out.println(postfix);
	}
}

import pkg.*;
import java.util.*;
import java.time.*;
import java.lang.*;
import java.security.cert.LDAPCertStoreParameters;

class main {
	public static void main(String args[]) {
		SinglyLinkedList yo = new SinglyLinkedList();
		for (int i = 0; i < 20; i++) {
			int num = (int)(Math.random() * 100);
			yo.insert(0, num);
		}
		for (int i = 0; i < 20; i++) {
			yo.insert(i, -1);
		}
		yo.printList();
		for (int i = 0, j = 39; i < 20; i++, j--) {
			yo.swap(i, j);
		}
		yo.printList();

	}
}

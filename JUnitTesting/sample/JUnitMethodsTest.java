package sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Hier werden die JUunit Tests durchführt
 */
class JUnitMethodsTest {

	private static JUnitMethods Testing;
	private static int arrSize;

	/**
	 * Neue Instanz
	 */
	@BeforeAll
	static void beforAll(){
		Testing = new JUnitMethods();
	}

	/**
	 * Gibt Array länge
	 */
	@BeforeEach
	void beforeEach(){
		arrSize = Testing.getStringArray().size();
	}
    @Test
    void addToList() {
		Testing.addToList("Hallo");
		Assertions.assertEquals(Testing.getStringArray().size(), arrSize + 1 );
		Testing.addToList("Test2");
		Assertions.assertNotEquals(Testing.getStringArray().size(), arrSize + 1 );
    }

    @Test
    void findInList() {
		Testing.addToList("Hallo");
		Assertions.assertEquals(0, Testing.findInList("Hallo"));
		Assertions.assertEquals(99, Testing.findInList("NotInArray"));
    }
}
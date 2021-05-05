import org.junit.Assert;
import org.junit.Test;

public class GreetTest {

	@Test
	public void testCase1() {
		String msg="Hello";
		
		Assert.assertEquals("Hello",msg);
	}

}

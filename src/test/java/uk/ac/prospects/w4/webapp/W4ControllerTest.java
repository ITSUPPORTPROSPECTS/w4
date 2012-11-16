package uk.ac.prospects.w4.webapp;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * @author vasileiosl
 *         Date: 15/11/12
 *         Time: 16:15
 */
public class W4ControllerTest {

	@Test
	public void testAnyPage(){
		W4Controller controller = new W4Controller();

		assertThat((String)controller.anyPage().getModel().get("msg"), CoreMatchers.equalTo("Any page"));
	}

}

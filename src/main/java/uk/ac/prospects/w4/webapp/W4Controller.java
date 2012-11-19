package uk.ac.prospects.w4.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author vasileiosl
 *         Date: 15/11/12
 *         Time: 11:12
 */
@Controller
public class W4Controller {

	@RequestMapping(value="/{page}.htm", method = RequestMethod.GET)
	public ModelAndView anyPage(@PathVariable String page){

		ModelAndView model = new ModelAndView(page);
		model.addObject("msg", "Any page");
		return model;
	}

}

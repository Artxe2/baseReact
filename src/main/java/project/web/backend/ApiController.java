package project.web.backend;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseController.class);
	@Autowired
	private ApiLogic apiLogic;

	@GetMapping("hello")
	public Object current_time(@RequestParam Map<String, Object> pMap) {
		logger.info("ApiController - hello");
		return "{\"message\": \"Hello Express!\"}";
	}

	@GetMapping("customers")
	public Object customers(@RequestParam Map<String, Object> pMap) {
		logger.info("ApiController - customers");
		try {
			return apiLogic.customers(pMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

package project.web.backend;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	@PostMapping("customers")
	public Object postCustomers(@RequestParam Map<String, Object> pMap, @RequestPart("image") MultipartFile file) {
		logger.info("ApiController - postCustomers");
		try {
			return apiLogic.postCustomers(pMap, file);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@GetMapping("customers")
	public Object getCustomers(@RequestParam Map<String, Object> pMap) {
		logger.info("ApiController - getCustomers");
		try {
			return apiLogic.getCustomers(pMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@DeleteMapping("customers/{id}")
	public Object deleteCustomers(@RequestParam Map<String, Object> pMap, @PathVariable("id") int id) {
		logger.info("ApiController - deleteCustomers");
		try {
			pMap.put("id", id);
			return apiLogic.deleteCustomers(pMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

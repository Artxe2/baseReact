package project.web.backend;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseController.class);

	@GetMapping("hello")
	public Object current_time(@RequestParam Map<String, Object> pMap) {
		logger.info("ApiController - hello");
		return "{\"message\": \"Hello Express!\"}";
	}

	@GetMapping("customers")
	public Object customers(@RequestParam Map<String, Object> pMap) {
		logger.info("ApiController - customers");
		return 
			"[" + 
			"  {" + 
			"    \"id\": 1," + 
			"    \"image\": \"https://placeimg.com/64/64/1\"," + 
			"    \"name\": \"나동빈\"," + 
			"    \"birthday\": \"961222\"," + 
			"    \"gender\": \"남자\"," + 
			"    \"job\": \"대학생\"" + 
			"  }," + 
			"  {" + 
			"    \"id\": 2," + 
			"    \"image\": \"https://placeimg.com/64/64/2\"," + 
			"    \"name\": \"홍진호\"," + 
			"    \"birthday\": \"222222\"," + 
			"    \"gender\": \"남자\"," + 
			"    \"job\": \"프로게이머\"" + 
			"  }," + 
			"  {" + 
			"    \"id\": 3," + 
			"    \"image\": \"https://placeimg.com/64/64/3\"," + 
			"    \"name\": \"이순신\"," + 
			"    \"birthday\": \"921205\"," + 
			"    \"gender\": \"남자\"," + 
			"    \"job\": \"디자이너\"" + 
			"  }," + 
			"  {" + 
			"    \"id\": 4," + 
			"    \"image\": \"https://placeimg.com/64/64/4\"," + 
			"    \"name\": \"홍길동\"," + 
			"    \"birthday\": \"960305\"," + 
			"    \"gender\": \"남자\"," + 
			"    \"job\": \"프로그래머\"" + 
			"  }" + 
			"]";
	}
}

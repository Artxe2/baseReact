package project.web.backend;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("base")
public class BaseController {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseController.class);
	@Autowired
	private BaseLogic baseLogic;

	@ResponseBody
	@GetMapping("currentTime")
	public Object currentTime(@RequestParam Map<String, Object> pMap) {
		logger.info("BaseController - currentTime");
		try {
			return baseLogic.currentTime(pMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("uploadFiles")
	public Object uploadFiles(@RequestParam Map<String, Object> pMap, @RequestPart("i_file") MultipartFile[] files) {
		logger.info("BaseController - uploadFiles");
		try {
			return baseLogic.uploadFiles(pMap, files);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

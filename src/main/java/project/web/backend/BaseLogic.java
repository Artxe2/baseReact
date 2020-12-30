package project.web.backend;

import java.io.IOException;
import java.util.Map;

import org.apache.mybatis.MybatisDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.spring.jpa.DualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BaseLogic {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseLogic.class);
	private static final String ROOTPATH = new java.io.File("src/main/resources/static/uploaded_files").getAbsolutePath();

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Autowired
	private DualRepository dualRepository;

	@Transactional
	public Object currentTime(Map<String, Object> pMap) {
		logger.info("BaseLogic - currentTime");
		Object mybatis = MybatisDao.selectObject(sqlSessionTemplate, "currentTime", pMap);
		Object jpa = dualRepository.currentTime().get(0).getCurrentTime();
		return mybatis + "<br/>" + jpa;
	}

	public Object uploadFiles(Map<String, Object> pMap, MultipartFile[] files) throws IOException {
		logger.info("BaseLogic - uploadFiles, files: {}", files.length);
		java.io.File saveFile;
		String filePath;
		int j = 0;
		for (MultipartFile f : files) {
			filePath = "base_files/" + f.getOriginalFilename();
			saveFile = new java.io.File(ROOTPATH, filePath);
			int i = 0;
			while (saveFile.exists()) {
				saveFile = new java.io.File(ROOTPATH, filePath.substring(0, filePath.lastIndexOf('.')) + ++i + '.' + filePath.substring(filePath.lastIndexOf('.') + 1));
			}
			if (i > 0) {
				filePath = filePath.substring(0, filePath.lastIndexOf('.')) + i + '.' + filePath.substring(filePath.lastIndexOf('.') + 1);
			}
			f.transferTo(saveFile);
			String name = f.getName() + j++;
			pMap.put(name, filePath);
			logger.info("{}: {} - {}", name, filePath, f.getSize());
		}
		return "redirect:/base/uploadFiles.jsp";
	}
}

package project.web.backend;

import java.util.Map;

import javax.transaction.Transactional;

import org.apache.mybatis.MybatisDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ApiLogic {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ApiLogic.class);
    private static final String ROOTPATH = new java.io.File("src/main/resources/static/uploaded_files").getAbsolutePath();
    
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Transactional
    public Object postCustomers(Map<String, Object> pMap, MultipartFile file) throws Exception {
        logger.info("ApiLogic - postCustomers");
        String filePath = "api_files/" + file.getOriginalFilename();
        java.io.File saveFile = new java.io.File(ROOTPATH, filePath);
        int i = 0;
        while (saveFile.exists()) {
            saveFile = new java.io.File(ROOTPATH, filePath.substring(0, filePath.lastIndexOf('.')) + ++i + '.' + filePath.substring(filePath.lastIndexOf('.') + 1));
        }
        if (i > 0) {
            filePath = filePath.substring(0, filePath.lastIndexOf('.')) + i + '.' + filePath.substring(filePath.lastIndexOf('.') + 1);
        }
        file.transferTo(saveFile);
        String name = file.getName();
        pMap.put(name, "http://localhost:3000/uploaded_files/" + filePath);
        logger.info(name + ": " + filePath + " - " + file.getSize() + "byte");
        return MybatisDao.insert(sqlSessionTemplate, "postCustomers", pMap);
    }

    public Object getCustomers(Map<String, Object> pMap) throws Exception {
        logger.info("ApiLogic - getCustomers");
        return MybatisDao.selectList(sqlSessionTemplate, "getCustomers", pMap);
    }

    @Transactional
    public Object deleteCustomers(Map<String, Object> pMap) throws Exception {
        logger.info("ApiLogic - deleteCustomers");
        return MybatisDao.update(sqlSessionTemplate, "deleteCustomers", pMap);
    }
}

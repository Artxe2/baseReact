package project.web.backend;

import java.util.List;
import java.util.Map;

import org.apache.mybatis.MybatisDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiLogic {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ApiLogic.class);
    private static final String ROOTPATH = new java.io.File("src/main/resources/static/uploaded_files").getAbsolutePath();

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    protected Object customers(Map<String, Object> pMap) throws Exception {
        logger.info("ApiLogic - customers");
        return MybatisDao.selectList(sqlSessionTemplate, "customers", pMap);
    }
}

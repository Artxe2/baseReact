package project.web.backend;

import java.util.Map;

import org.apache.mybatis.MybatisDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.spring.jpa.DualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardLogic {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BoardLogic.class);
    private static final String ROOTPATH = new java.io.File("src/main/resources/static/uploaded_files").getAbsolutePath();

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    private DualRepository dualRepository;

    protected Object current_time(Map<String, Object> pMap) throws Exception {
        logger.info("BoardLogic - current_time");
        return dualRepository.current_time().get(0).getCurrent_time();
    }

    @Transactional
    protected Object txCurrent_time(Map<String, Object> pMap) throws Exception {
        logger.info("BoardLogic - current_time");
        int test1 = 0;
        int test2 = 0;
        test1 = MybatisDao.insert(sqlSessionTemplate, "test1", pMap);
        System.out.println("test1: " + test1);
        test2 = MybatisDao.insert(sqlSessionTemplate, "test2", pMap);
        System.out.println("test2: " + test2);
        return MybatisDao.selectObject(sqlSessionTemplate, "current_time", pMap);
    }
}

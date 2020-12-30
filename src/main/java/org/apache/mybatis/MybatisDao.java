package org.apache.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;

public final class MybatisDao {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MybatisDao.class);

	private MybatisDao() {}

	public static List<Map<String, Object>> selectList(SqlSession sqlSessionTemplate, String sql,
			Map<String, Object> pMap) throws DataAccessException {
		logger.info("DAO [selectList - {}]", sql);
		return sqlSessionTemplate.selectList(sql, pMap);
	}

	public static Object selectObject(SqlSession sqlSessionTemplate, String sql, Map<String, Object> pMap)
			throws DataAccessException {
		logger.info("DAO [selectObject - {}]", sql);
		return sqlSessionTemplate.selectOne(sql, pMap);
	}

	public static Map<String, Object> selectMap(SqlSession sqlSessionTemplate, String sql, Map<String, Object> pMap)
			throws DataAccessException {
		logger.info("DAO [selectMap - {}]", sql);
		return sqlSessionTemplate.selectOne(sql, pMap);
	}

	public static int insert(SqlSession sqlSessionTemplate, String sql, Map<String, Object> pMap)
			throws DataAccessException {
		logger.info("DAO [insert - {}]", sql);
		return sqlSessionTemplate.insert(sql, pMap);
	}

	public static int update(SqlSession sqlSessionTemplate, String sql, Map<String, Object> pMap)
			throws DataAccessException {
		logger.info("DAO [update - {}]", sql);
		return sqlSessionTemplate.update(sql, pMap);
	}

	public static int delete(SqlSession sqlSessionTemplate, String sql, Map<String, Object> pMap)
			throws DataAccessException {
		logger.info("DAO [delete - {}]", sql);
		return sqlSessionTemplate.update(sql, pMap);
	}

	public static Map<String, Object> procedure(SqlSession sqlSessionTemplate, String sql, Map<String, Object> pMap)
			throws DataAccessException {
		logger.info("DAO [procedure - {}]", sql);
		sqlSessionTemplate.update(sql, pMap);
		return pMap;
	}

	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> openCursor(SqlSession sqlSessionTemplate, String sql,
			Map<String, Object> pMap) {
		logger.info("DAO [openCursor - {}]", sql);
		sqlSessionTemplate.update(sql, pMap);
		return (List<Map<String, Object>>) pMap.get(sql);
	}
}

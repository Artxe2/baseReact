package org.apache.mybatis;

public class MybatisMap extends java.util.HashMap<Object, Object> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Object put(Object key, Object value) {
        return super.put(key.toString().toLowerCase(), value);
    }
}

package indi.xezzon.school.common.util;

import org.hashids.Hashids;

/**
 * 对hashids工具进行封装
 *
 * @author xezzon
 */
public class HashidsUtil {
    public static String encode(long id, String salt) {
        Hashids hashids = new Hashids(salt);
        return hashids.encode(id);
    }
    
    public static long decode(String id, String salt) {
        Hashids hashids = new Hashids(salt);
        return hashids.decode(id)[0];
    }
}

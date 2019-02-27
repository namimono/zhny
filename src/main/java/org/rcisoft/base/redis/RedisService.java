package org.rcisoft.base.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Created by lcy on 18/1/8.
 */
@Service
@Slf4j
public class RedisService {

    @Autowired
    private JedisPool jedisPool;
    
    public Jedis getResource() {
        return jedisPool.getResource();
    }

    @SuppressWarnings("deprecation")
    public void returnResource(Jedis jedis) {
        if(jedis != null){
            jedisPool.returnResourceObject(jedis);
        }
    }

    public void set(String key, String value,int expire) {
        Jedis jedis=null;
        try{
            jedis = getResource();
            jedis.set(key, value);
            if (expire > 0)
                jedis.expire(key,expire);
            log.info("Redis set success - " + key + ", value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);
        }finally{
            returnResource(jedis);
        }
    }

    public void set(String key, String value) {
        Jedis jedis=null;
        try{
            jedis = getResource();
            jedis.set(key, value);
            log.info("Redis set success - " + key + ", value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);
        }finally{
            returnResource(jedis);
        }
    }

    public void setBytes(String key, byte[] value) {
        Jedis jedis=null;
        try{
            jedis = getResource();
            jedis.set(key.getBytes(), value);
            log.info("Redis set success - " + key + ", value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);
        }finally{
            returnResource(jedis);
        }
    }

    public void setBytes(String key, byte[] value,int expire) {
        Jedis jedis=null;
        try{
            jedis = getResource();
            jedis.set(key.getBytes(), value);
            if (expire > 0)
                jedis.expire(key,expire);
            log.info("Redis set success - " + key + ", value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);
        }finally{
            returnResource(jedis);
        }
    }
    
    public String get(String key) {
        String result = null;
        Jedis jedis=null;
        try{
            jedis = getResource();
            result = jedis.get(key);
            log.info("Redis get success - " + key + ", value:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + result);
        }finally{
            returnResource(jedis);
        }
        return result;
    }

    public byte[] getBytes(String key) {
        byte[] result = null;
        Jedis jedis=null;
        try{
            jedis = getResource();
            result = jedis.get(key.getBytes());
            log.info("Redis get success - " + key + ", value:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + result);
        }finally{
            returnResource(jedis);
        }
        return result;
    }

    public void remove(String key) {
        Jedis jedis=null;
        try{
            jedis = getResource();
            jedis.del(key);
            log.info("Redis del success - " + key );
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Redis set error: "+ e.getMessage() +" - " + key );
        }finally{
            returnResource(jedis);
        }

    }

    public List getList(String key) {
        Jedis jedis=null;
        List result = null ;
        try{
            jedis = getResource();
            result = jedis.lrange(key, 0, -1);
            log.info("Redis get list success - " + key + ", value:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Redis set error: "+ e.getMessage() +" - " + key );
        }finally{
            returnResource(jedis);
        }
        return result;
    }

    public void setList(String key, String value) {
        Jedis jedis=null;
        try{
            jedis = getResource();
            jedis.rpush(key, value);
            log.info("Redis set list success - " + key + ", value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);
        }finally{
            returnResource(jedis);
        }
    }

    public void removeList(String key,String value) {
        Jedis jedis=null;
        try{
            jedis = getResource();
            jedis.lrem(key,1,value);
            log.info("Redis remove list success - " + key + ", value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);
        }finally{
            returnResource(jedis);
        }
    }
}

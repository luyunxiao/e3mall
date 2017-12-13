package cn.e3mall.test;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {

	// 连接单机版 redis
	@Test
	public void testSingleJedisWithoutPool() {
		// 创建 jedis 对象，连接 redis 服务器
		Jedis jedis = new Jedis("192.168.25.18", 6379);
		// 向 redis 服务器设置值
		jedis.set("username", "刘西瓜");
		// 取值
		String username = jedis.get("username");
		System.out.println(username);
		jedis.close();
	}

	// 连接池 redis
	@Test
	public void testSingleJedisWithtPool() {
		// 创建连接池对象
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 设置最大空闲数
		jedisPoolConfig.setMaxIdle(2);
		// 设置最大连接数
		jedisPoolConfig.setMaxTotal(20);
		// 创建连接池jedisPool对象
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.25.18", 6379);
		// 从池子里面获取jedis对象
		Jedis jedis = jedisPool.getResource();
		// 向 redis 服务器设置值
		jedis.set("username", "霸刀");
		// 取值
		String username = jedis.get("username");
		System.out.println(username);
		jedis.close();
	}

	// 集群连接池 redis
	@Test
	public void testClusterJedisWithtPool() {
		// 创建连接池对象
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 设置最大空闲数
		jedisPoolConfig.setMaxIdle(2);
		// 设置最大连接数
		jedisPoolConfig.setMaxTotal(20);
		// 创建封装主机ip和端口nodes的set集合
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.25.18", 7001));
		nodes.add(new HostAndPort("192.168.25.18", 7002));
		nodes.add(new HostAndPort("192.168.25.18", 7003));
		nodes.add(new HostAndPort("192.168.25.18", 7004));
		nodes.add(new HostAndPort("192.168.25.18", 7005));
		nodes.add(new HostAndPort("192.168.25.18", 7006));
		nodes.add(new HostAndPort("192.168.25.18", 7007));
		nodes.add(new HostAndPort("192.168.25.18", 7008));
		// 创建连接集群jedisCluster对象
		JedisCluster jedisCluster = new JedisCluster(nodes, jedisPoolConfig);
		// 给redis集群设置值
		jedisCluster.set("address", "beijing");
		// 获取redis集群中值
		String address = jedisCluster.get("address");
		System.out.println(address);
		jedisCluster.close();
	}
}

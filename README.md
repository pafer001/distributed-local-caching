# distributed-local-caching:
一个分布式的本地缓存

## 存在的原因
在软件开发行业，数据的存储，一般分为持久化（mysql,pg,es,hbase）,分布式缓存（redis，Memcached），以及本地缓存（guava代表的）。而本地缓存,
是讲数据存储在每台服务器的内存中，这样常常设计到数据的更新的问题。而分布式本地缓存就是来解决这个问题
## 实现原理
![结构图](https://github.com/pafer001/distributed-local-caching/blob/master/doc/flow.png)

note:
* 生产者可能是消费者
* 消息是通过Fanout exchange实现广播的
* 数据的存储通过guava的cache实现的

## 例子

### 生产者：
 ``` java
    @Before
    public void initProductMessageClient() throws IOException{

        LocalCacheClientConfiguration configuration = new LocalCacheClientConfiguration();
        configuration.setAddress("128.0.0.1:5672");
        configuration.setvHost("/dev");
        configuration.setUser("user");
        configuration.setPwd("pwd");
        productMessageClient = new ProductMessageClient(configuration);
    }

    @Test
    public void testSendRemoveDataMessage() throws IOException {

        while (true) {
            productMessageClient.sendRemoveDataMessage("111");
        }
    }
 ```

### 消费者：
 ``` java

 @Before
    public void initProductMessageClient() throws IOException {

        LocalCacheClientConfiguration configuration = new LocalCacheClientConfiguration();
        configuration.setAddress("128.0.0.1:5672");
        configuration.setvHost("/dev");
        configuration.setUser("user");
        configuration.setPwd("pwd");
        configuration.setQueueName("lc-queue");
        configuration.setQosCount(5);

        consumerMessageClient = new ConsumerMessageClient(new LocalCacheConfiguration(), configuration);
    }

    @Test
    public void testConsumer() throws IOException {


        consumerMessageClient.consumer();

        try {
            Thread.sleep(10000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 ```




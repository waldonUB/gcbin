package cn.wdq.service.impl;

import cn.wdq.service.RequestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {
    private Logger logger = Logger.getLogger(RequestServiceImpl.class);
    @Autowired
    JdbcTemplate jdbcTemplate;
//    static {
//        System.out.println("进入定时任务");
//    }

//    @Scheduled(cron = "0 56 11 * * ?")
//    public static void requestTest(){
//        System.out.println("当前时间："+new Date());
//    }

//    @Scheduled(fixedRate = 500)
//    public void sysTest(){
//        System.out.println("真香警告");
//    }
//    @Scheduled(fixedRate = 20000)
//    public void postTest() throws IOException {
//        List<Map<String,Object>> list = jdbcTemplate.queryForList(" select * from sm_user");
//        logger.info(list);
//        List<Map<String,Object>> list1 = new ArrayList<>();
//        HttpClient client = HttpClients.createDefault();
//        System.out.println();
//        HttpPost request = new HttpPost("http://47.106.190.36:8080/gcbin/login_validate");
//        request.setHeader("Content-Type", "application/json");
//        JSONObject json = new JSONObject();
//        json.put("user_name", list.toString());
//        json.put("password", "1");
//        request.setEntity(new StringEntity(json.toJSONString()));
//        HttpResponse response = client.execute(request);
//        HttpEntity entity = response.getEntity();
//        String result = EntityUtils.toString(entity, "utf-8");
//        System.out.println(result);
//    }
}

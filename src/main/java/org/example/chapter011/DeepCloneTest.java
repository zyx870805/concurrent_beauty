package org.example.chapter011;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class DeepCloneTest {

    static Map<Integer, StrategyService> serviceMap = new HashMap<>();

    static {
        serviceMap.put(111, new StrategyOneService());
        serviceMap.put(222, new StrategyTwoService());
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Map<Integer, List<String>> appKeyMap = new HashMap<>();
        List<String> oneList = new ArrayList<>();
        oneList.add("device_id1");
        appKeyMap.put(111, oneList);

        List<String> twoList = new ArrayList<>();
        twoList.add("device_id2");
        appKeyMap.put(222, twoList);

        List<Msg> msgList = new ArrayList<>();
        Msg msg = new Msg();
        msg.setDataId("abc");
        msg.setBody("hello");
        msgList.add(msg);

        Iterator<Integer> iterator = appKeyMap.keySet().iterator();
        while(iterator.hasNext()) {
            Integer appkey = iterator.next();
            StrategyService strategyService = serviceMap.get(appkey);
            if (null != strategyService) {
                List<Msg> tmpList = new ArrayList<>();
                for (Msg m1 : msgList) {
                    tmpList.add((Msg) BeanUtils.cloneBean(m1));
                }
                strategyService.sendMsg(tmpList,appKeyMap.get(appkey));
            } else {
                System.out.println(String.format("appkey: %s, is not registerd service", appkey));
            }
        }
    }


}

interface StrategyService {
    void sendMsg(List<Msg> msgList, List<String> deviceIdList);
}

class StrategyOneService implements StrategyService {

    @Override
    public void sendMsg(List<Msg> msgList, List<String> deviceIdList) {
        for (Msg msg : msgList) {
            msg.setDataId("oneService_" + msg.getDataId());
            System.out.println(msg.getDataId() + " " + JSON.toJSONString(msg));
        }
    }
}

class StrategyTwoService implements StrategyService {

    @Override
    public void sendMsg(List<Msg> msgList, List<String> deviceIdList) {
        for (Msg msg : msgList) {
            msg.setDataId("twoService_" + msg.getDataId());
            System.out.println(msg.getDataId() + " " + JSON.toJSONString(msg));
        }
    }
}


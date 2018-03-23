package com.lqtest.casesuite;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hualala.grpc.executor.pool.GrpcPool;
import com.hualala.grpc.executor.pool.ServerConfig;
import com.hualala.grpc.grpc.HealthInterfaceData;
import com.hualala.grpc.grpc.HealthInterfaceGrpc;
import com.hualala.grpc.manager.GenTestCase;
import com.lqtest.common.entry.TblApi;
import com.lqtest.common.entry.TblReq;
import com.lqtest.common.entry.myenum.Active;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.grpc.ManagedChannel;

public class GenerateApi {

    public static Object genObj() {

        String INTERFACE_NAME = "com.hualala.grpc.grpc.HealthInterface";
        GenTestCase genTestCase = new GenTestCase();
        ServerConfig.SERVIER_URL = "localhost:6566";

        Class clazz = genTestCase.getServiceClass(INTERFACE_NAME);

        Method[] ms = genTestCase.getMethod(INTERFACE_NAME);
        Map<String, Object> listret = new HashMap<>();
        for (Method method : ms) {
            if (!GenTestCase.EXCLUDES.contains(method.getName())) {
//                System.out.println("AAAAAAAAAA   "+ genTestCase.genParameter(method).toString());
                if ("health".equalsIgnoreCase(method.getName())) {
                    Object param = genTestCase.genParameter(method);
                    return param;
                }
//                Map<String, Object> ret = genTestCase.genParameterDesc(method);
//                listret.put(method.getName(), ret);
//                TestCase testCase = new TestCaseCall();
//                testCase.setMethod(method);
//                testCase.setParameter(param);
//                testCase.setName(method.getName());
//                testCase.setServiceClass(clazz);
//                TestExeOne.getInstance().test(testCase);
            }
        }
        return null;
//        System.out.println(JSON.toJSON(listret));
//        analysisJson(JSON.toJSON(listret),0);
//        System.out.println(analysisJson(listret));
    }


    public static void main(String[] args) {
        try {
            String url = "dohko.permission.service.hualala.com";
            int port = 31664;
            ManagedChannel channel = GrpcPool.getChannel(url, port);
            try {
                HealthInterfaceGrpc.HealthInterfaceBlockingStub grpc = HealthInterfaceGrpc.newBlockingStub(channel);
                HealthInterfaceData.StatReq req = HealthInterfaceData.StatReq.getDefaultInstance();

                HealthInterfaceData.StatRes res = grpc.stat(req);
                System.out.println(res.toString());
            } finally {
                GrpcPool.returnObject(channel, url, port);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void createApi(String methodName, Map<String, Map<String, Object>> api) {

        TblApi tblApi = new TblApi();
        tblApi.setTitle(methodName);
        tblApi.setActive(Active.Activated);

        for (String paramName : api.keySet()) {
            TblReq tblReq = new TblReq();
            tblReq.setName(paramName);

            Map<String, Object> paramType = api.get(paramName);
        }
    }

    public static void analysisJson(Object objJson, Integer level) {
        //如果obj为json数组
        if (objJson instanceof JSONArray) {
            JSONArray objArray = (JSONArray) objJson;
            for (int i = 0; i < objArray.size(); i++) {
                analysisJson(objArray.get(i), level + 1);
            }
        }
        //如果为json对象
        else if (objJson instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) objJson;
            Iterator it = jsonObject.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next().toString();
                Object object = jsonObject.get(key);
                //如果得到的是数组
                if (object instanceof JSONArray) {
                    JSONArray objArray = (JSONArray) object;
                    analysisJson(objArray, level + 1);
                }
                //如果key中是一个json对象
                else if (object instanceof JSONObject) {
                    analysisJson((JSONObject) object, level + 1);

                }
                //如果key中是其他
                else {
                    System.out.println(getTabs(level) + "[" + key + "]:" + object.toString() + " ");
                }
            }
        }
    }

    public static String getTabs(Integer level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }

}

package com.lqtest.service;

import com.google.protobuf.Internal;

import com.alibaba.fastjson.JSONObject;
import com.hualala.grpc.HealthInterface;
import com.hualala.grpc.ProtoConverter;
import com.hualala.grpc.executor.pool.GrpcPool;
import com.hualala.grpc.grpc.HealthInterfaceData.StatRes;
import com.hualala.grpc.grpc.HealthInterfaceData.StatReq;
import com.hualala.grpc.grpc.HealthInterfaceGrpc;
import com.hualala.grpc.grpc.HealthInterfaceGrpc.HealthInterfaceBlockingStub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import io.grpc.ManagedChannel;

import static com.hualala.grpc.grpc.HealthInterfaceData.StatRes;

@Service
public class MonitorService {


    private static final Logger log = LoggerFactory.getLogger(MonitorService.class);

    public HealthInterface.StatRes statHealth(String url, Integer port) {

        HealthInterface.StatRes res = new HealthInterface.StatRes();
        try {
            ManagedChannel channel = com.hualala.grpc.executor.pool.GrpcPool.getChannel(url, port);
            try {
                HealthInterfaceBlockingStub grpc = HealthInterfaceGrpc.newBlockingStub(channel);
                StatReq req = StatReq.getDefaultInstance();

                StatRes statRes = grpc.stat(req);
                res =  ProtoConverter.messageToBean(statRes,HealthInterface.StatRes.class);
                return res;
            } finally {
                GrpcPool.returnObject(channel, url, port);
            }
        } catch (Exception ex) {
            log.info("exception: ", ex);
            return res;
        }

    }


    public static void main(String[] args) {
        MonitorService service = new MonitorService();
        HealthInterface.StatRes statRes = service.statHealth("dohko.permission.service.hualala.com", 31664);
        log.info(JSONObject.toJSONString("" + statRes));
        JSONObject res = new JSONObject();
//        res.put("statdata", statRes.getStatdataList());
//        res.put("services",statRes.getServicesList());
//        log.info(JSONObject.toJSONString(res));
//        for (HealthInterfaceData.Stat stat:list ) {
//            log.info(JSONObject.toJSONString("#######   " + stat.toString()));
//        }

    }
}

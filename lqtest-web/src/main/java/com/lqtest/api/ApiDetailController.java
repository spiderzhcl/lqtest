package com.lqtest.api;

import com.alibaba.fastjson.JSON;
import com.hualala.grpc.manager.DataType;
import com.hualala.grpc.manager.GenTestCase;
import com.lqtest.common.entry.ApiRepository;
import com.lqtest.common.entry.ReqRepository;
import com.lqtest.common.entry.TblApi;
import com.lqtest.common.entry.TblReq;
import com.lqtest.common.entry.myenum.Active;
import com.lqtest.common.entry.myenum.ENV;
import com.lqtest.common.entry.myenum.ReqType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/detail")
public class ApiDetailController {

    @Autowired
    private ApiRepository apiRepository;

    @Autowired
    private ReqRepository reqRepository;


    /**
     * @RequestParam String name
     * @RequestParam String email
     */
    @GetMapping(path = "/add") // Map ONLY GET Requests
    public @ResponseBody String addNewReq(@RequestParam String apiclass) {

        GenTestCase genTestCase = new GenTestCase();

        Method[] ms = genTestCase.getMethod(apiclass);
        Map<String, Map<String, Object>> listret = new HashMap<>();
        for (Method method : ms) {
            if (!GenTestCase.EXCLUDES.contains(method.getName())) {
                Map<String, Object> ret = genTestCase.genParameterDesc(method);
                TblApi tblApi = new TblApi();
                tblApi.setActive(Active.Activated);
                tblApi.setTitle(method.getName());
                tblApi.setDescription(apiclass);
                tblApi.setClassname(apiclass);
                tblApi.setEnv(ENV.dev);
                tblApi.setType(ReqType.HTTP_GET);
                tblApi.setMockUrl("http://dohko.hualala.com/api/" + apiclass);
                tblApi.setVersion(1);
                tblApi = apiRepository.save(tblApi);
                listret.put(method.getName(), ret);
                saveDetail(ret, tblApi.getApiId(), 0, 0);
            }
        }
        return JSON.toJSONString(listret);

    }

    public void saveDetail(Map<String, Object> detail, long apiId, long parentId, int level) {

        for (String pname : detail.keySet()) {
            TblReq tblReq = new TblReq();
            tblReq.setParentId(parentId);
            tblReq.setApiId(apiId);
            tblReq.setActive(Active.Activated);
            tblReq.setScopeDetail("");
            tblReq.setName(pname);
            tblReq.setLevel(level);

            Object dataType = detail.get(pname);
            if (dataType instanceof DataType) {
                tblReq.setType((DataType) dataType);
                tblReq.setScope((DataType) dataType);
                reqRepository.save(tblReq);
            } else if (dataType instanceof Object && dataType instanceof Map) {
                tblReq.setType(DataType.Object);
                tblReq.setScope(DataType.Object);
                tblReq = reqRepository.save(tblReq);
                saveDetail((Map<String, Object>) detail.get(pname), apiId, tblReq.getReqId(), level + 1);
            }
        }
    }
}

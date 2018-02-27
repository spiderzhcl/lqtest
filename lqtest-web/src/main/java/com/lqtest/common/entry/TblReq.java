package com.lqtest.common.entry;

import com.lqtest.common.entry.myenum.Active;
import com.lqtest.common.entry.myenum.DataType;
import com.lqtest.common.entry.myenum.ScopeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_api_req")
public class TblReq {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long reqId;

    Long apiId;
    Long parentId; //如果存在父类，则你类的类型为Object

    String  name; //变量名
    DataType type;   //类型
    ScopeType scope;  //范围
    String  scopeDetail; //范围详情
    Active active; //是否激活

}

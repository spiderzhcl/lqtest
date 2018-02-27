package com.lqtest.common.entry;

import com.lqtest.common.entry.myenum.Active;
import com.lqtest.common.entry.myenum.ENV;
import com.lqtest.common.entry.myenum.ReqType;

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
@Table(name = "tbl_api")
public class TblApi {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long apiId;

    private ENV env;
    private String  title;
    private ReqType type;
    private String  reqUrl;
    private String  mockUrl;
    private Active active;
    private Integer version;
    private String  description;



}

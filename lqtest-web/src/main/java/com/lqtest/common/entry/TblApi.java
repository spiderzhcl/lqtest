package com.lqtest.common.entry;

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

    private String apiName;

    private Integer env;

    private String mockUrl;

    private Integer enable;


}

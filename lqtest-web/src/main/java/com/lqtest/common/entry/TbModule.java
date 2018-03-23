package com.lqtest.common.entry;


import com.lqtest.common.entry.myenum.Protocol;

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
@Table(name = "tb_module")
public class TbModule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Protocol type;
    String name;
    String domain;
    String ipaddr;
    String location;
    String upstream1;
    String upstream2;
    String upstream3;
    String upstream4;

    public String getUpstream() {
        return upstream1 + "/" + upstream2 + "/" + upstream3;
    }

    public void setUpstream(String upstream1) {
        this.upstream1 = upstream1;
    }
}

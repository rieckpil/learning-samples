/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.items.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author Philip
 */
@Entity
@NamedQuery(name = "SELECT_ALL_PRODUCER", query = "SELECT p FROM Producer p")
@NamedQuery(name = "COUNT_ALL_PRODUCER", query = "SELECT count(p) FROM Producer p")
public class Producer implements Serializable {

    @Id
    private long id;
    
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}

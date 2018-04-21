/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.items.boundary;

import com.airhacks.items.entity.Item;
import com.airhacks.items.entity.Producer;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Startup
@Singleton
public class ItemsLoader {
    
    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void init() {
            
        Producer p1 = new Producer();
        p1.setId(1l);
        p1.setName("Rewe");
        
        Item i1 = new Item();
        i1.setAmount(10);
        i1.setName("Beer");
        i1.setProducer(p1);
        
        Producer p2 = new Producer();
        p2.setId(2l);
        p2.setName("Aldiiiiiii");
        
        Item i2 = new Item();
        i2.setAmount(55);
        i2.setName("Chicken");
        i2.setProducer(p2);
        
        this.em.merge(i1);
        this.em.merge(i2);
    }


}

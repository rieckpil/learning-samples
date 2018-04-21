package com.airhacks.trainers.control;

import com.airhacks.shop.boundary.ItemsStore;
import javax.inject.Inject;

public class TrainersCatalog {

    @Inject
    ItemsStore itemsStore;

    public String getAllTrainers() {
        return itemsStore.getItems().toUpperCase();
    }
}
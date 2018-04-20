package com.airhacks.trainers.control;

import javax.inject.Inject;

public class TrainersCatalog {

    @Inject
    ItemsStore itemsStore;

    public String getAllTrainers() {
        return itemsStore.getItems().toUpperCase();
    }
}
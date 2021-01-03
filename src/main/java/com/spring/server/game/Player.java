package com.spring.server.game;

import com.spring.server.game.entity.BusinessCardEntity;
import com.spring.server.game.entity.ProjectCardEntity;
import com.spring.server.game.exception.TurnNotPossibleException;

import java.util.HashMap;
import java.util.Map;

import static com.spring.server.game.entity.BusinessCardEntity.BAECKEREI;
import static com.spring.server.game.entity.BusinessCardEntity.WEIZENFELD;
import static com.spring.server.game.entity.ProjectCardEntity.*;

public class Player {

    private final String name;
    private final Map<ProjectCardEntity, Boolean> projects;
    private final Map<BusinessCardEntity, Integer> businesses;
    private int bank;

    public Player(String name) {
        this.name = name;
        this.bank = 3;
        this.projects = new HashMap<>();
        projects.put(BAHNHOF, false);
        projects.put(EINKAUFSZENTRUM, false);
        projects.put(FREIZEITPARK, false);
        projects.put(FUNKTURM, false);
        this.businesses = new HashMap<>();
        businesses.put(WEIZENFELD, 1);
        businesses.put(BAECKEREI, 1);
    }

    public String getName() {
        return name;
    }

    public int getBank() {
        return bank;
    }

    public void bookPurchase(int value) {
        if (bank - value < 0) {
            throw new TurnNotPossibleException(name + " got not enough Money.");
        }
        bank -= value;
    }

    public int steelMoney(int value) {
        bank -= value;
        if (bank < 0) {
            value += bank;
            bank = 0;
        }
        return value;
    }

    public boolean hasProject(ProjectCardEntity card) {
        return this.projects.get(card);
    }

    public Map<ProjectCardEntity, Boolean> getAllProjects() {
        return this.projects;
    }

    public int hasBusiness(BusinessCardEntity card) {
        return this.businesses.get(card);
    }

    public Map<BusinessCardEntity, Integer> getBusinesses() {
        return businesses;
    }
}

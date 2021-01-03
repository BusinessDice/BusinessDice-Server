package com.spring.server.game;

import com.spring.server.game.entity.BusinessCardEntity;
import com.spring.server.game.entity.ProjectCardEntity;
import com.spring.server.game.exception.CardNotAvailableException;

import java.util.HashMap;
import java.util.Map;

import static com.spring.server.game.entity.BusinessCardEntity.*;
import static com.spring.server.game.entity.CardBranch.*;
import static com.spring.server.game.entity.ProjectCardEntity.*;

public class Data {

    private final Map<ProjectCardEntity, ProjectCard> projectCards = new HashMap<>();
    private final Map<BusinessCardEntity, BusinessCard> businessCards = new HashMap<>();

    public Data() {
        projectCards.put(BAHNHOF,
                new ProjectCard(ProjectCardEntity.BAHNHOF, UNTERNEHMEN, 4, "Der Spieler darf in jedem Zug neu entscheiden, ob er 1 oder 2 Würfel verwendet."));
        projectCards.put(EINKAUFSZENTRUM,
                new ProjectCard(EINKAUFSZENTRUM, UNTERNEHMEN, 10, "Immer wenn der Spieler für ein Unternehmen mit einem der abgebildeten Symbole Einkommen erhält, bekommt er für jedes dieser gewürfelten Unternehmen 1 Münze mehr. Hat er z.B. 2 Bäckereien, erhält er 4 statt 2 Münzen. Hat er z.B. 1 Familien-Restaurant, erhält er 3 statt 2 Münzen."));
        projectCards.put(FREIZEITPARK,
                new ProjectCard(FREIZEITPARK, UNTERNEHMEN, 16, "Nur wenn der Spieler seinen Bahnhof gebaut hat, darf er auch mit 2 Würfeln würfeln. Er kann dadurch 2 gleiche Zahlen erhalten (Pasch). Der Spieler hat in diesem Fall einen vollständigen zweiten Spielzug. Er kann jetzt wieder wählen, ob er 1 oder 2 Würfel verwendet, erhält evtl. Einkommen und darf 1 Unternehmen oder 1 Großprojekt bauen. Würfelt er in diesem zweiten Zug wieder einen Pasch, gilt das jedoch nicht erneut."));
        projectCards.put(FUNKTURM,
                new ProjectCard(FUNKTURM, UNTERNEHMEN, 22, "Wenn der Spieler erneut würfelt, muss er die gleiche Anzahl Würfel verwenden, wie in seinem ersten Wurf. Es gilt dann nur das Ergebnis dieses zweiten Wurfs."));

        businessCards.put(WEIZENFELD,
                new BusinessCard(WEIZENFELD, LANDWIRTSCHAFT_PFLANZEN, 1, 1, "Erhalte 1 Münze aus der Bank."));
        businessCards.put(BAUERNHOF,
                new BusinessCard(BAUERNHOF, LANDWIRTSCHAFT_TIERE, 1, 2, "Erhalte 1 Münze aus der Bank."));
        businessCards.put(BAECKEREI,
                new BusinessCard(BAECKEREI, INDUSTRIE_PRODUKTION, 1, 2, 3, "Erhalte 1 Münze aus der Bank."));
        businessCards.put(CAFE,
                new BusinessCard(CAFE, RESTAURANTS, 2, 3, "Erhalte 1 Münze von dem Mitspieler, der eine '3' gewürfelt hat."));
        businessCards.put(MINI_MARKT,
                new BusinessCard(MINI_MARKT, INDUSTRIE_PRODUKTION, 2, 4, "Erhalte 3 Münzen aus der Bank."));
        businessCards.put(WALD,
                new BusinessCard(WALD, LANDWIRTSCHAFT_ROHSTOFFE, 3, 5, "Erhalte 1 Münze aus der Bank."));
        businessCards.put(STADION,
                new BusinessCard(STADION, UNTERNEHMEN, 6, 6, "Erhalte von jedem Mitspieler 2 Münzen."));
        businessCards.put(FERNSEHSENDER,
                new BusinessCard(FERNSEHSENDER, UNTERNEHMEN, 7, 6, "Erhalte von einem Mitspieler deiner Wahl 5 Münzen."));
        businessCards.put(BUEROHAUS,
                new BusinessCard(BUEROHAUS, UNTERNEHMEN, 8, 6, "Tausche 1 Karte mit einem Mitspieler deiner Wahl. Keine '" + UNTERNEHMEN + "' Karte."));
        businessCards.put(MOLKEREI,
                new BusinessCard(MOLKEREI, INDUSTRIE_VERKAUF, 5, 7, "Erhalte 3 Münzen aus der Bank für jedes deiner '" + LANDWIRTSCHAFT_TIERE + "' Karten."));
        businessCards.put(MOEBELFABRIK,
                new BusinessCard(MOEBELFABRIK, INDUSTRIE_VERKAUF, 3, 8, "Erhalte 3 Münzen aus der Bank für jedes deiner '" + LANDWIRTSCHAFT_ROHSTOFFE + "' Karten."));
        businessCards.put(BERGWERK,
                new BusinessCard(BERGWERK, LANDWIRTSCHAFT_ROHSTOFFE, 6, 9, "Erhalte 5 Münzen aus der Bank."));
        businessCards.put(FAMILIENRESTAURANT,
                new BusinessCard(FAMILIENRESTAURANT, RESTAURANTS, 3, 9, 10, "Erhalte 2 Münzen von dem Mitspieler, der eine '9' oder '10' gewürfelt hat."));
        businessCards.put(APFELPLANTAGE,
                new BusinessCard(APFELPLANTAGE, LANDWIRTSCHAFT_PFLANZEN, 3, 10, "Erhalte 3 Münzen aus der Bank."));
        businessCards.put(MARKTHALLE,
                new BusinessCard(MARKTHALLE, INDUSTRIE_VERKAUF, 2, 11, 12, "Erhalte 3 Münzen aus der Bank für jedes deiner '" + LANDWIRTSCHAFT_PFLANZEN + "' Karten."));
    }

    public ProjectCard getProjectCard(ProjectCardEntity card) throws CardNotAvailableException {
        if (projectCards.containsKey(card)) {
            return projectCards.get(card);
        } else {
            throw new CardNotAvailableException(card, "Not found in Data.");
        }
    }

    public Map<ProjectCardEntity, ProjectCard> getAllProjectCards() {
        return projectCards;
    }

    public BusinessCard getBusinessCard(BusinessCardEntity card) throws CardNotAvailableException {
        if (businessCards.containsKey(card)) {
            return businessCards.get(card);
        } else {
            throw new CardNotAvailableException(card, "Not found in Data.");
        }
    }

    public Map<BusinessCardEntity, BusinessCard> getAllBusinessCards() {
        return businessCards;
    }
}

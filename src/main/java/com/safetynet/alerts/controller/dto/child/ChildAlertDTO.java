package com.safetynet.alerts.controller.dto.child;

import java.util.List;

/**
 * Data Transfer Object (DTO) représentant la réponse
 * de l'endpoint d'alertes des enfants (child alert).
 *
 * <p>Ce DTO contient la liste des enfants vivant à une adresse donnée,
 * ainsi que les informations associées à chaque enfant.</p>
 */
public class ChildAlertDTO {

    /**
     * Liste des enfants vivant à l'adresse recherchée.
     */
    private List<ChildDTO> children;

    /**
     * Constructeur par défaut.
     */
    public ChildAlertDTO() {
    }

    /**
     * Constructeur avec initialisation de la liste des enfants.
     *
     * @param children liste des {@link ChildDTO}
     */
    public ChildAlertDTO(List<ChildDTO> children) {
        this.children = children;
    }

    /**
     * Retourne la liste des enfants.
     *
     * @return liste de {@link ChildDTO}
     */
    public List<ChildDTO> getChildren() {
        return children;
    }

    /**
     * Définit la liste des enfants.
     *
     * @param children liste de {@link ChildDTO}
     */
    public void setChildren(List<ChildDTO> children) {
        this.children = children;
    }
}

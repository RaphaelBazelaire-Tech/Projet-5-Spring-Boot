package com.safetynet.alerts.controller.dto.child;

import java.util.List;

public class ChildAlertDTO {

    private List<ChildDTO> children;

    public ChildAlertDTO() {
    }

    public ChildAlertDTO(List<ChildDTO> children) {
        this.children = children;
    }

    public List<ChildDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ChildDTO> children) {
        this.children = children;
    }
}

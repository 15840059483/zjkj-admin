package com.jeebase.system.security.dto;

import java.util.List;

/**
 * @author fyy
 */
public class UpdateDataPermission {

    private Long userId;

    private List<Long> addDataPermissions;

    private List<Integer> removeDataPermissions;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getAddDataPermissions() {
        return addDataPermissions;
    }

    public void setAddDataPermissions(List<Long> addDataPermissions) {
        this.addDataPermissions = addDataPermissions;
    }

    public List<Integer> getRemoveDataPermissions() {
        return removeDataPermissions;
    }

    public void setRemoveDataPermissions(List<Integer> removeDataPermissions) {
        this.removeDataPermissions = removeDataPermissions;
    }
}

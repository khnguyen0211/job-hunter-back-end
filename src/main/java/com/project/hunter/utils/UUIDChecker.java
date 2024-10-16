package com.project.hunter.utils;

import java.util.UUID;

public class UUIDChecker {

    public static boolean isValidUUID(String uuid) {
        try {
            return UUID.fromString(uuid) != null;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

package fr.papergx.papermod.sql.enums;

public enum REQUEST_TYPE {
    BAN("bans"), TEMPBAN("tempbans");

    private final String name;

    REQUEST_TYPE(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


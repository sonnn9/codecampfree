package com.son.oop.immutablility;

public class ImmutableUser {
    private final String id;
    private final String name;
    private ImmutableUser(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String id() {
        return id;
    }
    public String name() {
        return name;
    }

    public ImmutableUser withName(String newName) {
        return new ImmutableUser(this.id, newName);
    }

    public static class Builder {
        private String id;
        private String name;
        public Builder id(String id) {
            this.id = id;
            return this;
        }
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public ImmutableUser build() {
            if (id == null || name == null) {
                throw new IllegalStateException("id and name cannot be null");
            }
            return new ImmutableUser(id, name);
        }
    }
}

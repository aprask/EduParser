package io.apraskal.model;

import java.nio.file.Path;

public class Origin {
    private final String os;
    private final Path fullPath;

    private Origin(OriginBuilder builder) {
        this.os = builder.os;
        this.fullPath = builder.fullPath.toAbsolutePath().normalize();
    }

    public String getOs() {
        return os;
    }

    public Path getFullPath() {
        return fullPath;
    }

    public String getInitArg() {
        return fullPath.getFileName().toString();
    }

    public String[] getKArgs() {
        int nameCount = fullPath.getNameCount();
        String[] parts = new String[Math.max(0, nameCount - 1)];
        for (int i = 0; i < parts.length; i++) parts[i] = fullPath.getName(i).toString();
        return parts;
    }

    public static class OriginBuilder {
        private String os;
        private Path fullPath;

        public OriginBuilder setOs(String os) {
            this.os = os;
            return this;
        }

        public OriginBuilder setFullPath(Path fullPath) {
            this.fullPath = fullPath;
            return this;
        }

        public Origin build() {
            return new Origin(this);
        }
    }
}

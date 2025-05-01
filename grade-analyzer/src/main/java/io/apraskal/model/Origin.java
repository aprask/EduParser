package io.apraskal.model;

public class Origin {
    private String os;
    private String initArg;
    private String[] kArgs;

    private Origin(OriginBuilder builder) {
        this.os = builder.os;
        this.initArg = builder.initArg;
        this.kArgs = builder.kArgs;
    }

    public String getOs() {
        return os;
    }

    public String getInitArg() {
        return initArg;
    }

    public String[] getKArgs() {
        return kArgs;
    }

    public static class OriginBuilder {
        private String os;
        private String initArg;
        private String[] kArgs;

        public OriginBuilder setOs(String os) {
            this.os = os;
            return this;
        }

        public OriginBuilder setInitArg(String initArg) {
            this.initArg = initArg;
            return this;
        }

        public OriginBuilder setKArgs(String[] kArgs) {
            this.kArgs = kArgs;
            return this;
        }

        public Origin build() {
            return new Origin(this);
        }
    }
}
package model.enums;

public enum Marca {
    VW, GM, Fiat, Honda;
    public static Marca fromDb(String v) {
        if (v == null) return null;
        String s = v.trim().toUpperCase();
        switch (s) {
            case "VW":
            case "VOLKSWAGEN":
                return VW;
            case "GM":
            case "CHEVROLET":
            case "GENERAL MOTORS":
                return GM;
            case "HONDA":
                return Honda;
            case "FIAT":
                return Fiat;
            default:
                throw new IllegalArgumentException("Marca desconhecida: " + v);
        }
    }
}

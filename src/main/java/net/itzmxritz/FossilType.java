package net.itzmxritz;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum FossilType {

    CLAW_FOSSIL("the fossil i want lived underground and dug tunnels"),
    CLUBBED_FOSSIL("the fossil i want had a really fancy tail"),
    FOOTPRINT_FOSSIL("the fossil i want was the king of his kind"),
    HELIX_FOSSIL("the fossil i want lived underwater"),
    SPINE_FOSSIL("the fossil i want was kinda spiny u know"),
    TUSK_FOSSIL("the fossil i want lived in herds and was quite woolly"),
    UGLY_FOSSIL("the fossil i want is pretty rough to look at"),
    WEBBED_FOSSIL("the fossil i want had a really pointy beak");

    FossilType(String hintMessage) {
        this.hintMessage = hintMessage;
    }

    private final String hintMessage;

    public static FossilType getByHintMessage(String hintMessage) {
        for (FossilType fossilType : values()) {
            if (fossilType.getHintMessage().equals(hintMessage)) {
                return fossilType;
            }
        }

        return null;
    }

    public String getCuteName() {
        return Arrays.stream(name().split("_"))
                .map(word -> word.charAt(0) + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    public String getHintMessage() {
        return hintMessage;
    }
}

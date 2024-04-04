package ex2;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)

public enum TipTobe {
    ELECTRONICE,
    ACUSTICE
}

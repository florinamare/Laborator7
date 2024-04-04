package ex2;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)

public enum TipChitara {
    ELECTRICA,
    ACUSTICA,
    CLASICA
}

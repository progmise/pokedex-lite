package com.certant.app.utils;

import com.google.gson.annotations.SerializedName;

public enum SearchOption {
    @SerializedName("nombreTiposNivel") NOMBRE_TIPOS_NIVEL (0),
    @SerializedName("habilidadesEvoluciones") HABILIDADES_EVOLUCIONES (1),
    @SerializedName("evolucionesInformacion") EVOLUCIONES_INFORMACION (2);
    
    private final Integer value;
    
    public Integer getValue() {
        return value;
    }

    SearchOption(Integer value) {
        this.value = value;
    }
}
package br.com.tsantana.issdistancefromme.client.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IssGeolocation(@JsonProperty("iss_position") IssPosition position) {

    public record IssPosition(String latitude, String longitude) {

    }
}

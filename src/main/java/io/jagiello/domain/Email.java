package io.jagiello.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class Email {
    private String from;
    private Set<String> to;
    private String title;
    private String body;
}

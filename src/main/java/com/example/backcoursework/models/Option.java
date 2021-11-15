package com.example.backcoursework.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "options", schema = "public")
public class Option {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "description")
    private String description;
    @Column(name = "survey_id")
    private Integer surveyId;
    @Column(name = "votes")
    private Integer votes;
}

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
@Table(name = "answers", schema = "public")
public class Answer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "survey_id")
    private Integer surveyId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "option_id")
    private Integer optionId;
}

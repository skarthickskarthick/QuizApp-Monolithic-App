package com.karthick.QuizApp;

import lombok.Data;

@Data
public class QuestionWrapper {
    private Integer id;
    private String questiontitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public QuestionWrapper(Integer id, String questiontitle, String option1, String option2, String option4, String option3) {
        this.id = id;
        this.questiontitle = questiontitle;
        this.option1 = option1;
        this.option2 = option2;
        this.option4 = option4;
        this.option3 = option3;
    }
}

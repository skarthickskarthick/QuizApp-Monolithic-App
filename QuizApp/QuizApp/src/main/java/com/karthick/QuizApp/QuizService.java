package com.karthick.QuizApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Questions> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);


    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Optional<Quiz> quiz=quizDao.findById(id);
        List<Questions> questionsFromDB=quiz.get().getQuestions();
List<QuestionWrapper> questionsForUSer =new ArrayList<>();
for(Questions q: questionsFromDB)
{QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestiontitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
questionsForUSer.add(qw);
}return new ResponseEntity<>(questionsForUSer,HttpStatus.OK);

}

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
Quiz quiz=quizDao.findById(id).get();
List<Questions> questions=quiz.getQuestions();
int right=0;
int i=0;
for(Response response:responses){
if(response.getResponse().equals(questions.get(i).getRightanswer()))
right++;
i++;
}
return new ResponseEntity<>(right,HttpStatus.OK);
}
}
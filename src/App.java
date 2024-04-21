import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.lang.Thread;
public class App {
    public static void main(String[] args) {
        System.out.println("******* QUIZ APPLICATION *******");
        System.out.println("Below are 5 questions which need to be answered withing 20 seconds each.");
        System.out.println("The quiz starts now !!\n");
        try {
            Thread.sleep(2000); // delay for 2 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Question> questions = Arrays.asList(
            new Question("What does WWW stands for?", "World wide web", Duration.ofSeconds(20)),
            new Question("Which data structure uses LIFO?", "Stack", Duration.ofSeconds(20)),
            new Question("A do-while and a while loop are the same. State true or false.", "False", Duration.ofSeconds(20)),
            new Question("How many bits is a byte equal to?", "8", Duration.ofSeconds(20)),
            new Question("Which programming language is used for Android development?", "Java", Duration.ofSeconds(20))
        );

        Scanner scanner = new Scanner(System.in);
        int score = 0;
        int questionNumber = 1;

        for (Question question : questions) {
            System.out.println("Question " + questionNumber + ": " + question.getText());
            Instant startTime = Instant.now();
            String answer = scanner.nextLine();
            Instant endTime = Instant.now();

            if (Duration.between(startTime, endTime).compareTo(question.getTimeLimit()) <= 0 ) {
                if(question.isCorrect(answer)){
                System.out.println("Your answer is correct.");
                score++;
                }
                else{
                    System.out.println("Your exceeded the timelimit.");
                }
            }

            System.out.println("Your answer: " + answer);
            System.out.println("Correct answer: " + question.getCorrectAnswer());
            System.out.println();
            questionNumber++;
        }

        System.out.println("Your final score: " + score + " out of " + questions.size());
        scanner.close();
    }

    private static class Question {
        private String text;
        private String correctAnswer;
        private Duration timeLimit;

        public Question(String text, String correctAnswer, Duration timeLimit) {
            this.text = text;
            this.correctAnswer = correctAnswer;
            this.timeLimit = timeLimit;
        }

        public boolean isCorrect(String answer) {
            return this.correctAnswer.equalsIgnoreCase(answer);
        }

        public String getText() {
            return text;
        }

        public Duration getTimeLimit() {
            return timeLimit;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }
    }
}
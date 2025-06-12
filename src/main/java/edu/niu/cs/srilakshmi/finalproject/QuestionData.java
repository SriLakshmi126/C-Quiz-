


package edu.niu.cs.srilakshmi.finalproject;

import java.util.*;

// This class provides a set of questions based on the selected quiz number
public class QuestionData {

    // Returns a list of questions for the given quiz number (1–5)
    public static List<Question> getQuestionsForQuiz(int quizNumber) {
        List<Question> questions = new ArrayList<>();

        // Select quiz set based on quizNumber passed from QuizSelectionActivity
        switch (quizNumber) {
            case 1:
                // Quiz 1: C++ Basics
                questions.add(new Question("Which of the following is a valid identifier?",
                        Arrays.asList("3total", "total_3", "total-3", "total 3"), "total_3"));

                questions.add(new Question("Which operator has the highest precedence?",
                        Arrays.asList("*", "+", "()", "=="), "()"));

                questions.add(new Question("What will be printed by the following statement: int x = 5; cout << x++ << x;",
                        Arrays.asList("55", "65", "56", "Error"), "56"));

                questions.add(new Question("Which of the following is a keyword in C++?",
                        Arrays.asList("define", "main", "int", "include"), "int"));

                questions.add(new Question("What is the default return type of main() in C++?",
                        Arrays.asList("void", "int", "float", "char"), "int"));
                break;

            case 2:
                // Quiz 2: Data types and loops
                questions.add(new Question("What is the size of `int` on most systems?",
                        Arrays.asList("2 bytes", "4 bytes", "8 bytes", "Depends on system"), "4 bytes"));

                questions.add(new Question("Which loop is guaranteed to execute at least once?",
                        Arrays.asList("for", "while", "do-while", "none"), "do-while"));

                questions.add(new Question("Which symbol is used for comments in C++?",
                        Arrays.asList("//", "/*", "#", "!!"), "//"));

                questions.add(new Question("What is the correct syntax for 'not equal' in C++?",
                        Arrays.asList("!=", "<>", "><", "==!"), "!="));

                questions.add(new Question("Which is a relational operator?",
                        Arrays.asList("+", "&&", "<", "|"), "<"));
                break;

            case 3:
                // Quiz 3: Input/output and constants
                questions.add(new Question("Which header file is used for input/output operations?",
                        Arrays.asList("iostream", "math.h", "conio.h", "stdio.h"), "iostream"));

                questions.add(new Question("What does `cin` do in C++?",
                        Arrays.asList("Outputs data", "Clears screen", "Takes input", "Defines variables"), "Takes input"));

                questions.add(new Question("Which keyword is used to define a constant?",
                        Arrays.asList("const", "constant", "define", "final"), "const"));

                questions.add(new Question("What is the value of `5 % 2`?",
                        Arrays.asList("1", "2", "2.5", "0"), "1"));

                questions.add(new Question("Which of the following is a correct variable declaration?",
                        Arrays.asList("int 1x;", "int x1;", "int x-1;", "int x y;"), "int x1;"));
                break;

            case 4:
                // Quiz 4: Data types, loops, operators
                questions.add(new Question("Which data type is used for storing a single character?",
                        Arrays.asList("char", "string", "int", "float"), "char"));

                questions.add(new Question("What is the purpose of `#include <iostream>`?",
                        Arrays.asList("Defines variables", "Allows I/O operations", "Performs calculations", "None"), "Allows I/O operations"));

                questions.add(new Question("What will be printed by `cout << 2 + 3 * 4;`?",
                        Arrays.asList("20", "14", "24", "10"), "14"));

                questions.add(new Question("Which loop is best when the number of iterations is known?",
                        Arrays.asList("while", "for", "do-while", "goto"), "for"));

                questions.add(new Question("Which of the following is a conditional operator?",
                        Arrays.asList("&&", "||", "?:", "++"), "?:"));
                break;

            case 5:
                // Quiz 5: Functions, control statements
                questions.add(new Question("Which function terminates a program in C++?",
                        Arrays.asList("exit()", "stop()", "return()", "end()"), "exit()"));

                questions.add(new Question("Which is a valid `if` condition?",
                        Arrays.asList("if x = 5", "if (x == 5)", "if x == 5 then", "if x := 5"), "if (x == 5)"));

                questions.add(new Question("Which header file contains math functions like sqrt()?",
                        Arrays.asList("math.h", "iostream", "stdlib.h", "cmath"), "cmath"));

                questions.add(new Question("What does `break` do in a loop?",
                        Arrays.asList("Skips one iteration", "Exits loop", "Repeats loop", "Ends program"), "Exits loop"));

                questions.add(new Question("What is the use of `continue` in loops?",
                        Arrays.asList("Exits loop", "Skips next loop", "Skips current iteration", "Repeats current iteration"), "Skips current iteration"));
                break;
        }

        return questions; // Return the list of questions for the selected quiz
    }
}

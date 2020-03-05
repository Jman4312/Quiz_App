package com.traf1.demo;

public class QuestionLibrary {

    private static String questions [] = {
            "What is the square root of 361",
            "When was Thomas Jefferson Born",
            "In which state is Mount Rushmore",
            "In which state does the Kansas City Chiefs play",
            "What is Obama's last name",
            "Who is credited with inventing the lightbulb",
            "Who was the 4th president",
            "Which is the best spring sport",
            "What is the fastest land mammal in the world",
            "What is the largest animal on earth"
    };

    private static String choices [][] = {
            {"18", "19", "20", "21"},
            {"1643", "1743", "1843", "1043"},
            {"West Dakota", "East Dakota", "North Dakota", "South Dakota"},
            {"Kansas", "Tennessee", "Missouri", "Florida"},
            {"Biden", "Trump", "Warren", "Obama"},
            {"Joseph Swan", "Thomas Edison", "Alexander Bell", "Madonna"},
            {"George Washington", "Thomas Jefferson", "James Madison", "James Monroe"},
            {"Lacrosse", "Indoor track", "Crew", "Football"},
            {"Peregrine Falcon", "Cheetah", "Mexican Free-Tailed Bat", "Black Marlin"},
            {"North Pacific Right Whale", "African Bush Elephant", "White Rhinoceros", "Blue Whale"}
    };

    private static String correctAnswers[] = {
            "19",
            "1743",
            "South Dakota",
            "Missouri",
            "Obama",
            "Thomas Edison",
            "James Madison",
            "Lacrosse",
            "Cheetah",
            "Blue Whale"
    };

    public static String[] getQuestions() {
        return questions;
    }

    public static String[][] getChoices() {
        return choices;
    }

    public static String[] getCorrectAnswers() {
        return correctAnswers;
    }
}

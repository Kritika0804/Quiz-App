package com.example.quizapp

object Constants {
    fun getQuestions():ArrayList<Question>{
        val questiosList = ArrayList<Question>()

        val que1 = Question(
            1,"Select the valid statement",
            "char[] ch = new char(5)",
            "char[] ch = new char[5]",
            "char[] ch = new char()",
            "char[] ch = new char[]",
            2
        )
        questiosList.add(que1)

        val que2 = Question(
            2,"when an array is passed to a method, what does the method receive",
            "The reference of the array",
            "A copy of the array",
            "Length of the array",
            "Copy of first element",
            1
        )
        questiosList.add(que2)

        val que3 = Question(
            3,"Arrays in java are",
            "Object references",
            "Objects",
            "Primitive Data type",
            "None",
            2
        )
        questiosList.add(que3)

        val que4 = Question(
            4,"When is the object created with new keyword?",
            "At runtime",
            "At compile time",
            "Depends on the code",
            "None",
            1
        )
        questiosList.add(que4)

        val que5 = Question(
            5,"Identify the return type of a method that does not return any value",
            "int",
            "void",
            "double",
            "None",
            2
        )
        questiosList.add(que5)

        return questiosList
    }

}
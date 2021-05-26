package com.example.technpractiseandroid.repository

import com.example.technpractiseandroid.model.Word


object WordRepository {
    var words = ArrayList<Word>()

    fun getWordById(id:Int): Word?{
        words.forEach{
            if(it.id == id)
                return it
        }
        return null
    }

    fun getWords(): List<Word>{
        words = arrayListOf(
            Word(1, "Instead of giving yourself a reason why you can’t, give yourself a reason why you can"),
            Word(2, "Practice like you’ve never won. Perform like you’ve never lost"),
            Word(3, "Don’t let how you feel make you forget what you deserve"),
            Word(4, "Next to trying and winning, the best thing is trying and failing"),
            Word(5, "Everything you need to accomplish your goals is already in you"),
            Word(6, "It always seems impossible until it is done"),
            Word(7, "You’re allowed to scream, you’re allowed to cry, but do not give up"),
            Word(8, "Sometimes, you have to get knocked down lower than you have ever been to stand back up taller than you ever were"),
            Word(9, "When you feel like giving up, remember why you held on for so long in the first place"),
            Word(10,"There’s much more ahead of you. You’ll get through this"),
            Word(11,"If you’re not making mistakes, then you’re not making decisions"),
            Word(12,"Take a deep breath, it’s just a bad day not a bad life"),
            Word(13,"Believe you can and you are halfway there"),
            Word(14,"Sometimes when you are in a dark place you think you have been buried, but actually you have been planted"),
            Word(15,"Hey, little fighter, soon things will be brighter")
            )
        return words
    }
}
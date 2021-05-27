package com.example.technpractiseandroid.repository

import com.example.technpractiseandroid.model.Word


object WordRepository {
    private var words = ArrayList<Word>()

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
            Word(15,"Hey, little fighter, soon things will be brighter"),
            Word(16,"You can have anything you want if you want it badly enough. You can be anything you want to be, do anything you set out to accomplish if you hold to that desire with singleness of purpose."),
            Word(17,"I don’t care how much power, brilliance or energy you have, if you don’t harness it and focus it on a specific target, and hold it there you’re never going to accomplish as much as your ability warrants"),
            Word(18,"Don’t let what you cannot do interfere with what you can do"),
            Word(19,"Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time"),
            Word(20,"You’ve got to get up every morning with determination if you’re going to go to bed with satisfaction.")
        )
        return words
    }
}
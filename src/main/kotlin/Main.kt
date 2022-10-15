var clock: Int = 0
var ableBegin: Int = 0
var ableSt: Int = 0
var ableEnd: Int = 0
var bakerBegin: Int = 0
var bakerSt: Int = 0
var bakerEnd: Int = 0
var check:Boolean = true

fun main(args: Array<String>) {

    for (i in 0..7){
        println("enter IAN")
        if (i!=0){
            clock += randomProbability(readln().toInt())

        }
        println(clock)

        if (ableOrbaker()){
            println("enter able Random")
            ableEnd +=AbleProbability(readln().toInt())
            println(ableEnd)
        }
        else{
            println("enter backer random")
            bakerEnd +=BakerProbability(readln().toInt())
            println(bakerEnd)
        }
    }
    println(clock)



}

fun randomProbability(x: Int): Int {
    return when (x) {
        in 0..24 -> 1
        in 25..64 -> 2
        in 65..84 -> 3
        in 85..99 -> 4
        else -> 0
    }
}

fun AbleProbability(x: Int): Int {
    return when (x) {
        in 0..29 -> 2
        in 30..57 -> 3
        in 58..82 -> 4
        in 83..99 -> 5
        else -> 0
    }
}

fun BakerProbability(x: Int): Int {
    return when (x) {
        in 0..34 -> 3
        in 35..59 -> 4
        in 60..79 -> 5
        in 80..99 -> 6
        else -> 0
    }
}

fun ableOrbaker() : Boolean{
    println("able End $ableEnd")
    println("backe End $bakerEnd")
    println("clock $clock")
    if(clock == ableEnd ){
        return true
    }else if (clock > ableEnd && clock >bakerEnd){
        return true
    }else if (clock < ableEnd && clock >bakerEnd){
        return false
    }else if (clock < ableEnd && clock < bakerEnd){
        if (ableEnd < bakerEnd ){
            return true
        }else if (ableEnd > bakerEnd){
            return false
        }else{
            return true
        }
    }
    return true
}
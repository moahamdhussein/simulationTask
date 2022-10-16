import java.util.Queue
import kotlin.math.abs

var clock: Int = 0
var ableBegin: Int = 0
var ableSt: Int = 0
var ableEnd: Int = 0
var ableIdle : Int= 0
var bakerBegin: Int = 0
var bakerSt: Int = 0
var bakerEnd: Int = 0
var bakerIdle : Int = 0
var randomNumber : Int = 0
var iAt :Int = 0
var rnSt:Int = 0
var queueTime : Int= 0
var timeSpent :Int = 0


fun main(args: Array<String>) {
    for (i in 0..7){
        if (i!=0){
            println("enter IAN")
            randomNumber =readln().toInt()
            iAt = randomProbability(randomNumber)
            clock += iAt
        }

        if (ableOrbaker()){
            println("enter able St")
            rnSt = readln().toInt()
            queueTime = if ((clock-ableEnd)< 0) abs(clock-ableEnd) else 0
            ableBegin = if(clock > ableEnd) clock else ableEnd
            ableSt = AbleProbability(rnSt)
            ableEnd = ableBegin + ableSt
        }
        else{
            println("enter Baker St")
            rnSt = readln().toInt()
            queueTime = if ((clock-bakerEnd)< 0) abs(clock-bakerEnd) else 0
            bakerBegin = if(clock > bakerEnd) clock else bakerEnd
            bakerSt = BakerProbability(rnSt)
            bakerEnd = bakerBegin + bakerSt
        }
        println("id\tRN-IAN\tiAt\tClock\tRN-St\tAbleBegin\tAbleST\tAbleEnd\tBakerBegin\tBakerST\tBakerEnd\tQueuingTime")
        println("${i+1}\t\t$randomNumber\t $iAt\t  $clock\t  $rnSt\t    $ableBegin\t      $ableSt\t        $ableEnd\t\t$bakerBegin\t\t\t$bakerSt\t\t$bakerEnd\t\t\t$queueTime")
    }




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
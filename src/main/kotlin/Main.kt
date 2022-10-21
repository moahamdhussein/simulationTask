import kotlin.math.abs


var clock: Int = 0
var ableBegin: Int = 0
var ableSt: Int = 0
var ableEnd: Int = 0
var ableIdle: Int = 0
var ableTotalTimeBusy: Double = 0.0
var bakerBegin: Int = 0
var bakerSt: Int = 0
var bakerEnd: Int = 0
var bakerIdle: Int = 0
var bakerTotalTimeBusy: Double = 0.0
var randomNumber: Int = 0
var iAt: Int = 0
var rnSt: Int = 0
var queueTime: Int = 0
var timeSpent: Int = 0
var sumTimeSpent: Int = 0
var waitingTime: Double = 0.0
var countWaiting: Int = 0
var i: Int = 0
var endingTime: Int = 0

fun main(args: Array<String>) {
    // Ia is the list that have the random number for interval time
    val intervalRandom: List<Int> =
        listOf(
            0,
            94,
            73,
            70,
            82,
            25,
            35,
            61,
            42,
            48,
            26,
            88,
            31,
            90,
            55,
            95,
            58,
            70,
            15,
            73,
            65,
            74,
            75,
            98,
            72,
            59,
            85,
            98,
            21
        )

    // Ia is the list that have the random number for service time
    val serviceTimeRandom: List<Int> =
        listOf(
            32,
            96,
            89,
            32,
            67,
            48,
            63,
            99,
            98,
            66,
            85,
            58,
            6,
            36,
            15,
            2,
            48,
            63,
            85,
            61,
            40,
            16,
            18,
            52,
            71,
            16,
            34,
            96,
            90
        )
    println("enter ending time of simulation")
    endingTime = readln().toInt()


    // print the row name's
    println("id\t  RN-IAN\tiAt\t\tClock\tRN-St\tAbleBegin\tAbleST\tAbleEnd\tBakerBegin\tBakerST\tBakerEnd\tQueuingTime\tTimeSpent\tAbleIdle\tBakerIdle")

    while (true) {
        // for fist customer the clock will be zero without go to random table
        if (i != 0) {
            randomNumber = intervalRandom[i]
            iAt = randomProbability(randomNumber)
            clock += iAt
        }

        // here to stop the simulation after ending time as 60 min
        if (clock > endingTime) {
            break
        }

        if (ableOrBaker()) {
            rnSt = serviceTimeRandom[i]

            // هنا لو الوقت اللي حضر  في العميل اصغر من الوقت لنهاية السيرفيس للعميل القديم يبقا ساعتها في وقت انتظار
            queueTime = if ((clock - bakerEnd) < 0) abs(clock - bakerEnd) else 0

            //  هنا لو الوقت اللي حضر فيه العميل  الجديد اكبر من الوقت لنهاية السيرفيس للعميل القديم اذن بداية الشغل هتبقا بتساوي الوقت اللي حضر في العميل الجديد
            bakerBegin = if (clock > bakerEnd) clock else bakerEnd

            bakerIdle = bakerBegin - bakerEnd
            bakerSt = bakerProbability(rnSt)
            bakerTotalTimeBusy += bakerSt
            bakerEnd = bakerBegin + bakerSt
            timeSpent = queueTime + bakerSt
            println("${i + 1}\t\t${randomNumber}\t\t${iAt}\t\t${clock}\t\t ${rnSt}\t\t\t--\t\t\t--\t\t--\t\t\t$bakerBegin\t\t\t$bakerSt\t\t$bakerEnd\t\t\t$queueTime\t\t\t$timeSpent\t\t--\t\t$bakerIdle")

        } else {
            rnSt = serviceTimeRandom[i]

            // هنا لو الوقت اللي حضر  في العميل اصغر من الوقت لنهاية السيرفيس للعميل القديم يبقا ساعتها في وقت انتظار
            queueTime = if ((clock - ableEnd) < 0) abs(clock - ableEnd) else 0

            //  هنا لو الوقت اللي حضر فيه العميل  الجديد اكبر من الوقت لنهاية السيرفيس للعميل القديم اذن بداية الشغل هتبقا بتساوي الوقت اللي حضر في العميل الجديد
            ableBegin = if (clock > ableEnd) clock else ableEnd

            ableIdle = ableBegin - ableEnd
            ableSt = ableProbability(rnSt)
            ableTotalTimeBusy += ableSt
            ableEnd = ableBegin + ableSt
            timeSpent = queueTime + ableSt
            println("${i + 1}\t\t${randomNumber}\t\t${iAt}\t\t${clock}\t\t ${rnSt}\t\t\t${ableBegin}\t\t\t${ableSt}\t\t${ableEnd}\t\t\t--\t\t\t--\t\t--\t\t\t$queueTime\t\t\t$timeSpent\t\t$ableIdle\t\t--")

        }
        if (queueTime != 0) countWaiting++
        waitingTime += queueTime
        sumTimeSpent += timeSpent
        i++


    }
    println("Time percentage baker is busy = %.2f".format((bakerTotalTimeBusy / bakerEnd) * 100) + " %")
    println("Time percentage able is busy = %.2f".format((ableTotalTimeBusy / ableEnd) * 100) + " %")
    println("Average waiting time = %.2f min".format(waitingTime / countWaiting))

}

// function to get probability for interval time
fun randomProbability(x: Int): Int {
    return when (x) {
        in 0..24 -> 1
        in 25..64 -> 2
        in 65..84 -> 3
        in 85..99 -> 4
        else -> 0
    }
}

// function to get probability for able service time
fun ableProbability(x: Int): Int {
    return when (x) {
        in 0..29 -> 2
        in 30..57 -> 3
        in 58..82 -> 4
        in 83..99 -> 5
        else -> 0
    }
}

// function to get probability for baker service time
fun bakerProbability(x: Int): Int {
    return when (x) {
        in 0..34 -> 3
        in 35..59 -> 4
        in 60..79 -> 5
        in 80..99 -> 6
        else -> 0
    }
}

// function to check the customer work with baker or abel
// if return true the customer work with baker else work with able
fun ableOrBaker(): Boolean {

    return if (clock == bakerEnd) {
        true
    } else if (clock > bakerEnd) {
        true
    } else if (clock in ableEnd until bakerEnd) {
        false
    } else {
        if (bakerEnd < ableEnd) {
            true
        } else bakerEnd <= ableEnd
    }

}
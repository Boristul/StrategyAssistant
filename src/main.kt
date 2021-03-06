fun main(args: Array<String>) {

    var winStratCount = 0
    var lossStratCount = 0

    var st = SingleStrategy(10000.0, 10.0)
   print(st.countOfLoses(3.0))

    for (i in 1..100) {

        var st = SingleStrategy(10000.0, 15.0)
        while (st.singleBank < 100000 && st.statusStrat) {
            st.simulateBid(3.0, randomBool())

        }

        if (!st.statusStrat) {
            lossStratCount++
        println("you lost!")
        st.printStat()
        } else {
            winStratCount++
        println("you win!")
        st.printStat()
        }
    }

    println("win: $winStratCount, loss: $lossStratCount")

}
fun randomBool() : Boolean
{
    if ((1..100).random() < 50)
        return false
    return true
}
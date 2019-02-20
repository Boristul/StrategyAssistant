class SingleStrategy (var singleBank: Double, var percent: Double, var currentWinAmount : Double = 0.0,
                      var winCount: Int = 0, var lossCount: Int = 0, var statusStrat: Boolean = true)
{
    fun getWinAmount( bank: Double ) = bank * (percent / 100)

    fun getBidAmount(coefficient : Double, bidAmount: Double) = bidAmount / (coefficient - 1)

    fun getCountWinLine () : Int
    {
        var bank = singleBank
        var count = 0

        while (bank < singleBank * 10)
        {
            bank += getWinAmount(bank)
            count++
        }
        return count
    }

    fun simulateBid(coefficient: Double, status: Boolean) : Boolean
    {
        if (currentWinAmount == 0.0)
            currentWinAmount = getWinAmount(singleBank)

        if (status)
        {
            singleBank += currentWinAmount
            currentWinAmount = 0.0
            winCount++
            return true
        }
        else
        {
            if (getBidAmount(coefficient, currentWinAmount) > singleBank) {
                statusStrat = false
                return false
            }

            singleBank -= getBidAmount(coefficient, currentWinAmount)
            currentWinAmount += getBidAmount(coefficient, currentWinAmount)
            lossCount++
        }
        return true
    }

    fun printStat()
    {
        println("Bank: $singleBank, debt: $currentWinAmount, win: $winCount, loss: $lossCount, total: ${winCount + lossCount}")
    }

    fun countOfLoses(coefficient: Double): Int
    {
        var countOfLose = 0

        while (simulateBid(coefficient, false))
        {
            countOfLose ++
        }

        return countOfLose
    }

}
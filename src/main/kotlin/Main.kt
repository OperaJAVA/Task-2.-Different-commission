fun calculateCommission(
    cardType: String = "VK Pay",
    previousTransfersAmount: Int = 0,
    transferAmount: Int
): Double {
    val dailyLimit = 150000 // Рубли в сутки
    val monthlyLimit = 600000 // Рубли в месяц

    // Проверка на превышение разового лимита
    if (transferAmount > dailyLimit) {
        return 0.0
    }

    // Проверка на превышение месячного лимита
    if (previousTransfersAmount + transferAmount > monthlyLimit) {
        return 0.0
    }

    return if (cardType == "MasterCard" || cardType == "Maestro") {
        if (previousTransfersAmount + transferAmount > 75000)
            transferAmount * 0.6 / 100 + 20.0
        else
            0.0
    } else if (cardType == "Visa" || cardType == "Mir") {
        val commission = transferAmount * 0.75 / 100
        if (commission < 35) 35.0 else commission
    } else {
        0.0
    }
}

fun main() {
    val cardType = "MasterCard"
    val previousTransfersAmount = 70000
    val transferAmount = 150000 // Значение превышает разовой лимит

    val commission = calculateCommission(cardType, previousTransfersAmount, transferAmount)
    println("Размер комиссии: $commission рублей")
}


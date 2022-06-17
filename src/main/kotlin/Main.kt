const val MASTER_CARD = "MasterCard"
const val MAESTRO = "Maestro"
const val VK_PAY = "VK Pay"
const val VISA = "Visa"
const val MIR = "МИР"

const val LIMIT_PRICE = 1500000
var amountPreviousMM = 70000

fun main() {
    val amount = 123642
    val cardType = VISA

    if (cardType == VK_PAY && amount > LIMIT_PRICE) {
        println("Превышена сумма перевода")
    } else {
        val resultCommission = remittance(amount, cardType, amountPreviousMM)
        println("Перевод: $amount коп.")
        println("Комиссия: $resultCommission коп.")
        println("Вместе с комиссией сумма перевода составляет: ${amount + resultCommission} коп.")
    }
}

fun remittance(
    amountTransfer: Int,
    cardType: String = VK_PAY,
    amountPreviousTransfers: Int = 0,
): Int {
    amountPreviousMM += amountTransfer

    return when (cardType) {
        MASTER_CARD, MAESTRO -> {
            if (amountPreviousTransfers > 7500000) {
                val commissionMM = 0.006
                (amountTransfer * commissionMM + 2000).toInt()
            } else 0
        }
        VISA, MIR -> {
            val commissionVM = 0.0075
            val resultCommission = (amountTransfer * commissionVM).toInt()
            if (resultCommission > 3500) resultCommission else 3500
        }
        VK_PAY -> 0
        else -> 0
    }
}
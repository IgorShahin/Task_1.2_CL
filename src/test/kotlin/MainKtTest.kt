import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MainKtTest {

    @Test
    fun remittance_shouldNotAddCommissionMasterCard() {
        val amount = 123642
        var cardType = MASTER_CARD
        val amountPrevious = 0

        val resultMC = remittance(
            amountTransfer = amount,
            cardType = cardType,
            amountPreviousTransfers = amountPrevious
        )

        assertEquals(0, resultMC)

        cardType = MAESTRO
        val resultM = remittance(
            amountTransfer = amount,
            cardType = cardType,
            amountPreviousTransfers = amountPrevious
        )
        assertEquals(0, resultM)

    }

    @Test
    fun remittance_shouldAddCommissionMasterCard() {
        val amount = 123642
        var cardType = MASTER_CARD
        val amountPrevious = 7500001

        val resultMC = remittance(
            amountTransfer = amount,
            cardType = cardType,
            amountPreviousTransfers = amountPrevious
        )

        assertEquals(2741, resultMC)

        cardType = MAESTRO
        val resultM = remittance(
            amountTransfer = amount,
            cardType = cardType,
            amountPreviousTransfers = amountPrevious
        )
        assertEquals(2741, resultM)
    }

    @Test
    fun remittance_shouldNotAddCommissionVisa() {
        val amount = 542816
        var cardType = VISA
        val amountPreviousMM = 0

        val resultV = remittance(
            amountTransfer = amount,
            cardType = cardType,
            amountPreviousTransfers = amountPreviousMM
        )

        assertEquals(4071, resultV)

        cardType = MIR
        val resultM = remittance(
            amountTransfer = amount,
            cardType = cardType,
            amountPreviousTransfers = amountPreviousMM
        )
        assertEquals(4071, resultM)
    }

    @Test
    fun remittance_shouldAddCommissionVisa() {
        val amount = 123642
        var cardType = VISA
        val amountPreviousMM = 7500001

        val resultV = remittance(
            amountTransfer = amount,
            cardType = cardType,
            amountPreviousTransfers = amountPreviousMM
        )

        assertEquals(3500, resultV)

        cardType = MIR
        val resultM = remittance(
            amountTransfer = amount,
            cardType = cardType,
            amountPreviousTransfers = amountPreviousMM
        )

        assertEquals(3500, resultM)
    }

    @Test
    fun remittance_shouldNotAddCommission() {
        val amount = 123642
        var cardType = VK_PAY
        val amountPreviousMM = 0

        val resultVK = remittance(
            amountTransfer = amount,
            cardType = cardType,
            amountPreviousTransfers = amountPreviousMM
        )

        assertEquals(0, resultVK)

        cardType = "UnionPay"
        val result = remittance(
            amountTransfer = amount,
            cardType = cardType,
            amountPreviousTransfers = amountPreviousMM
        )

        assertEquals(0, result)
    }
}
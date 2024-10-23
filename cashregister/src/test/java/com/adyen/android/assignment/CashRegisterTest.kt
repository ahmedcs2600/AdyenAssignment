package com.adyen.android.assignment

import com.adyen.android.assignment.CashRegister.TransactionException
import com.adyen.android.assignment.money.Bill
import com.adyen.android.assignment.money.Change
import com.adyen.android.assignment.money.Coin
import org.junit.Test
import org.junit.Assert.assertThrows
import org.junit.Assert.assertEquals

class CashRegisterTest {


    @Test
    fun `When the amount paid is not enough, cash register should throw an exception`() {
        val shopperChange = Change()
        val cashRegister = CashRegister(shopperChange)
        val amountPaid = Change().add(Bill.FIFTY_EURO, 1)

        assertThrows("Amount Paid is not enough", TransactionException::class.java) {
            cashRegister.performTransaction(1000_00, amountPaid)
        }
    }

    @Test
    fun `When the shopper does not have enough change, cash register should throw an exception`() {
        val shopperChange = Change().add(Coin.TWO_EURO, 1)
        val cashRegister = CashRegister(shopperChange)

        val amountPaid = Change()
            .add(Bill.FIVE_HUNDRED_EURO, 2)

        assertThrows("Not Enough change", TransactionException::class.java) {
            cashRegister.performTransaction(900_00, amountPaid)
        }
    }

    @Test
    fun `When the price is equal to amountPaid, then shopper returns no change`() {
        val shopperChange = Change().add(Coin.TWO_EURO, 1)
        val cashRegister = CashRegister(shopperChange)

        val amountPaid = Change()
            .add(Bill.FIVE_HUNDRED_EURO, 2)

        val change = cashRegister.performTransaction(1000_00, amountPaid)

        assertEquals(Change.none(), change)
    }

    @Test
    fun `When the amount paid is greater than price, then shopper returns change`() {
        val shopperChange = Change().add(Bill.FIFTY_EURO, 8).add(Bill.TWENTY_EURO, 8).add(Bill.ONE_HUNDRED_EURO, 10)
        val cashRegister = CashRegister(shopperChange)
        val amountPaid = Change()
            .add(Bill.FIVE_HUNDRED_EURO, 5)
        val expected = Change().add(Bill.FIVE_HUNDRED_EURO, 1).add(Bill.ONE_HUNDRED_EURO, 1).add(Bill.TWENTY_EURO, 3)

        val change = cashRegister.performTransaction( 1840_00 , amountPaid)

        assertEquals(expected, change)
    }
}

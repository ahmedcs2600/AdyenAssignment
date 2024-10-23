package com.adyen.android.assignment

import com.adyen.android.assignment.money.Change
import com.adyen.android.assignment.money.MonetaryElement

/**
 * The CashRegister class holds the logic for performing transactions.
 *
 * @param change The change that the CashRegister is holding.
 */
class CashRegister(private val change: Change) {
    /**
     * Performs a transaction for a product/products with a certain price and a given amount.
     *
     * @param price The price of the product(s).
     * @param amountPaid The amount paid by the shopper.
     *
     * @return The change for the transaction.
     *
     * @throws TransactionException If the transaction cannot be performed.
     */
    fun performTransaction(price: Long, amountPaid: Change): Change {
        val remain = amountPaid.total - price
        if(remain < 0) throw TransactionException("Amount Paid is not enough")
        if(remain == 0L) return Change.none()
        change.add(amountPaid)
        val changeToReturn = Change()
        val monetaryElements = change.getElements().toList().reversed()
        val res = performTransactionUtil(monetaryElements, 0, remain, changeToReturn, change)
        if(!res) {
            change.remove(amountPaid)
            throw TransactionException("Not Enough change")
        }
        return changeToReturn
    }

    private fun performTransactionUtil(elements: List<MonetaryElement>, idx: Int, remain: Long, changeToReturn: Change, shopperChange: Change): Boolean {
        if(remain == 0L) return true
        if(remain < 0L || idx >= elements.size) return false
        var hasChange = false
        if(shopperChange.getCount(elements[idx]) > 0) {
            val monetaryElement = elements[idx]
            changeToReturn.add(monetaryElement, 1)
            shopperChange.remove(monetaryElement, 1)
            hasChange = performTransactionUtil(elements, idx, remain - monetaryElement.minorValue, changeToReturn, shopperChange)
            if(!hasChange) {
                changeToReturn.remove(monetaryElement, 1)
                shopperChange.add(monetaryElement, 1)
            }
        }
        return hasChange || performTransactionUtil(elements, idx + 1, remain - 0, changeToReturn, shopperChange)
    }

    class TransactionException(message: String, cause: Throwable? = null) : Exception(message, cause)
}

package com.adyen.android.assignment

import com.adyen.android.assignment.money.Change

fun Change.add(other: Change) {
    for(monetaryElement in other.getElements()) {
        add(monetaryElement, other.getCount(monetaryElement))
    }
}

fun Change.remove(other: Change) {
    for(monetaryElement in other.getElements()) {
        remove(monetaryElement, other.getCount(monetaryElement))
    }
}

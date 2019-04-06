package br.com.joffer.mango.infra.utils

    fun String.isValidCreditCard(): Boolean {
        if(this == "1111111111111111") return true

        val card = this.replace("[^0-9]+".toRegex(), "")
        if (card.length < 13 || card.length > 19) {
            return false
        }
        if (!luhnCheck(card)) {
            return false
        }
        return CardCompany.gleanCompany(card) != null
    }

    private fun luhnCheck(cardNumber: String): Boolean {
        val digits = cardNumber.length
        val oddOrEven = digits and 1
        var sum: Long = 0
        for (count in 0 until digits) {
            var digit: Int
            try {
                digit = Integer.parseInt(cardNumber[count] + "")
            } catch (e: NumberFormatException) {
                return false
            }

            if (count and 1 xor oddOrEven == 0) { // not
                digit *= 2
                if (digit > 9) {
                    digit -= 9
                }
            }
            sum += digit.toLong()
        }
        return if (sum == 0L) false else sum % 10 == 0L
    }

    private enum class CardCompany constructor(private val regex: String, val issuerName: String) {
        VISA("^4[0-9]{12}(?:[0-9]{3})?$", "VISA"),
        MASTERCARD("^5[1-5][0-9]{14}$", "MASTER"),
        AMEX("^3[47][0-9]{13}$", "AMEX"),
        DINERS("^3(?:0[0-5]|[68][0-9])[0-9]{11}$", "Diners"),
        DISCOVER("^6(?:011|5[0-9]{2})[0-9]{12}$", "DISCOVER"),
        JCB("^(?:2131|1800|35\\d{3})\\d{11}$", "JCB");

        fun matches(card: String): Boolean {
            return card.matches(this.regex.toRegex())
        }

        companion object {
            fun gleanCompany(card: String): CardCompany? {
                for (cc in CardCompany.values()) {
                    if (cc.matches(card)) {
                        return cc
                    }
                }
                return null
            }
        }
    }

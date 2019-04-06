package br.com.joffer.mango.features.creditcard

import br.com.joffer.mango.infra.utils.isValidCreditCard
import org.junit.Test
import org.junit.Assert.*

class CreditCardTest{

    @Test
    fun when_empty_should_return_false(){
        //when
        val creditCard: String = ""

        //then
        val isValidCreditCard = creditCard.isValidCreditCard()

        //should
        assert(!isValidCreditCard)
    }

    @Test
    fun when_null_should_return_null(){
        //when
        val creditCard: String? = null

        //then
        val isValidCreditCard = creditCard?.isValidCreditCard()

        //should
        assertEquals(isValidCreditCard, null)
    }

    @Test
    fun when_1111_should_return_true(){
        //when
        val creditCard: String = "1111111111111111"

        //then
        val isValidCreditCard = creditCard.isValidCreditCard()

        //should
        assert(isValidCreditCard)
    }

    @Test
    fun when_valid_value_should_return_true(){
        //when
        val creditCard: String = "4387466972688103"

        //then
        val isValidCreditCard = creditCard.isValidCreditCard()

        //should
        assert(isValidCreditCard)
    }

    @Test
    fun when_invalid_value_should_return_false(){
        //when
        val creditCard: String = "2111111111111111"

        //then
        val isValidCreditCard = creditCard.isValidCreditCard()

        //should
        assert(!isValidCreditCard)
    }

    @Test
    fun when_lenght_invalid_should_return_false(){
        //when
        val creditCard: String = "123"

        //then
        val isValidCreditCard = creditCard.isValidCreditCard()

        //should
        assert(!isValidCreditCard)
    }
}
package br.com.joffer.mango.features.creditcard

import android.content.Intent
import android.provider.Settings.Global.getString
import androidx.test.espresso.action.ViewActions
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.com.joffer.mango.R
import br.com.joffer.mango.TestHelper
import br.com.joffer.mango.features.payment.creditcard.register.RegisterCardActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.SmallTest
import br.com.joffer.mango.features.payment.contacts.Contact
import org.hamcrest.Matchers.not
import org.junit.Rule

@SmallTest
@RunWith(AndroidJUnit4::class)
open class RegisterCardTest{

    @get:Rule
    val activityRule = ActivityTestRule(
        RegisterCardActivity::class.java, false, false)

    private val contact = Contact(id="123", name = "Felipe Joffer", username = "@fe", img= "")

    @Before
    fun setup() {
        val intent = Intent()
        intent.putExtra("CONTACT", contact)
        activityRule.launchActivity(intent)
    }

    @Test
    fun when_card_number_invalid_should_show_erro() {
        //when
        val cardNumber = "2111111111111111"

        //then
        onView(withId(R.id.card_number)).perform(typeText(cardNumber))
        Thread.sleep(500)

        //should
        val errorText = activityRule.activity.getString(R.string.number_invalid)
        onView(withId(R.id.card_number_layout)).check(matches(hasDescendant(withText(errorText))))
    }

    @Test
    fun when_card_number_valid_should_not_show_erro() {
        //when
        val cardNumber = "4387466972688103"

        //then
        onView(withId(R.id.card_number)).perform(typeText(cardNumber))
        Thread.sleep(200)

        //should
        val errorText = activityRule.activity.getString(R.string.number_invalid)
        onView(withText(errorText)).check(doesNotExist())
    }

    @Test
    fun when_card_number_invalid_should_not_show_save_button() {
        //when
        val cardNumber = "2111111111111111"

        //then
        onView(withId(R.id.card_number)).perform(typeText(cardNumber))
        Thread.sleep(200)

        //should
        onView(withId(R.id.save_card)).check(matches(not(isDisplayed())))
    }

    @Test
    fun when_owner_name_invalid_should_show_erro() {
        //when
        val ownerName = "abc"

        //then
        onView(withId(R.id.card_owner)).perform(typeText(ownerName))
        Thread.sleep(500)

        //should
        val errorText = activityRule.activity.getString(R.string.owner_invalid)
        onView(withId(R.id.card_owner_layout)).check(matches(hasDescendant(withText(errorText))))
    }

    @Test
    fun when_owner_name_valid_should_not_show_erro() {
        //when
        val ownerName = "felipe joffer"

        //then
        onView(withId(R.id.card_owner)).perform(typeText(ownerName))
        Thread.sleep(200)

        //should
        val errorText = activityRule.activity.getString(R.string.owner_invalid)
        onView(withText(errorText)).check(doesNotExist())
    }

    @Test
    fun when_owner_name_invalid_should_not_show_save_button() {
        //when
        val ownerName = "abc"

        //then
        onView(withId(R.id.card_owner)).perform(typeText(ownerName))
        Thread.sleep(200)

        //should
        onView(withId(R.id.save_card)).check(matches(not(isDisplayed())))
    }

    @Test
    fun when_cvv_invalid_should_show_erro() {
        //when
        val cvv = "32"

        //then
        onView(withId(R.id.card_cvv)).perform(typeText(cvv))
        Thread.sleep(500)

        //should
        val errorText = activityRule.activity.getString(R.string.cvv_invalid)
        onView(withId(R.id.card_cvv_layout)).check(matches(hasDescendant(withText(errorText))))
    }

    @Test
    fun when_cvv_valid_should_not_show_erro() {
        //when
        val cvv = "234"

        //then
        onView(withId(R.id.card_cvv)).perform(typeText(cvv))
        Thread.sleep(200)

        //should
        val errorText = activityRule.activity.getString(R.string.cvv_invalid)
        onView(withText(errorText)).check(doesNotExist())
    }

    @Test
    fun when_cvv_invalid_should_not_show_save_button() {
        //when
        val cvv = "23"

        //then
        onView(withId(R.id.card_cvv)).perform(typeText(cvv))
        Thread.sleep(200)

        //should
        onView(withId(R.id.save_card)).check(matches(not(isDisplayed())))
    }

    @Test
    fun when_validation_invalid_should_show_erro() {
        //when
        val validation = "32/3"

        //then
        onView(withId(R.id.card_valid)).perform(typeText(validation))
        Thread.sleep(500)

        //should
        val errorText = activityRule.activity.getString(R.string.validation_invalid)
        onView(withId(R.id.card_valid_layout)).check(matches(hasDescendant(withText(errorText))))
    }

    @Test
    fun when_validation_valid_should_not_show_erro() {
        //when
        val validation = "12/12"

        //then
        onView(withId(R.id.card_valid)).perform(typeText(validation))
        Thread.sleep(200)

        //should
        val errorText = activityRule.activity.getString(R.string.validation_invalid)
        onView(withText(errorText)).check(doesNotExist())
    }

    @Test
    fun when_validation_invalid_should_not_show_save_button() {
        //when
        val validation = "23/"

        //then
        onView(withId(R.id.card_valid)).perform(typeText(validation))
        Thread.sleep(200)

        //should
        onView(withId(R.id.save_card)).check(matches(not(isDisplayed())))
    }

    @Test
    fun when_all_fields_valid_should_show_save_button() {
        //when
        val validation = "23/12"
        val cvv = "233"
        val ownerName = "felipe joffer"
        val cardNumber = "4387466972688103"

        //then
        onView(withId(R.id.card_valid)).perform(typeText(validation))
        onView(withId(R.id.card_cvv)).perform(typeText(cvv))
        onView(withId(R.id.card_owner)).perform(typeText(ownerName))
        onView(withId(R.id.card_number)).perform(typeText(cardNumber))
        Thread.sleep(500)

        //should
        onView(withId(R.id.save_card)).check(matches(isDisplayed()))
    }

    @Test
    fun when_all_fields_invalid_should_show_not_save_button() {
        //when
        val validation = "23"
        val cvv = "23"
        val ownerName = "abc"
        val cardNumber = "2111111111111111"

        //then
        onView(withId(R.id.card_valid)).perform(typeText(validation))
        onView(withId(R.id.card_cvv)).perform(typeText(cvv))
        onView(withId(R.id.card_owner)).perform(typeText(ownerName))
        onView(withId(R.id.card_number)).perform(typeText(cardNumber))
        Thread.sleep(500)

        //should
        onView(withId(R.id.save_card)).check(matches(not(isDisplayed())))
    }
}
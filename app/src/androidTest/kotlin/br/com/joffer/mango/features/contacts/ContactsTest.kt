package br.com.joffer.mango.features.contacts

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import br.com.joffer.mango.R
import br.com.joffer.mango.RecyclerviewMatcher.atPosition
import br.com.joffer.mango.RecyclerviewMatcher.recyclerViewCount
import br.com.joffer.mango.features.payment.contacts.ContactsActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class ContactsTest{

    @get:Rule
    val activityRule = ActivityTestRule(
        ContactsActivity::class.java, false, false)

    @Before
    fun setUp(){
        ContactsServer.start()
    }

    @Test
    fun when_open_should_have_two_contacts(){
        //when
        activityRule.launchActivity(null)

        //should
        verifyHasItem(0, "Eduardo Santos", "@eduardo.santos")
        verifyHasItem(1, "Marina Coelho", "@marina.coelho")
        onView(withId(R.id.recycler_view)).check(recyclerViewCount(2))
    }

    @Test
    fun when_search_should_have_one_contact(){
        //when
        activityRule.launchActivity(null)

        //then
        val searchQuery = "Eduardo"
        onView(withId(R.id.search_view)).perform(typeText(searchQuery))

        //should
        verifyHasItem(0, "Eduardo Santos", "@eduardo.santos")
        onView(withId(R.id.recycler_view)).check(recyclerViewCount(1))
    }

    private fun verifyHasItem(position: Int, title: String, subtitle: String) {
        onView(withId(R.id.recycler_view))
            .check(matches(atPosition(position, hasDescendant(withText(title)))))

        onView(withId(R.id.recycler_view))
            .check(matches(atPosition(position, hasDescendant(withText(subtitle)))))
    }
}

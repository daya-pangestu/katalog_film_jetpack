package com.daya.katalogfilm.utils

import android.content.res.Resources
import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher


//fun withText(resourceId: Int): Matcher<View> {
//
//    return object : BoundedMatcher<View, TextView>(TextView::class.java) {
//        private var resourceName: String? = null
//        private var expectedText: String? = null
//
//        override fun describeTo(description: Description) {
//            description.appendText("with string from resource id: ")
//            description.appendValue(resourceId)
//            if (null != this.resourceName) {
//                description.appendText("[")
//                description.appendText(this.resourceName)
//                description.appendText("]")
//            }
//            if (null != this.expectedText) {
//                description.appendText(" value: ")
//                description.appendText(this.expectedText)
//            }
//        }
//
//        override fun matchesSafely(textView: TextView): Boolean {
//            if (null == this.expectedText) {
//                try {
//                    this.expectedText = textView.resources.getString(
//                        resourceId
//                    )
//                    this.resourceName = textView.resources
//                        .getResourceEntryName(resourceId)
//                } catch (ignored: Resources.NotFoundException) {
//
//                }
//
//            }
//            return if (null != this.expectedText) {
//                this.expectedText == textView.text
//                    .toString()
//            } else {
//                false
//            }
//        }
//    }
//}

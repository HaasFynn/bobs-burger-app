package ch.fhaas.real_estate_rest_api

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class UtilityTest {

    companion object {
        const val WORKING_DOMAIN = "https://www.ergon.ch/"
        const val BROKEN_DOMAIN = "htt:/ww.super-domain."
    }

    //Working Url given
    @Test
    fun browserDoesOpen() {
        assertTrue(openUrl(WORKING_DOMAIN))
    }

    //Wrong Url given
    @Test
    fun browserDoesOpen2() {
        assertFalse(openUrl(BROKEN_DOMAIN))
    }

    //Working Url given
    @Test
    fun isValidUrlTest() {
        assertTrue(isValidUrl(WORKING_DOMAIN))
    }

    //Wrong Url given
    @Test
    fun isValidUrlTest2() {
        assertFalse(isValidUrl(BROKEN_DOMAIN))
    }


}
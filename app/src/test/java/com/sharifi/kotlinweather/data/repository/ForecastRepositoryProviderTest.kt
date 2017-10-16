package com.sharifi.kotlinweather.data.repository

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals

/**
 * Created by sharifi on 10/16/17.
 */
object ForecastRepositoryProviderTest : Spek({
    describe("a forecast provider") {
                val provider = ForecastRepositoryProvider.instance()

        on("provider sources") {
                        val sources = provider.sources

            it("should be equal to default sources") {
                assertEquals(sources, ForecastRepositoryProvider.SOURCES)
            }
        }
    }
})
package org.geepawhill.kontentment

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ClockTest {

    val clock = Clock()

    @Test
    fun `starts with reset and paused`() {
        clock.tick(1.0)
        assertThat(clock.delta).isEqualTo(0.0)
    }

    @Test
    fun `playing advances with ticks`() {
        clock.resume()
        clock.tick(1.0)
        clock.tick(2.0)
        assertThat(clock.delta).isEqualTo(2.0)
    }

    @Test
    fun `pause works after resume`() {
        clock.resume()
        clock.tick(1.0)
        clock.pause()
        clock.tick(2.0)
        assertThat(clock.delta).isEqualTo(1.0)
    }

    @Test
    fun `resume works after pause works after resume`() {
        clock.resume()
        clock.tick(1.0)
        clock.pause()
        clock.tick(2.0)
        clock.resume()
        clock.tick(3.0)
        assertThat(clock.delta).isEqualTo(2.0)
    }


}
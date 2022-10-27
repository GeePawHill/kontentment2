package org.geepawhill.kontentment

import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.kontentment.announce.CollectingAnnouncer
import org.geepawhill.kontentment.controller.PlayingChange
import org.junit.jupiter.api.Test

class AtomClockTest {

    val announcer = CollectingAnnouncer()
    val clock = AtomClock(announcer)

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
    fun `resume announces if paused`() {
        clock.resume()
        assertThat(announcer.announcements).containsExactly(PlayingChange(true))
    }

    @Test
    fun `resume does not announce if already playing`() {
        clock.resume()
        clock.resume()
        assertThat(announcer.announcements).containsExactly(PlayingChange(true))
    }

    @Test
    fun `pause announces if playing`() {
        clock.resume()
        clock.pause()
        assertThat(announcer.announcements).containsExactly(PlayingChange(true), PlayingChange(false))
    }

    @Test
    fun `pause does not announce if already paused`() {
        clock.pause()
        assertThat(announcer.announcements).isEmpty()
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
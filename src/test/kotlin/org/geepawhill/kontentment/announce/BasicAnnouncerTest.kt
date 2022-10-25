package org.geepawhill.kontentment.announce

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BasicAnnouncerTest {

    open class MyAnnouncement() : Announcement
    class MySubAnnouncement() : MyAnnouncement()

    val announcer = BasicAnnouncer()
    var announced = false

    @Test
    fun `basic announcement works`() {
        announcer.subscribe(MyAnnouncement::class) { announced = true }
        announcer.announce(MyAnnouncement())
        assertThat(announced).isTrue()
    }

    @Test
    fun `subclass announcement works`() {
        announcer.subscribe(MyAnnouncement::class) { announced = true }
        announcer.announce(MySubAnnouncement())
        assertThat(announced).isTrue()
    }

    @Test
    fun `superclass announcement fails`() {
        announcer.subscribe(MySubAnnouncement::class) { announced = true }
        announcer.announce(MyAnnouncement())
        assertThat(announced).isFalse()
    }

    @Test
    fun `use member function works`() {
        announcer.subscribe(MySubAnnouncement::class, this::handle)
        announcer.announce(MySubAnnouncement())
        assertThat(announced).isTrue()
    }

    fun handle(announcement: MySubAnnouncement) {
        announced = true
    }
}
package org.geepawhill.kontentment.controller

import org.geepawhill.kontentment.announce.Announcement

data class PlayingChange(val isPlaying: Boolean) : Announcement

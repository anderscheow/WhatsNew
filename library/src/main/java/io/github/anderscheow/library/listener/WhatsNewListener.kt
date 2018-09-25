package io.github.anderscheow.library.listener

import io.github.anderscheow.library.WhatsNew

interface WhatsNewListener {

    /**
     *  Notify user when WhatsNew is showed.
     */
    fun onWhatsNewShowed(whatsNew: WhatsNew)

    /**
     *  Notify user when WhatsNew is dismissed.
     */
    fun onWhatsNewDismissed()

    /**
     *  Notify user when primary button is clicked.
     */
    fun onPrimaryButtonClicked(whatsNew: WhatsNew)

    /**
     *  Notify user when secondary button is cliced.
     */
    fun onSecondaryButtonClicked(whatsNew: WhatsNew)
}
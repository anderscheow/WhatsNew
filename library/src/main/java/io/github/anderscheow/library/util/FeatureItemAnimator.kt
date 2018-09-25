package io.github.anderscheow.library.util

import jp.wasabeef.recyclerview.animators.*

/**
 *  Type of item animator for RecyclerView.
 */
enum class FeatureItemAnimator {

    NONE,
    SCALE_IN,
    SCALE_IN_TOP,
    SCALE_IN_BOTTOM,
    SCALE_IN_LEFT,
    SCALE_IN_RIGHT,
    FADE_IN,
    FADE_IN_UP,
    FADE_IN_DOWN,
    FADE_IN_LEFT,
    FADE_IN_RIGHT,
    SLIDE_IN_UP,
    SLIDE_IN_DOWN,
    SLIDE_IN_LEFT,
    SLIDE_IN_RIGHT,
    OVERSHOOT_IN_LEFT,
    OVERSHOOT_IN_RIGHT;

    /**
     *  Identify the item animator for RecyclerView.
     */
    fun getItemAnimator(): BaseItemAnimator? {
        when (this) {
            SCALE_IN -> return ScaleInAnimator()

            SCALE_IN_TOP -> return ScaleInTopAnimator()

            SCALE_IN_BOTTOM -> return ScaleInBottomAnimator()

            SCALE_IN_LEFT -> return ScaleInLeftAnimator()

            SCALE_IN_RIGHT -> return ScaleInRightAnimator()

            FADE_IN -> return FadeInAnimator()

            FADE_IN_UP -> return FadeInUpAnimator()

            FADE_IN_DOWN -> return FadeInDownAnimator()

            FADE_IN_LEFT -> return FadeInLeftAnimator()

            FADE_IN_RIGHT -> return FadeInRightAnimator()

            SLIDE_IN_UP -> return SlideInUpAnimator()

            SLIDE_IN_DOWN -> return SlideInDownAnimator()

            SLIDE_IN_LEFT -> return SlideInLeftAnimator()

            SLIDE_IN_RIGHT -> return SlideInRightAnimator()

            OVERSHOOT_IN_LEFT -> return OvershootInLeftAnimator()

            OVERSHOOT_IN_RIGHT -> return OvershootInRightAnimator()

            else -> return null
        }
    }
}
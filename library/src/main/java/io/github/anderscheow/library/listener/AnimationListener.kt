package io.github.anderscheow.library.listener

/**
 * Created by mertsimsek on 25/01/16.
 */
interface AnimationListener {

    /**
     * We need to make MaterialIntroView visible
     * before fade in animation starts
     */
    interface OnAnimationStartListener {
        fun onAnimationStart()
    }

    /**
     * We need to make MaterialIntroView invisible
     * after fade out animation ends.
     */
    interface OnAnimationEndListener {
        fun onAnimationEnd()
    }

}
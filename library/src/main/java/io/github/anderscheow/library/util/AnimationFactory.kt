package io.github.anderscheow.library.util

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import io.github.anderscheow.library.listener.AnimationListener

/**
 * Created by mertsimsek on 25/01/16.
 */
object AnimationFactory {

    /**
     * MaterialIntroView will appear on screen with
     * fade in animation. Notifies onAnimationStartListener
     * when fade in animation is about to start.
     *
     * @param view
     * @param duration
     * @param onAnimationStartListener
     */
    fun animateFadeIn(view: View, duration: Long, onAnimationStartListener: AnimationListener.OnAnimationStartListener?) {
        ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).apply {
            this.duration = duration
            this.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    onAnimationStartListener?.onAnimationStart()
                }

                override fun onAnimationEnd(animation: Animator) {
                }

                override fun onAnimationCancel(animation: Animator) {
                }

                override fun onAnimationRepeat(animation: Animator) {
                }
            })
            this.start()
        }
    }

    /**
     * MaterialIntroView will disappear from screen with
     * fade out animation. Notifies onAnimationEndListener
     * when fade out animation is ended.
     *
     * @param view
     * @param duration
     * @param onAnimationEndListener
     */
    fun animateFadeOut(view: View, duration: Long, onAnimationEndListener: AnimationListener.OnAnimationEndListener?) {
        ObjectAnimator.ofFloat(view, "alpha", 1f, 0f).apply {
            this.duration = duration
            this.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                }

                override fun onAnimationEnd(animation: Animator) {
                    onAnimationEndListener?.onAnimationEnd()
                }

                override fun onAnimationCancel(animation: Animator) {
                }

                override fun onAnimationRepeat(animation: Animator) {
                }
            })
            this.start()
        }
    }
}
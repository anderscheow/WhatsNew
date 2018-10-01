package io.github.anderscheow.library

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.anderscheow.library.adapter.FeatureAdapter
import io.github.anderscheow.library.listener.AnimationListener
import io.github.anderscheow.library.listener.WhatsNewListener
import io.github.anderscheow.library.model.Feature
import io.github.anderscheow.library.util.AnimationFactory
import io.github.anderscheow.library.util.FeatureItemAnimator
import io.github.anderscheow.library.util.VerticalSpaceItemDecoration

class WhatsNew : FrameLayout {

    companion object {
        const val FADE_ANIMATION_DURATION = 500L
        const val DELAY_MILLIS_DURATION = 0L
        const val RECYCLER_VIEW_DELAY_MILLIS = 500L
        const val FEATURE_ITEM_ANIMATOR_DURATION = 500L
        const val VERTICAL_ITEM_SPACE = 64
    }

    private lateinit var view: View
    private lateinit var activity: Activity

    private val contentLayout by lazy { view.findViewById<View>(R.id.layout_content) }
    private val titleTextView by lazy { view.findViewById<TextView>(R.id.text_view_title) }
    private val primaryButton by lazy { view.findViewById<Button>(R.id.button_primary) }
    private val secondaryButton by lazy { view.findViewById<TextView>(R.id.button_secondary) }
    private val featureRecyclerView by lazy { view.findViewById<RecyclerView>(R.id.recycler_view_feature) }

    /**
     *  Fade animation duration when showing or dismissing WhatsNew.
     */
    private var fadeAnimationDuration = FADE_ANIMATION_DURATION

    /**
     *  Delay on starting fade animation.
     */
    private var delayMillisDuration = DELAY_MILLIS_DURATION

    /**
     *  To enable or disable fade animation on showing or dismissing WhatsNew.
     */
    private var isFadeAnimationEnabled = true

    /**
     *  Type of animation to show when displaying feature(s).
     */
    private var featureItemAnimator = FeatureItemAnimator.NONE

    /**
     *  Duration on displaying feature animation.
     */
    private var featureItemAnimatorDuration = FEATURE_ITEM_ANIMATOR_DURATION

    /**
     *  Feature(s) for the WhatsNew.
     */
    private var features = ArrayList<Feature>()

    /**
     *  To notify user when WhatsNew is showed, dismissed, primaryButtonClicked or secondaryButtonClicked.
     */
    private var listener: WhatsNewListener? = null

    private var selfHandler = Handler()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    /**
     *  To determine whether window is focus.
     *  Hide system UI when it's focused.
     */
    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (hasWindowFocus) hideSystemUI()
    }

    /**
     *  Init WhatsNew.
     */
    private fun init() {
        visibility = View.INVISIBLE

        view = LayoutInflater.from(context).inflate(R.layout.whats_new, this)

        setupUI()
    }

    /**
     *  Setup button listener.
     */
    private fun setupUI() {
        primaryButton.setOnClickListener {
            dismiss()
            listener?.onPrimaryButtonClicked(this)
        }

        secondaryButton.setOnClickListener {
            listener?.onSecondaryButtonClicked(this)
        }
    }

    /**
     *  Show WhatsNew.
     */
    private fun show() {
        (activity.window.decorView as ViewGroup).addView(this)

        selfHandler.postDelayed({
            if (isFadeAnimationEnabled)
                AnimationFactory.animateFadeIn(this, fadeAnimationDuration, object : AnimationListener.OnAnimationStartListener {
                    override fun onAnimationStart() {
                        showView()
                    }
                })
            else {
                showView()
            }
        }, delayMillisDuration)
    }

    /**
     *  Show WhatsNew's view.
     */
    private fun showView() {
        visibility = View.VISIBLE
        listener?.onWhatsNewShowed(this)
        hideSystemUI()

        setupRecyclerView()
    }

    /**
     *  Dismiss WhatsNew.
     */
    fun dismiss() {
        selfHandler.postDelayed({
            if (isFadeAnimationEnabled)
                AnimationFactory.animateFadeOut(this, fadeAnimationDuration, object : AnimationListener.OnAnimationEndListener {
                    override fun onAnimationEnd() {
                        dismissView()
                    }
                })
            else {
                dismissView()
            }
        }, delayMillisDuration)
    }

    /**
     *  Dismiss WhatsNew's view.
     */
    private fun dismissView() {
        visibility = View.GONE

        if (parent != null)
            (parent as ViewGroup).removeView(this)

        listener?.onWhatsNewDismissed()
        listener = null

        showSystemUI()
    }

    /**
     *  Hide System UI.
     */
    private fun hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        } else {
            activity.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }

    /**
     *  Show System UI.
     */
    private fun showSystemUI() {
        activity.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    /**
     *  Setup and load features.
     */
    private fun setFeatures(features: List<Feature>) {
        this.features.apply {
            this.clear()
            this.addAll(features)
        }
    }

    /**
     *  Setup RecyclerView with specific item animator.
     */
    private fun setupRecyclerView() {
        val adapter = FeatureAdapter(context)
        with(featureRecyclerView) {
            featureItemAnimator.getItemAnimator()?.let {
                this.itemAnimator = it.apply {
                    this.addDuration = featureItemAnimatorDuration
                }
            }
            this.addItemDecoration(VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE))
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        selfHandler.postDelayed({
            adapter.setItems(features)
        }, RECYCLER_VIEW_DELAY_MILLIS)
    }

    class Builder(private val activity: Activity) {
        private var whatsNew: WhatsNew = WhatsNew(activity)

        /**
         *  Set background resource.
         *
         *  @param backgroundRes The identifier of the resource.
         */
        fun setBackgroundRes(@DrawableRes backgroundRes: Int): Builder {
            whatsNew.contentLayout.setBackgroundResource(backgroundRes)
            return this
        }

        /**
         *  Set background drawable.
         *
         *  @param drawable The Drawable to use as the background, or null to remove the
         *        background
         */
        fun setBackgroundDrawable(drawable: Drawable): Builder {
            whatsNew.contentLayout.background = drawable
            return this
        }

        /**
         *  Set background color.
         *
         *  @param color The color of the background.
         */
        fun setBackgroundColor(@ColorInt color: Int): Builder {
            whatsNew.contentLayout.setBackgroundColor(color)
            return this
        }

        /**
         *  Set title resource.
         *
         *  @param titleRes The identifier of the resource.
         */
        fun setTitleRes(@StringRes titleRes: Int): Builder {
            whatsNew.titleTextView.setText(titleRes)
            return this
        }

        /**
         *  Set title color.
         *
         *  @param color The color of the text.
         */
        fun setTitleColor(@ColorInt color: Int): Builder {
            whatsNew.titleTextView.setTextColor(color)
            return this
        }

        /**
         *  Set title typeface.
         *
         *  @param typeface
         */
        fun setTitleTypeface(typeface: Typeface): Builder {
            whatsNew.titleTextView.typeface = typeface
            return this
        }

        /**
         *  Set primary button text resource.
         *
         *  @param textRes The identifier of the resource.
         */
        fun setPrimaryButtonTextRes(@StringRes textRes: Int): Builder {
            whatsNew.primaryButton.setText(textRes)
            return this
        }

        /**
         *  Set primary button background resource.
         *
         *  @param backgroundRes The identifier of the resource.
         */
        fun setPrimaryButtonBackgroundRes(@DrawableRes backgroundRes: Int): Builder {
            whatsNew.primaryButton.setBackgroundResource(backgroundRes)
            return this
        }

        /**
         *  Set primary button background color with rounded corner.
         *
         *  @param color The color of the primary button background.
         */
        fun setPrimaryButtonBackgroundColor(@ColorInt color: Int): Builder {
            val shape = GradientDrawable().apply {
                this.cornerRadius = 24f
                this.setColor(color)
            }
            whatsNew.primaryButton.background = shape
            return this
        }

        /**
         *  Set primary button text color.
         *
         *  @param color The color of the primary button text.
         */
        fun setPrimaryButtonTextColor(@ColorInt color: Int): Builder {
            whatsNew.primaryButton.setTextColor(color)
            return this
        }

        /**
         *  Set primary button typeface.
         *
         *  @param typeface
         */
        fun setPrimaryButtonTypeface(typeface: Typeface): Builder {
            whatsNew.primaryButton.typeface = typeface
            return this
        }

        /**
         *  To enable or disable primary button all caps.
         *
         *  @param allCaps
         */
        fun enablePrimaryButtonAllCaps(allCaps: Boolean): Builder {
            whatsNew.primaryButton.setAllCaps(allCaps)
            return this
        }

        /**
         *  Set secondary button text resource.
         *
         *  @param textRes The identifier of the resource.
         */
        fun setSecondaryButtonTextRes(@StringRes textRes: Int): Builder {
            whatsNew.secondaryButton.setText(textRes)
            return this
        }

        /**
         *  Set secondary button text color.
         *
         *  @param color The color of the secondary button text.
         */
        fun setSecondaryButtonTextColor(@ColorInt color: Int): Builder {
            whatsNew.secondaryButton.setTextColor(color)
            return this
        }

        /**
         *  Set secondary button typeface.
         *
         *  @param typeface
         */
        fun setSecondaryButtonTypeface(typeface: Typeface): Builder {
            whatsNew.secondaryButton.typeface = typeface
            return this
        }

        /**
         *  To enable or disable secondary button all caps.
         *
         *  @param allCaps
         */
        fun enableSecondaryButtonAllCaps(allCaps: Boolean): Builder {
            whatsNew.secondaryButton.setAllCaps(allCaps)
            return this
        }

        /**
         *  Hide secondary button.
         */
        fun hideSecondaryButton(): Builder {
            whatsNew.secondaryButton.visibility = View.GONE
            return this
        }

        /**
         *  Set fade animation duration.
         *
         *  @param duration Duration of fade animation.
         */
        fun setFadeAnimationDuration(duration: Long): Builder {
            whatsNew.fadeAnimationDuration = duration
            return this
        }

        /**
         *  Set delay millis duration.
         *
         *  @param duration Duration of delay millis.
         */
        fun setDelayMillisDuration(duration: Long): Builder {
            whatsNew.delayMillisDuration = duration
            return this
        }

        /**
         *  To enable or disable fade animation.
         *
         *  @param isFadeAnimationEnabled
         */
        fun enableFadeAnimation(isFadeAnimationEnabled: Boolean): Builder {
            whatsNew.isFadeAnimationEnabled = isFadeAnimationEnabled
            return this
        }

        /**
         *  Set feature item animator.
         *
         *  @param featureItemAnimator Type of FeatureItemAnimator.
         */
        fun setFeatureItemAnimator(featureItemAnimator: FeatureItemAnimator): Builder {
            whatsNew.featureItemAnimator = featureItemAnimator
            return this
        }

        /**
         *  Set feature item animator duration.
         *
         *  @param duration Duration of feature item animator.
         */
        fun setFeatureItemAnimatorDuration(duration: Long): Builder {
            whatsNew.featureItemAnimatorDuration = duration
            return this
        }

        /**
         *  Set features.
         *
         *  @param features List of features.
         */
        fun setFeatures(features: List<Feature>): Builder {
            whatsNew.setFeatures(features)
            return this
        }

        /**
         *  Set WhatsNewListener.
         *
         *  @param listener
         */
        fun setListener(listener: WhatsNewListener): Builder {
            whatsNew.listener = listener
            return this
        }

        /**
         *  Build and show WhatsNew.
         */
        fun build(): WhatsNew {
            whatsNew.activity = activity
            whatsNew.show()
            return whatsNew
        }
    }
}
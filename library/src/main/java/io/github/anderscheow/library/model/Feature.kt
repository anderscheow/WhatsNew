package io.github.anderscheow.library.model

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Feature {

    /**
     *  Icon resource.
     */
    var iconRes: Int = 0
        private set

    /**
     *  Icon drawable.
     */
    var iconDrawable: Drawable? = null
        private set

    /**
     *  Icon color.
     */
    var iconColor: Int = 0
        private set

    /**
     *  Title resource.
     */
    var titleRes: Int = 0
        private set

    /**
     *  Title text color.
     */
    var titleTextColor: Int = 0
        private set

    /**
     *  Title typeface.
     */
    var titleTypeface: Typeface? = null
        private set

    /**
     *  Max lines of title.
     */
    var titleMaxLines = 2
        private set

    /**
     *  Description resource.
     */
    var descriptionRes: Int = 0
        private set

    /**
     *  Description text color.
     */
    var descriptionTextColor: Int = 0
        private set

    /**
     *  Description typeface.
     */
    var descriptionTypeface: Typeface? = null
        private set

    /**
     *  Max lines of description.
     */
    var descriptionMaxLines = 5
        private set

    constructor(builder: Builder) {
        this.iconRes = builder.iconRes
        this.iconDrawable = builder.iconDrawable
        this.iconColor = builder.iconColor
        this.titleRes = builder.titleRes
        this.titleTextColor = builder.titleTextColor
        this.titleTypeface = builder.titleTypeface
        this.titleMaxLines = builder.titleMaxLines
        this.descriptionRes = builder.descriptionRes
        this.descriptionTextColor = builder.descriptionTextColor
        this.descriptionTypeface = builder.descriptionTypeface
        this.descriptionMaxLines = builder.descriptionMaxLines
    }

    constructor(@DrawableRes iconRes: Int, @ColorInt iconColor: Int,
                @StringRes titleRes: Int, @ColorInt titleTextColor: Int,
                @StringRes descriptionRes: Int, @ColorInt descriptionTextColor: Int) {
        this.iconRes = iconRes
        this.iconColor = iconColor
        this.titleRes = titleRes
        this.titleTextColor = titleTextColor
        this.descriptionRes = descriptionRes
        this.descriptionTextColor = descriptionTextColor
    }

    class Builder {

        var iconRes: Int = 0
            private set

        var iconDrawable: Drawable? = null
            private set

        var iconColor: Int = 0
            private set

        var titleRes: Int = 0
            private set

        var titleTextColor: Int = 0
            private set

        var titleTypeface: Typeface? = null
            private set

        var titleMaxLines = 2
            private set

        var descriptionRes: Int = 0
            private set

        var descriptionTextColor: Int = 0
            private set

        var descriptionTypeface: Typeface? = null
            private set

        var descriptionMaxLines = 5
            private set

        /**
         *  Set icon resources.
         *
         *  @param iconRes The identifier of the resource.
         */
        fun setIconRes(@DrawableRes iconRes: Int) = apply { this.iconRes = iconRes }

        /**
         *  Set icon drawable.
         *
         *  @param iconDrawable Drawable of the icon.
         */
        fun setIconDrawable(iconDrawable: Drawable) = apply { this.iconDrawable = iconDrawable }

        /**
         *  Set icon color
         *
         *  @param iconColor Color of the icon.
         */
        fun setIconColor(@ColorInt iconColor: Int) = apply { this.iconColor = iconColor }

        /**
         *  Set title resources.
         *
         *  @param titleRes The identifier of the resource.
         */
        fun setTitleRes(@StringRes titleRes: Int) = apply { this.titleRes = titleRes }

        /**
         *  Set title text color.
         *
         *  @param titleTextColor Color of the title text.
         */
        fun setTitleTextColor(@ColorInt titleTextColor: Int) = apply { this.titleTextColor = titleTextColor }

        /**
         *  Set title typeface.
         *
         *  @param titleTypeface
         */
        fun setTitleTypeface(titleTypeface: Typeface) = apply { this.titleTypeface = titleTypeface }

        /**
         *  Set title max lines.
         *
         *  @param titleMaxLines Maximum of lines for the title.
         */
        fun setTitleMaxLines(titleMaxLines: Int) = apply { this.titleMaxLines = titleMaxLines }

        /**
         *  Set description resources.
         *
         *  @param descriptionRes The identifier of the resource.
         */
        fun setDescriptionRes(@StringRes descriptionRes: Int) = apply { this.descriptionRes = descriptionRes }

        /**
         *  Set description text color.
         *
         *  @param descriptionTextColor Color of the description text.
         */
        fun setDescriptionTextColor(@ColorInt descriptionTextColor: Int) = apply { this.descriptionTextColor = descriptionTextColor }

        /**
         *  Set description typeface.
         *
         *  @param descriptionTypeface
         */
        fun setDescriptionTypeface(descriptionTypeface: Typeface) = apply { this.descriptionTypeface = descriptionTypeface }

        /**
         *  Set description max lines.
         *
         *  @param descriptionMaxLines Maximum of lines for the description.
         */
        fun setDescriptionMaxLines(descriptionMaxLines: Int) = apply { this.descriptionMaxLines = descriptionMaxLines }

        /**
         *  Build Feature from Builder
         */
        fun build() = Feature(this)
    }
}
package io.github.anderscheow.library.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.ImageViewCompat
import io.github.anderscheow.library.R
import io.github.anderscheow.library.model.Feature

class FeatureAdapter(private val context: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder>() {

    private val features = ArrayList<Feature>()

    override fun getItemCount(): Int = features.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        return FeatureViewHolder(LayoutInflater.from(context).inflate(R.layout.view_feature, parent, false))
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        holder.bind(features[position])
    }

    /**
     *  Set features items and notify item range changed to enable animation.
     */
    fun setItems(features: List<Feature>) {
        this.features.clear()
        this.features.addAll(features)

        notifyItemRangeChanged(0, features.size)
    }

    class FeatureViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        private val imageView by lazy { view.findViewById<ImageView>(R.id.imageView) }
        private val titleTextView by lazy { view.findViewById<TextView>(R.id.text_view_title) }
        private val descriptionTextView by lazy { view.findViewById<TextView>(R.id.text_view_description) }

        /**
         *  Init feature item with content
         */
        fun bind(feature: Feature) {
            with(imageView) {
                if (feature.iconDrawable != null) {
                    this.setImageDrawable(feature.iconDrawable)
                } else {
                    this.setImageResource(feature.iconRes)
                }

                if (feature.iconColor != 0) {
                    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(feature.iconColor))
                }
            }

            with(titleTextView) {
                if (feature.titleRes != 0) {
                    this.setText(feature.titleRes)
                }

                if (feature.titleTextColor != 0) {
                    this.setTextColor(feature.titleTextColor)
                }

                if (feature.titleTypeface != null) {
                    this.typeface = feature.titleTypeface
                }

                this.maxLines = feature.titleMaxLines
            }

            with(descriptionTextView) {
                if (feature.descriptionRes != 0) {
                    this.setText(feature.descriptionRes)
                }

                if (feature.descriptionTextColor != 0) {
                    this.setTextColor(feature.descriptionTextColor)
                }

                if (feature.descriptionTypeface != null) {
                    this.typeface = feature.descriptionTypeface
                }

                this.maxLines = feature.descriptionMaxLines
            }
        }
    }
}
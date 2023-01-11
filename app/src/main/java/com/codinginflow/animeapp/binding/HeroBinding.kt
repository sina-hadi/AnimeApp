package com.codinginflow.animeapp.binding

import android.annotation.SuppressLint
import android.util.Log
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.codinginflow.animeapp.Hero
import com.codinginflow.animeapp.R
import com.codinginflow.animeapp.ui.HeroFragmentDirections
import com.codinginflow.animeapp.util.Constants.Companion.BASE_URL
import com.google.android.material.imageview.ShapeableImageView

class HeroBinding {

    companion object{

        @BindingAdapter("onRecipeClickListener")
        @JvmStatic
        fun onRecipeClickListener(rowLayout: ConstraintLayout, heroes: Hero) {
            rowLayout.setOnClickListener {
                try {
                    val action = HeroFragmentDirections.actionHeroFragmentToDetailsActivity(heroes)
                    rowLayout.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.e("onRecipeClickListener" , e.toString())
                }
            }
        }

        @BindingAdapter("android:setImage")
        @JvmStatic
        fun setImage(imageView: ShapeableImageView, imageUrl: String) {
            imageView.load("$BASE_URL$imageUrl"){
                crossfade(600)
                error(R.drawable.ic_placeholder)
            }
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("android:setRatingText")
        @JvmStatic
        fun setRatingText(textView: TextView, rating: Double) {
            textView.text = "($rating)"
        }

        @BindingAdapter("android:setRatingBar")
        @JvmStatic
        fun setRatingBar(ratingBar: RatingBar, rating: Double) {
            ratingBar.rating = rating.toFloat()
        }

        @BindingAdapter("android:setPower")
        @JvmStatic
        fun setPower(textView: TextView, power: Int) {
            textView.text = power.toString()
        }

        @BindingAdapter("android:setNumber")
        @JvmStatic
        fun setNumber(textView: TextView, property: java.util.List<String>) {
            textView.text = property[0]
        }

        @BindingAdapter("android:setProperty")
        @JvmStatic
        fun setProperty(textView: TextView, property: List<String>) {
            textView.text = property[1]
        }
    }
}
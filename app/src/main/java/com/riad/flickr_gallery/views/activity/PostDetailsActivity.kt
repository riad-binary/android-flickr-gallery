package com.riad.flickr_gallery.views.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.riad.flickr_gallery.R
import com.riad.flickr_gallery.data.models.Post
import com.riad.flickr_gallery.utils.Constant
import kotlinx.android.synthetic.main.activity_post_details.*

class PostDetailsActivity : AppCompatActivity() {
    private val TAG: String = PostDetailsActivity::class.java.getName()
    var post: Post? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        val bundle = intent.extras
        if (bundle!!.containsKey(Constant.POST_ARG)) {
            post = bundle.getSerializable(Constant.POST_ARG) as Post?
            Log.d(TAG, "postList: " + post)
            initUI()
        }

    }

    fun initUI(){
        Glide.with(this)
            .load(post?.link)
            .into(imageView)

        txtTitle.text = getString(R.string.post_details_title, post?.title)
        txtAuthor.text = getString(R.string.post_details_author, post?.author)
        txtPublishedAt.text = getString(R.string.post_details_published_at, post?.published)
    }

}
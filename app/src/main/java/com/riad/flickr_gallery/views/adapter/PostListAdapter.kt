package com.riad.flickr_gallery.views.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riad.flickr_gallery.R
import com.riad.flickr_gallery.data.models.Post
import com.riad.flickr_gallery.utils.Constant
import com.riad.flickr_gallery.views.activity.PostDetailsActivity

class PostListAdapter(var context: Context) : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    var dataList = emptyList<Post>()

    internal fun setDataList(dataList: List<Post>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    // Provide a direct reference to each of the views with data items

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var title: TextView

        init {
            image = itemView.findViewById(R.id.image)
            title = itemView.findViewById(R.id.title)
        }

    }

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListAdapter.ViewHolder {

        // Inflate the custom layout
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_layout, parent, false)
        return ViewHolder(view)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: PostListAdapter.ViewHolder, position: Int) {

        // Get the data model based on position
        var data = dataList[position]

        // Set item views based on your views and data model
        holder.title.text = data.title

//        holder.image.setImageResource(data.link)
        Glide.with(context)
            .load(data.link)
            .into(holder.image)

        holder.image.setOnClickListener {
            switchToPostDetails(data)
        }
    }

    //  total count of items in the list
    override fun getItemCount() = dataList.size

    private fun switchToPostDetails(post: Post) {
        val intent = Intent(context, PostDetailsActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra(Constant.POST_ARG, post)
        context.startActivity(intent)
    }
}
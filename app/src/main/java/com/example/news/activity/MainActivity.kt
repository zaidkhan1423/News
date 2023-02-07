package com.example.news.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.news.NewsApplication
import com.example.news.R
import com.example.news.adapter.NewsAdapter
import com.example.news.databinding.ActivityMainBinding
import com.example.news.manager.ConnectionManager
import com.example.news.modal.Article
import com.example.news.viewmodals.MainViewModal
import com.example.news.viewmodals.MainViewModalFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModal: MainViewModal
    lateinit var viewPager2: ViewPager2

    @Inject
    lateinit var mainViewModalFactory: MainViewModalFactory
    lateinit var toolbar: Toolbar
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.toolbar
        setUpToolbar(toolbar)
        (application as NewsApplication).applicationComponent.inject(this)

        mainViewModal = ViewModelProvider(this, mainViewModalFactory)[MainViewModal::class.java]

        viewPager2 = binding.newsViewPager2

        if (ConnectionManager().checkConnectivity(this)) {

            binding.trendingNews.setBackgroundResource(R.drawable.bg_category_click)

            mainViewModal.getNews(1, "")

            mainViewModal.newsList.observe(this, Observer {

                binding.progressLayout.visibility = View.GONE
                binding.progressBar.visibility = View.GONE

                val newsList: List<Article> = it.articles
                val newsAdapter = NewsAdapter(this, newsList, packageManager)
                viewPager2.adapter = newsAdapter

            })
            binding.trendingNews.setOnClickListener {

                binding.trendingNews.setBackgroundResource(R.drawable.bg_category_click)
                binding.technologyNews.setBackgroundResource(R.drawable.bg_category)
                binding.businessNews.setBackgroundResource(R.drawable.bg_category)
                binding.entertainmentNews.setBackgroundResource(R.drawable.bg_category)
                binding.sportsNews.setBackgroundResource(R.drawable.bg_category)

                getNewsFunction(1,"")
            }

            binding.sportsNews.setOnClickListener {

                binding.trendingNews.setBackgroundResource(R.drawable.bg_category)
                binding.technologyNews.setBackgroundResource(R.drawable.bg_category)
                binding.businessNews.setBackgroundResource(R.drawable.bg_category)
                binding.entertainmentNews.setBackgroundResource(R.drawable.bg_category)
                binding.sportsNews.setBackgroundResource(R.drawable.bg_category_click)

                getNewsFunction(1, "sports")

            }

            binding.entertainmentNews.setOnClickListener {

                binding.trendingNews.setBackgroundResource(R.drawable.bg_category)
                binding.technologyNews.setBackgroundResource(R.drawable.bg_category)
                binding.businessNews.setBackgroundResource(R.drawable.bg_category)
                binding.entertainmentNews.setBackgroundResource(R.drawable.bg_category_click)
                binding.sportsNews.setBackgroundResource(R.drawable.bg_category)

                getNewsFunction(1, "entertainment")

            }

            binding.businessNews.setOnClickListener {

                binding.trendingNews.setBackgroundResource(R.drawable.bg_category)
                binding.technologyNews.setBackgroundResource(R.drawable.bg_category)
                binding.businessNews.setBackgroundResource(R.drawable.bg_category_click)
                binding.entertainmentNews.setBackgroundResource(R.drawable.bg_category)
                binding.sportsNews.setBackgroundResource(R.drawable.bg_category)

                getNewsFunction(1, "business")

            }

            binding.technologyNews.setOnClickListener {

                binding.trendingNews.setBackgroundResource(R.drawable.bg_category)
                binding.technologyNews.setBackgroundResource(R.drawable.bg_category_click)
                binding.businessNews.setBackgroundResource(R.drawable.bg_category)
                binding.entertainmentNews.setBackgroundResource(R.drawable.bg_category)
                binding.sportsNews.setBackgroundResource(R.drawable.bg_category)

                getNewsFunction(1, "technology")

            }
        } else {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Error")
            dialog.setMessage("Internet Connection Not Found")
            dialog.setPositiveButton("Open Settings") { text, listener ->
                val settingIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingIntent)
                this.finish()
            }
            dialog.setNegativeButton("Exit") { text, listner ->
                ActivityCompat.finishAffinity(this)
            }
            dialog.create()
            dialog.show()
        }
    }

    private fun getNewsFunction(page: Int, category: String){
        mainViewModal.getNews(page, category)
        mainViewModal.newsList.observe(this, Observer {
            val newsList: List<Article> = it.articles
            val newsAdapter = NewsAdapter(this, newsList, packageManager)
            viewPager2.adapter = newsAdapter
        })
    }

    private fun setUpToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "News"
    }
}
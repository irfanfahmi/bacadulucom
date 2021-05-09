package com.descolab.bacadulucom.home

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.descolab.bacadulucom.R
import com.descolab.bacadulucom.detailArticle.detailArticleActivity
import com.descolab.bacadulucom.listSources.listSourcesActivity
import com.descolab.bacadulucom.service.response.ArticlesItem
import com.descolab.bacadulucom.service.response.Category
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(),CategoryAdapter.ListCategoryListener,TopHeadlineAdapter.ListTopHeadlineListener,HomeContract.View {
    private var progressDialog : ProgressDialog? = null
    private var mActionListener: HomePresenter? = null
    private var mAdapter: TopHeadlineAdapter? = null
    private var mAdapterCategoryAdapter: CategoryAdapter? = null
    val dataCategory: ArrayList<Category> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(context)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)

        mActionListener = context?.let { HomePresenter(it, this) }
        mActionListener?.loadTopHeadline()
        setupRvCategory()
        setData()
    }
    private fun setData() {
        val data = arrayListOf(
            Category("business", context?.getString(R.string.business).toString()),
            Category("entertainment", context?.getString(R.string.entertainment).toString()),
            Category("general", context?.getString(R.string.general).toString()),
            Category("health", context?.getString(R.string.health).toString()),
            Category("science", context?.getString(R.string.science).toString()),
            Category("sports", context?.getString(R.string.sports).toString()),
            Category("technology", context?.getString(R.string.technology).toString()),
        )
        mAdapterCategoryAdapter = context?.let {
            CategoryAdapter(it, data, this)
        }
        rvCategory?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvCategory?.setHasFixedSize(true)
        rvCategory?.adapter = mAdapterCategoryAdapter
    }

    private fun setupRvCategory() {


    }


    override fun showTopHeadline(data: ArrayList<ArticlesItem>) {
        mAdapter = context?.let {
            TopHeadlineAdapter(it, data, this)
        }
        rvTopHeadline?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvTopHeadline?.setHasFixedSize(true)
        rvTopHeadline?.adapter = mAdapter
    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }

    override fun toDetailTopHeadline(item: ArticlesItem) {
        val i = Intent(this.context, detailArticleActivity::class.java)
        i.putExtra("url",item.url.toString())
        this.startActivity(i)
    }

    override fun toDetailCategory(item: Category) {
        val i = Intent(this.context, listSourcesActivity::class.java)
        i.putExtra("category",item.id.toString())
        this.startActivity(i)
    }


}
package com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.kinematik.themoviedb.themoviedbappclean.R
import com.kinematik.themoviedb.themoviedbappclean.presentation.di.Injectable
import com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.favourites.FavouriteMoviesFragment
import com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.movies.OngoingMoviesFragment
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), Injectable {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_home, container, false)
    }

    lateinit var pagerAdapter: ViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        pagerAdapter = ViewPagerAdapter(childFragmentManager).apply {
            addFragment(OngoingMoviesFragment.newInstance(), getString(R.string.movies))
            addFragment(FavouriteMoviesFragment.newInstance(), getString(R.string.favourites))
        }

        view_pager.adapter = pagerAdapter

        tab_layout.setupWithViewPager(view_pager)
    }


    class ViewPagerAdapter(fragmentManager: FragmentManager) :
        FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val mFragments = mutableListOf<Fragment>()
        private val mFragmentsTitles = mutableListOf<String?>()

        fun addFragment(fragment: Fragment, title: String?) {
            mFragments.add(fragment)
            mFragmentsTitles.add(title)
        }


        override fun getItem(position: Int): Fragment {
            return mFragments[position]
        }

        override fun getCount(): Int {
            return mFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentsTitles.getOrNull(position)
        }

        override fun restoreState(state: Parcelable?, loader: ClassLoader?) {
            try {
                super.restoreState(state, loader)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}
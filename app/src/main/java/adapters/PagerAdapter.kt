package adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import fragments.MoviesFragment
import network.models.Genre
import views.MoviesActivity

class PagerAdapter(fragment: MoviesActivity, private val genreList: MutableList<Genre>) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun createFragment(position: Int): Fragment {
        /*
        Create a dynamic fragment and supply it with a different
        ID and Genre label in every tab accordingly.
         */

        val fragment = MoviesFragment()
        fragment.arguments = Bundle().apply {
            putString("name", genreList[position].name)
            putInt("id", genreList[position].id)
        }

        return fragment
    }
}
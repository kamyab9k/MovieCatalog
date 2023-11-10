package com.example.movieCatalog

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.movieCatalog.databinding.ActivityMainBinding
import menuFragments.AboutFragment
import menuFragments.FavoriteFragment
import menuFragments.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeFragment = HomeFragment()
    private val aboutFragment = AboutFragment()
    private val favoriteFragment = FavoriteFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val colorStateList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))
      binding.bottomNavigationView.itemIconTintList = colorStateList

        replaceFragment(homeFragment)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(homeFragment)
                R.id.favorite -> replaceFragment(favoriteFragment)
                R.id.about -> replaceFragment(aboutFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}

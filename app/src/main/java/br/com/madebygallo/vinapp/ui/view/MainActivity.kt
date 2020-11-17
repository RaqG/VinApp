package br.com.madebygallo.vinapp.ui.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import br.com.madebygallo.vinapp.R
import br.com.madebygallo.vinapp.model.Wine
import br.com.madebygallo.vinapp.ui.adapter.WineAdapter
import br.com.madebygallo.vinapp.ui.viewmodel.MainViewModel
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView.OnActionSelectedListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnActionSelectedListener {

    private val viewModel by viewModels<MainViewModel>()
    private var winesJob: Job? = null
    private var wineAdapter: WineAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prepopulateDatabase()
        searchWines()
        initializeFloatingButton()
    }

    private fun prepopulateDatabase() {
        winesJob?.cancel()
        winesJob = lifecycleScope.launch {
            viewModel.populateGrapeVariety()
            viewModel.populateWineVariety()
        }
    }

    private fun searchWines() {
        viewModel.wines.observe(this@MainActivity, Observer { wines ->
            if (wines.isNullOrEmpty()) emptyWineList() else hasWineList(wines)
        })
        viewModel.getWines()
    }

    private fun createAdapter(wines: List<Wine>) {
        if (wineAdapter == null)
            wineAdapter = WineAdapter(this@MainActivity)

        wineAdapter?.addItems(wines.toMutableList())
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerview_wines.adapter = wineAdapter
        recyclerview_wines.setHasFixedSize(true)
    }

    private fun emptyWineList() {
        recyclerview_wines.visibility = View.GONE
        empty_view.visibility = View.VISIBLE
    }

    private fun hasWineList(wines: List<Wine>) {
        recyclerview_wines.visibility = View.VISIBLE
        empty_view.visibility = View.GONE
        createAdapter(wines)
    }

    private fun initializeFloatingButton() {
        fabAdd.setOnActionSelectedListener(this)
        addFabItem(
            R.id.fab_bar_code,
            R.drawable.ic_add_a_photo_black,
            getString(R.string.label_bar_code)
        )
        addFabItem(
            R.id.fab_new_wine,
            R.drawable.ic_note_add_black,
            getString(R.string.label_new_wine)
        )
    }

    private fun addFabItem(@IdRes id: Int, @DrawableRes drawable: Int, label: String) {
        fabAdd.addActionItem(
            SpeedDialActionItem.Builder(id, drawable)
                .setFabBackgroundColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.colorPrimaryLight,
                        theme
                    )
                )
                .setFabImageTintColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.colorIcons,
                        theme
                    )
                )
                .setLabel(label)
                .setLabelColor(Color.BLACK)
                .setLabelBackgroundColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.colorIcons,
                        theme
                    )
                )
                .setLabelClickable(false)
                .create()
        )
    }

    override fun onActionSelected(actionItem: SpeedDialActionItem?): Boolean {
        val id = actionItem?.id ?: 0

        if (id == R.id.fab_bar_code) {
//            scan?.openBarCodeScanner()
        } else if (id == R.id.fab_new_wine) {
            startActivity(Intent(this, AddUpdateWineActivity::class.java))
        }
        return false
    }
}

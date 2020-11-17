package br.com.madebygallo.vinapp.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.madebygallo.vinapp.R
import br.com.madebygallo.vinapp.ui.viewmodel.AddUpdateViewModel

class AddUpdateWineActivity : AppCompatActivity() {

    private val viewModel by viewModels<AddUpdateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update_wine)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        viewModel.initListSearch()
    }
}
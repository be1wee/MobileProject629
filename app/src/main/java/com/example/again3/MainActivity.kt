package com.example.again3

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.again3.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val containerForCreatingBlocks = binding.appBarMain.includedContainerForCreating.containerForCreating
        val btnCreateBlock = binding.appBarMain.createBlock


        var isContainerVisible = false
        containerForCreatingBlocks.post {
            containerForCreatingBlocks.translationY = containerForCreatingBlocks.height.toFloat()
        }
        btnCreateBlock.setOnClickListener {
            val height = containerForCreatingBlocks.height.toFloat()
            val upContainer= -height
            if (!isContainerVisible) {
                containerForCreatingBlocks.animate()
                    .translationY(0f)
                    .setDuration(300)
                    .start()
                btnCreateBlock.animate()
                    .translationY(upContainer)
                    .setDuration(300)
                    .start()
                btnCreateBlock.setImageResource(R.drawable.ic_cancel)
            } else {
                containerForCreatingBlocks.animate()
                    .translationY(height)
                    .setDuration(300)
                    .withEndAction {
                    }
                    .start()
                btnCreateBlock.animate()
                    .translationY(0f)
                    .setDuration(300)
                    .start()
                btnCreateBlock.setImageResource(R.drawable.ic_create_new_block)
            }
            isContainerVisible = !isContainerVisible
        }


        val containerMain = binding.appBarMain.includedContentMain.idContentMain

        //тут создается перетаскиваемый блок переменной
        val createBtnBlockVar = binding.appBarMain.includedContainerForCreating.createBlockVar
        createBtnBlockVar.setOnClickListener {
            val blockVar = layoutInflater.inflate(R.layout.block_var, containerMain, false)
            containerMain.addView(blockVar)

            blockVar.setOnTouchListener(object : View.OnTouchListener {
                private var dX = 0f
                private var dY = 0f

                override fun onTouch(view: View, event: MotionEvent): Boolean {
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            dX = view.x-event.rawX
                            dY = view.y-event.rawY
                            return true
                        }
                        MotionEvent.ACTION_MOVE -> {
                            view.animate()
                                .x(event.rawX + dX)
                                .y(event.rawY + dY)
                                .setDuration(0)
                                .start()
                            return true
                        }
                        else -> return false
                    }
                }
            })
        }

        val createBtnBlockInt = binding.appBarMain.includedContainerForCreating.createBlockInt
        createBtnBlockInt.setOnClickListener {
            val blockInt = layoutInflater.inflate(R.layout.block_int, containerMain, false)
            containerMain.addView(blockInt)

            blockInt.setOnTouchListener(object : View.OnTouchListener {
                private var dX = 0f
                private var dY = 0f

                override fun onTouch(view: View, event: MotionEvent): Boolean {
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            dX = view.x-event.rawX
                            dY = view.y-event.rawY
                            return true
                        }
                        MotionEvent.ACTION_MOVE -> {
                            view.animate()
                                .x(event.rawX + dX)
                                .y(event.rawY + dY)
                                .setDuration(0)
                                .start()
                            return true
                        }
                        else -> return false
                    }
                }
            })
        }

        val createBtnOperatorEqual = binding.appBarMain.includedContainerForCreating.createBlockEqual
        createBtnOperatorEqual.setOnClickListener {
            val blockEqual = layoutInflater.inflate(R.layout.block_equal, containerMain, false)
            containerMain.addView(blockEqual)

            blockEqual.setOnTouchListener(object : View.OnTouchListener {
                private var dX = 0f
                private var dY = 0f

                override fun onTouch(view: View, event: MotionEvent): Boolean {
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            dX = view.x-event.rawX
                            dY = view.y-event.rawY
                            return true
                        }
                        MotionEvent.ACTION_MOVE -> {
                            view.animate()
                                .x(event.rawX + dX)
                                .y(event.rawY + dY)
                                .setDuration(0)
                                .start()
                            return true
                        }
                        else -> return false
                    }
                }
            })
        }









        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}